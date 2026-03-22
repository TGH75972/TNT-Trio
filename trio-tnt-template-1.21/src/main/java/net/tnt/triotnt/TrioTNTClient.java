package net.tnt.triotnt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.tnt.triotnt.client.render.entity.TrioTntEntityRenderer;

public class TrioTNTClient implements ClientModInitializer{
@Override
public void onInitializeClient(){
EntityRendererRegistry.register(TrioTNT.GEYSER_ENTITY, TrioTntEntityRenderer::new);
EntityRendererRegistry.register(TrioTNT.SOLAR_ENTITY, TrioTntEntityRenderer::new);
EntityRendererRegistry.register(TrioTNT.IMPLOSION_ENTITY, TrioTntEntityRenderer::new);
}
}