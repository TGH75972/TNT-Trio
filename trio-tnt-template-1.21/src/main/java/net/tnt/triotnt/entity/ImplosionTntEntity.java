package net.tnt.triotnt.entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tnt.triotnt.TrioTNT;
import org.jetbrains.annotations.Nullable;
public class ImplosionTntEntity extends TntEntity{
public ImplosionTntEntity(EntityType<? extends TntEntity> type, World world){
super(type, world);
}
public ImplosionTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter){
super(TrioTNT.IMPLOSION_ENTITY, world);
this.setPosition(x, y, z);
this.setFuse(80);
}
@Override
public void tick(){
if(this.getFuse() < 40){
this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(10.0)).forEach(e->{
Vec3d pull = this.getPos().subtract(e.getPos()).normalize().multiply(0.35);
e.addVelocity(pull.x, pull.y, pull.z);
e.velocityModified = true;
});
if(this.getWorld().isClient)
this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + 1.0, this.getZ(), 0, 0, 0);
 }
if(!this.getWorld().isClient && this.getFuse() <= 1){
this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 6.0F, World.ExplosionSourceType.TNT);
this.discard();
return;
}
super.tick();
}
  }