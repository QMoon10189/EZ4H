package org.meditation.ez4h.translators.bedrockTranslators;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.data.game.PlayerListEntry;
import com.github.steveice10.mc.protocol.data.game.PlayerListEntryAction;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.data.message.TextMessage;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerListPacket;
import org.meditation.ez4h.bedrock.Client;
import org.meditation.ez4h.translators.BedrockTranslator;
import com.nukkitx.protocol.bedrock.BedrockPacket;

import java.util.ArrayList;

public class PlayerListPacketTranslator implements BedrockTranslator {
    @Override
    public void translate(BedrockPacket inPacket, Client client) {
        PlayerListPacket packet=(PlayerListPacket)inPacket;
        ArrayList<PlayerListEntry> playerListEntries=new ArrayList<>();
        for(PlayerListPacket.Entry entry:packet.getEntries()){
            playerListEntries.add(new PlayerListEntry(new GameProfile(entry.getUuid(),entry.getName()), GameMode.SURVIVAL,0,new TextMessage(entry.getName())));
        }
        PlayerListEntry[] playerListEntriesL=playerListEntries.toArray(new PlayerListEntry[0]);
        switch (packet.getAction()){
            case ADD:{
                client.sendPacket(new ServerPlayerListEntryPacket(PlayerListEntryAction.ADD_PLAYER, playerListEntriesL));
                break;
            }
            case REMOVE:{
                client.sendPacket(new ServerPlayerListEntryPacket(PlayerListEntryAction.REMOVE_PLAYER, playerListEntriesL));
                break;
            }
        }
    }
}