package josegamerpt.realscoreboard.player;

import josegamerpt.realscoreboard.Enum;
import josegamerpt.realscoreboard.RealScoreboard;
import josegamerpt.realscoreboard.config.Config;
import josegamerpt.realscoreboard.config.Data;
import josegamerpt.realscoreboard.managers.TitleManager;
import josegamerpt.realscoreboard.utils.Placeholders;
import josegamerpt.realscoreboard.utils.Text;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SBPlayer {

    public Player p;
    public PlayerScoreboard scoreboard;
    public BukkitTask br;
    public Boolean toggle = false;

    public SBPlayer(Player p) {
        this.p = p;
        refresh();
    }

    public void stopScoreboard() {
        br.cancel();
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }

    public void refresh() {
        scoreboard = new PlayerScoreboard("RealScoreboard");

        br = new BukkitRunnable() {
            public void run() {
                if (toggle == false) {
                    if (Config.file().getStringList("Config.Disabled-Worlds").contains(p.getWorld().getName())) {
                        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                        return;
                    }

                    Map<String, Integer> linhas = new HashMap<String, Integer>();

                    List<String> lista = Config.file()
                            .getStringList("Config.Scoreboard." + Data.getCorrectPlace(p) + ".Lines");

                    int linha = lista.size();
                    for (String it : lista) {
                        String placeholders = Placeholders.setPlaceHolders(p, it);
                        scoreboard.add(placeholders, linha--);
                    }
                    scoreboard.setTitle(TitleManager.getTitleAnimation(p));
                    scoreboard.update();

                    scoreboard.send(p);

                } else {
                    p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                }
            }
        }.runTaskTimer(RealScoreboard.pl, 0L, (long) Data.getInt(Enum.DataInt.ANIMATIONS_REFRESH_DELAY));

    }

    public void toggleScoreboard() {
        if (this.toggle == true) {
            this.toggle = false;
            p.sendMessage(Text.addColor(RealScoreboard.getPrefix() + "&fScoreboard turned &aon."));
        } else {
            this.toggle = true;
            refresh();
            p.sendMessage(Text.addColor(RealScoreboard.getPrefix() + "&fScoreboard turned &coff."));
        }
    }

    public void reset() {
        stopScoreboard();
        refresh();
    }
}
