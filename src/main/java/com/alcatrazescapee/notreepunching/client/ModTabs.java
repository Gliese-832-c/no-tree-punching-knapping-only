/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.client;

import javax.annotation.Nonnull;

import com.alcatrazescapee.notreepunching.util.types.Metal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.alcatrazescapee.notreepunching.common.items.ModItems;
import com.alcatrazescapee.notreepunching.util.types.ToolType;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

public final class ModTabs
{
    public static final CreativeTabs MOD_TAB = new CreativeTabs(MOD_ID)
    {
        @SideOnly(Side.CLIENT)
        @Override
        @Nonnull
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.getTool(ToolType.SAW, Metal.IRON));
        }
    };
}
