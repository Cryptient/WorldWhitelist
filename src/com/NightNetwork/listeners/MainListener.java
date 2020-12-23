package com.NightNetwork.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.NightNetwork.main.Plugin;

public class MainListener implements Listener {

	private static Plugin plugin;
	
	public MainListener(Plugin plugin){
		MainListener.plugin = plugin;
	}
	
	@EventHandler
	public void teleportHandler(PlayerTeleportEvent e){
		if(!e.getFrom().getWorld().equals(e.getTo().getWorld())){
			//world change
			Player player = e.getPlayer();
			
			plugin.getLogger().info(e.getFrom().getWorld().getName() + " to " + e.getTo().getWorld().getName());
			
			//if player has bypass permission then bypass whitelist check
			if(player.hasPermission("worldWhitelist.bypass"))return;
			
			if(!plugin.isWhitelisted(player.getDisplayName(), e.getTo().getWorld().getName())){
				e.setCancelled(true);
				player.sendMessage("[WorldWhitelist]: You don't have permission to do that!");
				player.spawnParticle(Particle.SMOKE_LARGE, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 200);
				return;
			}
			
			
		}
	}
	
}
