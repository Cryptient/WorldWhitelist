package com.NightNetwork.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldName implements CommandExecutor {
	
	public WorldName() {}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender.hasPermission("worldWhitelist.worldName")){
			if(sender instanceof Player){
				sender.sendMessage("Current world name: " + ((Player) sender).getLocation().getWorld().getName());
			}else{
				System.out.println("\nThis command cannot be run from the console!\n");
			}
			return true;
		}else return false;
	}

}
