/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.integration.jei;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.alcatrazescapee.notreepunching.ModConfig;
import com.alcatrazescapee.notreepunching.common.recipe.KnifeRecipe;
import com.alcatrazescapee.notreepunching.common.recipe.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;

@JEIPlugin
public final class JeiPlugin implements IModPlugin
{
    static final String KNIFE_UID = MOD_ID + ".knife";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(
                new KnifeRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void register(IModRegistry registry)
    {
        // Hide vanilla wood + stone tools from JEI
        if (ModConfig.GENERAL.replaceVanillaRecipes)
        {
            IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

            blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_AXE));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_HOE));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_PICKAXE));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_SHOVEL));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_SWORD));

            blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_AXE));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_HOE));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_PICKAXE));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_SHOVEL));
            blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_SWORD));
        }

        // Knife Recipes
        registry.handleRecipes(KnifeRecipe.class, KnifeRecipeCategory.Wrapper::new, KNIFE_UID);
        registry.addRecipes(ModRecipes.KNIFE.getAll(), KNIFE_UID);
    }
}
