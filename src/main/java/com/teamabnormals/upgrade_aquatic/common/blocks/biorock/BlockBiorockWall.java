package com.teamabnormals.upgrade_aquatic.common.blocks.biorock;

import java.util.Random;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBiorockWall extends WallBlock {

	public BlockBiorockWall(Properties properties) {
		super(properties);
	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if(!worldIn.isAreaLoaded(pos, 3)) return;
		
		for(int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			if(UABlocks.BIOROCK_WALL_CONVERSION_MAP.containsKey(worldIn.getBlockState(blockpos).getBlock())) {
				BlockState newState = UABlocks.BIOROCK_WALL_CONVERSION_MAP.get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState()
					.with(UP, state.get(UP))
					.with(NORTH, state.get(NORTH))
					.with(EAST, state.get(EAST))
					.with(SOUTH, state.get(SOUTH))
					.with(WEST, state.get(WEST))
					.with(WATERLOGGED, state.get(WATERLOGGED));
				
				worldIn.setBlockState(pos, newState);
			}
		}
	}

}
