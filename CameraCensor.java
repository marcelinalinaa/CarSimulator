package CarSimulator;


public class CameraCensor{
	public RoadMap map;
	public static final int censorRange = 3;
	private char[][] tempRoad ;
	private int obsCol;
	private int obsRow;
	private boolean redTrafficLight = false;
	private boolean greenTrafficLight = false;
	private boolean parkingSignOnTheLeft = false;
	private boolean parkingSignOnTheRight = false;
	// update
	private boolean leftTurnSign = false;
	private boolean rightTurnSign = false;
	private boolean obstacleInFront = false;

	public CameraCensor(){
		map = new RoadMap();
		this.tempRoad = null;
	}
	public void doScanRoad(int row, int col, char direction){
		tempRoad = scanRoad(row, col, direction);
	}
	public char[][] scanRoad(int row, int col, char direction){ // parameter x dan y jadi diubah
		char[][] road = map.getRoad();
		char[][] temp = new char[7][7];
		if(direction == 'e') {				
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
		} else if(direction == 's'){
			for(int i = col - 3; i <= col + 3; i++){
				for(int j = row; j >= row + 6; j++){
					if(i >= 0 && i < RoadMap.MAP_WIDTH && j >= 0 && j < RoadMap.MAP_HEIGHT){
						temp[i - col + 3][j - row] = road[i][j];
					}
					else {
						temp[i - col + 3][j - row] = '0';
					}
				}
			}
		}
		
		return temp;
	}

