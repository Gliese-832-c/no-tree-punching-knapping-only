/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.items;

import com.alcatrazescapee.alcatrazcore.item.tool.ItemToolCore;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemHammer extends ItemToolCore
{
    public ItemHammer(ToolMaterial material)
    {
        super(material, 6.0f, -3.0f);

        setHarvestLevel("hammer", this.harvestLevel);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
    }

    /*@Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 1.0f;
    }*/
}
