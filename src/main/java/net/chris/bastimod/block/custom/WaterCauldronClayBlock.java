package net.chris.bastimod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.function.Predicate;


public class WaterCauldronClayBlock extends AbstractCauldronBlock {


    public WaterCauldronClayBlock(Properties pProperties, Map<Item, CauldronInteraction> pInteractions) {
        super(pProperties, pInteractions);
    }

    @Override
    public boolean isFull(BlockState pState) {
        return true;
    }

    @Override
    protected boolean isEntityInsideContent(BlockState pState, /*Level pLevel,*/ BlockPos pPos, Entity pEntity) {
            if(pEntity instanceof ItemEntity itemEntity && itemEntity.getItem().getItem() == Items.OAK_LEAVES){
                pEntity.level().setBlock(pPos, Blocks.OAK_LEAVES.defaultBlockState(), 3);
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
            }
        return super.isEntityInsideContent(pState, pPos, pEntity);
    }

    @Override
    protected double getContentHeight(BlockState pState) {
        return 0.5D;
    }
}
