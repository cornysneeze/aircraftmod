package fr.cornysneeze.aircraftmod.fluid;

import fr.cornysneeze.aircraftmod.aircraftmod;
import fr.cornysneeze.aircraftmod.setup.AirCraftModRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static fr.cornysneeze.aircraftmod.aircraftmod.MODID;

public class ModFluids {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);}
    public static final ResourceLocation OIL_STILL_RL = new ResourceLocation("aircraftmod:fluid/oil_still");
    public static final ResourceLocation OIL_FLOWING_RL = new ResourceLocation("aircraftmod:fluid/oil_flow");
    public static final ResourceLocation OIL_OVERLAY_RL = new ResourceLocation("aircraftmod:fluid/oil_overlay");


    public static final ResourceLocation FUEL_STILL_RL = new ResourceLocation("aircraftmod:fluid/fuel_still");
    public static final ResourceLocation FUEL_FLOWING_RL = new ResourceLocation("aircraftmod:fluid/fuel_flow");
    public static final ResourceLocation FUEL_OVERLAY_RL = new ResourceLocation("aircraftmod:fluid/fuel_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, aircraftmod.MODID);


    public static final RegistryObject<FlowingFluid> OIL_FLUID
            = FLUIDS.register("oil_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> OIL_FLOWING
            = FLUIDS.register("oil_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FUEL_FLUID
            = FLUIDS.register("fuel_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.FUEL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FUEL_FLOWING
            = FLUIDS.register("fuel_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.FUEL_PROPERTIES));


    public static final ForgeFlowingFluid.Properties OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> OIL_FLUID.get(), () -> OIL_FLOWING.get(), FluidAttributes.builder(OIL_STILL_RL, OIL_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(OIL_OVERLAY_RL)
            .color(0xbffcba03)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> ModFluids.OIL_BLOCK.get()).bucket(() -> AirCraftModRegister.OIL_BUCKET.get());


    public static final ForgeFlowingFluid.Properties FUEL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> FUEL_FLUID.get(), () -> FUEL_FLOWING.get(), FluidAttributes.builder(FUEL_STILL_RL, FUEL_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(FUEL_OVERLAY_RL)
            .color(0xbffcba03)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> ModFluids.FUEL_BLOCK.get()).bucket(() -> AirCraftModRegister.FUEL_BUCKET.get());


    public static final RegistryObject<LiquidBlock> OIL_BLOCK = ModFluids.BLOCKS.register("oil",
            () -> new LiquidBlock(() -> ModFluids.OIL_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<LiquidBlock> FUEL_BLOCK = ModFluids.BLOCKS.register("fuel",
            () -> new LiquidBlock(() -> ModFluids.FUEL_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}