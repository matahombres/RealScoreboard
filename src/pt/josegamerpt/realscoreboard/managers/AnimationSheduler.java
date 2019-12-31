package pt.josegamerpt.realscoreboard.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pt.josegamerpt.realscoreboard.Data;
import pt.josegamerpt.realscoreboard.RealScoreboard;
import pt.josegamerpt.realscoreboard.utils.Text;

public class AnimationSheduler
{
    public static void startAnimations() {
        IniciarTitulo();
        IniciarRainbow();
    }
    
    public static void IniciarTitulo() {
        new BukkitRunnable() {
            public void run() {
            	for (Player p : Bukkit.getOnlinePlayers())
            	{
                    Title.startAnimation(p);
            	}
            }
        }.runTaskTimer(RealScoreboard.pl, 0L, (long)Data.getTimes(1));
    }
    
    public static void IniciarRainbow() {
        new BukkitRunnable() {
            public void run() {
                Text.startAnimation();
            }
        }.runTaskTimer(RealScoreboard.pl, 0L, (long)Data.getTimes(2));
    }
}
