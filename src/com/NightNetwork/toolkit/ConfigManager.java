package com.NightNetwork.toolkit;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.NightNetwork.main.Plugin;

public class ConfigManager {
	
	private Plugin plugin;
	public File logFile;
	public FileConfiguration log;
	
	public ConfigManager(Plugin plugin){
		this.plugin = plugin;
	}
	
	public void setUp(){
		try {
			File dataFolder = plugin.getDataFolder();
			if(!dataFolder.exists()){
				dataFolder.mkdir();
			}
			
			logFile = new File(plugin.getDataFolder(), "whitelisted.yml");
			if(!logFile.exists()){
				logFile.createNewFile();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		log = YamlConfiguration.loadConfiguration(logFile);
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The worldWhitelist whitelist file has been loaded");
	}
	
	public void saveLog(){
		try {
			log.save(logFile);
		}catch(IOException e){
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "The log file could not be saved");
		}
	}
	
	public void reloadLog(){
		log = YamlConfiguration.loadConfiguration(logFile);
	}
	
	public void writeLog(String parent, Object data){
		log.set(parent, data);
	}
	
	public FileConfiguration getConfig(){
		return log;
	}
	
}
