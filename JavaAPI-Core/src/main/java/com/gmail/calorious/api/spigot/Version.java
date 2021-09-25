package com.gmail.calorious.api.spigot;

public enum Version {
	MC1_4_7(1),
	MC1_5_1(2),
	MC1_5_2(3),
	MC1_6_1(4),
	MC1_6_2(5),
	MC1_6_4(6),
	MC1_7_5(7),
	MC1_7_10(8),
	MC1_8(9),
	MC1_9(10),
	MC1_9_1(11),
	MC1_9_2(12),
	MC1_9_4(13),
	MC1_10(14),
	MC1_11(15),
	MC1_11_1(16),
	MC1_12(17),
	MC1_12_1(18),
	MC1_12_2(19),
	MC1_13(20),
	MC1_13_1(21),
	MC1_13_2(22),
	MC1_14(23),
	MC1_14_1(24),
	MC1_14_2(25),
	MC1_14_3(26),
	MC1_14_4(27),
	MC1_15(28),
	MC1_15_1(29),
	MC1_15_2(30),
	MC1_16(31),
	MC1_16_1(32),
	MC1_16_2(33),
	MC1_16_3(34),
	MC1_16_4(35),
	MC1_17(36);

	private final int weightage;
	Version(int weightage) {
		this.weightage = weightage;
	}
	
	public int getWeightage() {
		return weightage;
	}
}
