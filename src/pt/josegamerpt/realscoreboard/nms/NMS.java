package pt.josegamerpt.realscoreboard.nms;

import org.bukkit.entity.Player;

public abstract interface NMS {
	public abstract int pegarPing(Player paramPlayer);

	public abstract void enviarActionBar(Player paramPlayer, String paramString);
}
