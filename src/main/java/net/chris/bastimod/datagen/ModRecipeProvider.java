package net.chris.bastimod.datagen;

import net.chris.bastimod.BastiMod;
import net.chris.bastimod.block.ModBlocks;
import net.chris.bastimod.item.ModItems;
import net.chris.bastimod.potion.ModPotions;
import net.chris.bastimod.util.BetterBrewingRecipe;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(),
            ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
            ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModBlocks.END_STONE_SAPPHIRE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter,SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");
        oreSmelting(pWriter,SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ASH_BLOCK.get(), 8)
                .pattern("SSS")
                .pattern("SCS")
                .pattern("SSS")
                .define('S', Items.SAND)
                .define('C', Items.COAL)
                .unlockedBy(getHasName(Items.SAND), has(Items.SAND))
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CAULDRON_CLAY_BLOCK.get())
                .pattern("B B")
                .pattern("B B")
                .pattern("BBB")
                .define('B', Items.BRICK)
                .unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CAULDRON_COPPER_BLOCK.get())
                .pattern("C C")
                .pattern("C C")
                .pattern("CCC")
                .define('C', Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CAULDRON_REINFORCED_COPPER_BLOCK.get())
                .pattern("G G")
                .pattern("GCG")
                .pattern("GGG")
                .define('C', ModBlocks.CAULDRON_COPPER_BLOCK.get())
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(ModBlocks.CAULDRON_COPPER_BLOCK.get()), has(ModBlocks.CAULDRON_COPPER_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MIXTURE_BOTTLE.get(),3)
                .pattern("G G")
                .pattern("G G")
                .pattern(" G ")
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(pWriter);

//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MIXTURE_BARREL_BLOCK.get())
//                .pattern("HHH")
//                .pattern("HBH")
//                .pattern("HHH")
//                .define('H', Items.HONEYCOMB)
//                .define('B', Items.BARREL)
//                .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
//                .unlockedBy(getHasName(Items.BARREL), has(Items.BARREL))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_MIXTURE_BARREL_BLOCK.get())
//                .pattern("GGG")
//                .pattern("GMG")
//                .pattern("GGG")
//                .define('M', ModBlocks.MIXTURE_BARREL_BLOCK.get())
//                .define('G', Items.GOLD_INGOT)
//                .unlockedBy(getHasName(ModBlocks.MIXTURE_BARREL_BLOCK.get()), has(ModBlocks.MIXTURE_BARREL_BLOCK.get()))
//                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
//                .save(pWriter);

        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, Blocks.OAK_LEAVES, ModPotions.TEA_POTION.get()));
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, BastiMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