	public boolean isThereAnyObstacleinFront(int x, int y, char direction){	
		obstacleInFront = false;
		if (direction == 'e'){
			if(tempRoad[3][1] == 'C'){
				setObsCol(1);
				obstacleInFront = true;
			}
		} else if(direction == 's'){ // direction to south
			if(tempRoad[1][3] == 'C'){
				setObsRow(1);
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

	public boolean isPossibleToMoveDiagonallyToTheLeft (char direction) { 
		if(direction == 'e'){		//cek jika mobil arah east
			if(tempRoad[2][0] != ' ') //jika misalkan di sebelah kiri mobil ada sesuatu
	        	return false;		 //  maka tidak mungkin belok
			else{
				if(tempRoad[2][getObsCol()] != ' ') // jika tidak, di cek apakah di sebelah kiri objek ada sesuatu
					return false;					//jika iya maka tidak mungkin belok kiri
				else{return true;}					//jika tidak, maka akan belok kiri 
				}
		}else if(direction == 's'){						//cek jika mobil arah south
			if(tempRoad[0][4] != ' ') //jika misalkan di sebelah kiri mobil ada sesuatu
	        	return false;		 //  maka tidak mungkin belok
			else{
				if(tempRoad[getObsRow()][4] != ' ') // jika tidak, di cek apakah di sebelah kiri objek ada sesuatu
					return false;					//jika iya maka tidak mungkin belok kiri
				else{return true;}					//jika tidak, maka akan belok kiri 
				}
		}  
		return false;
	}
		
	    public boolean isPossibleToMoveDiagonallyToTheRight(char direction){
			if(direction == 'e'){		//cek jika mobil arah east
				if(tempRoad[4][0] != ' ') //jika misalkan di sebelah kanan mobil ada sesuatu
					return false;		 //  maka tidak mungkin belok
				else{
					if(tempRoad[4][getObsCol()] != ' ') // jika tidak, di cek apakah di sebelah kanan objek ada sesuatu
						return false;					//jika iya maka tidak mungkin belok kanan
					else{return true;}					//jika tidak, maka akan belok kanan 
					}
			}else if(direction == 's'){						//cek jika mobil arah south
				if(tempRoad[0][2] != ' ') //jika misalkan di sebelah kanan mobil ada sesuatu
					return false;		 //  maka tidak mungkin belok
				else{
					if(tempRoad[getObsRow()][2] != ' ') // jika tidak, di cek apakah di sebelah kanan objek ada sesuatu
						return false;					//jika iya maka tidak mungkin belok kanan
					else{return true;}					//jika tidak, maka akan belok kanan
					}
			
			}
			return false;
	    }

		public boolean isThereRedTrafficLight(char direction) {	
			redTrafficLight = false;
			if(direction == 'e') {
				for(int j = 0; j< 7; j++) {
					for(int i = 0; i < 7; i++) {
						if(tempRoad[i][j] == 'R') {
							setObsCol(j);
							redTrafficLight = true;
						}
					}
				}
			}
			else if (direction == 's') {
				for(int i = 0; i < 7; i++) {
					for(int j = 0; j < 7; j++) {
						if(tempRoad[i][j] == 'R'){
							setObsRow(i);
							redTrafficLight =  true;
						}
					}
				}	
			}
			else{
				redTrafficLight = false;
			}
			return redTrafficLight;
		}
		
		public boolean isThereGreenTrafficLight(char direction) {
			greenTrafficLight = false;
			if(direction =='e') {
				for(int j = 0; j< 7; j++) {
					for(int i = 0; i < 7 ; i++) {
						if(tempRoad[i][j] == 'G') {
							greenTrafficLight = true;
						}
					}
				}
			}
			else if (direction == 's') {
				for(int i = 0; i< 7;i++) {
					for(int j = 0; j < 7; j++) {
						if(tempRoad[i][j] == 'G'){
							greenTrafficLight = true;
						}
					}
				}
			}
			else { greenTrafficLight = false; }
			return greenTrafficLight;
		}	 

		public boolean isThereParkingSignOnTheLeft(char direction) {
			parkingSignOnTheLeft = false;
			if(direction =='e') {
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
			else if(direction == 's') {
			 	for(int i = 0; i <7; i++) {
			 	 	for(int j = 3; j<7; j++) {
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
		   
		public boolean isThereParkingSignOnTheRight(char direction) {
			parkingSignOnTheRight = false;
			if(direction =='e') {
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
			else if(direction == 's') {
			 	for(int i = 0; i <7; i++) {
			  		for(int j = 0; j<4; j++) {
			   			if(tempRoad[i][j]=='P') {
							setObsRow(i);
							setObsCol(j);
							parkingSignOnTheRight= true;
			   			}
			  		}
			 	}
			}
			else { parkingSignOnTheRight = false; }
			return parkingSignOnTheRight;
		   }

		// update
		public boolean isThereRightTurnSign(char direction){
			rightTurnSign = false;
			if(direction =='e') {
				for(int j = 0; j <7; j++) {
					for(int i = 0; i <7; i++) {
						if(tempRoad[i][j]=='>') {
							setObsRow(i);
					   		setObsCol(j);
				   			rightTurnSign = true;
				  		}
				 	}
				}
			} else if(direction == 's') {
				for(int i = 0; i <7; i++) {
					for(int j = 0; j<7; j++) {
						if(tempRoad[i][j]=='>') {
							setObsRow(i);
							setObsCol(j);
							rightTurnSign = true;
						}
					}
				}
			} else rightTurnSign = false;
			return rightTurnSign;
		}

		// update
		public boolean isThereLeftTurnSign(char direction) {
			leftTurnSign = false;
			if(direction =='e') {
				for(int j = 0; j <7; j++) {
					for(int i = 0; i < 7; i++) {
						if(tempRoad[i][j]=='<') {
							setObsRow(i);
							setObsCol(j);
							leftTurnSign = true;
						}
					}
				}
			} else if(direction == 's') {
				for(int i = 0; i <7; i++) {
					for(int j = 0; j<7; j++) {
						if(tempRoad[i][j]=='<') {
							setObsRow(i);
							setObsCol(j);
							leftTurnSign = true;
						}
					}
				}
			}
			else leftTurnSign = false;
			return leftTurnSign;
		}

			public boolean reachedDestination(char direction){
				if(direction =='e') {
					if(getObsRow() < 3){
						if(getObsRow() == 2 && getObsCol() == 0){
							return true;
						}
					}
					else if(getObsRow()>3){
						if(getObsRow() == 4 && getObsCol() == 0){
							return true;
						}
					}
				}
				else if(direction == 's') {
					if(getObsCol() < 3){
						if(getObsCol() == 2 && getObsRow() == 0){
							return true;
						}
					}
					else if(getObsCol()>3){
						if(getObsCol() == 4 && getObsRow() == 0){
							return true;
						}
					}
					
				}
				return false;
			}

	public String status(){
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
		


     


     
