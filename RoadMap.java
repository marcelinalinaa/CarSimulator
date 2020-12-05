package CarSimulator;

//import java.util.ArrayList;
//import java.util.List;
import java.util.*;


public class RoadMap {
		
//		private List<Coords> startingPoints = new ArrayList<Coords>();
//		private Coords carStartingPoint = new Coords();
		
		int x, y;
	    public static final int MAP_WIDTH = 35;
	    public static final int MAP_HEIGHT = 15;
		static String trafficLight = "\uD83D\uDEA6";
		static int mysteryTrafficCodePoint = trafficLight.codePointAt(trafficLight.offsetByCodePoints(0, 0)) + 1;
		static char mysteryTraffic[] = {Character.highSurrogate(mysteryTrafficCodePoint), Character.lowSurrogate(mysteryTrafficCodePoint)};
		public static final String Cone = String.valueOf(mysteryTraffic);

	    private String[][] road= {{"#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#" },
	                            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ", " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
	                            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ", " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
	                            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ", " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
	                            {"#","#","#","#","#","#","#","#","#","#","#","#","#","#"," "," "," ","#","#","#","#","#","#","#","#", "#","#","#","#","#","#","#","#","#","#","#"},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."},
	                            {".",".",".",".",".",".",".",".",".",".",".",".",".","#"," "," "," ","#",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","."}};
	    
	   
	
	   public RoadMap(){
		   setObstacle();
	   }
		   
	   public String[][] getRoad(){
		     return road;
	   }
	   
	   public void setObstacle(){
	        int count = 0;
	        int a,b;



	        while(count<2){
	            a = (int) Math.floor(Math.random() * 15);
	            b = (int) Math.floor(Math.random() * 35);
	            if(road[a][b] == " "){
	               road[a][b] = String.valueOf(Cone);
	            count++;
	            }
	        }
	        count = 0;
	        while(count<1){
	            a = (int) Math.floor(Math.random() * 15);
	            b = (int) Math.floor(Math.random() * 35);
	            if(road[a][b] == "#"){
	                road[a][b] = "P";
	            count++;
	            }
	        }
	    
	        count = 0;
	        while(count<1){
	            a = (int) Math.floor(Math.random() * 15);
	            b = (int) Math.floor(Math.random() * 35);
	            if(road[a][b] == "#"){
	                road[a][b] = "R";
	            count++;
	            }
	        }

	    }
	   
	   public String getPoint(int x, int y){
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

	   
}
