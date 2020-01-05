package josegamerpt.realscoreboard.utils;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import josegamerpt.realscoreboard.RealScoreboard;
import josegamerpt.realscoreboard.utils.TPS;

public class Placeholders {
	public static String stringerror = "Error.";
	public static int interror = 404;
	public static double doublerror = 404.0D;

	private static int ping(Player player) {
		try {

			return RealScoreboard.nms.pegarPing(player);
		} catch (Exception e) {
		}
		return 1;
	}

	private static String ram() {
		try {
			Runtime re = Runtime.getRuntime();
			int mbnumero = 1048576;
			return (re.totalMemory() - re.freeMemory()) / mbnumero + "MB";
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static int port() {
		try {
			return Bukkit.getPort();
		} catch (Exception e) {
		}
		return interror;
	}

	private static String serverIP() {
		try {
			String s = Bukkit.getIp();
			if (s == null) {
				return "null";
			}
			return s;
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String time() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			String data = String.valueOf(dateFormat.format(date));
			if (data == null) {
				return "null";
			}
			return data;
		} catch (Exception e) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			String data = String.valueOf(dateFormat.format(date));
			return data;
		}
	}

	private static String day() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String data = String.valueOf(dateFormat.format(date));
			if (data == null) {
				return "null";
			}
			return data;
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String cords(Player player) {
		try {
			return "X: " + player.getLocation().getBlockX() + " Y: " + player.getLocation().getBlockY() + " Z: "
					+ player.getLocation().getBlockZ();
		} catch (Exception e) {
		}
		return "Error.";
	}

	private static int onlinePlayers() {
		try {
			return Bukkit.getOnlinePlayers().size();
		} catch (Exception e) {
		}
		return interror;
	}

	private static int maxPlayers() {
		try {
			return Bukkit.getMaxPlayers();
		} catch (Exception e) {
		}
		return interror;
	}

	private static String getIP(Player p) {
		try {
			InetSocketAddress IPAdressPlayer = p.getAddress();
			String sfullip = IPAdressPlayer.toString();

			String[] fullip = sfullip.split("/");
			String sIpandPort = fullip[1];
			String[] ipandport = sIpandPort.split(":");
			String ip = ipandport[0];
			if (ip == null) {
				return "null";
			}
			return ip;
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String getVersion() {
		try {
			String s = Bukkit.getBukkitVersion();
			if (s == null) {
				return "null";
			}
			return s;
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String getWorldName(Player p) {
		try {
			String w = p.getLocation().getWorld().getName();
			if (w == null) {
				return "null";
			}
			return w;
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String getGroup(Player p) {
		try {
			if (RealScoreboard.vault == 0) {
				return "Vault Not-Found";
			}
			try {
				String w = RealScoreboard.perms.getPrimaryGroup(p);
				if (w == null) {
					return "null";
				}
				return Text.addColor(w);
			} catch (Exception e) {
				return "Vault Not-Found";
			}
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String sufix(Player p) {
		try {
			if (RealScoreboard.vault == 0) {
				return "Vault Not-Found";
			}
			try {
				String grupo = RealScoreboard.chat.getPrimaryGroup(p);
				String prefix = RealScoreboard.chat.getGroupPrefix(p.getWorld(), grupo);
				if (grupo == null) {
					return "null";
				}
				if (prefix == null) {
					return "null";
				}
				return Text.addColor(prefix);
			} catch (Exception e) {
				return "Vault Not-Found";
			}
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String prefix(Player p) {
		try {
			if (RealScoreboard.vault == 0) {
				return "Vault Not-Found";
			}
			try {
				String grupo = RealScoreboard.chat.getPrimaryGroup(p);
				String suffix = RealScoreboard.chat.getGroupSuffix(p.getWorld(), grupo);
				if (grupo == null) {
					return "null";
				}
				if (suffix == null) {
					return "null";
				}
				return Text.addColor(suffix);
			} catch (Exception e) {
				return "Vault Not-Found";
			}
		} catch (Exception e) {
		}
		return stringerror;
	}

	private static String money(Player p) {
		try {
			if (RealScoreboard.vault == 0) {
				return "0";
			}
			String kept = String.valueOf(RealScoreboard.Economia.getBalance(p));
			return kept.substring(0, kept.indexOf("."));
		} catch (Exception e) {
		}
		return "0";
	}

	private static int stats(Player p, int i) {
		try {
			if (i == 0) {
				return p.getStatistic(Statistic.PLAYER_KILLS);
			} else if (i == 1) {
				return p.getStatistic(Statistic.DEATHS);
			} else if (i == 2) {
				return p.getStatistic(Statistic.JUMP);
			} else if (i == 3) {
				return p.getStatistic(Statistic.MOB_KILLS);
			}
		} catch (Exception e) {
		}
		return 0;
	}

	private static double getKD(Player p) {
		try {
			double kills = p.getStatistic(Statistic.PLAYER_KILLS);
			double deaths = p.getStatistic(Statistic.DEATHS);
			return kills / deaths;
		} catch (Exception e) {
		}
		return 0.0D;
	}

	private static String placeholderAPI(Player p, String s) {
		try {
			if (RealScoreboard.placeholderapi == 0) {
				return s;
			}
			String s1 = PlaceholderAPI.setPlaceholders(p, s);
			if (s1 == null) {
				return "null";
			}
			return s1;
		} catch (Exception e) {
		}
		return stringerror;
	}

	public static String setPlaceHolders(Player p, String s) {
		try {
			String placeholders = s.replaceAll("&", "§").replaceAll("%playername%", p.getName())
					.replace("%loc%", cords(p)).replace("%rainbow%", Text.getRainbow()).replace("%time%", time())
					.replace("%day%", day()).replace("%serverip%", serverIP()).replace("%version%", getVersion())
					.replace("%ping%", String.valueOf(ping(p) + " ms")).replace("%ram%", ram())
					.replace("%jumps%", "" + stats(p, 2)).replace("%mobkills%", "" + stats(p, 3))
					.replace("%world%", getWorldName(p)).replace("%port%", String.valueOf(port()))
					.replace("%maxplayers%", String.valueOf(maxPlayers()))
					.replace("%online%", String.valueOf(onlinePlayers()))
					.replace("%playerip%", String.valueOf(getIP(p))).replace("%prefix%", prefix(p))
					.replace("%suffix%", sufix(p)).replace("%yaw%", String.valueOf(p.getLocation().getYaw()))
					.replace("%kills%", String.valueOf(stats(p, 0))).replace("%deaths%", String.valueOf(stats(p, 1)))
					.replace("%kd%", String.valueOf(getKD(p)))
					.replace("%pitch%", String.valueOf(p.getLocation().getPitch())).replace("%group%", getGroup(p))
					.replace("%tps%", TPS.pegarTps()).replace("%money%", String.valueOf(money(p)))
					.replace("%displayname%", p.getDisplayName());
			return placeholderAPI(p, placeholders);
		} catch (Exception e) {
		}
		return stringerror;
	}
}
