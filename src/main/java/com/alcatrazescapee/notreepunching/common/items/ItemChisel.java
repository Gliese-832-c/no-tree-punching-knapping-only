/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.items;

import com.alcatrazescapee.alcatrazcore.item.tool.ItemToolCore;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemChisel extends ItemToolCore
{
    public ItemChisel(ToolMaterial material)
    {
        super(material, 0.5f, -2.2f);

        setHarvestLevel("chisel", this.harvestLevel);
    }

    /*@Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 1.0f;
    }*/
}
