package dev.lexxie.mjolnir;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.Entity;


public class MjolnirRenderer extends EntityRenderer<Entity, EntityRenderState> {
	protected MjolnirRenderer( EntityRendererProvider.Context context ) {
		super(context);
	}

	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}

	@Override
	public void extractRenderState( Entity entity, EntityRenderState state, float partialTick ) {
		super.extractRenderState(entity, state, partialTick);
	}

	@Override
	public void submit( EntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector collector,
	                    CameraRenderState cameraState ) {
		super.submit(renderState, poseStack, collector, cameraState);
	}
}
