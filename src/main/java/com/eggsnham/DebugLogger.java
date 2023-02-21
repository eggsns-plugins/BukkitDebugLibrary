package com.eggsnham;

import org.bukkit.plugin.Plugin;

import com.eggsnham.Annotations.PluginMethodDeprecated;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class DebugLogger {
    Plugin plugin;
    Boolean logToConsole = false;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String time = dtf.format(now);

    public DebugLogger(Plugin plugin) {
        this.plugin = plugin;
    }

    public void logToConsole()
    {
        this.logToConsole = true;
    }

    public void log(String msg)
    {
        msg = "[" + time + " INFO]: " + msg;
        try {
            FileOutputStream fos = new FileOutputStream(plugin.getDataFolder() + "/debug.log", true);
            fos.write(msg.getBytes());
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void log(DebugLevel level, String msg)
    {
        if(logToConsole) plugin.getLogger().log(Level.parse(level.toString()), msg);
        msg = "[" + time + " " + level.toString() + "]: " + msg;
        try {
            FileOutputStream fos = new FileOutputStream(plugin.getDataFolder() + "/debug.log", true);
            fos.write(msg.getBytes());
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @PluginMethodDeprecated(reason = "Method was moved to DebugLogger.log(DebugLevel level, String msg)")
    @Deprecated
    public void log(String msg, DebugLevel level)
    {
        plugin.getLogger().log(Level.SEVERE, "| Method log(String, DebugLevel) was used and is deprecated!");
        plugin.getLogger().log(Level.SEVERE, "| Please replace it with log(DebugLevel, String)            ");
        plugin.getLogger().log(Level.SEVERE, "|-----------------------------------------------------------");
    }
}
