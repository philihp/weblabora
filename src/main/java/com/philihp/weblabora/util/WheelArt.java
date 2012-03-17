package com.philihp.weblabora.util;

import com.philihp.weblabora.model.Wheel;

public class WheelArt {

	public static class Point {
		private double x;
		private double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public String toString() {
			return x + "," + y;
		}
	}

	private static final double WHEEL_RADIUS = 140;

	private static final double ARM_RADIUS = 35;
	private static final double ARM_WIDTH = 6;
	private static final double ARM_LENGTH = 110;
	private static final double ARM_TEXT_RADIUS = 25;

	private static final Point[] points = new Point[13];
	static {
		double x, y;
		for (int n = 0; n < points.length; n++) {
			x = -Math.sin(n * Math.PI * 2 / points.length) * WHEEL_RADIUS;
			y = -Math.cos(n * Math.PI * 2 / points.length) * WHEEL_RADIUS;
			points[n] = new Point(x, y);
		}
	}

	public String getRotA() {
		return Double.toString(360 * 12.5d / points.length);
	}

	public String getRotB() {
		return Double.toString(360 * 11.5d / points.length);
	}

	public String getRotC() {
		return Double.toString(360 * 10.5d / points.length);
	}

	public String getRotD() {
		return Double.toString(360 * 9.5d / points.length);
	}

	public String getRotE() {
		return Double.toString(360 * 8.5d / points.length);
	}

	public String getRotF() {
		return Double.toString(360 * 7.5d / points.length);
	}

	public String getRotG() {
		return Double.toString(360 * 6.5d / points.length);
	}

	public String getRotH() {
		return Double.toString(360 * 5.5d / points.length);
	}

	public String getRotI() {
		return Double.toString(360 * 4.5d / points.length);
	}

	public String getRotJ() {
		return Double.toString(360 * 3.5d / points.length);
	}

	public String getRotK() {
		return Double.toString(360 * 2.5d / points.length);
	}

	public String getRotL() {
		return Double.toString(360 * 1.5d / points.length);
	}

	public String getRotM() {
		return Double.toString(360 * 0.5d / points.length);
	}

	public String getWedgeA() {
		return "0,0 " + points[0] + " " + points[1] + " 0,0";
	}

	public String getWedgeB() {
		return "0,0 " + points[1] + " " + points[2] + " 0,0";
	}

	public String getWedgeC() {
		return "0,0 " + points[2] + " " + points[3] + " 0,0";
	}

	public String getWedgeD() {
		return "0,0 " + points[3] + " " + points[4] + " 0,0";
	}

	public String getWedgeE() {
		return "0,0 " + points[4] + " " + points[5] + " 0,0";
	}

	public String getWedgeF() {
		return "0,0 " + points[5] + " " + points[6] + " 0,0";
	}

	public String getWedgeG() {
		return "0,0 " + points[6] + " " + points[7] + " 0,0";
	}

	public String getWedgeH() {
		return "0,0 " + points[7] + " " + points[8] + " 0,0";
	}

	public String getWedgeI() {
		return "0,0 " + points[8] + " " + points[9] + " 0,0";
	}

	public String getWedgeJ() {
		return "0,0 " + points[9] + " " + points[10] + " 0,0";
	}

	public String getWedgeK() {
		return "0,0 " + points[10] + " " + points[11] + " 0,0";
	}

	public String getWedgeL() {
		return "0,0 " + points[11] + " " + points[12] + " 0,0";
	}

	public String getWedgeM() {
		return "0,0 " + points[12] + " " + points[0] + " 0,0";
	}

	public String getMask() {
		return points[0] + " " + points[1] + " " + points[2] + " " + points[3] + " " + points[4] + " " + points[5] + " "
				+ points[6] + " " + points[7] + " " + points[8] + " " + points[9] + " " + points[10] + " " + points[11] + " "
				+ points[12] + " " + points[0];
	}

	public String getArmRadius() {
		return Double.toString(ARM_RADIUS);
	}

	public String getArmPath() {
		// uses the point where the circle would have been at the top as a control
		// point for
		// a quadratic bezier curve to the arm's extent
		return "M" + (-ARM_WIDTH / 2) + "," + (-ARM_LENGTH) + " " + "Q" + (-ARM_WIDTH / 2)
				+ "," + (-ARM_RADIUS) + " " + (Math.sin((12d / 13d) * 2 * Math.PI) * ARM_RADIUS) + ","
				+ (-Math.cos((12d / 13d) * 2 * Math.PI) * ARM_RADIUS) + " " + "A" + ARM_RADIUS + "," + ARM_RADIUS
				+ " 0 1,0 " + (Math.sin((1d / 13d) * 2 * Math.PI) * ARM_RADIUS) + ","
				+ (-Math.cos((1d / 13d) * 2 * Math.PI) * ARM_RADIUS) + " " + "Q" + (ARM_WIDTH / 2) + ","
				+ (-ARM_RADIUS) + " " + (ARM_WIDTH / 2) + "," + (-ARM_LENGTH) + " " + "z";
	}

	private static final double ARROW_RADIUS = 44;
	private static final double ARROW_SIZE = 7;

	public String getArrowPath() {
		return "M" + (-ARROW_SIZE / 2) + "," + (-ARROW_RADIUS) + " " + "l" + ARROW_SIZE + ","
				+ ARROW_SIZE / 2 + " " + "v" + -ARROW_SIZE + " " + "z";
	}

	private static final double HOUSE_ROOF_PEAK_RADIUS = 130;
	private static final double HOUSE_TEXT_RADIUS = 119;
	private static final double HOUSE_ROOF_HEIGHT = 5;
	private static final double HOUSE_FLOOR_HEIGHT = 8;
	private static final double HOUSE_WIDTH = 12;

	public String getHousePath() {
		return "M0" + "," + (-HOUSE_ROOF_PEAK_RADIUS) + " " + "l" + -HOUSE_WIDTH / 2 + ","
				+ HOUSE_ROOF_HEIGHT + " " + "v" + HOUSE_FLOOR_HEIGHT + " " + "h" + HOUSE_WIDTH + " " + "v"
				+ -HOUSE_FLOOR_HEIGHT + " " + "z";
	}

	public String getArmTextY() {
		return Double.toString(-ARM_TEXT_RADIUS);
	}

	public String getHouseTextY() {
		return Double.toString(-HOUSE_TEXT_RADIUS);
	}

	public static double deg(String pos) {
		if (pos.equals("A"))
			return 360 * 12.5 / points.length;
		if (pos.equals("B"))
			return 360 * 11.5 / points.length;
		if (pos.equals("C"))
			return 360 * 10.5 / points.length;
		if (pos.equals("D"))
			return 360 * 9.5 / points.length;
		if (pos.equals("E"))
			return 360 * 8.5 / points.length;
		if (pos.equals("F"))
			return 360 * 7.5 / points.length;
		if (pos.equals("G"))
			return 360 * 6.5 / points.length;
		if (pos.equals("H"))
			return 360 * 5.5 / points.length;
		if (pos.equals("I"))
			return 360 * 4.5 / points.length;
		if (pos.equals("J"))
			return 360 * 3.5 / points.length;
		if (pos.equals("K"))
			return 360 * 2.5 / points.length;
		if (pos.equals("L"))
			return 360 * 1.5 / points.length;
		if (pos.equals("M"))
			return 360 * 0.5 / points.length;
		return 0;
	}
}
