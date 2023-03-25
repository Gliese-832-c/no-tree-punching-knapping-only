/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.recipe;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import com.alcatrazescapee.alcatrazcore.inventory.recipe.IRecipeManager;
import com.alcatrazescapee.alcatrazcore.inventory.recipe.RecipeManager;
import com.alcatrazescapee.notreepunching.ModConfig;
import com.alcatrazescapee.notreepunching.common.blocks.BlockPottery;
import com.alcatrazescapee.notreepunching.common.items.ModItems;
import com.alcatrazescapee.notreepunching.util.Util;
import com.alcatrazescapee.notreepunching.util.types.Metal;
import com.alcatrazescapee.notreepunching.util.types.Pottery;
import com.alcatrazescapee.notreepunching.util.types.ToolType;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

public final class ModRecipes
{
    public static final IRecipeManager<KnifeRecipe> KNIFE = new RecipeManager<>();

    private static final List<IRecipeAction> actions = new LinkedList<>();

    public static void init()
    {
        // Pottery Smelting + Fire pit
        for (Pottery type : Pottery.values())
        {
            if (type != Pottery.WORKED)
            {
                BlockPottery block = BlockPottery.get(type);
                GameRegistry.addSmelting(block, block.getFiredType(), 0.1f);
            }
        }

        GameRegistry.addSmelting(ModItems.GRASS_STRING, new ItemStack(Items.STRING), 0.1f);
        GameRegistry.addSmelting(ModItems.CLAY_BRICK, new ItemStack(Items.BRICK), 0.1f);

        /* KNIFE RECIPES */

        KNIFE.add(new KnifeRecipe("rock", 1, new ItemStack(ModItems.FLINT_SHARD)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.FLINT), new ItemStack(ModItems.FLINT_SHARD, 2)));

        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.STRING, 4)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.WEB), new ItemStack(Items.STRING, 8)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.REEDS), new ItemStack(ModItems.GRASS_FIBER, 2)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.WHEAT), new ItemStack(ModItems.GRASS_FIBER, 1), new ItemStack(Items.WHEAT_SEEDS)));

        KNIFE.add(new KnifeRecipe(new ItemStack(Items.LEATHER_BOOTS), new ItemStack(Items.LEATHER, 2)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.LEATHER_CHESTPLATE), new ItemStack(Items.LEATHER, 5)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.LEATHER_LEGGINGS), new ItemStack(Items.LEATHER, 4)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.LEATHER_HELMET), new ItemStack(Items.LEATHER, 3)));

        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.MELON_BLOCK), new ItemStack(Items.MELON, 9)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Items.MELON), new ItemStack(Items.MELON_SEEDS, 1), new ItemStack(ModItems.GRASS_FIBER)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.PUMPKIN), new ItemStack(Items.PUMPKIN_SEEDS, 4), new ItemStack(ModItems.GRASS_FIBER, 2)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.VINE), new ItemStack(ModItems.GRASS_FIBER, 3)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.CACTUS), new ItemStack(ModItems.GRASS_FIBER, 2)));

        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.DOUBLE_PLANT, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.GRASS_FIBER, 2)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.RED_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.GRASS_FIBER)));
        KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.YELLOW_FLOWER, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.GRASS_FIBER)));

        for (int i = 0; i < 6; i++)
        {
            KNIFE.add(new KnifeRecipe(new ItemStack(i < 4 ? Blocks.LEAVES : Blocks.LEAVES2, 6, i % 4), new ItemStack(Blocks.SAPLING, 1, i), new ItemStack(ModItems.GRASS_FIBER, 2)));
        }
        KNIFE.add(new KnifeRecipe("treeSapling", 1, new ItemStack(ModItems.GRASS_FIBER, 2), new ItemStack(Items.STICK)));

    }

    public static void postInit()
    {
        actions.forEach(IRecipeAction::apply);
    }

    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        IForgeRegistryModifiable<IRecipe> r = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();

        if (ModConfig.TOOLS.enableTinTools)
        {
            registerToolRecipes(r, Metal.TIN);
        }

        if (ModConfig.TOOLS.enableCopperTools)
        {
            registerToolRecipes(r, Metal.COPPER);
        }

        if (ModConfig.TOOLS.enableBronzeTools)
        {
            registerToolRecipes(r, Metal.BRONZE);
        }

        if (ModConfig.TOOLS.enableSteelTools)
        {
            registerToolRecipes(r, Metal.STEEL);
        }

        if (ModConfig.GENERAL.replaceVanillaRecipes)
        {
            remove(r, "wooden_pickaxe");
            remove(r, "wooden_shovel");
            remove(r, "wooden_hoe");
            remove(r, "wooden_sword");
            remove(r, "wooden_axe");

            remove(r, "stone_pickaxe");
            remove(r, "stone_shovel");
            remove(r, "stone_hoe");
            remove(r, "stone_sword");
            remove(r, "stone_axe");

            remove(r, "flower_pot");
        }
    }

    public static void addScheduledAction(IRecipeAction action)
    {
        actions.add(action);
    }

    private static void registerToolRecipes(IForgeRegistry<IRecipe> r, Metal metalType)
    {
        String metalName = metalType.name().toLowerCase() + "_";
        String ingotName = Util.CASE_CONVERTER.convert("INGOT_" + metalType.name());
        register(r, new ShapedOreRecipe(new ResourceLocation(MOD_ID, metalName + "knife"),
                new ItemStack(ModItems.getTool(ToolType.KNIFE, metalType)), "I", "S", 'I', ingotName, 'S', "stickWood"));
        register(r, new ShapedOreRecipe(new ResourceLocation(MOD_ID, metalName + "saw"),
                new ItemStack(ModItems.getTool(ToolType.SAW, metalType)), "  S", " SI", "SI ", 'I', ingotName, 'S', "stickWood"));
        register(r, new ShapedOreRecipe(new ResourceLocation(MOD_ID, metalName + "mattock"),
                new ItemStack(ModItems.getTool(ToolType.MATTOCK, metalType)), "III", " SI", " S ", 'I', ingotName, 'S', "stickWood"));
    }

    private static void register(IForgeRegistry<IRecipe> registry, IRecipe recipe)
    {
        recipe.setRegistryName(new ResourceLocation(recipe.getGroup()));
        registry.register(recipe);
    }

    private static void remove(IForgeRegistryModifiable<IRecipe> registry, String name)
    {
        registry.remove(new ResourceLocation("minecraft:" + name));
    }

}
