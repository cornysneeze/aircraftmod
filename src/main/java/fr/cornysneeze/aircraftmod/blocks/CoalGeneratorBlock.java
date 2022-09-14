package fr.cornysneeze.aircraftmod.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class CoalGeneratorBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final String MESSAGE_COALGEN = "message.coalgen";
    public static final String SCREEN_AICRAFTMOD_COALGEN = "screen.aircraftmod.coalgen";


    public CoalGeneratorBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(2.0f)
                .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
                .requiresCorrectToolForDrops()
        );
    }




    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent(MESSAGE_COALGEN, Integer.toString(CoalgenConfig.COALGEN_GENERATE.get()))
                .withStyle(ChatFormatting.BLUE));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CoalgenBE(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof CoalgenBE tile) {
                tile.tickServer();
            }
        };
    }

    public CoalGeneratorBlock (Properties p_54120_) {
        super(p_54120_);
    }

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.box(1, 13, 1, 15, 14, 15),
            Block.box(1, 14, 14, 14, 16, 15),
            Block.box(1, 14, 1, 2, 16, 14),
            Block.box(14, 14, 2, 15, 16, 15),
            Block.box(2, 14, 1, 15, 16, 2),
            Block.box(0, 0, 1, 16, 13, 15),
            Block.box(4.5, 2.5, 15, 6.5, 11.5, 16),
            Block.box(9.5, 2.5, 15, 11.5, 11.5, 16),
            Block.box(3, 1, 14.1, 13, 12, 15.1),
            Block.box(7, 5, 14.2, 9, 9, 15.2),
            Block.box(4, 8, 0.9, 12, 12, 1.9),
            Block.box(11.75, 12, 0, 12.25, 12.5, 1),
            Block.box(11.90054, 8.5, -0.52, 12.09946, 15.5, 0.48),
            Block.box(11.5, 8.5, -0.11, 12.5, 15.5, 0.08892),
            Block.box(11.5, 5.5, 0.4, 12.5, 11.5, 1.4),
            Block.box(11.45, 8, -0.55, 12.55, 9, 0.55),
            Block.box(2, 0, 0, 14, 1, 1),
            Block.box(3, 3, 0, 4, 4, 1),
            Block.box(2, 1, 0, 3, 3, 1),
            Block.box(4, 4, 0, 5, 5, 1),
            Block.box(5, 5, 0, 11, 6, 1),
            Block.box(12, 3, 0, 13, 4, 1),
            Block.box(11, 4, 0, 12, 5, 1),
            Block.box(13, 1, 0, 14, 3, 1),
            Block.box(3, 1, 0.99, 13, 3, 1.99),
            Block.box(11, 3, 0.99, 12, 4, 1.99),
            Block.box(4, 3, 0.99, 5, 4, 1.99),
            Block.box(5, 3, 0.99, 11, 5, 1.99)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.box(1, 13, 1, 15, 14, 15),
            Block.box(2, 14, 1, 15, 16, 2),
            Block.box(14, 14, 2, 15, 16, 15),
            Block.box(1, 14, 1, 2, 16, 14),
            Block.box(1, 14, 14, 14, 16, 15),
            Block.box(0, 0, 1, 16, 13, 15),
            Block.box(9.5, 2.5, 0, 11.5, 11.5, 1),
            Block.box(4.5, 2.5, 0, 6.5, 11.5, 1),
            Block.box(3, 1, 0.9000000000000004, 13, 12, 1.9000000000000004),
            Block.box(7, 5, 0.8000000000000007, 9, 9, 1.8000000000000007),
            Block.box(4, 8, 14.1, 12, 12, 15.1),
            Block.box(3.75, 12, 15, 4.25, 12.5, 16),
            Block.box(3.9005399999999995, 8.5, 15.52, 4.0994600000000005, 15.5, 16.52),
            Block.box(3.5, 8.5, 15.91108, 4.5, 15.5, 16.11),
            Block.box(3.5, 5.5, 14.6, 4.5, 11.5, 15.6),
            Block.box(3.4499999999999993, 8, 15.45, 4.550000000000001, 9, 16.55),
            Block.box(2, 0, 15, 14, 1, 16),
            Block.box(12, 3, 15, 13, 4, 16),
            Block.box(13, 1, 15, 14, 3, 16),
            Block.box(11, 4, 15, 12, 5, 16),
            Block.box(5, 5, 15, 11, 6, 16),
            Block.box(3, 3, 15, 4, 4, 16),
            Block.box(4, 4, 15, 5, 5, 16),
            Block.box(2, 1, 15, 3, 3, 16),
            Block.box(3, 1, 14.01, 13, 3, 15.01),
            Block.box(4, 3, 14.01, 5, 4, 15.01),
            Block.box(11, 3, 14.01, 12, 4, 15.01),
            Block.box(5, 3, 14.01, 11, 5, 15.01)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.box(1, 13, 1, 15, 14, 15),
            Block.box(1, 14, 1, 2, 16, 14),
            Block.box(2, 14, 1, 15, 16, 2),
            Block.box(1, 14, 14, 14, 16, 15),
            Block.box(14, 14, 2, 15, 16, 15),
            Block.box(1, 0, 0, 15, 13, 16),
            Block.box(0, 2.5, 4.5, 1, 11.5, 6.5),
            Block.box(0, 2.5, 9.5, 1, 11.5, 11.5),
            Block.box(0.9000000000000004, 1, 3, 1.9000000000000004, 12, 13),
            Block.box(0.8000000000000007, 5, 7, 1.8000000000000007, 9, 9),
            Block.box(14.1, 8, 4, 15.1, 12, 12),
            Block.box(15, 12, 11.75, 16, 12.5, 12.25),
            Block.box(15.52, 8.5, 11.90054, 16.52, 15.5, 12.09946),
            Block.box(15.91108, 8.5, 11.5, 16.11, 15.5, 12.5),
            Block.box(14.6, 5.5, 11.5, 15.6, 11.5, 12.5),
            Block.box(15.45, 8, 11.45, 16.55, 9, 12.55),
            Block.box(15, 0, 2, 16, 1, 14),
            Block.box(15, 3, 3, 16, 4, 4),
            Block.box(15, 1, 2, 16, 3, 3),
            Block.box(15, 4, 4, 16, 5, 5),
            Block.box(15, 5, 5, 16, 6, 11),
            Block.box(15, 3, 12, 16, 4, 13),
            Block.box(15, 4, 11, 16, 5, 12),
            Block.box(15, 1, 13, 16, 3, 14),
            Block.box(14.01, 1, 3, 15.01, 3, 13),
            Block.box(14.01, 3, 11, 15.01, 4, 12),
            Block.box(14.01, 3, 4, 15.01, 4, 5),
            Block.box(14.01, 3, 5, 15.01, 5, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SHAPE_W = Stream.of(
            Block.box(1, 13, 1, 15, 14, 15),
            Block.box(14, 14, 2, 15, 16, 15),
            Block.box(1, 14, 14, 14, 16, 15),
            Block.box(2, 14, 1, 15, 16, 2),
            Block.box(1, 14, 1, 2, 16, 14),
            Block.box(1, 0, 0, 15, 13, 16),
            Block.box(15, 2.5, 9.5, 16, 11.5, 11.5),
            Block.box(15, 2.5, 4.5, 16, 11.5, 6.5),
            Block.box(14.1, 1, 3, 15.1, 12, 13),
            Block.box(14.2, 5, 7, 15.2, 9, 9),
            Block.box(0.9000000000000004, 8, 4, 1.9000000000000004, 12, 12),
            Block.box(0, 12, 3.75, 1, 12.5, 4.25),
            Block.box(-0.5199999999999996, 8.5, 3.9005399999999995, 0.4800000000000004, 15.5, 4.0994600000000005),
            Block.box(-0.10999999999999943, 8.5, 3.5, 0.08891999999999989, 15.5, 4.5),
            Block.box(0.40000000000000036, 5.5, 3.5, 1.4000000000000004, 11.5, 4.5),
            Block.box(-0.5500000000000007, 8, 3.4499999999999993, 0.5500000000000007, 9, 4.550000000000001),
            Block.box(0, 0, 2, 1, 1, 14),
            Block.box(0, 3, 12, 1, 4, 13),
            Block.box(0, 1, 13, 1, 3, 14),
            Block.box(0, 4, 11, 1, 5, 12),
            Block.box(0, 5, 5, 1, 6, 11),
            Block.box(0, 3, 3, 1, 4, 4),
            Block.box(0, 4, 4, 1, 5, 5),
            Block.box(0, 1, 2, 1, 3, 3),
            Block.box(0.9900000000000002, 1, 3, 1.9900000000000002, 3, 13),
            Block.box(0.9900000000000002, 3, 4, 1.9900000000000002, 4, 5),
            Block.box(0.9900000000000002, 3, 11, 1.9900000000000002, 4, 12),
            Block.box(0.9900000000000002, 3, 5, 1.9900000000000002, 5, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch (p_60555_.getValue(FACING)) {
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.POWERED);
        builder.add(BlockStateProperties.FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(BlockStateProperties.POWERED, false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CoalgenBE) {
                MenuProvider containerProvider = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return new TranslatableComponent(SCREEN_AICRAFTMOD_COALGEN);
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return new CoalgenContainer(windowId, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

}
