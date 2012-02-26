package com.philihp.weblabora.util;

public class WheelArt {
	
	public class Point {
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

	// offsetting this by 0.5 makes the lines centered on pixels rather than
	// half in two pixels
	private final Point center = new Point(150.5f, 150.5f);
	
	private static final double WHEEL_RADIUS = 140;
	
	private static final double ARM_RADIUS = 35;
	private static final double ARM_WIDTH = 6;
	private static final double ARM_LENGTH = 110; 
	private static final double ARM_TEXT_RADIUS = 25;

	private final Point[] points = new Point[13];
	{
		double x,y;
		for(int n = 0; n < points.length; n++) {
			x = -Math.sin(n*Math.PI*2/points.length)*WHEEL_RADIUS + center.x;
			y = -Math.cos(n*Math.PI*2/points.length)*WHEEL_RADIUS + center.y;
			points[n] = new Point(x,y);
		}
	}
	

	public String getRotA() {
		return Double.toString(360*12.5d/points.length);
	}
	public String getRotB() {
		return Double.toString(360*11.5d/points.length);
	}
	public String getRotC() {
		return Double.toString(360*10.5d/points.length);
	}
	public String getRotD() {
		return Double.toString(360*9.5d/points.length);
	}
	public String getRotE() {
		return Double.toString(360*8.5d/points.length);
	}
	public String getRotF() {
		return Double.toString(360*7.5d/points.length);
	}
	public String getRotG() {
		return Double.toString(360*6.5d/points.length);
	}
	public String getRotH() {
		return Double.toString(360*5.5d/points.length);
	}
	public String getRotI() {
		return Double.toString(360*4.5d/points.length);
	}
	public String getRotJ() {
		return Double.toString(360*3.5d/points.length);
	}
	public String getRotK() {
		return Double.toString(360*2.5d/points.length);
	}
	public String getRotL() {
		return Double.toString(360*1.5d/points.length);
	}
	public String getRotM() {
		return Double.toString(360*0.5d/points.length);
	}

	public String getWedgeA() {
		return center + " " + points[0] + " " + points[1] + " " + center;
	}

	public String getWedgeB() {
		return center + " " + points[1] + " " + points[2] + " " + center;
	}

	public String getWedgeC() {
		return center + " " + points[2] + " " + points[3] + " " + center;
	}

	public String getWedgeD() {
		return center + " " + points[3] + " " + points[4] + " " + center;
	}

	public String getWedgeE() {
		return center + " " + points[4] + " " + points[5] + " " + center;
	}

	public String getWedgeF() {
		return center + " " + points[5] + " " + points[6] + " " + center;
	}

	public String getWedgeG() {
		return center + " " + points[6] + " " + points[7] + " " + center;
	}

	public String getWedgeH() {
		return center + " " + points[7] + " " + points[8] + " " + center;
	}

	public String getWedgeI() {
		return center + " " + points[8] + " " + points[9] + " " + center;
	}

	public String getWedgeJ() {
		return center + " " + points[9] + " " + points[10] + " " + center;
	}

	public String getWedgeK() {
		return center + " " + points[10] + " " + points[11] + " " + center;
	}

	public String getWedgeL() {
		return center + " " + points[11] + " " + points[12] + " " + center;
	}

	public String getWedgeM() {
		return center + " " + points[12] + " " + points[0] + " " + center;
	}

	public String getCenterX() {
		return Double.toString(center.x);
	}
	public String getCenterY() {
		return Double.toString(center.y);
	}
	public String getArmRadius() {
		return Double.toString(ARM_RADIUS);
	}

	public String getArmPath() {
		//uses the point where the circle would have been at the top as a control point for
		//a quadratic bezier curve to the arm's extent
		return "M"+(center.x-ARM_WIDTH/2)+","+(center.y-ARM_LENGTH)+" "
				  +"Q"+(center.x-ARM_WIDTH/2)+","+(center.y-ARM_RADIUS)+" "
				  	+(center.x+Math.sin((12d/13d)*2*Math.PI)*ARM_RADIUS)+","+(center.y-Math.cos((12d/13d)*2*Math.PI)*ARM_RADIUS)+ " "
				  +"A"+ARM_RADIUS+","+ARM_RADIUS+" 0 1,0 "
				    +(center.x+Math.sin((1d/13d)*2*Math.PI)*ARM_RADIUS)+","+(center.y-Math.cos((1d/13d)*2*Math.PI)*ARM_RADIUS)+" "
				  +"Q"+(center.x+ARM_WIDTH/2)+","+(center.y-ARM_RADIUS)+" "
				    +(center.x+ARM_WIDTH/2)+","+(center.y-ARM_LENGTH)+" "
				  +"z";
	}
	public String getArmTextY() {
		return Double.toString(center.y-ARM_TEXT_RADIUS);
	}
	
}
