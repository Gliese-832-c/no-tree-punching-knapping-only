package com.alcatrazescapee.notreepunching.common.items;

import com.alcatrazescapee.alcatrazcore.item.tool.ItemToolCore;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemAxe extends ItemToolCore {
    public ItemAxe(Item.ToolMaterial material) {
        this(material, 5.0f, -3.0F);
    }

    public ItemAxe(Item.ToolMaterial material, float damage, float speed) {
        super(material, damage, speed);
        this.addToolClass(ToolClass.AXE);
        this.effectiveMaterials.add(Material.WOOD);
        this.effectiveMaterials.add(Material.PLANTS);
        this.effectiveMaterials.add(Material.VINE);
    }
}
