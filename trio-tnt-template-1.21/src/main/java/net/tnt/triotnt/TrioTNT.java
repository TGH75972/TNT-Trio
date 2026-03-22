package net.tnt.triotnt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tnt.triotnt.block.*;
import net.tnt.triotnt.entity.*;
public class TrioTNT implements ModInitializer{
public static final String MOD_ID = "triotnt";
public static final Block GEYSER_TNT = new GeyserTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
public static final Block SOLAR_FLARE_TNT = new SolarFlareTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
public static final Block IMPLOSION_TNT = new ImplosionTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
public static final EntityType<GeyserTntEntity> GEYSER_ENTITY = Registry.register(Registries.ENTITY_TYPE,
Identifier.of(MOD_ID, "geyser_tnt"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, GeyserTntEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build());
public static final EntityType<SolarFlareTntEntity> SOLAR_FLARE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
Identifier.of(MOD_ID, "solar_flare_tnt"),
FabricEntityTypeBuilder.create(SpawnGroup.MISC, SolarFlareTntEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build());
public static final EntityType<ImplosionTntEntity> IMPLOSION_ENTITY = Registry.register(Registries.ENTITY_TYPE,
Identifier.of(MOD_ID, "implosion_tnt"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, ImplosionTntEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build());
@Override
public void onInitialize(){
registerBlock("geyser_tnt", GEYSER_TNT);
registerBlock("solar_flare_tnt", SOLAR_FLARE_TNT);
registerBlock("implosion_tnt", IMPLOSION_TNT);
ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content->{
content.add(GEYSER_TNT);
content.add(SOLAR_FLARE_TNT);
content.add(IMPLOSION_TNT);
  });
}
private void registerBlock(String name, Block block){
Identifier id = Identifier.of(MOD_ID, name);
Registry.register(Registries.BLOCK, id, block);
Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
 }
}