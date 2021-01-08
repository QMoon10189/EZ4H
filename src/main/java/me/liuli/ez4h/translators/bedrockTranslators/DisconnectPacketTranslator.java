package me.liuli.ez4h.translators.bedrockTranslators;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket;
import me.liuli.ez4h.bedrock.Client;
import me.liuli.ez4h.translators.BedrockTranslator;

public class DisconnectPacketTranslator implements BedrockTranslator {
    @Override
    public void translate(BedrockPacket inPacket, Client client) {
        DisconnectPacket packet=(DisconnectPacket)inPacket;
        client.javaSession.disconnect(packet.getKickMessage());
    }
}