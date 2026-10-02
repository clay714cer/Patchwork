package com.clay.patchwork;

import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
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
        () -> new SpellBook(12, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant())
            .withSpellbookAttributes(
                new AttributeContainer(AttributeRegistry.LIGHTNING_SPELL_POWER, 0.10, AttributeModifier.Operation.MULTIPLY_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADDITION),
                new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.10, AttributeModifier.Operation.MULTIPLY_BASE)
            ));

    public Patchwork() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        LOGGER.info("Patchwork initialized!");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class CreativeTabHandler {
        @SubscribeEvent
        public static void fillTabs(BuildCreativeModeTabContentsEvent event) {
            ResourceKey<CreativeModeTab> ironTab = ResourceKey.create(
                net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "spellbook_equipment")
            );
            if (event.getTabKey() == ironTab) {
                event.accept(LIGHTNING_SPELL_BOOK);
            }
        }
    }
}
