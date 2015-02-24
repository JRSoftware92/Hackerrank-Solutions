import java.util.Scanner;
import java.util.regex.*;
public class Solution { public static void main(String[] args) {
		String RESULT_STRING = "CountryCode=%s,LocalAreaCode=%s,Number=%s\n";
    	String PHONE_REGEX = "\\d{1,3}(-|\\s)\\d{1,3}(-|\\s)\\d{4,10}";
    	String SPLIT = "-|\\s";
    	Pattern pattern = Pattern.compile(PHONE_REGEX);
        Scanner in = new Scanner(System.in);
        int numbers = Integer.parseInt(in.nextLine());	//retrieve the number of lines
//--------------------------Actual functionality------------------------------------------//
        for(int i = 0; i < numbers; i++){	//Match each, split the match, format the result
        	Matcher matcher = pattern.matcher(in.nextLine());
        	if(matcher.find()){ System.out.format(RESULT_STRING, matcher.group().split(SPLIT)); }
        	else{ System.out.println(""); }	//or print a blank
        }
//---------------------------End of actual functionality---------------------------------//
        in.close();
}}