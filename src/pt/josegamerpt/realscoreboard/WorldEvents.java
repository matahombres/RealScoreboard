package pt.josegamerpt.realscoreboard;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class WorldEvents implements Listener {
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e) {
		List<String> lista = Configuration.ficheiro().getStringList("Config.Disabled-Worlds");
		for (String w : lista) {
			if (e.getPlayer().getLocation().getWorld().getName() != w) {
				Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

				Objective o = board.registerNewObjective("Scoreboard", "dummy");

				o.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6"));
				o.setDisplaySlot(DisplaySlot.SIDEBAR);

				e.getPlayer().setScoreboard(board);
			}
		}
	}
}
