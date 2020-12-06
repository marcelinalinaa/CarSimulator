package carSimulator;

import java.util.*;

public class CameraCensor{
	public RoadMap map;
	public static final int censorRange = 3;
	//private HashMap<Coords, Character> obstacles = new HashMap<Coords, Character>();
	//private HashMap<Coords, Double> distance = new HashMap<Coords, Double>();
	private char[][] tempRoad ;
	private int obsCol;
	private int obsRow;

	public CameraCensor(char direction){
	map = new RoadMap();
	//this.obstacles = null;
	//this.distance = null;
	this.tempRoad = null;
	}
	
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
	
	public void doScanRoad(int x, int y, char direction){
		tempRoad = scanRoad(x, y, direction);
	}

	public char[][] scanRoad(int y, int x, char direction){
		char[][] road = map.getRoad();
		char[][] temp = new char[5][5];
		if(direction == 'e') {				//misal kita di (1,0)
			for(int i = y - 2; i <= y + 2; i++){ //i = -2
				for(int j = x; j <= x + 4; j++){  // j=1
					if((i >= 0 && i < 15) && (j >= 0 && j < 35)){
			
						temp[i - y + 2][j - x] = road[i][j];
					}
					else {
						temp[i - y + 2][j - x] = '0';
					}
				}
			}
		} else if(direction == 'w') {
			for(int i = y - 2; i <= y + 2; i++){
				for(int j = x; j >= x - 4; j--){
					if(i >= 0 && i < 15 || j >= 0 && j < 35){
						temp[i - y + 2][j - x + 4] = road[i][j];
					}
					else {
						temp[i - y + 2][j - x + 4] = '0';
					}
				}
			}
		} else if(direction == 'n') {
			for(int i = x - 2; i <= x + 2; i++){
				for(int j = y; j <= y + 4; j++){
					if(i >= 0 && i < 35 || j >= 0 && j < 15){
						temp[i - x + 2][j - y] = road[i][j];
					}
					else {
						temp[i - x + 2][j - y] = '0';
					}
				}
			}
		} else {
			for(int i = x - 2; i <= x + 2; i++){
				for(int j = y - 1; j >= y - 5; j--){
					if(i >= 0 && i < 35 || j >= 0 && j < 15){
						temp[i - x + 2][j - y + 4] = road[i][j];
					}
					else {
						temp[i - x + 2][j - y + 4] = '0';
					}
				}
			}
		}
		
		return temp;
	}
		
	public boolean isThereAnyObstacleinFront(int x, int y, char direction){	
		if (direction == 'e'){
			for(int j=0; j<5; j++){
				if(tempRoad[2][j] == 'C'){
					setObsCol(j);
					return true;
				}
			}
			setObsCol(0);
			return false;
	            
	    }
	    else{ // direction to south
			for(int i=0; i<5; i++){
				if(tempRoad[i][2] == 'C'){
					setObsRow(i);
	                return true;
	            }
			}
			setObsRow(0);
			return false;
		}          
	}
	
	
	public void setObsCol(int j){
		obsCol = j;
	}
	public int getObsCol(){
		return this.obsCol;
	}

	public void setObsRow(int i){
		obsRow = i;
	}
	public int getObsRow(){
		return this.obsRow;
	}

	public boolean isPossibleToSlideLeft (char direction) { 
		if(direction == 'e'){		//cek jika mobil arah east
			if(tempRoad[1][0] != ' ') //jika misalkan di sebelah kiri mobil ada sesuatu
	        	return false;		 //  maka tidak mungkin belok
			else{
				if(tempRoad[1][getObsCol()] != ' ') // jika tidak, di cek apakah di sebelah kiri objek ada sesuatu
					return false;					//jika iya maka tidak mungkin belok kiri
				else{return true;}					//jika tidak, maka akan belok kiri 
				}
		}else{						//cek jika mobil arah south
			if(tempRoad[0][3] != ' ') //jika misalkan di sebelah kiri mobil ada sesuatu
	        	return false;		 //  maka tidak mungkin belok
			else{
				if(tempRoad[getObsRow()][3] != ' ') // jika tidak, di cek apakah di sebelah kiri objek ada sesuatu
					return false;					//jika iya maka tidak mungkin belok kiri
				else{return true;}					//jika tidak, maka akan belok kiri 
				}
		}  
	}
		
	
	public boolean isPossibleToSlideRight(char direction){
			if(direction == 'e'){		//cek jika mobil arah east
				if(tempRoad[3][0] != ' ') //jika misalkan di sebelah kanan mobil ada sesuatu
					return false;		 //  maka tidak mungkin belok
				else{
					if(tempRoad[3][getObsCol()] != ' ') // jika tidak, di cek apakah di sebelah kanan objek ada sesuatu
						return false;					//jika iya maka tidak mungkin belok kanan
					else{return true;}					//jika tidak, maka akan belok kanan 
					}
			}else{						//cek jika mobil arah south
				if(tempRoad[0][1] != ' ') //jika misalkan di sebelah kanan mobil ada sesuatu
					return false;		 //  maka tidak mungkin belok
				else{
					if(tempRoad[getObsRow()][1] != ' ') // jika tidak, di cek apakah di sebelah kanan objek ada sesuatu
						return false;					//jika iya maka tidak mungkin belok kanan
					else{return true;}					//jika tidak, maka akan belok kanan
					}
			
	        }
	    }
	
		public boolean isPossibleToMoveDiagonallyToTheLeft() {
		if(direction =='e') {
			if(tempRoad[1][1] == ' ') {
				return true;
			}
		}
		else if(direction =='s') {
//			ini bayanginnya kyk east cmn posisi car itu ada di (0,2)
			if(tempRoad[1][3] == ' ') {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPossibleToMoveDiagonallyToTheRight() {
		if(direction =='e') {
			if(tempRoad[3][1] == ' ') {
				return true;
			}
		}
		else if(direction =='s') {
			if(tempRoad[1][1] == ' ') {
				return true;
			}
		}
		return false;
	}
	
	public boolean isThereRedTrafficLight() {	
		if(direction == 'e') {
			for(int j = 0; j<= 4; j++) {
			for(int i = 0; i <=4; i++) {
				if(tempRoad[i][j] == 'R') {
					setObsCol(j);
					return true;
				}
			}
		}
		}
		else if (direction == 's') {
			for(int i = 0; i <=4; i++) {
				for(int j = 0; j <=4; j++) {
					if(tempRoad[i][j] == 'R'){
						setObsRow(i);
						return true;
					}
				}
			}	
		}
		return false;
	}
	
	public boolean isThereGreenTrafficLight() {
		if(direction =='e') {
			for(int j = 0; j<= 4; j++) {
			for(int i = 0; i <=4 ; i++) {
					if(tempRoad[i][j] == 'G') {
						return true;
					}
				}
			}
		}
		else if (direction == 's') {
			for(int i = 0; i<=4;i++) {
				for(int j = 0; j <=4; j++) {
					if(tempRoad[i][j] == 'G'){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isThereParkingSign() {
		if(direction =='e') {
			for(int j = 0; j <5; j++) {
				for(int i = 0; i <=4; i++) {
					if(tempRoad[i][j]=='P') {
						setObsRow(i);
						setObsCol(j);
						return true;
					}
				}
			}
		}
		else if(direction == 's') {
			for(int i = 0; i <5; i++) {
				for(int j = 0; j<5; j++) {
					if(tempRoad[i][j]=='P') {
						setObsRow(i);
						setObsCol(j);
						return true;
					}
				}
			}
		}
		return false;
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
