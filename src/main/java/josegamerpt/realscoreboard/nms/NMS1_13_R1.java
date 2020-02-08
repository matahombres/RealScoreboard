package josegamerpt.realscoreboard.nms;


import josegamerpt.realscoreboard.utils.Text;
import net.minecraft.server.v1_13_R1.EntityPlayer;
import net.minecraft.server.v1_13_R1.IChatBaseComponent;
import net.minecraft.server.v1_13_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMS1_13_R1 implements NMS {
    public int getPing(Player p) {
        CraftPlayer playerping = (CraftPlayer) p;

        EntityPlayer pegarPingInterno = playerping.getHandle();

        return pegarPingInterno.ping;
    }
}
