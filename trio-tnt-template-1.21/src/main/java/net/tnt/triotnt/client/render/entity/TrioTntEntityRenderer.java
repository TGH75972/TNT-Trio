package net.tnt.triotnt.client.render.entity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.tnt.triotnt.TrioTNT;
import net.tnt.triotnt.entity.GeyserTntEntity;
import net.tnt.triotnt.entity.ImplosionTntEntity;
import net.tnt.triotnt.entity.SolarFlareTntEntity;
public class TrioTntEntityRenderer extends EntityRenderer<TntEntity>{
public TrioTntEntityRenderer(EntityRendererFactory.Context context){
super(context);
this.shadowRadius = 0.5F;
}
@Override
public void render(TntEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light){
matrices.push();
matrices.translate(0.0, 0.5, 0.0);
int fuse = entity.getFuse();
if((float)fuse - tickDelta + 1.0F < 10.0F){
float f = 1.0F - ((float)fuse - tickDelta + 1.0F) / 10.0F;
f = MathHelper.clamp(f, 0.0F, 1.0F);
f *= f; f *= f;
float g = 1.0F + f * 0.3F;
matrices.scale(g, g, g);
}
matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
matrices.translate(-0.5, -0.5, 0.5);
matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
BlockState state = TrioTNT.GEYSER_TNT.getDefaultState();
if(entity instanceof SolarFlareTntEntity)
state = TrioTNT.SOLAR_FLARE_TNT.getDefaultState();
else if(entity instanceof ImplosionTntEntity)
state = TrioTNT.IMPLOSION_TNT.getDefaultState();
else if(entity instanceof GeyserTntEntity)
state = TrioTNT.GEYSER_TNT.getDefaultState();
BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
int overlay = (fuse / 5 % 2 == 0) ? OverlayTexture.getUv(OverlayTexture.getU(1.0F), true) : OverlayTexture.DEFAULT_UV;
blockRenderManager.renderBlockAsEntity(state, matrices, vertexConsumers, light, overlay);
matrices.pop();
super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
}
@Override
public Identifier getTexture(TntEntity entity){
return Identifier.of("minecraft", "textures/atlas/blocks.png");
  }
}