package josegamerpt.realscoreboard.managers;

import josegamerpt.realscoreboard.player.SBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;

public class PlayerManager implements Listener {

    public static ArrayList<SBPlayer> players = new ArrayList<SBPlayer>();

    public static SBPlayer getPlayer(Player p) {
        for (SBPlayer sp : players) {
            if (sp.p == p) {
                return sp;
            }
        }
        return null;
    }

    public static void loadPlayer(Player p) {
        SBPlayer sb = new SBPlayer(p);
        players.add(sb);
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        loadPlayer(e.getPlayer());
    }


    @EventHandler
    public void leave(PlayerQuitEvent e) {
        PlayerManager.getPlayer(e.getPlayer()).stopScoreboard();
    }

    @EventHandler
    public void changeWorld(PlayerTeleportEvent e) {
        PlayerManager.getPlayer(e.getPlayer()).reset();
    }
}
