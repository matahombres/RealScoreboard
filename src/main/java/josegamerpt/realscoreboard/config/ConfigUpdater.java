package josegamerpt.realscoreboard.config;

import josegamerpt.realscoreboard.RealScoreboard;

public class ConfigUpdater {

    public static int getConfigVersion() {
        if (Config.file().getInt("Version") == 0) {
            return 1;
        } else {
            return Config.file().getInt("Version");
        }
    }

    public static void updateConfig(int i) {
        switch (i) {
            case 1:
                //update to 2
                Config.file().set("Version", 2);
                Config.file().set("Config.Messages.Scoreboard-Toggle.ON", "&fScoreboard turned &aON&f.");
                Config.file().set("Config.Messages.Scoreboard-Toggle.OFF", "&fScoreboard turned &cOFF&f.");
                Config.file().set("Config.Animations.Scroll-Text.Size", 12);
                Config.file().set("Config.Animations.Scroll-Text.Space", 2);
                Config.save();
                RealScoreboard.log("Config file updated to version 2.");
                break;
            case 2:
                //update to 3
                //RealScoreboard.log("upd to 3");
                break;
        }
    }
}
