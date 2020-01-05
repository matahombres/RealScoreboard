package josegamerpt.realscoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Data {

    static HashMap<String, String> data = new HashMap<String, String>();
    static HashMap<String, List<String>> dataList = new HashMap<String, List<String>>();

    public static void init() {
        data.put("Config.Animations.Rainbow-Delay",
                Configuration.ficheiro().getString("Config.Animations.Rainbow-Delay"));
        data.put("Config.Animations.Title-Delay", Configuration.ficheiro().getString("Config.Animations.Title-Delay"));
        data.put("Config.Animations.Refresh-Delay",
                Configuration.ficheiro().getString("Config.Animations.Refresh-Delay"));

        dataList.put("Config.Disabled-Worlds", Configuration.ficheiro().getStringList("Config.Disabled-Worlds"));
    }

    public static String getData(String id) {
        return data.get(id);
    }

    public static List<String> getDataList(String id) {
        return dataList.get(id);
    }

    public static ArrayList<String> getRegisteredWorlds() {
        ArrayList<String> worlds = new ArrayList<String>();

        ConfigurationSection cs = Configuration.ficheiro().getConfigurationSection("Config.Scoreboard");
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

        }
        return correctWorld;
    }

    public static int getTimes(int i) {
        if (i == 1) {
            return Configuration.ficheiro().getInt("Config.Animations.Title-Delay");
        }
        if (i == 2) {
            return Configuration.ficheiro().getInt("Config.Animations.Rainbow-Delay");
        }
        if (i == 3) {
            return Configuration.ficheiro().getInt("Config.Animations.Refresh-Delay");
        }
        return 5;
    }
}
