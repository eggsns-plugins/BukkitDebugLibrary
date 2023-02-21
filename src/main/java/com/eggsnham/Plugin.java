package com.eggsnham;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!new File(this.getDataFolder() + "/").exists())
        {
            new File(this.getDataFolder() + "/").mkdir();
        }

        DebugLogger logger = new DebugLogger(this);
        logger.logToConsole();

        this.getCommand("checklog").setExecutor(new CheckLogCommand());

        logger.log(DebugLevel.INFO, this.getName() + " has started!");

    }

    @Override
    public void onDisable() {
        DebugLogger logger = new DebugLogger(this);
        logger.logToConsole();

        logger.log(DebugLevel.WARNING, this.getName() + " is shutting down!");
        logger.log(DebugLevel.WARNING, "Some plugins may not work!");
    }
}
