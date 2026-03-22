package net.tnt.triotnt;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
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
public static final GeyserTntBlock GEYSER_TNT = new GeyserTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
public static final SolarFlareTntBlock SOLAR_FLARE_TNT = new SolarFlareTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
public static final ImplosionTntBlock IMPLOSION_TNT = new ImplosionTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
public static final EntityType<GeyserTntEntity> GEYSER_ENTITY = registerEntity("geyser_tnt", GeyserTntEntity::new);
public static final EntityType<SolarFlareTntEntity> SOLAR_ENTITY = registerEntity("solar_flare_tnt", SolarFlareTntEntity::new);
public static final EntityType<ImplosionTntEntity> IMPLOSION_ENTITY = registerEntity("implosion_tnt", ImplosionTntEntity::new);
@Override
public void onInitialize(){
registerBlock("geyser_tnt", GEYSER_TNT);
registerBlock("solar_flare_tnt", SOLAR_FLARE_TNT);
registerBlock("implosion_tnt", IMPLOSION_TNT);
ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(c->{
c.add(GEYSER_TNT);
c.add(SOLAR_FLARE_TNT);
c.add(IMPLOSION_TNT);
});
}
private static void registerBlock(String name, net.minecraft.block.Block block){
Identifier id = Identifier.of(MOD_ID, name);
Registry.register(Registries.BLOCK, id, block);
Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
}
private static <T extends net.minecraft.entity.Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> factory){
return Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID, name),
EntityType.Builder.create(factory, SpawnGroup.MISC).dimensions(0.98f, 0.98f).build());
  }
}