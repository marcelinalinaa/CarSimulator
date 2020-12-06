package CarSimulator;

public class CameraCensor{
	public RoadMap map;
	public static final int censorRange = 3;
	private char[][] tempRoad ;
	private int obsCol;
	private int obsRow;
	private boolean redTrafficLight = false;
	private boolean greenTrafficLight = false;
	public boolean parkingSignOnTheLeft = false;
	private boolean parkingSignOnTheRight = false;
	private boolean obstacleInFront = false;

	public CameraCensor(){
	map = new RoadMap();
	this.tempRoad = null;
	}
	public void doScanRoad(int x, int y, char direction){
		tempRoad = scanRoad(x, y, direction);
	}
	public char[][] scanRoad(int y, int x, char direction){ // parameter x dan y jadi diubah
		char[][] road = map.getRoad();
		char[][] temp = new char[5][5];
		if(direction == 'e') {				
			for(int i = y - 2; i <= y + 2; i++){ 
				for(int j = x; j <= x + 4; j++){ 
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
			for(int j=0; j<7; j++){
				if(tempRoad[3][j] == 'C'){
					setObsCol(j);
					obstacleInFront = true;
				}
			}
			obstacleInFront = false; 
	    }
	    else{ // direction to south
			for(int i=0; i<7; i++){
				if(tempRoad[i][3] == 'C'){
					setObsRow(i);
	                obstacleInFront = true;
	            }
			}
			obstacleInFront = false;
		} 
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
			else
				redTrafficLight = false;
			return redTrafficLight;
		}
		
		public boolean isThereGreenTrafficLight(char direction) {
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
			else greenTrafficLight = false;
			return greenTrafficLight;
		}	 


		public boolean isThereParkingSignOnTheLeft(char direction) {
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
			else parkingSignOnTheLeft = false;
			return parkingSignOnTheLeft;
		   }
		   
		public boolean isThereParkingSignOnTheRight(char direction) {
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
			else parkingSignOnTheRight = false;
			return parkingSignOnTheRight;
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


/*	
	//dari eric 22:50
	public void findTurns(RoadMap road){
	    boolean[][] temp = censor.findRoad(x, y);
        if(direction == 'e') {
            for(int i = 4; i >= 0; i--){
                if(temp[0][i] && temp[1][i]){
                    setLeftTurn(i + 1);
                } else if(temp[3][i] && temp[4][i]){
                    setRightTurn(i + 1);
                }
            }
        } else if(direction == 'w') {
            for(int i = 0; i <= 4; i++){
				if(temp[3][i] && temp[4][i]){
                    setLeftTurn(5 - i);
                } else if(temp[0][i] && temp[1][i]){
                    setRightTurn(5 - i);
                }
			}
        } else if(direction == 'n') {
            for(int i = 0; i <= 4; i++){
				if(temp[i][0] && temp [i][1]){
					setLeftTurn(5 - i);
				} else if(temp[i][3] && temp[i][4]){
					setRightTurn(5 - i);
				}
			}
        } else {
            for(int i = 4; i >= 0; i++){
				if(temp[i][3] && temp[i][4]){
					setLeftTurn(i + 1);
				} else if(temp[i][0] && temp[i][1]){
					setRightTurn(i + 1);
				}
			}
        }
	}	

*/

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
		


     