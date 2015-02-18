import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int lonelyinteger(int[] a) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(a.length);
        for(int i = 0; i < a.length; i++){ queue.add(a[i]); } //enqueue array
    
        int num = 0;
        while(!queue.isEmpty()){
        	num = queue.poll();		//clear dupicates
        	if(!queue.isEmpty()){
        		if(queue.peek() != num){ break; }   //found lonely number
        		else{
        			while(num == queue.peek()){ num = queue.poll(); }
        		}
        	}
        }
        return num;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int size = Integer.parseInt(in.nextLine());
        String next = in.nextLine();
        String[] split = next.split(" ");
        int[] arr = new int[size];
        
        for(int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
    
        System.out.println(lonelyinteger(arr));
    }
}
