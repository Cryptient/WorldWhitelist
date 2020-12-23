package com.NightNetwork.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.NightNetwork.commands.WorldName;
import com.NightNetwork.commands.WorldWhitelist;
import com.NightNetwork.listeners.MainListener;
import com.NightNetwork.toolkit.ConfigManager;

public class Plugin extends JavaPlugin {
	
	private static ConfigManager cm;
	
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new MainListener(this), this);
		cm = new ConfigManager(this);
		cm.setUp();
		
		this.getCommand("WorldWhitelist").setExecutor(new WorldWhitelist(this));
		this.getCommand("WorldName").setExecutor(new WorldName());
		
		
		if(cm.getConfig().get("whitelisted") == null) {
			List<List<String>> people = new ArrayList<>();
	        cm.getConfig().set("whitelisted", people);
	        cm.saveLog();
	        cm.reloadLog();
		}
		
		getLogger().info("WorldWhitelist v" + this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable(){
		cm.saveLog();
	}
	
	public boolean isWhitelisted(String name, String world){
		if(!cm.getConfig().isSet("whitelisted."+world))return false;
		@SuppressWarnings("unchecked")
		List<String> whitelistedPlayers = (List<String>) cm.getConfig().get("whitelisted."+world);
		return whitelistedPlayers.contains(name);
	}
	
	@SuppressWarnings("unchecked")
	public void whitelist(String name, String world){
		List<String> whitelistedPlayers;
		if(cm.getConfig().isSet("whitelisted."+world)){
			whitelistedPlayers = (List<String>) cm.getConfig().get("whitelisted."+world);
		}else {
			whitelistedPlayers = new ArrayList<String>();
		}
		whitelistedPlayers.add(name);
		cm.getConfig().set("whitelisted."+world, whitelistedPlayers);
		cm.saveLog();
		cm.reloadLog();
	}
	
	public void unWhitelist(String name, String world){
		@SuppressWarnings("unchecked")
		List<String> whitelistedPlayers = (List<String>) cm.getConfig().get("whitelisted."+world);
		whitelistedPlayers.remove(name);
		cm.getConfig().set("whitelisted."+world, whitelistedPlayers);
		cm.saveLog();
		cm.reloadLog();
	}
	
}
