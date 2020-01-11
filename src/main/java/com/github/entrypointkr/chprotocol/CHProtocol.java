package com.github.entrypointkr.chprotocol;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

import java.util.logging.Level;

@MSExtension("CHProtocol")
public final class CHProtocol extends AbstractExtension {
    private final PacketProcessor processor = new PacketProcessor(CommandHelperPlugin.self);

    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 0, 3);
    }

    @Override
    public void onStartup() {
        Validate.isTrue(Bukkit.getPluginManager().isPluginEnabled("ProtocolLib"), "ProtocolLib not found.");
        processor.register();
        Bukkit.getLogger().log(Level.INFO, "Enable CHProtocol " + getVersion());
    }

    @Override
    public void onShutdown() {
        processor.unregister();
        Bukkit.getLogger().log(Level.INFO, "Disable CHProtocol " + getVersion());
    }
}
