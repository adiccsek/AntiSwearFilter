package hu.shiya.antiSwearFilter;

import hu.shiya.antiSwearFilter.listeners.AntiSwearSystem;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiSwearFilter extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents( new AntiSwearSystem( this ), this );
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll( this );
    }
}
