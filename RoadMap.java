package carSimulator;

//import java.util.ArrayList;
//import java.util.List;
import java.util.*;


public class RoadMap {
		
//		private List<Coords> startingPoints = new ArrayList<Coords>();
//		private Coords carStartingPoint = new Coords();
		
		int x, y;
	    public static final int MAP_WIDTH = 35;
	    public static final int MAP_HEIGHT = 15;
	
	    private char[][] road= {{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#' },
	                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
	                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
	                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
	                            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' ','#','#','#','#','#','#','#','#', '#','#','#','#','#','#','#','#','#','#','#'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
	                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}};
	    
	   
	
	   public RoadMap(){
		   setObstacle();
	   }
		   
	   public char[][] getRoad(){
		     return road;
	   }
	   
	   public void setObstacle(){
	        int count = 0;
	        int a,b;
	        String cone = "\uD83D\uDEA7";
	        String trafficLight = "\uD83D\uDEA6";
	        int coneCodePoint = cone.codePointAt(cone.offsetByCodePoints(0,0));
	        int trafficCodePoint = trafficLight.codePointAt(trafficLight.offsetByCodePoints(0, 0));

	        int mysteryConeCodePoint = coneCodePoint + 1;
	        int mysteryTrafficCodePoint = trafficCodePoint + 1;

	        char mysteryCone[] = {Character.highSurrogate(mysteryConeCodePoint), Character.lowSurrogate(mysteryConeCodePoint)};
	        char mysteryTraffic[] = {Character.highSurrogate(mysteryTrafficCodePoint), Character.lowSurrogate(mysteryTrafficCodePoint)};
	        
	        while(count<2){
	            a = (int) Math.floor(Math.random() * 15);
	            b = (int) Math.floor(Math.random() * 35);
	            if(road[a][b] == ' '){
	                System.out.println(mysteryCone);
	            count++;
	            }
	        }
	        count = 0;
	        while(count<1){
	            a = (int) Math.floor(Math.random() * 15);
	            b = (int) Math.floor(Math.random() * 35);
	            if(road[a][b] == '#'){
	                road[a][b] = 'P';
	            count++;
	            }
	        }
	    
	        count = 0;
	        while(count<1){
	            a = (int) Math.floor(Math.random() * 15);
	            b = (int) Math.floor(Math.random() * 35);
	            if(road[a][b] == '#'){
	                road[a][b] = 'R';
	            count++;
	            }
	        }

	    }
	   
	   public char getPoint(int x, int y){
	     return road[x][y];
	   }
	   
//	   public List<Coords> startingPoints() {
//		
//		   for(int i = 1; i<=8; i++) {
//			   startingPoints.add(new Coords(i,0));
//		   }
//		   return startingPoints;
//	   }
//	   
//	   public Coords getStartingPoint(int index) {
//		  return startingPoints.get(index);
//	   }
//	   
//	   public void setStartingPoint(int index) {
//		  carStartingPoint = startingPoints.get(index);
//	   }
//	   

	   
//	   public void setObstacle(){
//	        int count = 0;
//	        int a,b;
//	        while(count<2){
//	            a = (int) Math.floor(Math.random() * 15);
//	            b = (int) Math.floor(Math.random() * 35);
//	            if(road[a][b] == ' '){
//	                road[a][b] = 'C';
//	            count++;
//	            }
//	        }
//	        count = 0;
//	        while(count<1){
//	            a = (int) Math.floor(Math.random() * 15);
//	            b = (int) Math.floor(Math.random() * 35);
//	            if(road[a][b] == '#'){
//	                road[a][b] = 'P';
//	            count++;
//	            }
//	        }
//	    
//	        count = 0;
//	        while(count<1){
//	            a = (int) Math.floor(Math.random() * 15);
//	            b = (int) Math.floor(Math.random() * 35);
//	            if(road[a][b] == '#'){
//	                road[a][b] = 'R';
//	            count++;
//	            }
//	        }
//
//	    }
	   
}
