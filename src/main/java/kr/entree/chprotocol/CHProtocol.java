package kr.entree.chprotocol;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import kr.entree.chprotocol.protocollib.PacketEventNotifier;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@MSExtension("CHProtocol")
public class CHProtocol extends AbstractExtension {
    private final PacketEventNotifier processor = new PacketEventNotifier(CommandHelperPlugin.self);

    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 0, 3);
    }

    @Override
    public void onStartup() {
        Validate.isTrue(Bukkit.getPluginManager().isPluginEnabled("ProtocolLib"), "ProtocolLib not found.");
        Bukkit.getLogger().log(Level.INFO, "Enable CHProtocol " + getVersion());
        processor.register();
    }

    @Override
    public void onShutdown() {
        Bukkit.getLogger().log(Level.INFO, "Disable CHProtocol " + getVersion());
        processor.unregister();
    }
}
