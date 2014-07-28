package io.github.KoenMulder.Yolo;

import org.bukkit.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Yolo extends JavaPlugin {
	
	public void onEnable(){
		getLogger().info("yolo plugin loaded :)");
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
		return false;
	}
}
