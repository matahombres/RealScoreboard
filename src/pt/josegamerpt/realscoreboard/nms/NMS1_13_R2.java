package pt.josegamerpt.realscoreboard.nms;

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import pt.josegamerpt.realscoreboard.utils.Text;

public class NMS1_13_R2 implements NMS {
	public int pegarPing(Player p) {
		CraftPlayer playerping = (CraftPlayer) p;

		EntityPlayer pegarPingInterno = playerping.getHandle();

		return pegarPingInterno.ping;
	}

	public void enviarActionBar(Player p, String msg) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Text.addColor(msg) + "\"}");

		PacketPlayOutChat bar = new PacketPlayOutChat(icbc);

		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
}
