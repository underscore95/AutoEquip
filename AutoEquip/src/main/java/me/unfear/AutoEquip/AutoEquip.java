package me.unfear.AutoEquip;

import org.bukkit.plugin.java.JavaPlugin;

public class AutoEquip extends JavaPlugin {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new OnPickupItem(), this);
	}
}
