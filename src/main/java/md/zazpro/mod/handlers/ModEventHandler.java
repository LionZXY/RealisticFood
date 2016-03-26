package md.zazpro.mod.handlers;

import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ModEventHandler {
	private static final List<String> BLACKLIST = Arrays.asList(new String[] {
			"minecraft:rotten_flesh","minecraft:spider_eye","minecraft:poisonous_potato"});
	
	int cooldown = 0;

	@SubscribeEvent
	public void checkRootEvent(LivingUpdateEvent e) {
		EntityPlayer player = null;
		World world;
		if (e.entityLiving instanceof EntityPlayer) {
			player = (EntityPlayer) e.entityLiving;
			world = player.worldObj;
			if (!world.isRemote){
			int maxinv = player.inventory.getSizeInventory();
			for (int i = 0; i < maxinv; i++){
				ItemStack currentStack = player.inventory.getStackInSlot(i);
				if (currentStack != null && currentStack.getItem() instanceof ItemFood && !currentStack.hasTagCompound() && !BLACKLIST.contains(Item.itemRegistry.getNameForObject(currentStack.getItem())))
					setRot(currentStack, world);
			}
		}
		}
	}
	
	@SubscribeEvent
	public void setRootEvent(LivingUpdateEvent e) {
		EntityLivingBase player = null;
		if (e.entityLiving instanceof EntityPlayer) {
			player = e.entityLiving;
			if (cooldown > 20) {
				checkRot(player);
				cooldown = 0;
			} else
				cooldown++;
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		EntityLivingBase player = null;
		if (e.entity instanceof EntityPlayer) {
			player = (EntityLivingBase) e.entity;
			checkRot(player);
		}

	}

	public void checkRot(EntityLivingBase entity) {
		EntityPlayer player = (EntityPlayer) entity;
		World world = player.worldObj;
		long currentTime = world.getTotalWorldTime();
		int maxinv = player.inventory.getSizeInventory();
		int count;
		for (int i = 0; i < maxinv; i++) {
			ItemStack currentStack = player.inventory.getStackInSlot(i);
			if (currentStack != null && currentStack.getItem() instanceof ItemFood && currentStack.hasTagCompound()
					&& ((currentStack.getTagCompound().getLong("dateCreated") + 200) < currentTime)){
					count = currentStack.stackSize;
					player.inventory.decrStackSize(i, count);
					player.inventory.setInventorySlotContents(i, new ItemStack(Items.rotten_flesh,count));
			}
		}
	}

	public void setRot(ItemStack itemStack, World world) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setLong("dateCreated", timeRound(world.getTotalWorldTime()));
				itemStack.setTagCompound(tag);
	}
	
	@SubscribeEvent
	public void onDegugTick(LivingUpdateEvent e) {;
		if (e.entityLiving instanceof EntityPlayer) {
			if (e.entityLiving.getHeldItem() != null){
			//System.out.println(Item.itemRegistry.getNameForObject(e.entityLiving.getHeldItem().getItem()));
			 if(e.entityLiving.getHeldItem().hasTagCompound()){
				System.out.println(e.entityLiving.getHeldItem().getTagCompound().getLong("dateCreated"));}
		}
		}
	}
	
	public long timeRound(long x)
	{
		long result;
		if (x<1000)
			result = 1000;
		else if ((x % 1000)>=500)
			result = x / 1000*1000+1000;
		else 
			result = x / 1000*1000;
		
		return result;
	}
}
