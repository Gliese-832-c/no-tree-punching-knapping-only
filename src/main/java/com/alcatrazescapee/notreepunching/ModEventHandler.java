/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.alcatrazescapee.alcatrazcore.util.RegistryHelper;
import com.alcatrazescapee.notreepunching.common.capability.CapabilityPlayerItem;
import com.alcatrazescapee.notreepunching.common.capability.IPlayerItem;
import com.alcatrazescapee.notreepunching.common.recipe.ModRecipes;
import com.alcatrazescapee.notreepunching.util.HarvestBlockHandler;
import com.alcatrazescapee.notreepunching.util.PlayerInteractionHandler;
import com.alcatrazescapee.notreepunching.util.WoodRecipeHandler;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
@SuppressWarnings("unused")
public final class ModEventHandler
{
    // Update the IPlayerItem capability so that the HarvestDropsEvent can get the correct tool
    @SubscribeEvent
    public static void breakEvent(BlockEvent.BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        if (player != null)
        {
            IPlayerItem cap = player.getCapability(CapabilityPlayerItem.CAPABILITY, null);
            if (cap != null)
            {
                cap.setStack(player.getHeldItemMainhand());
            }
        }
    }

    // Controls the slow mining speed of blocks that aren't the right tool
    @SubscribeEvent
    public static void breakSpeed(PlayerEvent.BreakSpeed event)
    {
        EntityPlayer player = event.getEntityPlayer();
        if (player != null && !(player instanceof FakePlayer) && !player.isCreative() && ModConfig.GENERAL.enableBreakingChanges)
        {
            // No need to check IPlayerItem because it won't of broken yet
            ItemStack stack = player.getHeldItemMainhand();
            IBlockState state = event.getState();

            if (HarvestBlockHandler.isInvalidTool(stack, player, state))
            {
                String toolClass = state.getBlock().getHarvestTool(state);
                event.setNewSpeed(event.getOriginalSpeed() * HarvestBlockHandler.getSpeedModifier(toolClass == null ? "" : toolClass));
            }
        }
    }

    // Controls the drops of any block that is broken to require specific tools.
    // Lowest priority is to beat other mods' that might create or remove drops
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHarvestBlock(BlockEvent.HarvestDropsEvent event)
    {
        EntityPlayer player = event.getHarvester();
        if (!event.getWorld().isRemote && player != null && !(player instanceof FakePlayer) && !player.isCreative() && ModConfig.GENERAL.enableBreakingChanges)
        {
            IBlockState state = event.getState();
            IPlayerItem cap = player.getCapability(CapabilityPlayerItem.CAPABILITY, null);
            ItemStack stack = ItemStack.EMPTY;
            // Default to using the capability (as it's more accurate in case the tool was broken before we get to this event). But if it's empty, then try the main hand item, if something weird intercepted it. (see #89)
            if (cap != null)
            {
                stack = cap.getStack();
            }
            if (stack.isEmpty())
            {
                stack = player.getHeldItemMainhand();
            }

            HarvestBlockHandler.addExtraDrops(event.getDrops(), state, player, stack, event.isSilkTouching());
            if (HarvestBlockHandler.isInvalidTool(stack, player, state))
            {
                event.getDrops().clear();
            }
        }
    }

    @SubscribeEvent
    public static void playerInteractEvent(PlayerInteractEvent.RightClickBlock event)
    {
        ItemStack stack = event.getItemStack();

        EntityPlayer player = event.getEntityPlayer();
        if (event.getHand() == EnumHand.OFF_HAND)
        {
            ItemStack mainStack = player.getHeldItem(EnumHand.MAIN_HAND);
            if (PlayerInteractionHandler.hasAction(event.getWorld(), event.getPos(), mainStack, event.getFace()))
            {
                event.setCancellationResult(EnumActionResult.SUCCESS);
                event.setCanceled(true);
                return;
            }
        }

        if (PlayerInteractionHandler.hasAction(event.getWorld(), event.getPos(), stack, event.getFace()))
        {
            if (PlayerInteractionHandler.performAction(event.getWorld(), event.getPos(), player, stack, event.getFace(), event.getHand()))
            {
                event.setCancellationResult(EnumActionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        //WoodRecipeHandler.registerRecipes(event);
    }

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(CapabilityPlayerItem.KEY, new CapabilityPlayerItem.Instance());
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        RegistryHelper.get(MOD_ID).initItems(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        RegistryHelper.get(MOD_ID).initBlocks(event);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        RegistryHelper.get(MOD_ID).initSounds(event);
    }

    // Blocks that wouldn't normally break (like loose stones) actually reach block breaking logic
    @SubscribeEvent
    public void harvestBlockInitialCheck(PlayerEvent.HarvestCheck event)
    {
        if (HarvestBlockHandler.isWhitelisted(event.getTargetBlock()))
        {
            event.setCanHarvest(true);
        }
    }

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MOD_ID))
        {
            ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
            HarvestBlockHandler.reloadWhitelist();
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event)
    {
        RegistryHelper.get(MOD_ID).initModels(event);
    }
}