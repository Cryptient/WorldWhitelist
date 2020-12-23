package com.NightNetwork.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.NightNetwork.main.Plugin;

public class WorldWhitelist implements CommandExecutor {

	private static Plugin plugin;
	
	public WorldWhitelist(Plugin plugin){
		WorldWhitelist.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender.hasPermission("worldWhitelist.whitelist")){
			if(args[0].equals("add")){
				plugin.whitelist(args[1], args[2]);
				return true;
			}else if(args[0].equals("remove")){
				plugin.unWhitelist(args[1], args[2]);
				return true;
			}else return false;
		}else return false;
	}

}
