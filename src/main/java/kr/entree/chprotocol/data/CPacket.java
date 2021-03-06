package kr.entree.chprotocol.data;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chprotocol.Conversions;
import kr.entree.chprotocol.event.CPacketEvent;
import kr.entree.chprotocol.protocollib.ProtocolLibraries;
import lombok.val;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static kr.entree.chprotocol.Conversions.adjustObject;
import static kr.entree.chprotocol.Conversions.convertMixedToObject;

/**
 * Created by JunHyung Im on 2020-07-05
 */
public class CPacket extends Construct {
    private final PacketContainer packet;

    public CPacket(String value, ConstructType ctype, int lineNum, File file, int column, PacketContainer packet) {
        super(value, ctype, lineNum, file, column);
        this.packet = packet;
    }

    public CPacket(String value, ConstructType ctype, Target t, PacketContainer packet) {
        super(value, ctype, t);
        this.packet = packet;
    }

    public static CPacket create(PacketContainer packet, Target target) {
        return new CPacket("PacketData", ConstructType.RESOURCE, target, packet);
    }

    public static CPacket create(Environment env, Target target) {
        return create(CPacketEvent.getPacket(env, target), target);
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    public Object read(int index) {
        return packet.getModifier().read(index);
    }

    public Mixed readMixed(int index, Target target) {
        return Conversions.convertObjectToMixed(read(index), target);
    }

    public void write(int index, Object object) {
        packet.getModifier().write(index, object);
    }

    public void writeMixed(int index, Mixed mixed) {
        val field = packet.getModifier().getField(index);
        Class<?> type = field.getType();
        write(index, adjustObject(convertMixedToObject(mixed, type), type));
    }

    public CArray getFields(Target target) {
        CArray information = new CArray(target);
        for (Field field : packet.getModifier().getFields()) {
            information.push(new CString(field.getType().getSimpleName(), target), target);
        }
        return information;
    }

    public void send(MCPlayer player, Target target) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(
                    ((BukkitMCPlayer) player)._Player(),
                    packet
            );
        } catch (InvocationTargetException e) {
            throw new CREException("Error while sending the packet " + getKind(), target, e);
        }
    }

    public PacketKind getKind() {
        return ProtocolLibraries.getPacketKind(packet.getType());
    }
}
