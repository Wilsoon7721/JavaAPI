package com.gmail.calorious.api.spigot.util;

import java.util.LinkedList;

import org.bukkit.block.BlockFace;

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
