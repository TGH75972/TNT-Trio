package net.tnt.triotnt.entity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.tnt.triotnt.TrioTNT;
import org.jetbrains.annotations.Nullable;
public class SolarFlareTntEntity extends TntEntity{
public SolarFlareTntEntity(EntityType<? extends TntEntity> type, World world){
super(type, world);
}
public SolarFlareTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter){
super(TrioTNT.SOLAR_ENTITY, world);
this.setPosition(x, y, z);
this.setFuse(80);
}
@Override
public void tick(){
if (!this.getWorld().isClient && this.getFuse() <= 1){
if (this.getWorld() instanceof ServerWorld sw){
BlockPos center = this.getBlockPos();
sw.getEntitiesByClass(LivingEntity.class, new Box(center).expand(8), e -> true).forEach(entity->{
entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200, 1));
entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0));
entity.setOnFireFor(10);
});
BlockPos.iterate(center.add(-7, -2, -7), center.add(7, 3, 7)).forEach(p->{
if(this.getWorld().getBlockState(p).isOf(Blocks.SAND) || this.getWorld().getBlockState(p).isOf(Blocks.RED_SAND)){
this.getWorld().setBlockState(p, Blocks.GLASS.getDefaultState());
}
if(this.getWorld().isAir(p.up()) && this.getWorld().getBlockState(p).isFullCube(this.getWorld(), p) && this.random.nextInt(3) == 0){
this.getWorld().setBlockState(p.up(), Blocks.FIRE.getDefaultState());
}
});
sw.spawnParticles(ParticleTypes.FLASH, this.getX(), this.getY(), this.getZ(), 10, 0.5, 0.5, 0.5, 0.1);
sw.spawnParticles(ParticleTypes.LAVA, this.getX(), this.getY(), this.getZ(), 50, 2.0, 2.0, 2.0, 0.5);
  }
this.discard();
return;
 }
super.tick();
  }
}