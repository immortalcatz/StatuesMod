package com.svennieke.statues.entity.fakeentity;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FakeHusk extends EntityHusk{

	private int lifetime;

	public FakeHusk(World worldIn) {
		super(worldIn);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
	}

	@Override
	@Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }
	
	@Override
	protected boolean canDropLoot() {
		return false;
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Lifetime", this.lifetime);
    }
	
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.lifetime = compound.getInteger("Lifetime");
    }
	
	@Override
	public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.world.isRemote)
        {
            if (!this.isNoDespawnRequired())
            {
                ++this.lifetime;
            }

            if (this.lifetime >= 2400)
            {
                this.setDead();
            }
        }
    }
}
