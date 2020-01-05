package josegamerpt.realscoreboard.managers;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import josegamerpt.realscoreboard.Configuration;
import josegamerpt.realscoreboard.Data;
import josegamerpt.realscoreboard.utils.Placeholders;

public class Title {

	public static Logger log = Bukkit.getLogger();
	public static HashMap<String, String> texto = new HashMap<String, String>();
	private static int i;

	static {
		Title.i = 0;
	}

	public static void startAnimation(Player p) {
		String go = "Config.Scoreboard." + Data.getCorrectPlace(p) + ".Title";
		try {
			if (Title.i >= Configuration.ficheiro().getStringList(go).size()) {
				Title.i = 0;
			}
			texto.put(p.getName(), Configuration.ficheiro().getStringList(go).get(Title.i).replaceAll("&", "§"));
			++Title.i;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getTitleAnimation(final Player p) {

		return Placeholders.setPlaceHolders(p, Title.texto.get(p.getName()));
	}
}
