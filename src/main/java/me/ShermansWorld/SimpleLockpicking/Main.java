package me.ShermansWorld.SimpleLockpicking;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.ShermansWorld.SimpleLockpicking.commands.SimpleLockpickingCommands;
import me.ShermansWorld.SimpleLockpicking.commands.SimpleLockpickingTabCompletion;
import me.ShermansWorld.SimpleLockpicking.lang.Languages;
import me.ShermansWorld.SimpleLockpicking.listeners.LockpickListener;

public class Main extends JavaPlugin {

	public static Main instance = null;
	PluginManager pm = Bukkit.getPluginManager();

	public static Main getInstance() {
		return instance;
	}
	
	private void registerEvents() {
		
		this.pm.registerEvents((Listener) new LockpickListener(), (Plugin) this);
		this.pm.registerEvents((Listener) new CraftingRecipes(), (Plugin) this);
		LockpickListener.removeDisabled();

		// Custom recipes
		CraftingRecipes customRecipies = new CraftingRecipes();
		customRecipies.lockpickRecipe();
		
		getCommand("SimpleLockpicking").setExecutor((CommandExecutor) new SimpleLockpickingCommands(this));
		getCommand("SimpleLockpicking").setTabCompleter(new SimpleLockpickingTabCompletion());
	}
	
	public static void initHooks() {
	}

	public void onEnable() {
		instance = this;
		Languages.initLangs();
		registerEvents();
		this.saveDefaultConfig();
		initHooks();
	}

	public void onDisable() {
	}
}

