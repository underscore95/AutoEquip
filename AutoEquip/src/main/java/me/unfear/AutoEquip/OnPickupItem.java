package me.unfear.AutoEquip;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class OnPickupItem implements Listener {

	@EventHandler
	void onPickupItem(PlayerPickupItemEvent e) {
		if (e.isCancelled()) return;
		
		ArmorItem newItem = new ArmorItem(e.getItem().getItemStack(), e.getPlayer());
		Material type = e.getItem().getItemStack().getType();
		
		// i can't think of a better way to do this
		if (Utils.isHelmet(type)) {
			ItemStack currentItemStack = e.getPlayer().getInventory().getHelmet();
			ArmorItem current = new ArmorItem(currentItemStack, e.getPlayer());
			if (newItem.compareTo(current) <= 0) return;
			
			// swap items
			e.getPlayer().getInventory().setHelmet(e.getItem().getItemStack());
			if (currentItemStack != null && currentItemStack.getType() != Material.AIR) e.getPlayer().getInventory().addItem(currentItemStack);
			e.getItem().remove();
			e.setCancelled(true);
		} 
		
		else if (Utils.isChestplate(type)) {
			ItemStack currentItemStack = e.getPlayer().getInventory().getChestplate();
			ArmorItem current = new ArmorItem(currentItemStack, e.getPlayer());
			if (newItem.compareTo(current) <= 0) return;
			
			// swap items
			e.getPlayer().getInventory().setChestplate(e.getItem().getItemStack());
			if (currentItemStack != null && currentItemStack.getType() != Material.AIR) e.getPlayer().getInventory().addItem(currentItemStack);
			e.getItem().remove();
			e.setCancelled(true);
		} 
		
		else if (Utils.isLeggings(type)) {
			ItemStack currentItemStack = e.getPlayer().getInventory().getLeggings();
			ArmorItem current = new ArmorItem(currentItemStack, e.getPlayer());
			if (newItem.compareTo(current) <= 0) return;
			
			// swap items
			e.getPlayer().getInventory().setLeggings(e.getItem().getItemStack());
			if (currentItemStack != null && currentItemStack.getType() != Material.AIR) e.getPlayer().getInventory().addItem(currentItemStack);
			e.getItem().remove();
			e.setCancelled(true);
		} 
		
		else if (Utils.isBoots(type)) {
			ItemStack currentItemStack = e.getPlayer().getInventory().getBoots();
			ArmorItem current = new ArmorItem(currentItemStack, e.getPlayer());
			if (newItem.compareTo(current) <= 0) return;
			
			// swap items
			e.getPlayer().getInventory().setBoots(e.getItem().getItemStack());
			if (currentItemStack != null && currentItemStack.getType() != Material.AIR) e.getPlayer().getInventory().addItem(currentItemStack);
			e.getItem().remove();
			e.setCancelled(true);
		}
	}
}
