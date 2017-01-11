package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.datasyslab.geospark.spatialOperator.RangeQuery;
import org.datasyslab.geospark.spatialRDD.RectangleRDD;

import com.vividsolutions.jts.geom.Envelope;

import org.datasyslab.geospark.spatialRDD.PointRDD;

public class GetCount {

	public static double getMean() {
		return mean;
	}

	public static void setMean(double mean) {
		GetCount.mean = mean;
	}

	public static double getS() {
		return S;
	}

	public static void setS(double s) {
		S = s;
	}

	public static int getTotal() {
		return total;
	}

	public static void setTotal(int total) {
		GetCount.total = total;
	}

	static private Map<Coordinate, Double> envelopeCountMap = new HashMap<Coordinate, Double>();

	static private double mean = 0;
	static private double S = 0;
	static private int total = 0;

	public static double calculateGValue(List<Coordinate> neighbors) {
		double var1 = 0;
		double var2 = mean * neighbors.size();
		double var3 = total * neighbors.size();
		double var4 = Math.pow(neighbors.size(), 2);
		double var5 = S * Math.sqrt((var3 - var4) / total - 1);
		for (Coordinate neighbour : neighbors) {
			if (envelopeCountMap.containsKey(neighbour)) {
				var1 += envelopeCountMap.get(neighbour);
			}
		}

		return (var1 - var2) / var5;
	}

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("GetCount");
		JavaSparkContext sc = new JavaSparkContext(conf);

		RectangleRDD rectangleRDD = new RectangleRDD(sc, "cubes.txt", 0, "csv",
				"rtree");
		rectangleRDD.buildIndex("rtree");
		List<Envelope> envelopes = rectangleRDD.getRawRectangleRDD().collect();
		List<PointRDD> pointRDDList = new ArrayList<PointRDD>();
		for (int i = 1; i <= 31; i++) {
			PointRDD pointRDD = new PointRDD(sc, i + ".txt", 0, "csv");
			pointRDD.buildIndex("rtree");
			pointRDDList.add(pointRDD);
		}

		for (int i = 1; i <= 31; i++) {
			PointRDD pointRDD = pointRDDList.get(i - 1);

			for (Envelope envelope : envelopes) {
				double size = RangeQuery
						.SpatialRangeQueryUsingIndex(pointRDD, envelope, 0)
						.getRawPointRDD().count();
				total++;
				mean += size;
				S += Math.pow(size, 2);
				envelopeCountMap.put(new Coordinate(envelope.getMinX(),
						envelope.getMinY(), i), size);
			}
		}

		mean = mean / total;
		S = Math.sqrt((S / total) - Math.pow(mean, 2));

		Map<Coordinate, Double> cubeGMap = new HashMap<Coordinate, Double>();

		for (Coordinate coordinate : envelopeCountMap.keySet()) {
			List<Coordinate> neighbours = FindNeighBour.findNeigh(coordinate);
			neighbours.add(coordinate);
			double gValue = calculateGValue(neighbours);
			cubeGMap.put(coordinate, gValue);
		}

		ArrayList<Double> values = new ArrayList<Double>(cubeGMap.values());
		Collections.sort(values, Collections.reverseOrder());

		for (int i = 0; i < 50; i++) {
			for (Coordinate coord : cubeGMap.keySet()) {
				if (cubeGMap.get(coord).equals(values.get(i))) {
					System.out.println(coord.getX()*100 + "," + coord.getY()*100 + ","
							+ String.valueOf((coord.getZ()-1)).split("\\.")[0] + "," + values.get(i));
				}
			}
		}
	}
}
