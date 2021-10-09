package com.gmail.calorious.api.spigot.util;

import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VectorUtil {
	public static final double STRAIGHT_PITCH = 0.0D;
	public static double getYaw(BlockFace facing) {	
		if(facing == BlockFace.NORTH) {
			return 180.0D;
		}
		if(facing == BlockFace.EAST) {
			return -90.0D;
		}
		if(facing == BlockFace.WEST) {
			return 90.0D;
		}
		if(facing == BlockFace.SOUTH) {
			return 0.0D;
		}
		if(facing == BlockFace.NORTH_EAST) {
			return -135.0D;
		}
		if(facing == BlockFace.NORTH_WEST) {
			return 135.0D;
		}
		if(facing == BlockFace.SOUTH_WEST) {
			return 45.0D;
		}
		if(facing == BlockFace.SOUTH_EAST) {
			return -45.0D;
		}
		return 360.0D;
	}
	
	public static Vector rotateAroundY(Vector vector, double angle) {
		double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);

        double x = angleCos * vector.getX() + angleSin * vector.getZ();
        double z = -angleSin * vector.getX() + angleCos * vector.getZ();
        return vector.setX(x).setZ(z);
	}
	
	public static Vector getDirection(BlockFace face) {
		Vector direction = new Vector(face.getModX(), face.getModY(), face.getModZ());
        if (face.getModX() != 0 || face.getModY() != 0 || face.getModZ() != 0) {
            direction.normalize();
        }
        return direction;
	}
	
	public static LinkedList<Location> get3By3BlocksInfrontOfPlayer(Player player){
		Location eyeLoc = player.getEyeLocation();
		Vector dir = player.getLocation().getDirection().clone().setY(0);
		Vector block5Origin = dir.clone();
		Vector block4Origin = rotateAroundY(dir.clone(), 90).add(dir);
		Vector block6Origin = rotateAroundY(dir.clone(), -90).add(dir);
		Vector block1Origin = block4Origin.clone().add(getDirection(BlockFace.UP));
		Vector block2Origin = block5Origin.clone().add(getDirection(BlockFace.UP));
		Vector block3Origin = block6Origin.clone().add(getDirection(BlockFace.UP));
		Vector block7Origin = block4Origin.clone().add(getDirection(BlockFace.DOWN));
		Vector block8Origin = block5Origin.clone().add(getDirection(BlockFace.DOWN));
		Vector block9Origin = block6Origin.clone().add(getDirection(BlockFace.DOWN));
		
		Location block_5 = block5Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_4 = block4Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_6 = block6Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_7 = block7Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_8 = block8Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_9 = block9Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_1 = block1Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_2 = block2Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		Location block_3 = block3Origin.add(eyeLoc.toVector()).toLocation(player.getWorld());
		LinkedList<Location> locations = new LinkedList<>(); 
		locations.add(block_1);
		locations.add(block_2);
		locations.add(block_3);
		locations.add(block_4);
		locations.add(block_5);
		locations.add(block_6);
		locations.add(block_7);
		locations.add(block_8);
		locations.add(block_9);
		return locations;
	}
	
	public static LinkedList<Double> getClosestCardinalYawAndPitch(int yaw, int pitch) {
		LinkedList<Double> values = new LinkedList<>();
		if(yaw >= 0 && yaw <= 45) {
			values.add(0.0D); // CARDINAL VALUE
		}
		if(yaw >= 46 && yaw <= 90) {
			values.add(90.0D);
		}
		if(yaw >= 91 && yaw <= 135) {
			values.add(90.0D); // CARDINAL VALUE
		}
		if(yaw >= 136 && yaw <= 180) {
			values.add(180.0D);
		}
		if(yaw <= -0 && yaw >= -45) {
			values.add(0.0D); // CARDINAL VALUE
		}
		if(yaw <= -46 && yaw >= -90) {
			values.add(-90.0D);
		}
		if(yaw <= -91 && yaw >= -135) {
			values.add(-90.0D); // CARDINAL VALUE
		}
		if(yaw <= -136 && yaw >= -180) {
			values.add(-180.0D);
		}
		
		// Pitch
		if(pitch >= 45.0D && pitch <= 90.0D) {
			values.add(90.0D);
		}
		if(pitch < 45.0D && pitch >= 0.0D) {
			values.add(0.0D);
		}
		if(pitch <= -45.0D && pitch >= -90.0D) {
			values.add(-90.0D);
		}
		if(pitch > -45.0D && pitch < -0.0D) {
			values.add(0.0D);
		}
		if(values.size() > 2) throw new IllegalStateException("JavaAPI - getClosestCardinalYawAndPitch() returned more than 3 values in the LinkedList!");
		return values;
	}
	
	public static double getPitch(BlockFace facing) {
		if(facing == BlockFace.UP) {
			return -90.0D;
		}
		if(facing == BlockFace.SELF) {
			return 0.0D;
		}
		if(facing == BlockFace.DOWN) {
			return 90.0D;
		}
		return 0.0D;
	}
}