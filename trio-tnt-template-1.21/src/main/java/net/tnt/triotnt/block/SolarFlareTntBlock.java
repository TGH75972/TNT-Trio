package net.tnt.triotnt.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.tnt.triotnt.entity.SolarFlareTntEntity;
import org.jetbrains.annotations.Nullable;
public class SolarFlareTntBlock extends TntBlock{
public SolarFlareTntBlock(Settings settings){
super(settings);
}
private void primeCustomTnt(World world, BlockPos pos, @Nullable LivingEntity igniter){
if(!world.isClient){
SolarFlareTntEntity tntEntity = new SolarFlareTntEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, igniter);
world.spawnEntity(tntEntity);
world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
 }
}
@Override
protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
if(stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE)){
primeCustomTnt(world, pos, player);
world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
if(!player.isCreative()){
if(stack.isOf(Items.FLINT_AND_STEEL)) stack.damage(1, player, LivingEntity.getSlotForHand(hand));
else stack.decrement(1);
}
return ItemActionResult.success(world.isClient);
}
return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
}
@Override
public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify){
if(world.isReceivingRedstonePower(pos)){
primeCustomTnt(world, pos, null);
world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
}
 }
@Override
public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion){
if(!world.isClient){
primeCustomTnt(world, pos, explosion.getCausingEntity());
  }
   }
}