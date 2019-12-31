package pt.josegamerpt.realscoreboard.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import pt.josegamerpt.realscoreboard.Configuration;
import pt.josegamerpt.realscoreboard.Data;
import pt.josegamerpt.realscoreboard.RealScoreboard;
import pt.josegamerpt.realscoreboard.utils.Placeholders;

public class Scoreboard {
	
	public static void update() {
		new BukkitRunnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.hasMetadata("toggle") == false) {
						final Map<String, Integer> linhas = new HashMap<String, Integer>();

						List<String> lista = Configuration.ficheiro()
								.getStringList("Config.Scoreboard." + Data.getCorrectPlace(p) + ".Lines");

						int linha = lista.size();
						for (final String s : lista) {
							final String placeholders = Placeholders.setPlaceHolders(p, s);
							linhas.put(placeholders, linha--);
						}
						for (final String s : Data.getDataList("Config.Disabled-Worlds")) {
							if (p.getLocation().getWorld().getName().equals(s)) {
								p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
								final org.bukkit.scoreboard.Scoreboard board = Bukkit.getServer().getScoreboardManager()
										.getNewScoreboard();
								final Objective o = board.registerNewObjective("Scoreboard", "dummy");
								o.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6"));
								o.setDisplaySlot(DisplaySlot.SIDEBAR);
								p.setScoreboard(board);
								return;
							}
							displayScoreboard(p, Title.getTitleAnimation(p), linhas);
						}
					} else {
						org.bukkit.scoreboard.Scoreboard sd = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

						Objective o = sd.registerNewObjective("c", "a");

						o.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6"));
						o.setDisplaySlot(DisplaySlot.SIDEBAR);

						p.setScoreboard(sd);
					}
				}
			}
		}.runTaskTimer(RealScoreboard.pl, 0L, (long) Data.getTimes(3));
	}

	private static void displayScoreboard(final Player p, String title, final Map<String, Integer> elements) {
		if (title == null) {
			title = "Erro no Titulo";
		}
		if (title.length() > 32) {
			title = title.substring(0, 32);
		}
		while (elements.size() > 15) {
			String minimumKey = (String) elements.keySet().toArray()[0];
			int minimum = elements.get(minimumKey);
			for (final String string : elements.keySet()) {
				if (elements.get(string) < minimum
						|| (elements.get(string) == minimum && string.compareTo(minimumKey) < 0)) {
					minimumKey = string;
					minimum = elements.get(string);
				}
			}
			elements.remove(minimumKey);
		}
		for (final String string2 : new ArrayList<String>(elements.keySet())) {
			if (string2 != null && string2.length() > 40) {
				final int value = elements.get(string2);
				elements.remove(string2);
				elements.put(string2.substring(0, 40), value);
			}
		}
		if (p.getScoreboard() == null
				|| p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)) == null) {
			p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			p.getScoreboard().registerNewObjective(p.getUniqueId().toString().substring(0, 16), "dummy");
			p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16))
					.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(title);
		for (final String string2 : elements.keySet()) {
			if (p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(string2).getScore() != elements
					.get(string2)) {
				p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(string2)
						.setScore((int) elements.get(string2));
			}
		}
		for (final String string2 : new ArrayList<String>(p.getScoreboard().getEntries())) {
			if (!elements.keySet().contains(string2)) {
				p.getScoreboard().resetScores(string2);
			}
		}
	}
}
