package hu.shiya.antiSwearFilter.listeners;

import hu.shiya.antiSwearFilter.AntiSwearFilter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class AntiSwearSystem implements Listener {
    private final AntiSwearFilter instance;
    public AntiSwearSystem( AntiSwearFilter instance ) {
        this.instance = instance;
    }
    @EventHandler
    public void onSwear(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        List<String> badWords = new ArrayList<>();
        badWords.addAll(instance.getConfig().getStringList("badwords"));
        List<Player> onlinePlayers = new ArrayList<>();
        onlinePlayers.addAll( Bukkit.getOnlinePlayers());
        String message = event.getMessage();

        for (String badWord : badWords) {
            if (message.toLowerCase().contains(badWord.toLowerCase()) && !player.hasPermission("chat-pass")) {
                event.setCancelled(true);
                player.sendMessage( "You cannot swear on the server!" );
                onlinePlayers.forEach( (p) -> {
                    if (p.hasPermission("moderator") ){
                        p.sendMessage( player.getName() + "swore: " + "§c" + message  );
                    }}
                );
                return;
            }
        }
    }
}
