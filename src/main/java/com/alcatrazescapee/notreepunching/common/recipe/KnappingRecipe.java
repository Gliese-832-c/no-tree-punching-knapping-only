/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.recipe;

import com.alcatrazescapee.alcatrazcore.inventory.recipe.RecipeMultiOutput;
import net.minecraft.item.ItemStack;

public class KnappingRecipe extends RecipeMultiOutput
{
    public KnappingRecipe(ItemStack inputStack, ItemStack... outputStacks)
    {
        super(inputStack, outputStacks);
    }

    public KnappingRecipe(String inputOre, int inputAmount, ItemStack... outputStacks)
    {
        super(inputOre, inputAmount, outputStacks);
    }
}
