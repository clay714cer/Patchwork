package com.clay.patchwork;

import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.compat.Curios;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import org.slf4j.Logger;

@Mod(Patchwork.MODID)
public class Patchwork {
    public static final String MODID = "patchwork";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(Registries.ITEM, MODID);

    public static final DeferredHolder<Item, Item> LIGHTNING_SPELL_BOOK = 
        ITEMS.register("lightning_spell_book",
            () -> new SpellBook(12, SpellRarity.EPIC)
                .withAttributes(Curios.SPELLBOOK_SLOT, 
                    new AttributeContainer(AttributeRegistry.LIGHTNING_SPELL_POWER, 0.10, 
                        AttributeModifier.Operation.MULTIPLY_BASE))
                .withAttributes(Curios.SPELLBOOK_SLOT, 
                    new AttributeContainer(AttributeRegistry.MAX_MANA, 200, 
                        AttributeModifier.Operation.ADDITION))
                .withAttributes(Curios.SPELLBOOK_SLOT, 
                    new AttributeContainer(AttributeRegistry.COOLDOWN_REDUCTION, 0.10, 
                        AttributeModifier.Operation.MULTIPLY_BASE)));

    public Patchwork(IEventBus modEventBus, ModContainer modContainer) {
        ITEMS.register(modEventBus);
        LOGGER.info("Patchwork initialized!");
    }
}
