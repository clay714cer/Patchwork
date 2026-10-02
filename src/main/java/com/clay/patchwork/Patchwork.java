package com.clay.patchwork;

import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.item.SpellBook;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(Patchwork.MODID)
public class Patchwork {
    public static final String MODID = "patchwork";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> LIGHTNING_SPELL_BOOK = ITEMS.register("lightning_spell_book",
        () -> new SpellBook(12, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public Patchwork() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        LOGGER.info("Patchwork initialized!");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CreativeTabHandler {
        @SubscribeEvent
        public static void fillTabs(BuildCreativeModeTabContentsEvent event) {
            ResourceLocation ironTab = new ResourceLocation("irons_spellbooks", "spellbook_equipment");
            if (event.getTabKey().location().equals(ironTab)) {
                event.accept(LIGHTNING_SPELL_BOOK);
            }
        }
    }
}
