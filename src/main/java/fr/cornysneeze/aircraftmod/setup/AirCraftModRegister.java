package fr.cornysneeze.aircraftmod.setup;

import fr.cornysneeze.aircraftmod.blocks.CoalGeneratorBlock;
import fr.cornysneeze.aircraftmod.blocks.CoalgenBE;
import fr.cornysneeze.aircraftmod.blocks.CoalgenContainer;
import fr.cornysneeze.aircraftmod.fluid.ModFluids;
import fr.cornysneeze.aircraftmod.items.compressed_wood;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static fr.cornysneeze.aircraftmod.aircraftmod.MODID;
import static net.minecraftforge.registries.ForgeRegistries.BLOCK_ENTITIES;
import static net.minecraftforge.registries.ForgeRegistries.CONTAINERS;

public class AirCraftModRegister {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
    }
    public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
    public static final BlockBehaviour.Properties HARD_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).strength(50f,1200f).requiresCorrectToolForDrops();
    public static final BlockBehaviour.Properties MACHINE = BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(7f,30f).requiresCorrectToolForDrops();
    public static final Item.Properties ITEM_PROPERTIES_I = new Item.Properties().tab(AirCraftModSetup.AIRITEM);
    public static final Item.Properties ITEM_PROPERTIES_M = new Item.Properties().tab(AirCraftModSetup.MACHINE);
    public static final Item.Properties ITEM_PROPERTIES_P = new Item.Properties().tab(AirCraftModSetup.PLANES);
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES_I));
    }

    public static final RegistryObject<Block> LITHIUM_ORE = BLOCKS.register("lithium_ore", () -> new Block(ORE_PROPERTIES));
    public static final RegistryObject<Item> LITHIUM_ORE_ITEM = fromBlock(LITHIUM_ORE);
    public static final RegistryObject<Block> HARDENED_IRON_BLOCK = BLOCKS.register("hardend_iron_block", () -> new Block(HARD_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> HARDENED_IRON_BLOCK_ITEM = fromBlock(HARDENED_IRON_BLOCK);



    public static final RegistryObject<CoalGeneratorBlock> COALGEN = BLOCKS.register("coal_generator", CoalGeneratorBlock::new);
    public static final RegistryObject<Item> COALGEN_ITEM = fromBlock(COALGEN);

    public static final RegistryObject<BlockEntityType<CoalgenBE>> COALGEN_BE = BLOCK_ENTITIES.register("coal_generator",
            () -> BlockEntityType.Builder.of(CoalgenBE::new,
                    AirCraftModRegister.COALGEN.get()).build(null));
    public static final RegistryObject<MenuType<CoalgenContainer>> COALGEN_CONTAINER = CONTAINERS.register("coal_generator",
            () -> IForgeMenuType.create((windowId, inv, data) -> new CoalgenContainer(windowId, data.readBlockPos(), inv, inv.player)));





    public static final RegistryObject<Item> COMPRESSED_WOOD = ITEMS.register("compressed_wood.json", () -> new compressed_wood(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> BAROMETER = ITEMS.register("barometer", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> AIRCRAFT_BOOK = ITEMS.register("aircraft_book", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> BIG_STICK = ITEMS.register("big_stick", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> ELECTRONIC_CELL = ITEMS.register("electronic_cell", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> ENERGIZED_LITHIUM_PLATE = ITEMS.register("energized_lithium_plate", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> LITHIUM_PLATE = ITEMS.register("lithium_plate", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> RAW_CELL = ITEMS.register("raw_cell", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> RAW_RUBBER = ITEMS.register("raw_rubber", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> REFINED_RUBBER = ITEMS.register("refined_rubber", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> RUBBER_BALOON = ITEMS.register("rubber_baloon", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> WOOL_FIBRE = ITEMS.register("wool_fibre", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> TIER_I_ALTERNATOR = ITEMS.register("tier_1_alternator", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> TIER_II_ALTERNATOR = ITEMS.register("tier_2_alternator", () -> new Item(ITEM_PROPERTIES_I));
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new BucketItem(ModFluids.OIL_FLUID, new Item.Properties().tab(AirCraftModSetup.AIRITEM).stacksTo(1)));
    public static final RegistryObject<Item> FUEL_BUCKET = ITEMS.register("fuel_bucket", () -> new BucketItem(ModFluids.FUEL_FLUID, new Item.Properties().tab(AirCraftModSetup.AIRITEM).stacksTo(1)));


}
