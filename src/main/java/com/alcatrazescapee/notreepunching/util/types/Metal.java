/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.util.types;

import javax.annotation.Nullable;

import net.minecraft.item.Item;

import static com.alcatrazescapee.notreepunching.common.ModMaterials.*;

public enum Metal
{
    IRON(true, Item.ToolMaterial.IRON),
    COPPER(true, TOOL_COPPER),
    BRONZE(true, TOOL_BRONZE),
    STEEL(true, TOOL_STEEL);

    public final boolean isEnabled;
    public final Item.ToolMaterial toolMaterial;

    Metal(boolean isEnabled, @Nullable Item.ToolMaterial toolMaterial)
    {
        this.isEnabled = isEnabled;
        this.toolMaterial = toolMaterial;
    }
}
