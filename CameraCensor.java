package carSimulator;

import java.util.*;

public class CameraCensor{
	// private float x;
	// private float y;
	// private float x2;
	//private float y2;
	private float maxRange;
	public boolean isVertical;
	public RoadMap map;
	public char direction;
	public static final int censorRange = 3;
	private HashMap<Coords, Character> obstacles = new HashMap<Coords, Character>();
	private HashMap<Coords, Double> distance = new HashMap<Coords, Double>();

	public CameraCensor(char direction){
	map = new RoadMap();
	this.direction = direction ;
	this.obstacles = null;
	this.distance = null;
	}

	/*public void readMap(RoadMap road[][]) {
	}
	*/
	public void findObstacles(int x, int y){
		char[][] road = map.getRoad();
		obstacles = new HashMap<Coords, Character>(); //menyimpan koordinat untuk setiap obstacle (characteR)
		distance = new HashMap<Coords, Double>(); // menyimpan koordinat untuk setiap obstacle, dan jaraknya terhadap mobil
		if(direction == 'e') {
			//alur iterasinya, untuk setiap kolom, akan di cek setiap barisnya
			for(int j = y; j<=y+censorRange; j++) {
				//if out of bound (column), then break (do not need to check next column)
				if(j > RoadMap.MAP_WIDTH)
					break;
				for(int i = x-censorRange; i <= x+censorRange; i++) {
					//if out of bound (top row), then next iteration
					if(i < 0)
						continue;
					if(road[i][j] != ' ' && road[i][j] != '.') {
						obstacles.put(new Coords(i,j), road[i][j]);
						distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
					}
				}
			}
		}
		else if( direction == 's') {
			//alur iterasinya, untuk setiap baris, akan dicek setiap kolomnya
			for(int i = x; i < x+censorRange; i++) {
				// if out of bound (bottom row), then break ( do not neet to check next row)
				if(i>=RoadMap.MAP_HEIGHT)
					break;
				for(int j = y-censorRange; j<= y+censorRange; y++) {
					if(road[i][j] != ' ' && road[i][j] != '.') {
						obstacles.put(new Coords(i,j), road[i][j]);
						distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
					}
				}
			}
		}
	}
	
	public char[][] scanRoad(int x, int y){
        char[][] road = map.getRoad();
        char[][] temp = new char[5][5];
        if(direction == 'e') {
            for(int i = y - 2; i <= y + 2; i++){
                for(int j = x + 1; j <= x + 5; j++){
					temp[i - y + 2][j - x - 1] = road[i][j];
                }
            }
        } else if(direction == 'w') {
            for(int i = y - 2; i <= y + 2; i++){
                for(int j = x - 1; j >= x - 5; j--){
                    temp[i - y + 2][j - x + 5] = road[i][j];
                }
            }
        } else if(direction == 'n') {
            for(int i = x - 2; i <= x + 2; i++){
                for(int j = y + 1; j <= y + 5; j++){
                    temp[i - x + 2][j - y - 1] = road[i][j];
                }
            }
        } else {
            for(int i = x - 2; i <= x + 2; i++){
                for(int j = y - 1; j >= y - 5; j--){
                    temp[i - x + 2][j - y + 5] = road[i][j];
                }
            }
        }
        return temp;
    }
	
	public HashMap<Coords, Character> getObstacles(int x, int y){
		findObstacles(x, y);
		return this.obstacles;
	}
	
	public HashMap<Coords, Double> getCoordsDistance(int x, int y){
		findObstacles(x, y);
		this.distance = sortByValue(this.distance);
		return this.distance;
	}

	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		double dist = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
		return dist;
	}

	public void setMaxRange(float maxRange) {
		this.maxRange = maxRange;
	}

	public float getMaxRange() {
		return maxRange;
	}
	
	   public boolean isThereAnyObstacleinFront(int x, int y){
	        if (direction == 'e'){
	            if(map.getPoint(x+1, y) != ' '){
	                return false;
	            }
	            else{ return true;}
	        }
	        else{
	            if(map.getPoint(x, y-1) != ' '){
	                return false;
	            }
	            else{ return true;}
	        }
	    }    

	    public boolean isPossibleToSlideLeft(int x, int y){ //klo east aj ya
	        if(map.getPoint(x+1, y+1) != ' ')
	            return false;
	        else{
	            if(map.getPoint(x, y+1) != ' ')
	                return false;
	            else{return true;}
	        }
	    }
	    public boolean isPossibleToSlideRight(int x, int y){       //kalo east aja ya
	        if(map.getPoint(x+1, y-1) != ' ')
	            return false;
	        else{
	            if(map.getPoint(x, y-1) != ' ')
	                return false;
	            else{return true;}
	        }
	    }


	   // function to sort hashmap by values 
	   public static HashMap<Coords, Double> sortByValue(HashMap<Coords, Double> hm){ 
		// Create a list from elements of HashMap 
		List<Map.Entry<Coords, Double> > list = 
			new LinkedList<Map.Entry<Coords, Double> >(hm.entrySet()); 

		// Sort the list 
		Collections.sort(list, new Comparator<Map.Entry<Coords, Double> >() { 
		 public int compare(Map.Entry<Coords, Double> o1,  
						  Map.Entry<Coords, Double> o2){ 
		   return (o1.getValue()).compareTo(o2.getValue()); 
		} 
		}); 
	 
		// put data from sorted list to hashmap  
		HashMap<Coords, Double> temp = new LinkedHashMap<Coords, Double>(); 
		for (Map.Entry<Coords, Double> aa : list) { 
			temp.put(aa.getKey(), aa.getValue()); 
		} 
		return temp; 
	}
