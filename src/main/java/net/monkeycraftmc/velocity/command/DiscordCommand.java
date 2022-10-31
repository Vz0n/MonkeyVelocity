package net.monkeycraftmc.velocity.command;

import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.monkeycraftmc.velocity.util.Configuration;

//Why I don't use Brigadier? because it's a simple Discord command XD
public class DiscordCommand implements SimpleCommand {

    private final Configuration config;

    public DiscordCommand(Configuration configuration){
        this.config = configuration;
    }

    @Override
    public void execute(Invocation invocation) {
        MiniMessage formatter = MiniMessage.miniMessage();
        CommandSource source = invocation.source();
        Toml values = config.getValues();

        Component kyoriMessage = formatter.deserialize(values.getString("discord-command-message",
                "Please put a message on the config. (MonkeyVelocity)"));

        source.sendMessage(kyoriMessage);
    }

}
