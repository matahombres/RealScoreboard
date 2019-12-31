package pt.josegamerpt.realscoreboard.nms;

import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import pt.josegamerpt.realscoreboard.utils.Text;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMS1_11_R1 implements NMS {
	
	public int pegarPing(Player p) {
		CraftPlayer playerping = (CraftPlayer) p;

		EntityPlayer pegarPingInterno = playerping.getHandle();

		return pegarPingInterno.ping;
	}

	public void enviarActionBar(Player p, String msg) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Text.addColor(msg) + "\"}");

		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
}
