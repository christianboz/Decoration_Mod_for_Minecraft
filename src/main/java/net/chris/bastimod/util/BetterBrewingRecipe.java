package net.chris.bastimod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BetterBrewingRecipe implements IBrewingRecipe {
    private final Potion input;
    private final Item ingredient;
    private final Block ingredient_block;
    private final Potion output;

    public BetterBrewingRecipe(Potion potion, Item ingredient, Potion output) {
        this.input = potion;
        this.ingredient = ingredient;
        this.ingredient_block = Block.byItem(ingredient);
        this.output = output;
    }

    public BetterBrewingRecipe(Potion potion, Block ingredient, Potion output) {
        this.input = potion;
        this.ingredient = ingredient.asItem();
        this.ingredient_block = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(ItemStack input) {
        return PotionUtils.getPotion(input) == this.input;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if(!this.isInput(input) || !this.isIngredient(ingredient)) return ItemStack.EMPTY;

        ItemStack itemStack = new ItemStack(input.getItem());
        itemStack.setTag(new CompoundTag());
        PotionUtils.setPotion(itemStack, this.output);
        return itemStack;
    }
}
