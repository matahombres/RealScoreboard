package josegamerpt.realscoreboard.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import josegamerpt.realscoreboard.Enum;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Data {

    public static int getInt(Enum.DataInt di) {
        switch (di) {
            case ANIMATIONS_TITLE_DELAY:
                return Config.file().getInt("Config.Animations.Title-Delay");
            case ANIMATIONS_RAINBOW_DELAY:
                return Config.file().getInt("Config.Animations.Rainbow-Delay");
            case ANIMATIONS_REFRESH_DELAY:
                return Config.file().getInt("Config.Scoreboard-Refresh");
            default:
                throw new IllegalArgumentException("DataInt isnt configured: " + di.name());
        }
    }

    public static List<String> getList(Enum.DataList ds) {
        switch (ds) {
            case CONFIG_DISABLED_WORLDS:
                return Config.file().getStringList("Config.Disabled-Worlds");
            default:
                throw new IllegalArgumentException("DataList isnt configured: " + ds.name());
        }
    }

    public static ArrayList<String> getRegisteredWorlds() {
        ArrayList<String> worlds = new ArrayList<String>();

        ConfigurationSection cs = Config.file().getConfigurationSection("Config.Scoreboard");
        Set<String> keys = cs.getKeys(false);
        for (Iterator<String> iterator1 = keys.iterator(); iterator1.hasNext(); ) {
            worlds.add((String) iterator1.next());
        }
        return worlds;
    }

    public static String getCorrectPlace(Player p) {
        if (checkSystem(p) == true) {
            return p.getLocation().getWorld().getName();
        } else {
            return Data.getRegisteredWorlds().get(0);
        }
    }


    public static boolean checkSystem(Player p) {
        String world = p.getLocation().getWorld().getName();
        ArrayList<String> worlds = Data.getRegisteredWorlds();
        boolean correctWorld = false;
        try {
            if (worlds.contains(world)) {
                correctWorld = true;
                return correctWorld;
            } else {
                correctWorld = false;
                return correctWorld;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return correctWorld;
    }

}
