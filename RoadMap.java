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
		   setObstacleCone();
		   setTrafficLight();
		   setParkSignAndDestination();
	   }
		   
	   public char[][] getRoad(){
		     return road;
	   }
	   
	   public void setParkSignAndDestination(){
			int a,b;
			int count = 0;
			int type = getRandomInt(0, 2);
			// kalo type == 0, berarti tanda parkir dan tujuan berada di ujung kanan map
			if(type == 0){
				while(count<1){
					a = getRandomInt(0, 5); //baris ke 1-3
					b = MAP_WIDTH-1;		//kolom terakhir
					if(road[a][b] == '#'){
						road[a][b] = 'P';
						if(a==0){
							road[a+1][b] = 'X';
						}
						else{ road[a-1][b]= 'X';}
					count++;
					}
				}
			}
			else{ // berarti tanda parkir dan tujuan berada di bagian bawah map
				while(count<1){
					a = MAP_HEIGHT-1;		//baris terakhir
					b = getRandomInt(14, 19);		//kolom terakhir
					if(road[a][b] == '#'){
						road[a][b] = 'P';
						if(b==14){
							road[a][b+1] = 'X';
						}
						else{ road[a][b-1]= 'X';}
					count++;
					}
				}
			}
	        
	    }
	   
	   public char getPoint(int x, int y){
	     return road[x][y];
	   }

	   public void setObstacleCone(){
			int count = 0;
			int a,b;
			while(count<2){
				a = getRandomInt(0, MAP_HEIGHT);
				b = getRandomInt(0, MAP_WIDTH);
				if(road[a][b] == ' '){
				road[a][b] = 'C';
				count++;
				}
			}
	   }
	   public void setTrafficLight(){
		   	int a,b;
			int count = 0;
			while(count<1){
				a = getRandomInt(0, MAP_HEIGHT);
				b = getRandomInt(0, MAP_WIDTH);
				if(road[a][b] == '#'){
					road[a][b] = 'R';
				count++;
				}
			}
	   }

	   public void setPoint(int x,int y, char input){
		   this.road[x][y] = input;
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
		public int getRandomInt(int min, int max) {
			return (int) Math.floor(Math.random() * (max - min) + min); //The maximum is exclusive and the minimum is inclusive
		}
	   
}
