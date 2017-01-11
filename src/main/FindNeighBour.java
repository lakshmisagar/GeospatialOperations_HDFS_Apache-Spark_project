package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



public class FindNeighBour {

	static Coordinate startCoordinate = new Coordinate(40.50, -74.25,1);
	static Coordinate endCoordinate = new Coordinate(40.90, -73.70,31);
	static double small_cube_length = 0.01;
	static double small_cube_height = 1;
	static double no_of_days = 31;
	
	static DecimalFormat df2 = new DecimalFormat(".##");

	public static ArrayList<Coordinate> findNeigh(Coordinate coordinate) {
		ArrayList<Coordinate> neighbour = new ArrayList<Coordinate>();
		int temp_arr[][] = { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 },
				{ 0, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 }, { 1, 1, 1 },
				{ 0, 0, -1 }, { 0, -1, 0 }, { -1, 0, 0 }, { 0, -1, -1 },
				{ -1, -1, 0 }, { -1, 0, -1 }, { -1, -1, -1 }, { 0, -1, 1 },
				{ 0, 1, -1 }, { -1, 0, 1 }, { 1, 0, -1 }, { -1, 1, 0 },
				{ 1, -1, 0 }, { -1, 1, 1 }, { 1, -1, 1 }, { 1, 1, -1 },
				{ 1, -1, -1 }, { -1, -1, 1 }, { -1, 1, -1 } };
		double x = coordinate.getX();
		double y = coordinate.getY();
		double z = coordinate.getZ();
		/*df2.setRoundingMode(RoundingMode.UP);*/
		for (int i = 0; i < 26; i++) {
			double temp_x = Double.parseDouble(df2.format(x + temp_arr[i][0] * small_cube_length));
			double temp_y = Double.parseDouble(df2.format(y + temp_arr[i][1] * small_cube_length));
			double temp_z = Double.parseDouble(df2.format(z + temp_arr[i][2] * small_cube_height));
			if (temp_x > endCoordinate.getX() | temp_x < startCoordinate.getX()
					| temp_y > endCoordinate.getY() | temp_y < startCoordinate.getY()
					| temp_z > no_of_days | temp_z < 1) {
				continue;
			} else {
				neighbour.add(new Coordinate(temp_x, temp_y, temp_z));
			}
		}
		
		return neighbour;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		
		double d = 23.1;
		String d1 = String.valueOf(d).split("\\.")[0];
		
		BufferedReader reader = new BufferedReader(new FileReader("cubes.txt"));
		String line = "";
		while((line=reader.readLine())!=null) {
			String [] arr = line.split(",");
			Coordinate coordinate = new Coordinate(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]),5);
			List<Coordinate> neighbours = findNeigh(coordinate);
		}
		
		reader.close();
		
	}
}
