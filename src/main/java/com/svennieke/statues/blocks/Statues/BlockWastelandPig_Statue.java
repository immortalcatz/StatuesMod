package com.svennieke.statues.blocks.Statues;

import com.svennieke.statues.blocks.iStatue;
import com.svennieke.statues.blocks.StatueBase.BlockWastelandPig;
import com.svennieke.statues.init.StatuesItems;
import com.svennieke.statues.tileentity.StatueTileEntity;
import com.svennieke.statues.util.RandomLists;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWastelandPig_Statue extends BlockWastelandPig implements iStatue, ITileEntityProvider{
	
	private int TIER;
	
	public BlockWastelandPig_Statue(String unlocalised, String registry, int tier) {
		super();
		this.TIER = tier;
		setUnlocalizedName(unlocalised);
		setRegistryName(registry);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if (this.TIER == 2 || this.TIER == 3 || this.TIER == 4)
		{
			return new StatueTileEntity();
		}
		else
		return null;
	}
	
	private StatueTileEntity getTE(World world, BlockPos pos) {
        return (StatueTileEntity) world.getTileEntity(pos);
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(this.TIER == 2 || this.TIER == 3 || this.TIER == 4)
		{
	        if (!worldIn.isRemote) {
	        	int statuetier = getTE(worldIn, pos).getTier();
	        	if(statuetier != this.TIER)
	        	{
	        		getTE(worldIn, pos).setTier(this.TIER);
	        	}

	        	getTE(worldIn, pos).WastelandBehavior(worldIn, pos, playerIn);;
	        }
	        return true;
		}
		else
		return false;
	}
}
