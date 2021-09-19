package com.gmail.calorious.api.spigot.plugins.luckperms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import com.gmail.calorious.api.spigot.util.MsgUtils;

import net.luckperms.api.model.data.TemporaryNodeMergeStrategy;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.Node;

public class LPGroupManager {
	protected LPGroupManager() {} // Only instantiatable by LPIntegration
	
	public Group getGroup(String name) { // Will always return group as creation occurs if group not found.
		if(LPIntegration.getAPIInstance().getGroupManager().getGroup(name) == null) {
			try {
				return LPIntegration.getAPIInstance().getGroupManager().createAndLoadGroup(name).get();
			} catch (InterruptedException | ExecutionException e) {
				MsgUtils.sendConsoleMessage("&cJavaAPI - Error");
				MsgUtils.sendConsoleMessage("&cLPIntegration group creation failed.");
				e.printStackTrace();
				return null;
			}
		}
		return LPIntegration.getAPIInstance().getGroupManager().getGroup(name);
	}
	
	public boolean isPlayerInGroup(Player player, String group) {
	    return player.hasPermission("group." + group);
	}
	
	public Set<Group> getPlayerGroup(Player player) {
		Set<String> groups = new HashSet<>();
		Set<String> retrievedsGroups = new HashSet<>();
		for(Group group : LPIntegration.getAPIInstance().getGroupManager().getLoadedGroups()) {
			groups.add(group.getName());
		}
		for(String sGroup : groups) {
			if(!(player.hasPermission("group." + sGroup))) return null;
			retrievedsGroups.add(sGroup);
		}
		if(retrievedsGroups.size() == 1) {
			Set<Group> finalizedGroups = new HashSet<>();
			String s = retrievedsGroups.stream().collect(Collectors.toList()).get(0);
			Group group = LPIntegration.getAPIInstance().getGroupManager().getGroup(s);
			finalizedGroups.add(group);
			return finalizedGroups;
		}
		Set<Group> finalizedGroups = new HashSet<>();
		for(String string : retrievedsGroups) {
			Group group = LPIntegration.getAPIInstance().getGroupManager().getGroup(string);
			finalizedGroups.add(group);
		}
		return finalizedGroups;
	}
	
	public static void deleteGroup(Group group) {
		LPIntegration.getAPIInstance().getGroupManager().deleteGroup(group).join();
	}
	
	public static void addNode(Group group, Node node) {
		for(Node n : group.data().toCollection()) {
			if(!n.getKey().equalsIgnoreCase(node.getKey())) {
				group.data().add(node);
				LPIntegration.getAPIInstance().getGroupManager().saveGroup(group);
				return;
			}
		}
		// Node already added. Attempt to modify.
		boolean value = node.getValue();
		Node existingNode = group.data().toCollection().stream().filter(filter -> filter.getKey().equalsIgnoreCase(node.getKey())).collect(Collectors.toList()).get(0);
		if(existingNode.getValue() != value) {
			group.data().remove(existingNode);
			group.data().add(node, TemporaryNodeMergeStrategy.REPLACE_EXISTING_IF_DURATION_LONGER);
		}
	}
	
	public static void removeNode(Group group, Node node) {
		for(Node n : group.data().toCollection()) {
			if(n.getKey().equalsIgnoreCase(node.getKey())) {
				group.data().remove(node);
				LPIntegration.getAPIInstance().getGroupManager().saveGroup(group);
				return;
			}
		}
		// No value found
		return;
	}
	
	protected void transformGroups() { // Changes all uppercase group names to lowercase
		for(Group group : LPIntegration.getAPIInstance().getGroupManager().getLoadedGroups()) {
			// Grab Group Node Data
			// Delete group
			// Re-create group
			// Re-add nodes
			Collection<Node> nodes = group.getNodes();
			String formattedGroupName = group.getName().toLowerCase();
			LPIntegration.getAPIInstance().getGroupManager().deleteGroup(group);
			LPIntegration.getAPIInstance().getGroupManager().createAndLoadGroup(formattedGroupName);
			Group newGroup = LPIntegration.getAPIInstance().getGroupManager().getGroup(formattedGroupName);
			for(Node node : nodes) {
				newGroup.data().clear();
				newGroup.data().add(node);
			}
		}
	}
}
