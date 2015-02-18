package com.dev.test;

import com.dev.main.Solution.Position;

public class TestUtils {
	public static void displayGrid(String[] grid, Position playerPos){
    	String[] newGrid = grid;
    	for(int i = 0; i < newGrid.length + 1; i++){
    		if(i == 0){
    			for(int j = 0; j < newGrid[0].length() + 1; j++){
    				if(j == 0){
    					System.out.print(' ');
    				}
    				else{
    					System.out.print(j - 1 < 10 ? j - 1 : Character.toString((char)(j - 11 + 65)));
    				}
    			}
    		}
    		else{
    		for(int j = 0; j < newGrid[i - 1].length() + 1; j++){
    			if(j == 0){
    				if(i != 0){
    					System.out.print(i - 1 < 10 ? i - 1 : Character.toString((char)(i - 11 + 65)));
    				}
    				else{
    					System.out.print(' ');
    				}
    			}
    			else if(i == playerPos.y + 1 && j == playerPos.x + 1){
    				System.out.print('O');
    			}
    			else{
    				System.out.print(grid[i - 1].charAt(j - 1));
    			}
    		}
    		}
    		System.out.print('\n');
    	}
    }
}
