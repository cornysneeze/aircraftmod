package fr.cornysneeze.aircraftmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import fr.cornysneeze.aircraftmod.aircraftmod;
import fr.cornysneeze.aircraftmod.blocks.CoalgenBE;
import fr.cornysneeze.aircraftmod.blocks.CoalgenConfig;
import fr.cornysneeze.aircraftmod.setup.AirCraftModRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static java.lang.Boolean.TRUE;

public class CoalgenRenderer implements BlockEntityRenderer<CoalgenBE> {

    public static final ResourceLocation HALO = new ResourceLocation(aircraftmod.MODID, "effect/halo");

    public CoalgenRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CoalgenBE powergen, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {

        Boolean powered = powergen.getBlockState().getValue(BlockStateProperties.POWERED);
        if (TRUE != powered) {
            return;
        }

        int brightness = LightTexture.FULL_BRIGHT;
        // To achieve a pulsating effect we use the current time
        float s = (System.currentTimeMillis() % 1000) / 1000.0f;
        if (s > 0.5f) {
            s = 1.0f - s;
        }
        // Get our texture from the atlas
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(HALO);

        // Always remember to push the current transformation so that you can restore it later
        poseStack.pushPose();

        // Translate to the middle of the block and 1 unit higher
        poseStack.translate(0.5, 1.5, 0.5);

        // Use the orientation of the main camera to make sure the single quad that we are going to render always faces the camera
        Quaternion rotation = Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
        poseStack.mulPose(rotation);

    }

    public static void register() {
        BlockEntityRenderers.register(AirCraftModRegister.COALGEN_BE.get(), CoalgenRenderer::new);
    }

}
