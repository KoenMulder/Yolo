package io.github.KoenMulder.Yolo;

import org.bukkit.*;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import io.github.KoenMulder.Yolo.SwearUtils;

import java.util.*;

public class Yolo extends JavaPlugin implements Listener {
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("yolo plugin loaded :)");
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		
		for (String key: SwearUtils.swearwordMap.keySet()) {
			if (msg.toLowerCase().contains(key)) {
				getLogger().info("found " + key);
				int index = msg.toLowerCase().indexOf(key);
				String replacementWord = SwearUtils.swearwordMap.get(key)[0];
				String newMsg = msg.replaceAll("\\b" + key + "\\b", replacementWord);
				msg = newMsg;
			}
		}
		getLogger().info(msg);
		
		event.setMessage(msg);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("yolo")) {
			player.sendMessage("<Herobrine> Hello idiot");
			player.setFireTicks(40);
		}
		else if (cmd.getName().equalsIgnoreCase("wild")) {
			player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Succesfully teleported to wild.");
			World world = player.getWorld();
			int x = -3000 + (int)(Math.random() * ((3000 - -3000) + 1));
			int z = -3000 + (int)(Math.random() * ((3000 - -3000) + 1));
			int y = world.getHighestBlockYAt(x, z);
			
			Location loc = new Location(world, x, y + 3, z);
			player.teleport(loc);
		}
		else if (cmd.getName().equalsIgnoreCase("hashtag")) {
			player.chat(ChatColor.GREEN + "Liked pooplozers instagram picture.");
			
		}
		else if (cmd.getName().equalsIgnoreCase("ip")) {
			player.sendMessage(player.getAddress().getHostName());
			
		}
		else if  (cmd.getName().equalsIgnoreCase("deathwish")) {
			player.sendMessage(ChatColor.RED + " survival of the" + ChatColor.BOLD + " fittest.");
			World world = player.getWorld();
			Location location = player.getLocation();
			world.playEffect(location, Effect.ENDER_SIGNAL, 2003);
			world.strikeLightning(location);
			world.createExplosion(location, 2);
		}
		else if (cmd.getName().equalsIgnoreCase("godspeed")) {
			player.sendMessage(ChatColor.AQUA + "You have gained the power of" + ChatColor.ITALIC + " ZEUS.");
			World world = player.getWorld();
			Location location = player.getLocation();
			world.playEffect(location, Effect.SMOKE, 3000);
			world.playSound(location, Sound.BLAZE_DEATH, 50, 3);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 5));
			
		}
		else if (cmd.getName().equalsIgnoreCase("jazz")) {
			World world = player.getWorld();
			Location location = player.getLocation();
			world.playEffect(location, Effect.ENDER_SIGNAL, 3000);
			world.playSound(location, Sound.BLAZE_HIT, 50, 3);
			world.playEffect(location, Effect.SMOKE, 3000, 500);
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2000, 5));
			world.playEffect(location, Effect.MOBSPAWNER_FLAMES, 3000, 10);
			
		}
		else if (cmd.getName().equalsIgnoreCase("lol")) {
			List<PotionEffect> potions = new ArrayList<PotionEffect>();
			
			potions.add(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2000, 5));
			potions.add(new PotionEffect(PotionEffectType.REGENERATION, 2000, 5));
			potions.add(new PotionEffect(PotionEffectType.FAST_DIGGING, 2000, 5));
			potions.add(new PotionEffect(PotionEffectType.ABSORPTION, 2000, 5));
			
			player.addPotionEffects(potions);
		}
		return false;
	}
}
