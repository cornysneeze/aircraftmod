package fr.cornysneeze.aircraftmod;

import com.mojang.logging.LogUtils;
import fr.cornysneeze.aircraftmod.client.CoalgenRenderer;
import fr.cornysneeze.aircraftmod.fluid.ModFluids;
import fr.cornysneeze.aircraftmod.setup.AirCraftClientSetup;
import fr.cornysneeze.aircraftmod.setup.AirCraftModConfig;
import fr.cornysneeze.aircraftmod.setup.AirCraftModRegister;
import fr.cornysneeze.aircraftmod.setup.AirCraftModSetup;
import fr.cornysneeze.aircraftmod.varia.CustomEnergyStorage;
import fr.cornysneeze.aircraftmod.worldgen.ores.OresConfig;
import fr.cornysneeze.aircraftmod.worldgen.ores.ores;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;


import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(aircraftmod.MODID)
public class aircraftmod {

    public static final Logger LOGGER = (Logger) LogManager.getLogger();
    public static final String MODID = "aircraftmod";

    public aircraftmod() {;
        // Register the deferred registry
        ModFluids.init();
        AirCraftModRegister.init();
        AirCraftModSetup.setup();
        AirCraftModConfig.register();
        ores.registerConfiguredFeatures();
        CoalgenRenderer.register();


        // Register the setup method for modloading
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(AirCraftModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(AirCraftClientSetup::init));

    }
}
