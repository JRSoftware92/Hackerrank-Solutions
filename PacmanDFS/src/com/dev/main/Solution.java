package com.dev.main;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import com.dev.test.TestUtils;

public class Solution {
    
    final static char WALL = '%';
    final static char FOOD = '.';
    final static char PACMAN = 'P';
    final static char EMPTY = '-';
    final static String TURN_DIVIDER = "-------------------------------";
    
    public enum Direction { 
        NONE(0, 0), UP(0, -1), LEFT(-1, 0), RIGHT(1, 0), DOWN(0, 1);
        
        public final Position move;
        
        Direction(int x, int y){
            move = new Position(x, y);
        }
        
        public static ArrayList<Direction> getDirections(Position pos, String[] grid){
            Direction[] values = values();
            ArrayList<Direction> list = new ArrayList<Direction>();
            Position newPos;
            
            for(int i = 1; i < values.length; i++){
            	
                newPos = pos.add(values[i].move);
                if(newPos.isValid(grid) && grid[newPos.y].charAt(newPos.x) != WALL){
                    list.add(values[i]);
                }
            }
            
            return list;
        }
    }
    
    public static class Logger{
        private static Logger m_instance = null;
        
        private ArrayList<String> expQueue = new ArrayList<String>();
        private ArrayList<String> visQueue = new ArrayList<String>();
        
        private Logger(){}
        
        public static Logger instance(){
            if(m_instance == null){
                m_instance = new Logger();
            }
            return m_instance;
        }
        
        public static void logVisit(String msg){
            if(m_instance == null){
                m_instance = new Logger();
            }
            m_instance.visQueue.add(msg);
        }
        
        public static void logExplored(String msg){
            if(m_instance == null){
                m_instance = new Logger();
            }
            m_instance.expQueue.add(msg);
        }
        
        public static void print(){
            if(m_instance != null){
            	System.out.println(m_instance.expQueue.size());
                while(!m_instance.expQueue.isEmpty()){
                    System.out.println(m_instance.expQueue.remove(0));
                }
                
                System.out.println(m_instance.visQueue.size() - 1);
                while(!m_instance.visQueue.isEmpty()){
                    System.out.println(m_instance.visQueue.remove(0));
                }
            }
        }
    }
    
    public static class Position{
    	
        public final int x, y;
        
        public Position(int x, int y){
            this.x = x; this.y = y;
        }
        
        public Position add(Position other){
            return add(this, other);
        }
        
        public boolean isValid(String[] grid){
            if(grid == null){
                return false;
            }
            else if(x < 0 || y < 0 || y > grid.length - 1){
                return false;
            }
            else{
                if(x > grid[y].length() - 1){
                    return false;
                }   
                else{
                    return true;
                }
            }
        }
        
        public static Position parsePosition(String position){
        	String[] split = position.split(" ");
        	return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        
        @Override
        public int hashCode(){
        	return x | (y << 16);
        }
        
        @Override
        public String toString(){
            return y + " " + x;
        }
        
        @Override
        public boolean equals(Object o){
            if(o instanceof Position){
                Position other = (Position) o;
                return other.x == this.x && other.y == this.y;
            }
            else{
                return false;
            }
        }
        
        public static Position add(Position a, Position b){ return new Position(a.x + b.x, a.y + b.y); }
    }
    
    public static class Bot{
        final Position goal;
        final Position init;
        
        Position curr;
        Stack<Position> stack;
        HashSet<Position> visited, explored;
        
        public Bot(Position init, Position goal){
            this.goal = goal;
            this.init = init;
            curr = init;
            stack = new Stack<Position>();
            visited = new HashSet<Position>();
            explored = new HashSet<Position>();
        }
        
        public boolean atGoal(){ return curr.equals(goal); }
        
        public boolean hasMoves(){ return !stack.isEmpty(); }
        
        public void visit(Position pos){ 
        	if(!visited.contains(pos)){
        		visited.add(pos); 
        		Logger.logVisit(pos.toString());
        		System.out.println("Visiting: " + pos.toString());
        	}
        }
        
        public void explore(Position pos){ 
        	if(!explored.contains(pos)){
        		explored.add(pos); 
        		Logger.logExplored(pos.toString());
        		System.out.println("Exploring: " + pos.toString());
        	}
        }
        
        /**
         * Iterates over a grid until solved
         * @param grid
         */
        public void solve(String[] grid){
        	stack.push(curr);
        	while(!atGoal()){
        		next(grid);
        	}
        }
        
        /**
         * Handles a single turn
         * @param grid
         */
        public void next(String[] grid){
        	TestUtils.displayGrid(grid, curr);
        	
        	curr = stack.pop();
        	if(!explored.contains(curr)){
        		populateStack(curr, grid);
        	}
        	
        	visit(curr);
        	
            System.out.println(TURN_DIVIDER);
        }
        
        public void populateStack(Position pos, String[] grid){
        	explore(pos);
        	
            ArrayList<Direction> moves = Direction.getDirections(pos, grid);
            Position temp;
            for(int i = 0; i < moves.size(); i ++){
            	temp = pos.add(moves.get(i).move);
            	if(!explored.contains(temp) && !visited.contains(temp)){
            		stack.push(temp);
            	}
            }
        }
    }
    
    static void dfs(int pacman_r, int pacman_c, int food_r, int food_c, String [] grid){
        Bot bot = new Bot(new Position(pacman_c, pacman_r), 
        		new Position(food_c, food_r));
        
        bot.solve(grid);
        Logger.print();
    }
    
    public static void main(String[] args) {
    	try {
			BufferedReader in = new BufferedReader(new FileReader("sample.txt"));
			
			Position pacman = Position.parsePosition(in.readLine());
			Position food = Position.parsePosition(in.readLine());
			Position size = Position.parsePosition(in.readLine());
			
			String grid[] = new String[size.x];
			for(int i = 0; i < size.x; i++){
				grid[i] = in.readLine();
			}
			
			dfs(pacman.x, pacman.y, food.x, food.y, grid);
			
			in.close();
		} 
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int pacman_r = in.nextInt();
        int pacman_c = in.nextInt();

        int food_r = in.nextInt();
        int food_c = in.nextInt();

        int r = in.nextInt();
        int c = in.nextInt();
    
        String grid[] = new String[r];

        for(int i = 0; i < r; i++) {
            grid[i] = in.next();
        }

        dfs(pacman_r, pacman_c, food_r, food_c, grid);
    }
    */
}
