package net.tnt.triotnt.entity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tnt.triotnt.TrioTNT;
import org.jetbrains.annotations.Nullable;
public class GeyserTntEntity extends TntEntity{
public GeyserTntEntity(EntityType<? extends TntEntity> type, World world){
super(type, world);
}
public GeyserTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter){
super(TrioTNT.GEYSER_ENTITY, world);
this.setPosition(x, y, z);
this.setFuse(80);
}
@Override
public void tick(){
if (!this.getWorld().isClient){
if (this.getFuse() < 20 && this.getFuse() > 1){
this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(6.0)).forEach(e -> {
Vec3d pull = this.getPos().subtract(e.getPos()).normalize().multiply(0.2);
e.addVelocity(pull.x, 0, pull.z);
e.velocityModified = true;
});
}
if (this.getFuse() <= 1) {
if (this.getWorld() instanceof ServerWorld sw){
BlockPos center = this.getBlockPos();
this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(4, 2, 4)).forEach(e->{
e.setVelocity(e.getVelocity().x, 3.8, e.getVelocity().z);
e.velocityModified = true;
});
BlockPos.iterate(center.add(-5, -2, -5), center.add(5, 5, 5)).forEach(p->{
if(this.getWorld().getBlockState(p).isOf(Blocks.FIRE)){
this.getWorld().setBlockState(p, Blocks.AIR.getDefaultState());
}
});
for(int i = 0; i < 100; i++){
sw.spawnParticles(ParticleTypes.SPLASH, this.getX(), this.getY() + (i * 0.2), this.getZ(), 10, 0.3, 0.1, 0.3, 0.05);
if(i % 5 == 0) sw.spawnParticles(ParticleTypes.CLOUD, this.getX(), this.getY() + (i * 0.2), this.getZ(), 3, 0.5, 0.5, 0.5, 0.01);
 }
}
this.discard();
return;
  }
}
super.tick();
}
  }