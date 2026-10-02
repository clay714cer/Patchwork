package com.clay.patchwork;

import com.google.common.collect.ImmutableMultimap;
import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.item.spell_books.SimpleAttributeSpellBook;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.UUID;

@Mod(Patchwork.MODID)
public class Patchwork {
    public static final String MODID = "patchwork";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> LIGHTNING_SPELL_BOOK = ITEMS.register("lightning_spell_book", () -> {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3");
        builder.put(AttributeRegistry.LIGHTNING_SPELL_POWER.get(), 
            new AttributeModifier(uuid, "Weapon modifier", .10, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), 
            new AttributeModifier(uuid, "Weapon modifier", .10, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(AttributeRegistry.MAX_MANA.get(), 
            new AttributeModifier(uuid, "Weapon modifier", 200, AttributeModifier.Operation.ADDITION));
        return new SimpleAttributeSpellBook(12, SpellRarity.EPIC, builder.build());
    });

    public Patchwork(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        LOGGER.info("Patchwork initialized!");
    }
}
