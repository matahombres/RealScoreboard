package pt.josegamerpt.realscoreboard;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Configuration implements Listener {
	static Configuration instance = new Configuration();
	Plugin p;
	static FileConfiguration data;
	static File dfile;

	public static Configuration getInstance() {
		return instance;
	}

	public static void setup(Plugin p) {
		dfile = new File(p.getDataFolder(), "config.yml");
		if (!dfile.exists()) {
			try {
				dfile.createNewFile();
			} catch (IOException localIOException) {
			}
		}
		data = YamlConfiguration.loadConfiguration(dfile);
	}

	public static FileConfiguration ficheiro() {
		return data;
	}

	public static void save() {
		try {
			data.save(dfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
		}
	}

	public static void reload() {
		data = YamlConfiguration.loadConfiguration(dfile);
	}
	
	public PluginDescriptionFile desc() {
		return this.p.getDescription();
	}
}