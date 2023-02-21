package com.eggsnham;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.charset.Charset;

public class CheckLogCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0)
        {
            sender.sendMessage(ChatColor.RED + "Command requires 1+ arguments");
            return true;
        }

        if(Bukkit.getServer().getPluginManager().getPlugin(args[0]) != null)
        {
            Plugin rp = Bukkit.getServer().getPluginManager().getPlugin(args[0]);
            File f = new File(rp.getDataFolder() + "/debug.log");
            if(f.exists())
            {
                try {
                    char[] c = new char[2048];
                    FileReader reader = new FileReader(f, Charset.forName("UTF8"));
                    reader.read(c);
                    sender.sendMessage(new String(c));
                    reader.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return true;
    }
}
