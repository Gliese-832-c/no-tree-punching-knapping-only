/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.integration.crafttweaker;

import com.alcatrazescapee.alcatrazcore.inventory.ingredient.IRecipeIngredient;
import com.alcatrazescapee.notreepunching.common.recipe.KnappingRecipe;
import com.alcatrazescapee.notreepunching.common.recipe.KnifeRecipe;
import com.alcatrazescapee.notreepunching.common.recipe.ModRecipes;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;

@ZenRegister
@SuppressWarnings("unused")
@ZenClass("mods.yesflintknapping.Knapping")
public class CTKnappingRecipe
{
    @ZenMethod
    public static void add(IIngredient input, IItemStack... output)
    {
        KnappingRecipe recipe;
        ItemStack[] outputStack = Arrays.stream(output).map(CraftTweakerPlugin::toStack).toArray(ItemStack[]::new);
        if (input instanceof IOreDictEntry)
        {
            IOreDictEntry ore = (IOreDictEntry) input;
            recipe = new KnappingRecipe(ore.getName(), ore.getAmount(), outputStack);
        }
        else
        {
            recipe = new KnappingRecipe(CraftTweakerPlugin.toStack(input), outputStack);
        }
        CraftTweakerAPI.apply(new IAction()
        {
            @Override
            public void apply()
            {
                ModRecipes.addScheduledAction(() -> ModRecipes.KNAPPING.add(recipe));
            }

            @Override
            public String describe()
            {
                return "Adding knapping recipe for " + recipe.getName();
            }
        });
    }

    @ZenMethod
    public static void remove(IIngredient input)
    {
        IRecipeIngredient ingredient;
        if (input instanceof IOreDictEntry)
        {
            IOreDictEntry ore = (IOreDictEntry) input;
            ingredient = IRecipeIngredient.of(ore.getName(), ore.getAmount());
        }
        else
        {
            ingredient = IRecipeIngredient.of(CraftTweakerPlugin.toStack(input));
        }
        CraftTweakerAPI.apply(new IAction()
        {
            @Override
            public void apply()
            {
                ModRecipes.addScheduledAction(() -> ModRecipes.KNAPPING.remove(ingredient));
            }

            @Override
            public String describe()
            {
                return "Removing knapping recipe for " + ingredient.getName();
            }
        });
    }
}
