/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.alcatrazescapee.alcatrazcore.util.RegistryHelper;
import com.alcatrazescapee.notreepunching.common.tile.TileLargeVessel;

import static com.alcatrazescapee.alcatrazcore.util.CoreHelpers.getNull;
import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;
import static com.alcatrazescapee.notreepunching.client.ModTabs.MOD_TAB;

@GameRegistry.ObjectHolder(value = MOD_ID)
public final class ModBlocks
{
    //public static Block CERAMIC_LARGE_VESSEL_RAW;
    public static final Block CERAMIC_LARGE_VESSEL_RAW = getNull();
    public static final Block CERAMIC_LARGE_VESSEL = getNull();

    public static void preInit()
    {
        RegistryHelper r = RegistryHelper.get(MOD_ID);

        r.registerBlock(new BlockLargeVesselRaw(), "ceramic_large_vessel_raw", MOD_TAB);
/*        CERAMIC_LARGE_VESSEL_RAW = new BlockLargeVesselRaw().setTranslationKey(MOD_ID + "." + "ceramic_large_vessel_raw").setRegistryName(new ResourceLocation(MOD_ID, "ceramic_large_vessel_raw"));
        ForgeRegistries.BLOCKS.register(CERAMIC_LARGE_VESSEL_RAW);
        ItemBlock itemBlock = new ItemBlock(CERAMIC_LARGE_VESSEL_RAW);
        ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(CERAMIC_LARGE_VESSEL_RAW.getRegistryName()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(CERAMIC_LARGE_VESSEL_RAW), 0, new ModelResourceLocation(CERAMIC_LARGE_VESSEL_RAW.getRegistryName(), "inventory"));*/

        r.registerBlock(new BlockLargeVessel(), "ceramic_large_vessel", MOD_TAB);

        // Tile Entities
        r.registerTile(TileLargeVessel.class, "ceramic_large_vessel");
    }
}