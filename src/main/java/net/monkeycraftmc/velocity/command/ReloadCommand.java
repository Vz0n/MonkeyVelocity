package net.monkeycraftmc.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.monkeycraftmc.velocity.MonkeyVelocity;

public class ReloadCommand implements SimpleCommand {

    private final MonkeyVelocity plugin;

    public ReloadCommand(MonkeyVelocity instance){
        this.plugin = instance;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();

        plugin.reload();

        source.sendMessage(Component.text("Reload completed."));
    }
}
