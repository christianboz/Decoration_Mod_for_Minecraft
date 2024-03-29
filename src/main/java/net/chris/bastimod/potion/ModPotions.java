package net.chris.bastimod.potion;

import net.chris.bastimod.BastiMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, BastiMod.MOD_ID);

    public static final RegistryObject<Potion> TEA_POTION = POTIONS.register("tea_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.HEAL, 1)));

    //TODO Glas-Behälter mit 3x Füllungen
//    public static final RegistryObject<Potion> MIXTURE_BOTTLE_POTION = POTIONS.register("mixture_bottle_potion",
//            () -> new Potion());


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
