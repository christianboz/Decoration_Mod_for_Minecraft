package net.chris.bastimod;

import com.mojang.logging.LogUtils;
import net.chris.bastimod.block.CauldronClayInteraction;
import net.chris.bastimod.block.ModBlocks;
import net.chris.bastimod.item.ModCreativeModTab;
import net.chris.bastimod.item.ModItems;
import net.chris.bastimod.potion.ModPotions;
import net.chris.bastimod.sound.ModSounds;
import net.chris.bastimod.util.BetterBrewingRecipe;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BastiMod.MOD_ID)
public class BastiMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "bastimod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BastiMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModCreativeModTab.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModPotions.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CauldronClayInteraction.boostrap();
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, Blocks.OAK_LEAVES, ModPotions.TEA_POTION.get()));
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
