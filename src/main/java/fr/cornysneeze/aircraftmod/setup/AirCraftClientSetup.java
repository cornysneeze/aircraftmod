package fr.cornysneeze.aircraftmod.setup;

import fr.cornysneeze.aircraftmod.aircraftmod;
import fr.cornysneeze.aircraftmod.client.CoalgenRenderer;
import fr.cornysneeze.aircraftmod.client.CoalgenScreen;
import fr.cornysneeze.aircraftmod.fluid.ModFluids;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

@Mod.EventBusSubscriber(modid = aircraftmod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirCraftClientSetup {
    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_BLOCK.get(),RenderType.translucent());


            event.enqueueWork(() -> {
                MenuScreens.register(AirCraftModRegister.COALGEN_CONTAINER.get(), CoalgenScreen::new);
                ItemBlockRenderTypes.setRenderLayer(AirCraftModRegister.COALGEN.get(), RenderType.translucent());
                CoalgenRenderer.register();
            });
        }

        }

