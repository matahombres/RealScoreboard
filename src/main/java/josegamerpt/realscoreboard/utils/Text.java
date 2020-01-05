package josegamerpt.realscoreboard.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

public class Text {
	private static int i;
	private static String texto;

	static {
		Text.i = 1;
		Text.texto = "";
	}

	public static String addColor(final String string) {
		final String s = ChatColor.translateAlternateColorCodes('&', string);
		return s;
	}

	public static String rainbow(final String original) {
		final char[] chars = {'c', '6', 'e', 'a', 'b', '3', 'd'};
		int index = 0;
		String returnValue = "";
		char[] charArray;
		for (int length = (charArray = original.toCharArray()).length, i = 0; i < length; ++i) {
			final char c = charArray[i];
			returnValue = String.valueOf(returnValue) + "&" + chars[index] + c;
			if (++index == chars.length) {
				index = 0;
			}
		}
		return ChatColor.translateAlternateColorCodes('&', returnValue);
	}

	public static void startAnimation() {
		final List<String> lista = Arrays.asList("&c", "&6", "&e", "&a", "&b", "&3", "&d");
		final int s = lista.size();
		try {
			if (Text.i >= s) {
				Text.i = 0;
			}
			Text.texto = lista.get(Text.i).replaceAll("&", "§");
			++Text.i;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getRainbow() {
		return Text.texto;
	}
}
