package josegamerpt.realscoreboard.managers;

import josegamerpt.realscoreboard.Data;
import josegamerpt.realscoreboard.RealScoreboard;
import josegamerpt.realscoreboard.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AnimationSheduler {
    public static void startAnimations() {
        IniciarTitulo();
        IniciarRainbow();
    }

    public static void IniciarTitulo() {
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Title.startAnimation(p);
                }
            }
        }.runTaskTimer(RealScoreboard.pl, 0L, (long) Data.getTimes(1));
    }

    public static void IniciarRainbow() {
        new BukkitRunnable() {
            public void run() {
                Text.startAnimation();
            }
        }.runTaskTimer(RealScoreboard.pl, 0L, (long) Data.getTimes(2));
    }
}
