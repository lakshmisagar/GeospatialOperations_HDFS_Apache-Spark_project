package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ProcessFile {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("yellow_tripdata_2015-01.csv"));
		String line = reader.readLine();
		while((line = reader.readLine())!=null) {
			String[] arr = line.split(",");
			int day = Integer.parseInt(arr[1].split(" ")[0].split("-")[2]);
			BufferedWriter writer = new BufferedWriter(new FileWriter(day+".txt",true));
			String entry = arr[6]+","+arr[5];
			writer.append(entry+"\n");
			writer.flush();
			writer.close();
		}
		
		reader.close();
	}
}
