package josegamerpt.realscoreboard;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import josegamerpt.realscoreboard.config.Config;
import josegamerpt.realscoreboard.managers.AnimationManager;
import josegamerpt.realscoreboard.managers.PlayerManager;
import josegamerpt.realscoreboard.nms.*;
import josegamerpt.realscoreboard.utils.TPS;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class RealScoreboard extends JavaPlugin {
    public static NMS nms;
    public static Permission perms = null;
    public static Economy Economia = null;
    public static Chat chat = null;
    public static int abcompatible = 0;
    public static int vault = 0;
    public static int placeholderapi = 0;
    public static Logger log = Bukkit.getLogger();
    public static Plugin pl;
    static String prefix = "§bReal§9Scoreboard §7| §r";
    PluginDescriptionFile desc = getDescription();
    PluginManager pm = Bukkit.getPluginManager();
    String name = "[" + this.desc.getName() + "] ";

    public static String getPrefix() {
        return prefix;
    }

    public void onEnable() {

        try {
            pl = this;
            log.info(name + "Checking the server version.");
            if (setupNMS()) {
                if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
                    vault = 1;
                    setupEconomy();
                    setupPermissions();
                    setupChat();
                }
                if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                    placeholderapi = 1;
                }

                log.info(name + "Setting up the configuration.");
                saveDefaultConfig();
                Config.setup(this);

                log.info(name + "Registering Events.");
                pm.registerEvents(new PlayerManager(), this);

                log.info(name + "Registering Commands.");
                getCommand("realscoreboard").setExecutor(new RSBcmd());

                log.info(name + "Starting up the Scoreboard.");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    PlayerManager.loadPlayer(p);
                }
                AnimationManager.startAnimations();

                Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);

                status(Arrays.asList("RealScoreboard loaded sucessfully.", "", "Server version: " + pegarVersao(),
                        "Configuration: OK", "Events: OK", "Commands: OK", "Scoreboard: OK"));
            } else {
                status(Arrays.asList("Failed to initialize RealScoreboard.",
                        "Your server version (" + pegarVersao() + ") is not compatible with RealScoreboard.",
                        "If you think this is a bug, please contact JoseGamer_PT.", "https://www.spigotmc.org/members/josegamer_pt.40267/"));

                HandlerList.unregisterAll();

                Bukkit.getPluginManager().disablePlugin(this);
            }
        } catch (Exception ex) {
            status(Arrays.asList("A fatal error has ocourred.",
                    "Delete your config.yml. If that doesn't help, please contact JoseGamer_PT (https://www.spigotmc.org/members/josegamer_pt.40267/)", "", ex.toString()));
            ex.printStackTrace();
        }

    }

    private boolean setupNMS() {
        String versao;
        String compatible = name + "Your server is compatible with RealScoreboard.";

        try {
            versao = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }
        log.info(name + "Your server version is: " + versao);
        if (versao.equals("v1_13_R2")) {
            log.info(compatible);
            nms = new NMS1_13_R2();
        } else if (versao.equals("v1_9_R1")) {
            log.info(compatible);
            nms = new NMS1_9_R1();
        } else if (versao.equals("v1_11_R1")) {
            log.info(compatible);
            nms = new NMS1_11_R1();
        } else if (versao.equals("v1_11_R2")) {
            log.info(compatible);
            nms = new NMS1_11_R1();
        } else if (versao.equals("v1_12_R2")) {
            log.info(compatible);
            nms = new NMS1_12_R1();
        } else if (versao.equals("v1_12_R1")) {
            log.info(compatible);
            nms = new NMS1_12_R1();
        } else if (versao.equals("v1_9_R1")) {
            log.info(compatible);
            nms = new NMS1_9_R1();
        } else if (versao.equals("v1_13_R1")) {
            log.info(compatible);
            nms = new NMS1_13_R1();
        } else if (versao.equals("v1_13_R2")) {
            log.info(compatible);
            nms = new NMS1_13_R2();
        } else if (versao.equals("v1_15_R1")) {
            log.info(compatible);
            nms = new NMS1_15_R1();
        } else if (versao.equals("v1_8_R3")) {
            log.info(compatible);
            nms = new NMS1_8_R3();
        } else if (versao.equals("v1_8_R1")) {
            log.info(compatible);
            nms = new NMS1_8_R1();
        } else if (versao.equals("v1_10_R1")) {
            log.info(compatible);
            nms = new NMS1_10_R1();
        } else if (versao.equals("v1_14_R1")) {
            log.info(compatible);
            nms = new NMS1_14_R1();
        } else if (versao.equals("v1_14_R4")) {
            log.info(compatible);
            nms = new NMS1_14_R1();
        }


        return nms != null;
    }

    protected void status(List<String> l) {
        log.info("----------------------------------");
        log.info("");
        log.info(this.desc.getName());
        log.info("Version: " + this.desc.getVersion());
        log.info("");
        for (String s : l) {
            log.info(s);
        }
        log.info("");
        log.info("----------------------------------");
    }

    protected String pegarVersao() {
        String versao = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        return versao;
    }

    // Vault
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        Economia = (Economy) rsp.getProvider();
        return Economia != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager()
                .getRegistration(Permission.class);
        if (permissionProvider != null) {
            perms = (Permission) permissionProvider.getProvider();
        }
        return perms != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = (Chat) chatProvider.getProvider();
        }
        return chat != null;
    }
}