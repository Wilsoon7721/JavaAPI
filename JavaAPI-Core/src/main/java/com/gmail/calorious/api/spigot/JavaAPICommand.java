package com.gmail.calorious.api.spigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.calorious.api.spigot.util.MsgUtils;

public class JavaAPICommand implements CommandExecutor {
    private JavaAPI_Base instance;

    public JavaAPICommand(JavaAPI_Base instance) {
	this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	sender.sendMessage(MsgUtils.color("&bJavaAPI v&a" + instance.getDescription().getVersion() + "&b"));
	sender.sendMessage(MsgUtils.color("&b Created by Java."));
	APIRegistration.printAPIRegisters(sender);
	return true;
    }

}
