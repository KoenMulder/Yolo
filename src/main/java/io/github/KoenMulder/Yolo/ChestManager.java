package io.github.KoenMulder.Yolo;

import java.io.*;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map.Entry;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.inventory.*;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChestManager {
	private static String CHESTS_EXTENSION = ".chest.yml";
	
	private HashMap<String, Inventory> chests;
	private File chestsDir;
	
	private Logger logger;
	
	public void ChestsManager(File dataDir, Logger logger) {
		this.chestsDir = dataDir;
		this.chests = new HashMap<String, Inventory>();
		
		this.logger = logger;
		
		init();
	}
	
	private void init() {
		chestsDir.mkdirs(); // mkdir -p
		
		FilenameFilter fnFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(CHESTS_EXTENSION);
			}
		};
		
		for (File chestFile : chestsDir.listFiles(fnFilter)) {
			String chestFileName = chestFile.getName();
			
			try {
				String playerName = chestFileName.substring(0, chestFileName.length() - CHESTS_EXTENSION.length());
				chests.put(playerName.toLowerCase(), loadInventoryFromFile(chestFile));
			} catch (Exception e) {
				logger.log(Level.WARNING, "Could not load chest file" + chestFileName, e);
			}
		}
	}
	
	public void save() {
		chestsDir.mkdirs();

		Iterator<Entry<String, Inventory>> chestIterator = chests.entrySet().iterator();
		while (chestIterator.hasNext()) {
			final Entry<String, Inventory> entry = chestIterator.next();
			final String playerName = entry.getKey();
			final Inventory chest = entry.getValue();

			final File chestFile = new File(chestsDir, playerName + CHESTS_EXTENSION);
			if (chest == null) {
				// Chest got removed, so we have to delete the file.
				chestFile.delete();
				chestIterator.remove();

			} else {
				try {
					saveInventoryToFile(chest, chestFile);
				} catch (IOException e) {
					logger.log(Level.WARNING, "Couldn't save chest file: " + chestFile.getName(), e);
				}
			}
		}
	}
	
	private Inventory loadInventoryFromFile(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
		YamlConfiguration yaml = new YamlConfiguration();
		yaml.load(file);

		int inventorySize = yaml.getInt("size", 6 * 9);
		Inventory inventory = Bukkit.getServer().createInventory(null, inventorySize);

		ConfigurationSection items = yaml.getConfigurationSection("items");
		for (int slot = 0; slot < inventorySize; slot++) {
			String slotString = String.valueOf(slot);
			if (items.isItemStack(slotString)) {
				ItemStack itemStack = items.getItemStack(slotString);
				inventory.setItem(slot, itemStack);
			}
		}

		return inventory;
	}
	
	private void saveInventoryToFile(Inventory inventory, File file) throws IOException {
		YamlConfiguration yaml = new YamlConfiguration();

		int inventorySize = inventory.getSize();
		yaml.set("size", inventorySize);

		ConfigurationSection items = yaml.createSection("items");
		for (int slot = 0; slot < inventorySize; slot++) {
			ItemStack stack = inventory.getItem(slot);
			if (stack != null) {
				items.set(String.valueOf(slot), stack);
			}
		}

		yaml.save(file);
	}
}
