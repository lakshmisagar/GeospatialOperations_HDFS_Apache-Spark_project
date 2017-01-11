package main;

import java.io.*;
import java.math.*;


public class CreateCube {


public static void main(String[] args) throws IOException {
	FileWriter file = new FileWriter(new File("cubes.txt"));
	BigDecimal startX = new BigDecimal("40.50");
	BigDecimal window = new BigDecimal("0.01");
	int count = 0;
	while(startX.compareTo(new BigDecimal("40.90"))!=0) {
		BigDecimal startY = new BigDecimal("-74.25");
		while(startY.compareTo(new BigDecimal("-73.70"))!=0) {
			BigDecimal tempY = startY.add(window);
			BigDecimal tempX = startX.add(window);
			String val = startX.toString()+","+startY.toString()+","+tempX.toString()+","+tempY.toString()+"\n";
			file.write(val);
			count++;
			System.out.print(val);
			startY = startY.add(window);
				
		}
		startX = startX.add(window);
	}	

	System.out.println("Count = "+ count);
}
	

}
