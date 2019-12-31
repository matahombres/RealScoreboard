package pt.josegamerpt.realscoreboard.nms;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import pt.josegamerpt.realscoreboard.utils.Text;

public class NMS1_8_R1 implements NMS {
	public int pegarPing(Player p) {
		CraftPlayer playerping = (CraftPlayer) p;

		EntityPlayer pegarPingInterno = playerping.getHandle();

		return pegarPingInterno.ping;
	}

	public void enviarActionBar(Player p, String msg) {
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + Text.addColor(msg) + "\"}");

		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
}