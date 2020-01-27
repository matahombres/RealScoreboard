package josegamerpt.realscoreboard.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import josegamerpt.realscoreboard.RealScoreboard;

public class Placeholders {
    private static int ping(Player player) {
        return RealScoreboard.nms.pegarPing(player);
    }

    private static String ram() {
        Runtime re = Runtime.getRuntime();
        int mbnumero = 1048576;
        return (re.totalMemory() - re.freeMemory()) / mbnumero + "MB";
    }

    private static int port() {
        return Bukkit.getPort();
    }

    private static String serverIP() {
        return Bukkit.getIp();
    }

    private static String time() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private static String day() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private static String cords(Player player) {
        return "X: " + player.getLocation().getBlockX() + " Y: " + player.getLocation().getBlockY() + " Z: "
                + player.getLocation().getBlockZ();
    }

    private static int onlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

    private static int maxPlayers() {
        return Bukkit.getMaxPlayers();
    }

    private static String getVersion() {
        return Bukkit.getBukkitVersion();
    }

    private static String getWorldName(Player p) {
        return p.getLocation().getWorld().getName();
    }

    private static String getGroup(Player p) {
        if (RealScoreboard.vault == 0) {
            return "Vault Not-Found";
        }
        String w = RealScoreboard.perms.getPrimaryGroup(p);
        if (w == null) {
            return "null";
        }
        return Text.addColor(w);
    }

    private static String sufix(Player p) {
        if (RealScoreboard.vault == 0) {
            return "Vault Not-Found";
        }
        String grupo = RealScoreboard.chat.getPrimaryGroup(p);
        String prefix = RealScoreboard.chat.getGroupPrefix(p.getWorld(), grupo);
        if (grupo == null) {
            return "null";
        }
        if (prefix == null) {
            return "null";
        }
        return Text.addColor(prefix);
    }

    private static String prefix(Player p) {
        if (RealScoreboard.vault == 0) {
            return "Vault Not-Found";
        }
        String grupo = RealScoreboard.chat.getPrimaryGroup(p);
        String suffix = RealScoreboard.chat.getGroupSuffix(p.getWorld(), grupo);
        if (grupo == null) {
            return "null";
        }
        if (suffix == null) {
            return "null";
        }
        return Text.addColor(suffix);
    }

    private static String money(Player p) {
        if (RealScoreboard.vault == 0) {
            return "0";
        }
        String kept = String.valueOf(RealScoreboard.Economia.getBalance(p));
        return kept.substring(0, kept.indexOf("."));
    }

    private static int stats(Player p, Statistic s) {
        return p.getStatistic(s);
    }

    private static String getKD(Player p) {
        double kills = p.getStatistic(Statistic.PLAYER_KILLS);
        double deaths = p.getStatistic(Statistic.DEATHS);
        String send = (kills / deaths + "");
        return send.substring(0, send.indexOf("."));
    }

    private static String placeholderAPI(Player p, String s) {
        if (RealScoreboard.placeholderapi == 0) {
            return s;
        }
        return PlaceholderAPI.setPlaceholders(p, s);
    }

    public static String setPlaceHolders(Player p, String s) {
        String placeholders = s.replaceAll("&", "§").replaceAll("%playername%", p.getName())
                .replace("%loc%", cords(p)).replace("%rainbow%", Text.getRainbow()).replace("%time%", time())
                .replace("%day%", day()).replace("%serverip%", serverIP()).replace("%version%", getVersion())
                .replace("%ping%", ping(p) + " ms").replace("%ram%", ram())
                .replace("%jumps%", "" + stats(p, Statistic.JUMP)).replace("%mobkills%", "" + stats(p, Statistic.MOB_KILLS))
                .replace("%world%", getWorldName(p)).replace("%port%", String.valueOf(port()))
                .replace("%maxplayers%", String.valueOf(maxPlayers()))
                .replace("%online%", String.valueOf(onlinePlayers()))
                .replace("%prefix%", prefix(p))
                .replace("%suffix%", sufix(p)).replace("%yaw%", String.valueOf(p.getLocation().getYaw()))
                .replace("%kills%", String.valueOf(stats(p, Statistic.PLAYER_KILLS))).replace("%deaths%", String.valueOf(stats(p, Statistic.DEATHS)))
                .replace("%kd%", getKD(p))
                .replace("%pitch%", String.valueOf(p.getLocation().getPitch())).replace("%group%", getGroup(p))
                .replace("%money%", money(p))
                .replace("%displayname%", p.getDisplayName());
        return placeholderAPI(p, placeholders);

    }
}
