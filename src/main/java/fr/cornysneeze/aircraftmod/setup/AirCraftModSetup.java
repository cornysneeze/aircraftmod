package fr.cornysneeze.aircraftmod.setup;

import fr.cornysneeze.aircraftmod.aircraftmod;
import fr.cornysneeze.aircraftmod.client.CoalgenScreen;
import fr.cornysneeze.aircraftmod.worldgen.ores.ores;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(modid = aircraftmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirCraftModSetup {
    public static final String AIRITEMTAB = "AirItem";
    public static final String PLANESTAB = "Planes";
    public static final String MACHINETAB = "Machines_aircraft";

    public static final CreativeModeTab AIRITEM = new CreativeModeTab(AIRITEMTAB) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AirCraftModRegister.AIRCRAFT_BOOK.get());
        }
    };

    public static final CreativeModeTab PLANES = new CreativeModeTab(PLANESTAB) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AirCraftModRegister.BAROMETER.get());
        }
    };

    public static final CreativeModeTab MACHINE = new CreativeModeTab(MACHINETAB) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(AirCraftModRegister.COALGEN.get());
        }
    };


    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(ores::onBiomeLoadingEvent);}


    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(AirCraftModRegister.COALGEN_CONTAINER.get(), CoalgenScreen::new);

        });


    }
}
