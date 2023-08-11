/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.items;

import com.alcatrazescapee.notreepunching.ModConfig;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.alcatrazescapee.alcatrazcore.util.OreDictionaryHelper;
import com.alcatrazescapee.alcatrazcore.util.RegistryHelper;
import com.alcatrazescapee.alcatrazcore.util.collections.ImmutableEnumTable;
import com.alcatrazescapee.notreepunching.util.types.Metal;
import com.alcatrazescapee.notreepunching.util.types.ToolType;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;
import static com.alcatrazescapee.notreepunching.client.ModTabs.MOD_TAB;

@GameRegistry.ObjectHolder(value = MOD_ID)
public final class ModItems
{
    private static ImmutableEnumTable<ToolType, Metal, Item> METAL_TOOLS;

    public static Item getTool(ToolType type, Metal metal)
    {
        return METAL_TOOLS.get(type, metal);
    }

    public static void preInit()
    {
        RegistryHelper r = RegistryHelper.get(MOD_ID);
        Item item;
        if (ModConfig.TOOLS.enableTools) {
            // Tools
            ImmutableEnumTable.Builder<ToolType, Metal, Item> metalTools = new ImmutableEnumTable.Builder<>(ToolType.class, Metal.class);

            for (ToolType type : ToolType.values())
            {
                /*if (type.isNewTool)
                {*/
                    for (Metal metal : Metal.values())
                    {
                        // Metal Tools
                        // todo: make these always register and then check if enabled later
                        if (metal.isEnabled)
                        {
                            metalTools.put(type, metal, item = r.registerItem(type.createTool(metal.toolMaterial), type.name() + "/" + metal.name(), MOD_TAB));
                            OreDictionaryHelper.register(item, "tool", type.name());
                        }
                    }
                //}
            }

            METAL_TOOLS = metalTools.build();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void init()
    {
    }
}
