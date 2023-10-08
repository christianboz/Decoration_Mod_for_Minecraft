package net.chris.bastimod.block;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.apache.logging.log4j.util.SystemPropertiesPropertySource;

import java.util.Map;
import java.util.function.Predicate;

public class CauldronClayInteraction implements CauldronInteraction {
    public static Map<Item, CauldronInteraction> WATER = newInteractionMap();
    public static Map<Item, CauldronInteraction> EMPTY = newInteractionMap();
    static CauldronInteraction FILL_WATER = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
        return CauldronInteraction.emptyBucket(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_, ModBlocks.WATER_CAULDRON_CLAY_BLOCK.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY);
    };

    @Override
    public InteractionResult interact(BlockState pBlockState, Level pLevel, BlockPos pBlockPos,
                                      Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        return CauldronInteraction.emptyBucket(pLevel, pBlockPos, pPlayer, pHand, pStack,
                ModBlocks.WATER_CAULDRON_CLAY_BLOCK.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY);
    }

    public static void boostrap() {
        EMPTY.put(Items.WATER_BUCKET, FILL_WATER);
        WATER.put(Items.BUCKET, (p_175725_, p_175726_, p_175727_, p_175728_, p_175729_, p_175730_) -> {
            return fillBucket(p_175725_, p_175726_, p_175727_, p_175728_, p_175729_, p_175730_, new ItemStack(Items.WATER_BUCKET), (p_175660_) -> {
                return true;
            }, SoundEvents.BUCKET_FILL);
        });
    }

    static InteractionResult fillBucket(BlockState pBlockState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack pEmptyStack, ItemStack pFilledStack, Predicate<BlockState> pStatePredicate, SoundEvent pFillSound) {
        if (!pStatePredicate.test(pBlockState)) {
            return InteractionResult.PASS;
        } else {
            if (!pLevel.isClientSide) {
                Item item = pEmptyStack.getItem();
                pPlayer.setItemInHand(pHand, ItemUtils.createFilledResult(pEmptyStack, pPlayer, pFilledStack));
                pPlayer.awardStat(Stats.USE_CAULDRON);
                pPlayer.awardStat(Stats.ITEM_USED.get(item));
                pLevel.setBlockAndUpdate(pPos, ModBlocks.CAULDRON_CLAY_BLOCK.get().defaultBlockState());
                pLevel.playSound((Player)null, pPos, pFillSound, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, pPos);
            }

            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }

    static Object2ObjectOpenHashMap<Item, CauldronInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<>(), (p_175646_) -> {
            p_175646_.defaultReturnValue((p_175739_, p_175740_, p_175741_, p_175742_, p_175743_, p_175744_) -> {
                return InteractionResult.PASS;
            });
        });
    }
}
