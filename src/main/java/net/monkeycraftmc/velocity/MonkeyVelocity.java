package net.monkeycraftmc.velocity;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import com.google.inject.Inject;

import net.monkeycraftmc.velocity.command.DiscordCommand;
import net.monkeycraftmc.velocity.command.ReloadCommand;
import net.monkeycraftmc.velocity.util.Configuration;

import org.slf4j.Logger;

import java.io.File;

@Plugin(id = "monkeyvelocity", version = "1.0", name = "MonkeyCore-Velocity")
public class MonkeyVelocity{

    private final Logger logger;
    private final ProxyServer proxy;
    private Configuration config;

    @Inject
    public MonkeyVelocity(ProxyServer p, Logger log){
        this.logger = log;
        this.proxy = p;
    }

    @Subscribe
    public void ProxyStart(ProxyInitializeEvent e){
        logger.info("Starting MonkeyVelocity...");
        Configuration config = new Configuration(this.getDataFolder(), "config.toml");
        if(!config.exists()) config.saveFile();
        config.loadFile();
        this.config = config;

        CommandManager manager = proxy.getCommandManager();
        manager.register("discord", new DiscordCommand(this.config), "dc");
        manager.register("monkeyreload", new ReloadCommand(this));
        logger.info("Plugin started.");
    }

    //Dizzy getDataFolder implementation, but it's a simple Discord command plugin
    public File getDataFolder(){
        File dataFolder = new File("plugins/monkeyvelocity");
        if(!dataFolder.exists()) dataFolder.mkdirs();
        return dataFolder;
    }

    public void reload(){
        this.config.loadFile();
    }

}