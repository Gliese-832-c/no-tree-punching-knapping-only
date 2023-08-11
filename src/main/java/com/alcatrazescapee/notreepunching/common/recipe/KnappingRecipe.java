/*
 *  Part of the No Tree Punching Mod by alcatrazEscapee
 *  Work under Copyright. Licensed under the GPL-3.0.
 *  See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.notreepunching.common.recipe;

import com.alcatrazescapee.alcatrazcore.inventory.ingredient.IRecipeIngredient;
import com.alcatrazescapee.alcatrazcore.inventory.recipe.RecipeMultiOutput;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

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

    public ItemStack getBlockStack() {
        if (blockOverride.equals("default")) {

            ItemStack stack = new ItemStack(Item.getItemFromBlock(Blocks.STONE));

            NBTTagCompound customTag = new NBTTagCompound();
            NBTTagCompound displayTag = new NBTTagCompound();

            NBTTagList loreList = new NBTTagList();
            loreList.appendTag(new NBTTagString("§r§aAll blocks with Material.ROCK are valid for this recipe!§r"));
            loreList.appendTag(new NBTTagString("§r§7This should include things like stone, cobblestone, bricks, and many other things. Just try it out!§r"));

            displayTag.setTag("Lore", loreList);
            customTag.setTag("display", displayTag);

            stack.setTagCompound(customTag);
            return stack;
        } else {
            return new ItemStack(ItemBlock.getItemFromBlock(Block.getBlockFromName(blockOverride)));
        }

    }

    public IRecipeIngredient getBlockIngredient() {
        return IRecipeIngredient.of(getBlockStack());
    }
}
