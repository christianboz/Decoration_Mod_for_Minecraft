package net.chris.bastimod.block.custom;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class BurnedClayPotBlock extends AbstractCauldronBlock {
    public BurnedClayPotBlock(Properties pProperties, Map<Item, CauldronInteraction> pInteractions) {
        super(pProperties, pInteractions);
    }

    @Override
    public boolean isFull(BlockState pState) {
        return false;
    }
}
