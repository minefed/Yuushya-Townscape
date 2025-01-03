package com.yuushya.block;

import com.yuushya.block.blockstate.PositionVerticalState;
import com.yuushya.utils.YuushyaUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.List;

import static com.yuushya.block.YuushyaBlockFactory.isTheSameBlock;
import static com.yuushya.block.blockstate.YuushyaBlockStates.POS_VERTICAL;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class ColumnBlock extends AbstractYuushyaBlockType {
    public ColumnBlock() {
        super();
    }

    @Override
    public List<Property<?>> getBlockStateProperty() {
        List<Property<?>> properties = new java.util.ArrayList<>();
        properties.add(POS_VERTICAL);
        return properties;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return blockState.setValue(POS_VERTICAL, getPositionOfColumn(blockState,levelAccessor,blockPos));
    }
    public static PositionVerticalState getPositionOfColumn(BlockState stateIn, LevelAccessor worldIn, BlockPos currentPos) {
        BlockState posUp = YuushyaUtils.getBlockState (worldIn.getBlockState(currentPos.above()),worldIn,currentPos.above());
        BlockState posDown =YuushyaUtils.getBlockState( worldIn.getBlockState(currentPos.below()),worldIn,currentPos.below());
        if (isTheSameBlock(posUp,stateIn)){
            if(isTheSameBlock(posDown,stateIn))
                return PositionVerticalState.MIDDLE;
            else
                return PositionVerticalState.BOTTOM;}
        else if(isTheSameBlock(posDown,stateIn)){
            return PositionVerticalState.TOP;}
        else {
            return PositionVerticalState.NONE;}
    }

}
