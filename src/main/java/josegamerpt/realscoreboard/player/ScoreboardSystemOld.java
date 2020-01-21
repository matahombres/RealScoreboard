package josegamerpt.realscoreboard.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import josegamerpt.realscoreboard.Enum;
import josegamerpt.realscoreboard.managers.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import josegamerpt.realscoreboard.config.Config;
import josegamerpt.realscoreboard.config.Data;
import josegamerpt.realscoreboard.RealScoreboard;
import josegamerpt.realscoreboard.utils.Placeholders;

public class ScoreboardSystemOld {

	public SBPlayer sb;
	public BukkitTask br;

	public ScoreboardSystemOld(SBPlayer sb) {
		this.sb = sb;
	}

	private static void displayScoreboard(final Player p, String title, final Map<String, Integer> elements) {
		if (title == null) {
			title = "Title error";
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
		for (String string2 : new ArrayList<String>(elements.keySet())) {
			if (string2 != null && string2.length() > 40) {
				final int value = elements.get(string2);
				elements.remove(string2);
				elements.put(string2.substring(0, 40), value);
			}
		}
		if (p.getScoreboard() == null
				|| p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16)) == null) {
			p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
			p.getScoreboard().registerNewObjective(p.getUniqueId().toString().substring(0, 16), "dummy");
			p.getScoreboard().getObjective(p.getUniqueId().toString().substring(0, 16))
					.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(title);
		for (String string2 : elements.keySet()) {
			if (p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(string2).getScore() != elements
					.get(string2)) {
				p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(string2)
						.setScore((int) elements.get(string2));
			}
		}
		for (String string2 : new ArrayList<String>(p.getScoreboard().getEntries())) {
			if (!elements.keySet().contains(string2)) {
				p.getScoreboard().resetScores(string2);
			}
		}
	}

	public void update() {
		br = new BukkitRunnable() {
			public void run() {
				if (sb.toggle == false) {
					if (Config.file().getStringList("Config.Disabled-Worlds").contains(sb.p.getWorld().getName())) {
						sb.p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
						return;
					}

					Objective o = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(sb.p.getUniqueId().toString().substring(0, 16));
					if (o != null) {
						o.unregister();
					}

					Map<String, Integer> linhas = new HashMap<String, Integer>();

					List<String> lista = Config.file()
							.getStringList("Config.Scoreboard." + Data.getCorrectPlace(sb.p) + ".Lines");

					int linha = lista.size();
					for (String it : lista) {
						String placeholders = Placeholders.setPlaceHolders(sb.p, it);
						linhas.put(placeholders, linha--);
					}
					displayScoreboard(sb.p, TitleManager.getTitleAnimation(sb.p), linhas);
				} else {
					sb.p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				}
			}
		}.runTaskTimer(RealScoreboard.pl, 0L, (long) Data.getInt(Enum.DataInt.ANIMATIONS_REFRESH_DELAY));
	}

	public void stop() {
		br.cancel();
	}
}