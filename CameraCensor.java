package CarSimulator;

public class CameraCensor extends Censor{
	public RoadMap map;
	private char[][] tempRoad ;
	private int obsCol;
	private int obsRow;
	private boolean redTrafficLight = false;
	private boolean greenTrafficLight = false;
	private boolean parkingSignOnTheLeft = false;
	private boolean parkingSignOnTheRight = false;
	private boolean obstacleInFront = false;
	private boolean haveReachedDestination = false;

	public CameraCensor(){
		map = Main.getMap();
		this.tempRoad = null;
		setName("CameraCensor");
	}

	public void doScanRoad(int row, int col, String direction){
		tempRoad = scan(row, col, direction);
	}
	@Override
	public char[][] scan(int row, int col, String direction){ 
		char[][] road = map.getRoad();
		char[][] temp = new char[7][7];
		if(direction.equals("e")){
			for(int i = row - 3; i <= row + 3; i++){ 
				for(int j = col; j <= col + 6; j++){ 
					if((i >= 0 && i < RoadMap.MAP_HEIGHT) && (j >= 0 && j < RoadMap.MAP_WIDTH)){
						temp[i - row + 3][j - col] = road[i][j];
					}
					else {
						temp[i - row + 3][j - col] = '0';
					}
				}
			}
		}
		return temp;
	}

	public boolean isThereAnyObstacleinFront(int x, int y, String direction){	
		obstacleInFront = false;
		if (direction.equals("e")){
			if(tempRoad[3][1] == 'C'){
				setObsCol(1);
				obstacleInFront = true;
			}
		} else { obstacleInFront = false; }
		return obstacleInFront;     
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

	public boolean isPossibleToMoveDiagonallyToTheLeft (String direction) { 
		if(direction.equals("e")){		//cek jika mobil arah east
			if(tempRoad[2][0] != ' ') //jika misalkan di sebelah kiri mobil ada sesuatu
	        	return false;		 //  maka tidak mungkin belok
			else{
				if(tempRoad[2][getObsCol()] != ' ') // jika tidak, di cek apakah di sebelah kiri objek ada sesuatu
					return false;					//jika iya maka tidak mungkin belok kiri
				else{return true;}					//jika tidak, maka akan belok kiri 
				}
		}
		return false;
	}
		
	    public boolean isPossibleToMoveDiagonallyToTheRight(String direction){
			if(direction.equals("e")){		//cek jika mobil arah east
				if(tempRoad[4][0] != ' ') //jika misalkan di sebelah kanan mobil ada sesuatu
					return false;		 //  maka tidak mungkin belok
				else{
					if(tempRoad[4][getObsCol()] != ' ') // jika tidak, di cek apakah di sebelah kanan objek ada sesuatu
						return false;					//jika iya maka tidak mungkin belok kanan
					else{return true;}					//jika tidak, maka akan belok kanan 
					}
			}
			return false;
	    }

	public boolean isPossibleMoveToTheLeft(String direction){
		if(direction.equals("e")){
			if(tempRoad[2][0] != ' ') //jika misalkan di sebelah kiri mobil ada sesuatu
				return false;
			else return true;		 //  maka tidak mungkin belok
		}
		return false;		
	} 

	public boolean isPossibleMoveToTheRight(String direction){
		if(direction.equals("e")){
			if(tempRoad[4][0] != ' ') //jika misalkan di sebelah kanan mobil ada sesuatu
				return false;		 //  maka tidak mungkin belok
			else return true;	
		}
		return false;
	} 
		
	public boolean isThereRedTrafficLight(String direction) {	
			redTrafficLight = false;
			if(direction.equals("e")) {
				for(int j = 0; j< 7; j++) {
					for(int i = 0; i < 7; i++) {
						if(tempRoad[i][j] == 'R') {
							setObsCol(j);
							redTrafficLight = true;
						}
					}
				}
			}
			else{
				redTrafficLight = false;
			}
			return redTrafficLight;
		}
		
		public boolean isThereGreenTrafficLight(String direction) {
			greenTrafficLight = false;
			if(direction.equals("e")) {
				for(int j = 0; j< 7; j++) {
					for(int i = 0; i < 7 ; i++) {
						if(tempRoad[i][j] == 'G') {
							greenTrafficLight = true;
						}
					}
				}
			}
			else { greenTrafficLight = false; }
			return greenTrafficLight;
		}	 

		public boolean isThereParkingSignOnTheLeft(String direction) {
			parkingSignOnTheLeft = false;
			if(direction.equals("e")) {
				for(int j = 0; j <7; j++) {
					for(int i = 0; i <4; i++) {
			   			if(tempRoad[i][j]=='P') {
							setObsRow(i);
							setObsCol(j);
							parkingSignOnTheLeft = true;
			   			}
			  		}
			 	}
			}
			else { parkingSignOnTheLeft = false; }
			return parkingSignOnTheLeft;
		   }
		   
		public boolean isThereParkingSignOnTheRight(String direction) {
			parkingSignOnTheRight = false;
			if(direction.equals("e")) {
			 	for(int j = 0; j <7; j++) {
			  		for(int i = 3; i <7; i++) {
			   			if(tempRoad[i][j]=='P') {
							setObsRow(i);
							setObsCol(j);
							parkingSignOnTheRight = true;
			  			}
			  		}
			 	}
			}
			else { parkingSignOnTheRight = false; }
			return parkingSignOnTheRight;
		   }

			public boolean reachedDestination(String direction){
				haveReachedDestination = false;
				if(direction.equals("e")) {
					if(getObsRow() < 3){
						if(getObsRow() == 2 && getObsCol() == 0){
							haveReachedDestination = true;
						}
					}
					else if(getObsRow()>3){
						if(getObsRow() == 4 && getObsCol() == 0){
							haveReachedDestination = true;
						}
					}
				}
				else {haveReachedDestination = false;}
				return haveReachedDestination;
			}

	@Override
	public String status(){
		if(haveReachedDestination)
			return "You have reached your destination.";
		if(redTrafficLight)
			return "Warning: The traffic light ahead you is red.";
		else if(obstacleInFront)
			return "Warning: There is cone ahead you.";
		else if(greenTrafficLight)
			return "The traffic light ahead you is green.";
		else if(parkingSignOnTheLeft)
			return "There's parking sign on your left.";
		else if(parkingSignOnTheRight)
			return "There's parking sign on your right.";
		else 
			return "You are on the right track.";
	}

}
		


     


     