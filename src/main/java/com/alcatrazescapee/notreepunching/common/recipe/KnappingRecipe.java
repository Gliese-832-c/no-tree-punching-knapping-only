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
    public String blockOverride;
    public String soundEvent;
    public float chance;
    public float chanceSuccess;

    public KnappingRecipe(String blockOverride, String soundEvent, float chance, float chanceSuccess, ItemStack inputStack, ItemStack... outputStacks)
    {
        super(inputStack, outputStacks);
        this.blockOverride = blockOverride;
        this.soundEvent = soundEvent;
        this.chance = chance;
        this.chanceSuccess = chanceSuccess;
    }

    public KnappingRecipe(String blockOverride, String soundEvent, float chance, float chanceSuccess, String inputOre, int inputAmount, ItemStack... outputStacks)
    {
        super(inputOre, inputAmount, outputStacks);
        this.blockOverride = blockOverride;
        this.soundEvent = soundEvent;
        this.chance = chance;
        this.chanceSuccess = chanceSuccess;
    }
}
