package io.github.KoenMulder.Yolo;

import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
			World world = player.getWorld();
			int x = -3000 + (int)(Math.random() * ((3000 - -3000) + 1));
			int z = -3000 + (int)(Math.random() * ((3000 - -3000) + 1));
			int y = world.getHighestBlockYAt(x, z);
			
			Location loc = new Location(world, x, y + 3, z);
			player.teleport(loc);
		}
		return false;
	}
}
