package josegamerpt.realscoreboard.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import josegamerpt.realscoreboard.Enum;
import josegamerpt.realscoreboard.RealScoreboard;
import josegamerpt.realscoreboard.utils.Text;
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
            case SCROLLTEXT_SIZE:
                return Config.file().getInt("Config.Animations.Scroll-Text.Size");
            case SCROLLTEXT_SPACE:
                return Config.file().getInt("Config.Animations.Scroll-Text.Space");
            default:
                throw new IllegalArgumentException("DataInt isnt configured: " + di.name());
        }
    }

    public static String getString(Enum.DataString di, boolean prefix) {
        if (prefix) {
            return RealScoreboard.getPrefix() + getString(di);
        } else {
            return getString(di);
        }
    }

    private static String getString(Enum.DataString di) {
        switch (di) {
            case SCOREBOARD_TOGGLE_ON:
                return Text.addColor(Config.file().getString("Config.Messages.Scoreboard-Toggle.ON"));
            case SCOREBOARD_TOGGLE_OFF:
                return Text.addColor(Config.file().getString("Config.Messages.Scoreboard-Toggle.OFF"));
            default:
                throw new IllegalArgumentException("DataString isnt configured: " + di.name());
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
            worlds.add(iterator1.next());
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
        for (String s : worlds) {
            if (s.equalsIgnoreCase(world)) {
                return true;
            }
        }
        return false;
    }
}
