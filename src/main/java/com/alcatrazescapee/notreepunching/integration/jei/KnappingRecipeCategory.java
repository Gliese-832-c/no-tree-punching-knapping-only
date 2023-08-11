/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.integration.jei;

import com.alcatrazescapee.notreepunching.NoTreePunching;
import com.alcatrazescapee.notreepunching.common.recipe.KnappingRecipe;
import com.google.common.collect.ImmutableList;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.alcatrazescapee.notreepunching.NoTreePunching.MOD_ID;
import static com.alcatrazescapee.notreepunching.integration.jei.JeiPlugin.KNAPPING_UID;

@ParametersAreNonnullByDefault
public class KnappingRecipeCategory implements IRecipeCategory<KnappingRecipeCategory.Wrapper>
{
    private static final String TRANSLATION_KEY = "jei.category.knapping_recipe";
    private static final ResourceLocation GUI_LOCATION = new ResourceLocation(MOD_ID, "textures/jei/knapping.png");

    private final IDrawable background;
    private final IDrawable icon;

    public KnappingRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(GUI_LOCATION, 0, 0, 135, 18);
        icon = guiHelper.createDrawable(GUI_LOCATION, 135, 0, 16, 16);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return KNAPPING_UID;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public String getTitle()
    {
        return I18n.format(TRANSLATION_KEY);
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return NoTreePunching.MOD_NAME;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, Wrapper wrapper, IIngredients ingredients)
    {
        int index = 0;
        recipeLayout.getItemStacks().init(index, true, 0, 0);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(0));

        index++;
        recipeLayout.getItemStacks().init(index, true, 59, 0);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(1));

        index++;
        recipeLayout.getItemStacks().init(index, false, 117, 0);
        recipeLayout.getItemStacks().set(index, ingredients.getOutputs(ItemStack.class).get(0));
    }

    public static class Wrapper implements IRecipeWrapper
    {
        private final List<List<ItemStack>> input;
        private final List<List<ItemStack>> output;

        public Wrapper(KnappingRecipe recipe)
        {
            ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();

            // Add the main ingredient and knife items
            builder.add(recipe.getInput().getStacks());
            builder.add(recipe.getBlockIngredient().getStacks());

            // Set the input
            input = builder.build();

            // Reset builder and add output
            builder = ImmutableList.builder();
            builder.add(Arrays.asList(recipe.getOutput()));

            // Set the output
            output = builder.build();
        }

        @Override
        public void getIngredients(@Nonnull IIngredients ingredients)
        {
            ingredients.setInputLists(ItemStack.class, input);
            ingredients.setOutputLists(ItemStack.class, output);
        }
    }
}
