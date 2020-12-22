package com.NightNetwork.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable(){
		getLogger().info("WorldWhitelist v" + this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable(){
		
	}
	
}
