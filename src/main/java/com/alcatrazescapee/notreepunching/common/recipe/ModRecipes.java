/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.recipe;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.alcatrazescapee.alcatrazcore.inventory.recipe.IRecipeManager;
import com.alcatrazescapee.alcatrazcore.inventory.recipe.RecipeManager;

public final class ModRecipes
{
    public static final IRecipeManager<KnifeRecipe> KNIFE = new RecipeManager<>();
    public static final IRecipeManager<KnappingRecipe> KNAPPING = new RecipeManager<>();

    private static final List<IRecipeAction> actions = new LinkedList<>();

    public static void init()
    {
        /* KNIFE RECIPES */
        //KNIFE.add(new KnifeRecipe(new ItemStack(Blocks.MELON_BLOCK), new ItemStack(Items.MELON, 9)));

        /* KNAPPING RECIPES */
        //KNAPPING.add(new KnappingRecipe(new ItemStack(Items.DIAMOND_PICKAXE), new ItemStack(Items.DIAMOND, 2)));
    }

    public static void postInit()
    {
        actions.forEach(IRecipeAction::apply);
    }

    public static void addScheduledAction(IRecipeAction action)
    {
        actions.add(action);
    }
}
