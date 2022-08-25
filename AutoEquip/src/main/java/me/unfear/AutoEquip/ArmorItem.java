package me.unfear.AutoEquip;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmorItem implements Comparable<ArmorItem> {

	private long bars = 0, prot = 0, bonus = 0, playerArmorBars = 0;
	/**
	 * This class is used to compare the "value" of one piece of armor to another.
	 * 1 targeted protection (eg feather falling, fire protection) is defined as equal to 0.5 protection
	 * 
	 * Supports unsafe enchantments
	 */
	public ArmorItem(ItemStack item, Player p) {
		if (item == null || !Utils.getArmorBars().containsKey(item.getType())) return; // not armor
		
		bars = Utils.getArmorBars().get(item.getType());
		prot = Utils.getProtection(item);
		bonus = Utils.getOtherProtection(item);
		playerArmorBars = Utils.getPlayerArmor(p) + bars;
		
		// remove equipped items bars, since we added new item bars
		ItemStack equipped = null;
		if (Utils.isHelmet(item.getType())) equipped = p.getInventory().getHelmet();
		else if (Utils.isChestplate(item.getType())) equipped = p.getInventory().getChestplate();
		else if (Utils.isLeggings(item.getType())) equipped = p.getInventory().getLeggings();
		else if (Utils.isBoots(item.getType())) equipped = p.getInventory().getBoots();
		
		if (equipped != null && Utils.getArmorBars().containsKey(equipped.getType())) playerArmorBars -= Utils.getArmorBars().get(equipped.getType());
	}
	
	/** <a href="https://minecraft.fandom.com/wiki/Protection#Usage">Source</a>
	 * @return 0-1, representing value of this armor
	 */
	private double getReductionPercentage() {
		// armor bars
		double percent = bars * 0.04;
		// enchants
		percent += (1 - Math.min(0.8, playerArmorBars * 0.04)) * (prot + 0.5 * bonus) * 0.04;
		return percent;
	}
	
	@Override
	public int compareTo(ArmorItem o) {
		if (o == null) return 1;
		return (int) Math.round(this.getReductionPercentage() * 100 - o.getReductionPercentage() * 100);
	}
}
