/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.util.types;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;

import com.alcatrazescapee.alcatrazcore.item.tool.ItemAxeCore;
import com.alcatrazescapee.alcatrazcore.item.tool.ItemHoeCore;
import com.alcatrazescapee.alcatrazcore.item.tool.ItemPickCore;
import com.alcatrazescapee.alcatrazcore.item.tool.ItemSpadeCore;
import com.alcatrazescapee.notreepunching.common.items.ItemKnife;
import com.alcatrazescapee.notreepunching.common.items.ItemMattock;
import com.alcatrazescapee.notreepunching.common.items.ItemSaw;

public enum ToolType
{
    PICKAXE(false),
    AXE(false),
    SHOVEL(false),
    HOE(false),
    KNIFE(true),
    MATTOCK(true),
    SAW(true);
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
            case KNIFE:
                return new ItemKnife(material);
            case SAW:
                return new ItemSaw(material);
            case MATTOCK:
                return new ItemMattock(material);
        }
        throw new IllegalStateException("This type does not support new tools");
    }
}
