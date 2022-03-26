package money.moneyplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MoneyPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("[Edeep MoneyPlugin] SET MONEY!");
        System.out.println("[Edeep MoneyPlugin] SAVE YOUR MONEY!");
        System.out.println("[Edeep MoneyPlugin] 돈 설정 완료!");
        System.out.println("[Edeep MoneyPlugin] 유저의 돈 저장 완료!");
        System.out.println("Made By ㄷㅇㄷ데#9708");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("[Edeep MoneyPlugin] SAVE YOUR MONEY!");
        System.out.println("[Edeep MoneyPlugin] 돈 저장 완료!");
        System.out.println("Made By ㄷㅇㄷ데#9708");
        saveConfig();
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String playername = p.getName();
        if (!p.hasPlayedBefore()) {
            this.getConfig().set(playername + ".money", 0);
            saveConfig();
        }
        int don = this.getConfig().getInt(playername + ".money");
        String won = Integer.toString(don);
        p.sendMessage(won);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("돈")) {
            Player p = (Player) sender;
            String playername = p.getName();
            int mi = this.getConfig().getInt(playername + ".money");
            String money = Integer.toString(mi);
            if (args.length == 0) {
                p.sendMessage("/돈 [확인|설정|송금] [-대상] [-금액]");
            }
            if (args.length == 1 || args.length == 2) {
                if (args[0].equalsIgnoreCase("확인")) {
                    if (args.length == 2) {
                        int tmi = this.getConfig().getInt(args[1] + ".money");
                        String tmoney = Integer.toString(tmi);
                        p.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.WHITE + "님의 돈 : " + ChatColor.GREEN + tmoney);
                    } else if (args.length == 1) {
                        p.sendMessage(ChatColor.YELLOW + playername + ChatColor.WHITE + "님의 돈 : " + ChatColor.GREEN + money);
                    }
                }
            }
            if (args.length == 1 || args.length == 2 || args.length == 3) {
                if (p.isOp()) {
                    if (args[0].equalsIgnoreCase("설정")) {
                        if (args.length == 1) {
                            p.sendMessage("/돈 [확인|설정|송금] [-대상] [-금액]");
                        }
                        else if (args.length == 2) {
                            p.sendMessage("/돈 [확인|설정|송금] [-대상] [-금액]");
                        }
                        else if (args.length == 3) {
                            int don = Integer.valueOf(args[2]);
                            this.getConfig().set(playername + ".money", don);
                            saveConfig();
                            p.sendMessage(ChatColor.GREEN + "돈 설정 완료!");
                        }
                    }
                } else {
                    p.sendMessage(ChatColor.DARK_RED + "당신은 관리자가 아닙니다.");
                }

            }
            if (args.length == 1 || args.length == 2 || args.length == 3) {
                if (args[0].equalsIgnoreCase("송금")) {
                    if (args.length == 1) {
                        p.sendMessage("/돈 [확인|설정|송금] [-대상] [-금액]");
                    }
                    else if (args.length == 2) {
                        p.sendMessage("/돈 [확인|설정|송금] [-대상] [-금액]");
                    }
                    else if (args.length == 3) {
                        int frommoney = this.getConfig().getInt(args[1] + ".money");
                        int mymoney = this.getConfig().getInt(playername + ".money");
                        int don = Integer.parseInt(args[2]);
                        if (don > mymoney) {
                            p.sendMessage("돈이 부족합니다.");
                        } else if (don <= mymoney) {
                            this.getConfig().set(args[1] + ".money", frommoney + don);
                            this.getConfig().set(playername + ".money", mymoney - don);
                            this.saveConfig();
                            p.sendMessage(ChatColor.YELLOW + playername + ChatColor.WHITE + "님이 " + ChatColor.YELLOW + args[1] + ChatColor.WHITE + "님 에게 " + ChatColor.GREEN + don + ChatColor.WHITE + "원을 뜯겼습니다.");
                        }
                    }
                }
            }
        }
        return true;
    }
}