//	
//	public void findObstacles(int x, int y){
//		char[][] road = map.getRoad();
//		HashMap<Coords, Character> obstacles = new HashMap<Coords, Character>();
//		HashMap<Coords, Double> distance = new HashMap<Coords, Double>();
//		if(direction == 'e') {
//			if(x == 1) {
//				for(int i = x; i<x+5; i++) {
//					for(int j = y; j < y+5; j++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 2) {
//				for (int i = x-1; i<x+5; i++) {
//					for(int j = y; j < y+5; j++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 3) {
//				for (int i = x-2; i <x+5; i++) {
//					for(int j = y; j < y+5; j++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 4) {
//				for(int i = x - 3; i < x+5; i++) {
//					for(int j = y; j < y+5; j++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x >= 5) {
//				for(int i = x-4; i<x+5; i++) {
//					for(int j = y; j < y+5; j++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//		}
//		// madep south
//		else if( direction == 's') {
//			if(x <16) {
//				for(int i = x+1; i < x+5; i++) {
//					for(int j = y-5; y< y+5; y++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 16) {
//				for(int i = x+1; i < x+4; i++) {
//					for(int j = y-5; y< y+5; y++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 17) {
//				for(int i = x+1; i < x+3; i++) {
//					for(int j = y-5; y< y+5; y++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 18) {
//				for(int i = x+1; i < x+2; i++) {
//					for(int j = y-5; y< y+4; y++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 19) {
//				for(int i = x+1; i < x+1; i++) {
//					for(int j = y-5; y< y+4; y++) {
//						if(road[i][j] != ' ' && road[i][j] != '.') {
//							obstacles.put(new Coords(i,j), road[i][j]);
//							distance.put(new Coords(i,j), calculateDistance(x, y, i, j));
//						}
//					}
//				}
//			}
//			else if(x == 20) {
//				
//				for(int j = y-5; y< y+4; y++) {
//					if(road[x][j] != ' ' && road[x][j] != '.') {
//						obstacles.put(new Coords(x,j), road[x][j]);
//						distance.put(new Coords(x,j), calculateDistance(x, y, x, j));
//					}
//				}				
//			}
//		}
//		
//	}
	



	
	/*public HashMap<Coords, Character> findObstacles(int x, int y){
	char[][] road = map.getRoad();
	HashMap<Coords, Character> obstacle = new HashMap<Coords,Character>();
	if(direction == 'e') {
	for(int i = y - 2; i <= y + 2; i++){
	for(int j = x + 1; i <= x + 4; j++){
//	if(road[i][j] != ' ' && road[i][j] = '.')
	if(road[i][j] == 'S'){
	obstacle.put(new Coords(i,j), road[i][j]);
	}
	else if(road[i][j] == 'P') {
	obstacle.put(new Coords(i,j), road[i][j]);
	}

	        }
	    }}
	    else if( direction == 'w') {
	     for(int i = y - 2; i <= y + 2; i++){
	            for(int j = x - 1; i <= x - 4; j++){
	             if(road[i][j] == 'S'){
	                    obstacle.put(new Coords(i,j), road[i][j]);
	                }
	                else if(road[i][j] == 'P') {
	                 obstacle.put(new Coords(i,j), road[i][j]);
	                }
	            }
	        }
	    }
	    else if(direction == 'n') {
	     for(int i = x - 2; i <= x + 2; i++){
	            for(int j = y + 1; i <= y + 4; j++){
	             if(road[i][j] == 'S'){
	                    obstacle.put(new Coords(i,j), road[i][j]);
	                }
	                else if(road[i][j] == 'P') {
	                 obstacle.put(new Coords(i,j), road[i][j]);
	                }
	            }
	        }
	    }
	    else {
	     for(int i = x - 2; i <= x + 2; i++){
	            for(int j = y - 1; i <= y - 4; j++){
	             if(road[i][j] == 'S'){
	                    obstacle.put(new Coords(i,j), road[i][j]);
	                }
	                else if(road[i][j] == 'P') {
	                 obstacle.put(new Coords(i,j), road[i][j]);
	                }
	            }
	        }
	    }
	    return obstacle;
	}*/
		/*public CameraCensor(float maxRange, float x, float y, float x2, float y2){
		setMaxRange(maxRange);
		setX(x);
		setY(y);
		}
		*/

		/* public float calculateDistanceXAxis() {
		return x2 - x;
		}
	
		public float calculateDistanceYAxis() {
		return y2-y;
		}

	*/
	// 	public float read(boolean isVertical) {
	//// float maxDist;
	// if(isVertical) {
	// return this.y + this.maxRange;
	// }
	// else {
	// return this.x + this.maxRange;
	// }
	// }
/*
		public void setX(float x) {
		this.x = x;
		}
		
		public float getX(){
		return x;
		}
		
		public void setY(float y) {
		this.y = y;
		}
		
		public float getY(){
		return y;
		}
/
/
		public String sendWarnings(){
		if(isVertical) {
		if(this.calculateDistanceYAxis() < this.maxRange && this.calculateDistanceYAxis() >0) {
		return "You are way to close to the car in front of you";
		}
		else if(this.calculateDistanceYAxis() < 0 && this.calculateDistanceYAxis() > -this.maxRange) {
		return "You are way to close to the car behind you";
		}
		else
		return "You are in a safe postion";
		}
		else {
		if(this.calculateDistanceXAxis() < this.maxRange && this.calculateDistanceYAxis() >0) {
		return "You are way to close to the car on your right";
		}
		else if(this.calculateDistanceXAxis() < 0 && this.calculateDistanceYAxis() > -this.maxRange) {
		return "You are way to close to the car on your left";
		}
		else
		return "You are in a safe postion";
		}
		}
		*/
}
