/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.util.types;

import javax.annotation.Nonnull;

import com.alcatrazescapee.alcatrazcore.item.tool.*;
import com.alcatrazescapee.notreepunching.common.items.*;
import net.minecraft.item.Item;

public enum ToolType
{
    PICKAXE(false),
    AXE(false),
    SHOVEL(false),
    HOE(false),
    SWORD(false),
    KNIFE(true),
    MATTOCK(true),
    SAW(true),
    HAMMER(true),
    CHISEL(true);
    public final boolean isNewTool;

    ToolType(boolean isNewTool)
    {
        this.isNewTool = isNewTool;
    }

    @Nonnull
    public Item createTool(Item.ToolMaterial material)
    {
        switch (this)
        {
            case PICKAXE:
                return new ItemPickCore(material);
            case AXE:
                return new ItemAxe(material);
            case SHOVEL:
                return new ItemSpade(material);
            case HOE:
                return new ItemHoeCore(material);
            case SWORD:
                return new ItemSwordCore(material);
            case KNIFE:
                return new ItemKnife(material);
            case MATTOCK:
                return new ItemMattock(material);
            case SAW:
                return new ItemSaw(material);
            case HAMMER:
                return new ItemHammer(material);
            case CHISEL:
                return new ItemChisel(material);
        }
        throw new IllegalStateException("This type does not support new tools");
    }
}
