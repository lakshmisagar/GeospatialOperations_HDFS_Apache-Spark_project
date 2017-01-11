package main;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Coordinate {

	private double x;
	private double y;
	private double z;
	private static DecimalFormat df2 = new DecimalFormat(".##");
	public Coordinate(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	/*@Override
	public String toString() {
		df2.setRoundingMode(RoundingMode.UP);
		String z = String.valueOf(this.z -1).split("\\.")[0];
		
		String x = df2.format(this.x);
		String y = df2.format(this.y);
		
		double x1 = Double.parseDouble(x)*100;
		double y1 = Double.parseDouble(y)*100;
		
		x = String.valueOf(x1).split("\\.")[0];
		y = String.valueOf(y1).split("\\.")[0];
		
		return x+","+y+","+z;

	}*/
}
