package me.unfear.AutoEquip;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {

	/**
	 * @return Map of all armor and amount of armor bars each piece gives. <br><a href="https://minecraft.fandom.com/wiki/Armor#Defense_points">Source</a>
	 */
	public static Map<Material, Integer> getArmorBars() {
		HashMap<Material, Integer> armor = new HashMap<Material, Integer>() {
			private static final long serialVersionUID = -5724685979980922605L;
		{
			
			/*
			 * Toughness doesn't exist in 1.8
			 */
			
			put(Material.LEATHER_HELMET, 1);
			put(Material.LEATHER_CHESTPLATE, 3);
			put(Material.LEATHER_LEGGINGS, 2);
			put(Material.LEATHER_BOOTS, 1);
			
			put(Material.GOLD_HELMET, 2);
			put(Material.GOLD_CHESTPLATE, 5);
			put(Material.GOLD_LEGGINGS, 3);
			put(Material.GOLD_BOOTS, 1);
			
			put(Material.CHAINMAIL_HELMET, 2);
			put(Material.CHAINMAIL_CHESTPLATE, 5);
			put(Material.CHAINMAIL_LEGGINGS, 4);
			put(Material.CHAINMAIL_BOOTS, 2);
			
			put(Material.IRON_HELMET, 2);
			put(Material.IRON_CHESTPLATE, 6);
			put(Material.IRON_LEGGINGS, 5);
			put(Material.IRON_BOOTS, 2);
			
			put(Material.DIAMOND_HELMET, 3);
			put(Material.DIAMOND_CHESTPLATE, 8);
			put(Material.DIAMOND_LEGGINGS, 6);
			put(Material.DIAMOND_BOOTS, 3);
			}
		};
		
		return armor;
	}
	
	private static Set<Enchantment> NON_ENVIROMENTAL_PROTECTIONS() {
		HashSet<Enchantment> enchants = new HashSet<Enchantment>() {
			private static final long serialVersionUID = -1398092267246859701L;
			{
				add(Enchantment.PROTECTION_EXPLOSIONS);
				add(Enchantment.PROTECTION_FALL);
				add(Enchantment.PROTECTION_FIRE);
				add(Enchantment.PROTECTION_PROJECTILE);
			}
		};
		
		return enchants;
	}
	
	/**
	 *  @return Protection level of this item , 0 if i is null
	 */
	public static int getProtection(ItemStack i) {
		if (i == null) return 0;
		return i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
	}
	
	/**
	 * @return Highest protection level of this item, excluding enviromental. 0 if i is null
	 */
	public static int getOtherProtection(ItemStack i) {
		if (i == null) return 0;
		int highest = 0;
		for (Enchantment enchant : NON_ENVIROMENTAL_PROTECTIONS()) {
			int lvl = i.getEnchantmentLevel(enchant);
			if (lvl > highest) highest = lvl;
		}
		
		return highest;
	}
	
	public static boolean isHelmet(Material type) {
		return (type == null) ? false : Arrays.asList(Material.LEATHER_HELMET, Material.GOLD_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET).contains(type);
	}
	
	public static boolean isChestplate(Material type) {
		return (type == null) ? false : Arrays.asList(Material.LEATHER_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE).contains(type);
	}
	
	public static boolean isLeggings(Material type) {
		return (type == null) ? false : Arrays.asList(Material.LEATHER_LEGGINGS, Material.GOLD_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.DIAMOND_LEGGINGS).contains(type);
	}
	
	public static boolean isBoots(Material type) {
		return (type == null) ? false : Arrays.asList(Material.LEATHER_BOOTS, Material.GOLD_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS).contains(type);
	}
	
	/** @return the amount of armor bars p has */
	public static int getPlayerArmor(Player p) {
		if (p == null) return 0;
		int total = 0;
		for (ItemStack item : p.getInventory().getArmorContents()) {
			if (item == null || !getArmorBars().containsKey(item.getType())) continue; // this item isn't armor
			
			total += getArmorBars().get(item.getType());
		}
		return total;
	}
}
