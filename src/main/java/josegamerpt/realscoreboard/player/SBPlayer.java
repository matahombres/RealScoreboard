package josegamerpt.realscoreboard.player;


import josegamerpt.realscoreboard.Enum;
import josegamerpt.realscoreboard.RealScoreboard;
import josegamerpt.realscoreboard.config.Config;
import josegamerpt.realscoreboard.config.Data;
import josegamerpt.realscoreboard.managers.TitleManager;
import josegamerpt.realscoreboard.scoreboard.PlayerScoreboard;
import josegamerpt.realscoreboard.scoreboard.animate.ScrollableString;
import josegamerpt.realscoreboard.utils.Placeholders;
import josegamerpt.realscoreboard.utils.Text;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;
import java.util.List;

public class SBPlayer {

    public Player p;
    public PlayerScoreboard scoreboard;
    private HashMap<Integer, ScrollableString> hiPos = new HashMap<>();
    public BukkitTask br;
    public Boolean toggle = false;

    public SBPlayer(Player p) {
        this.p = p;
        startScoreboard();
    }

    public void stopScoreboard() {
        br.cancel();
        scoreboard = null;
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }

    public void startScoreboard() {
        scoreboard = new PlayerScoreboard("RealScoreboard");

        br = new BukkitRunnable() {
            public void run() {
                if (!toggle) {
                    if (Config.file().getStringList("Config.Disabled-Worlds").contains(p.getWorld().getName())) {
                        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                        return;
                    }

                    List<String> lista = Config.file()
                            .getStringList("Config.Scoreboard." + Data.getCorrectPlace(p) + ".Lines");

                    int linha = lista.size();
                    for (String it : lista) {
                        String place = Placeholders.setPlaceHolders(p, it);

                        if (it.equalsIgnoreCase("%blank%")) {
                            scoreboard.add(Text.randomColor() + "§r" + Text.randomColor(), linha);
                        } else if (it.contains("#")) {
                            String before = StringUtils.substringBefore(place, "#");
                            String placeholder = StringUtils.substringBetween(place, "#", "#");
                            //String after = place.substring(place.lastIndexOf("#") + 1);

                            if (!hiPos.containsKey(linha)) {
                                hiPos.put(linha, new ScrollableString(placeholder, Data.getInt(Enum.DataInt.SCROLLTEXT_SIZE), Data.getInt(Enum.DataInt.SCROLLTEXT_SPACE)));
                            }

                            scoreboard.add(before + hiPos.get(linha).next(), linha);
                        } else {
                            scoreboard.add(place, linha);
                        }
                        linha--;
                    }

                    scoreboard.setTitle(TitleManager.getTitleAnimation(p));
                    scoreboard.update();
                    scoreboard.send(p);
                } else {
                    p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                }
            }
        }.runTaskTimer(RealScoreboard.pl, 0L, Data.getInt(Enum.DataInt.ANIMATIONS_REFRESH_DELAY));

    }

    public void toggleScoreboard() {
        if (this.toggle) {
            this.toggle = false;
            startScoreboard();
            p.sendMessage(Text.addColor(RealScoreboard.getPrefix() + " " + Config.file().getString("Config.Messages.Scoreboard-Toggle.ON")));
        } else {
            this.toggle = true;
            stopScoreboard();
            p.sendMessage(Text.addColor(RealScoreboard.getPrefix() + " " + Config.file().getString("Config.Messages.Scoreboard-Toggle.OFF")));
        }
    }

    public void reset() {
        stopScoreboard();
        hiPos.clear();
        startScoreboard();
    }
}