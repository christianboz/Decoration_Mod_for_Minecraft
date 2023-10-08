package net.chris.bastimod.datagen;

import net.chris.bastimod.BastiMod;
import net.chris.bastimod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BastiMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ASH_BLOCK);
        blockWithItem(ModBlocks.CAULDRON_CLAY_BLOCK);
        blockWithItem(ModBlocks.CAULDRON_COPPER_BLOCK);
        blockWithItem(ModBlocks.CAULDRON_REINFORCED_COPPER_BLOCK);
//        blockWithItem(ModBlocks.MIXTURE_BARREL_BLOCK);
//        blockWithItem(ModBlocks.REINFORCED_MIXTURE_BARREL_BLOCK);

        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);

//        blockWithItem(ModBlocks.FAKE_BREWING_STAND_BLOCK);
        blockWithItem(ModBlocks.FAKE_FURNACE_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
