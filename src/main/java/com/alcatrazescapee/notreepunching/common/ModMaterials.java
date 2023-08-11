/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import com.alcatrazescapee.notreepunching.ModConfig;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

public final class ModMaterials
{
    public static final Item.ToolMaterial TOOL_COPPER = EnumHelper.addToolMaterial(MOD_ID + ":copper", 0, 220, 4f, 0.0f, 4);
    public static final Item.ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial(MOD_ID + ":bronze", 1, 560, 8f, 1.5f, 8);
    public static final Item.ToolMaterial TOOL_STEEL = EnumHelper.addToolMaterial(MOD_ID + ":steel", 3, 1800, 11f, 3.0f, 10);
}
