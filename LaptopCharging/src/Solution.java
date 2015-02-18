import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;


public class Solution {
	
	static final String FILE_NAME = "trainingdata.txt";
	private static Scanner in;
	
	static Double predict(HashMap<Double, Double> map, Double entry){
		Double value;
		if(map.containsKey(entry)){
			value = map.get(entry);
		}
		else{
			Set<Double> keys = map.keySet();
			PriorityQueue<Double> queue = new PriorityQueue<Double>();
			Double low = 0d, high = 0d;
			for(Double d : keys){
				queue.add(d);
			}
			
			while(entry > high && map.get(high) < 8d){
				low = high;
				high = queue.poll();
			}
			System.out.println("low: " + low + " is " + map.get(low));
			System.out.println("High: " + high + " is " + map.get(high));
			Double slope = (map.get(high) - map.get(low)) / (high - low);
			value = (slope * entry);
		}
		
		//rounds to two decimal places
		return ((double) Math.round(value * 100d)) * .01d;
	}
	
	static HashMap<Double, Double> readTrainingData(String fileName){
		HashMap<Double, Double> map = new HashMap<Double, Double>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			for(int i = 0; i < 100; i ++){
				String[] split = in.readLine().split(",");
				map.put(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map;
	}
	
   
   
   public static void main(String[] args) {
      
      in = new Scanner(System.in);
      double timp_incarcare = in.nextDouble();
      
      if (timp_incarcare >= 4.0) System.out.println("8.0");
      else{
      
      HashMap<Double, Double> map = readTrainingData(FILE_NAME);
      Double value = predict(map, timp_incarcare);
      System.out.println(Double.toString(value));

      }
   }
   

}
