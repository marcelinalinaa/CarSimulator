package carSimulator;

import java.util.*;
import java.util.Map.Entry;

public class Car {
    
	private Speedometer speedometer;
	private Steer steer;
	private int x, y;
	//private boolean isVisible;
	private int whenLeftTurn, whenRightTurn;
	private CameraCensor censor;
	private HashMap<Coords, Character> obstacles;
	private char direction;
	private Pedals pedals;

	public Car (int x, int y, char direction){
	    setCoords(x, y);
	    censor = new CameraCensor(direction);
	}

	public void setCoords(int x, int y){
	    this.x = x;
	    this.y = y;
	}

	public int getX(){
	    return x;
	}

	public int getY(){
	    return y;
	}
	
	public void run(){
        if(direction == 'e'){
            y = y + (speedometer.getSpeed()/10);
        } else{
            x = x + (speedometer.getSpeed()/10);
        }
	}
	
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

	public void moveForward(){
		int temp_x = getX();
		int temp_y = getY();
        
		if(direction == 'w'){
			temp_x = temp_x - (int)(speedometer.getSpeed()/10);
		} else if(direction == 'e'){
			temp_x = temp_x + (int)(speedometer.getSpeed()/10);
		} else if(direction == 'n'){
			temp_y = temp_y - (int)(speedometer.getSpeed()/10);
		} else if(direction == 's'){
			temp_y = temp_y + (int)(speedometer.getSpeed()/10);
		}
		setCoords(temp_x, temp_y);
    	}

	public void moveRight(){
		int temp_x = getX();
		int temp_y = getY();
		if(getRightTurn() == 0){
			if(direction == 'e'){
//				setCoords(x, y + 1);
				temp_y = temp_y + (int) (speedometer.getSpeed()/10);
			} else if(direction == 'n'){
//				setCoords(x + 1, y);
				temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			} else if(direction == 'w'){
//				setCoords(x, y - 1);
				temp_y = temp_y - (int) (speedometer.getSpeed()/10);
			} else {
//				setCoords(x - 1, y);
				temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			}
		}
	}

	public void turnLeft(){
		if(getLeftTurn() == 0){
			if(direction == 'e'){
				direction = 'n';
			} else if(direction == 'n'){
				direction = 'w';
			} else if(direction == 'w'){
				direction = 's';
			} else {
				direction = 'w';
			}
		}
	}

	public void moveLeft(){
		int temp_x = getX();
		int temp_y = getY();
		if(getLeftTurn() == 0){
			if(direction == 'e'){
//				setCoords(x, y - 1);
				temp_y = temp_y - (int) (speedometer.getSpeed()/10);
			} else if(direction == 'n'){
				temp_x = temp_x - (int) (speedometer.getSpeed()/10);
//				setCoords(x - 1, y);
			} else if(direction == 'w'){
//				setCoords(x, y + 1);
				temp_y = temp_y + (int) (speedometer.getSpeed()/10);
			} else {
//				setCoords(x + 1, y);
				temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			}
		}
		setCoords(temp_x, temp_y);
	}
	
	//dibuat 22:18 5 dec
	public void moveDiagonallyToTheLeft() {
		int temp_x = getX();
		int temp_y = getY();
		if(direction == 'e') {
			temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			temp_y = temp_y + (int) (speedometer.getSpeed()/10);
		}
		else if(direction == 's') {
			temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			temp_y = temp_y + (int) (speedometer.getSpeed()/10);
		}
		else if(direction == 'n') {
			temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			temp_y = temp_y - (int) (speedometer.getSpeed()/10);
		}
		else {
			temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			temp_y = temp_y - (int) (speedometer.getSpeed()/10);
		}
		setCoords(temp_x, temp_y);
	}

	// dibuat 22:20 5 dec
	public void moveDiagonallyToTheRight() {
		int temp_x = getX();
		int temp_y = getY();
		if(direction == 'e') {
			temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			temp_y = temp_y + (int) (speedometer.getSpeed()/10);
		}
		else if(direction == 's') {
			temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			temp_y = temp_y - (int) (speedometer.getSpeed()/10);
		}
		else if(direction == 'n') {
			temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			temp_y = temp_y + (int) (speedometer.getSpeed()/10);
		}
		else {
			temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			temp_y = temp_y - (int) (speedometer.getSpeed()/10);
		}
		setCoords(temp_x, temp_y);
	}
	
	public void action() {
	    obstacles = null;
	    obstacles = censor.getObstacles();
	    HashMap<Coords, Double> distance = censor.getCoordsDistance();
	    List<Coords> keyObstacle = new ArrayList<>(distance.keySet());
	    if(obstacles != null){
	        if(direction == 'e') {
	    	for(int i = 0; i<obstacles.size(); i++)
	        {
//	        	cek apakah ada cone, dan cone tsb sejajar dgn jalur mobil (pas d dpnnya)
	        	if(obstacles.get(keyObstacle.get(i)) == 'C' && x== keyObstacle.get(i).getCoordsX()) {
//	        		cek apakah ada sesuatu di sebelah mobil
	        		for(int j = 0; j < obstacles.size();j++) {
//	        		kalau misalnya cone sejajar (dalam column) dgn mobil	
	        			if(keyObstacle.get(j).getCoordsY() == y) {
	        				// cek serong depan kiri mobil, jika mobil menghadap ke east
	        				if(keyObstacle.get(j).getCoordsX() == x-1 && keyObstacle.get(j).getCoordsY() == y+1 ) {
//	        				cek serong depan kanannya mobil
	        					if(keyObstacle.get(j).getCoordsX() == x+1 && keyObstacle.get(j).getCoordsY() == y+1 ) {
//	        					karena serong kiri kanan ada, makanya stop
	        						pedals.decelerateToN(0);
	        					}
//	        				kalo misal gada dikanan, kita kekanan
	        					else {
	        						moveDiagonallyToTheRight();
	        					}
	        				}
	        				else {
	        					moveDiagonallyToTheLeft();
	        				}
	        				}
	        			}
	        		}
//	        	kalau misal mobil ud sejajar sm rambu merah, dia hrs stop (ini buat east)
	        	else if(obstacles.get(keyObstacle.get(i)) == 'R' && y== keyObstacle.get(i).getCoordsY() ){
	        		pedals.decelerateToN(0);
	        	}
//	        	kalau misal mobil ud sejajar sm rambu merah, dia hrs stop (ini ke south)
	        	else if(obstacles.get(keyObstacle.get(i)) == 'R' && x== keyObstacle.get(i).getCoordsX() ){
	        		pedals.decelerateToN(0);
	        	}
	        	else if(obstacles.get(keyObstacle.get(i)) == 'G') {
	        		moveForward();
	        	}
	        	else if(obstacles.get(keyObstacle.get(i)) == 'P' ) {
	        		int obs_x = keyObstacle.get(i).getCoordsX();
	        		int obs_y = keyObstacle.get(i).getCoordsY();
	        		if(x > obs_x) {
	        			for(int j = 0; j < obstacles.size();j++) {
//	    	        		cek serong depan kanannya mobil
	    	        		if(keyObstacle.get(j).getCoordsX() == x+1 && keyObstacle.get(j).getCoordsY() == y+1 ) {
	    	        			moveForward();
	    	        		}
//	    	        		kalo misal gada dikanan, kita kekanan
	    	        		else {
	    	        			moveDiagonallyToTheRight();
	    	        		}
	    	        	}		
	    	        }
     			
//	        			else if(direction =='w') {
//	        				while(x!=obs_x+1 && y!= obs_y) {
//		        				moveDiagonallyToTheRight();
//		        			}
//	        			}
	        		else if(x < obs_x) {
	        			for(int j = 0; j < obstacles.size();j++) {
	    	        	// cek serong depan kiri mobil, jika mobil menghadap ke east
	    	        		if(keyObstacle.get(j).getCoordsX() == x-1 && keyObstacle.get(j).getCoordsY() == y+1 ) {
	    	        			moveForward();
	    	        		}
//	    	        		kalo misal gada di serong kiri dpn, kita ke serong kiri
	    	        		else {
	    	        			moveDiagonallyToTheLeft();
	    	        		}
	    	        	}				
	        		}
//	        			else if(direction =='w') {
//	        				while(x!=obs_x-1 && y!= obs_y) {
//		        				moveDiagonallyToTheLeft();
//		        			}
//	        			}
	        		else if(y<obs_y) {
	        			if(direction =='s') {
		        			while(x!=obs_x && y!= obs_y-1) {
		        				moveDiagonallyToTheLeft();
		        			}
	        			}
	        			else if(direction =='n') {
	        				while(x!=obs_x && y!= obs_y-1) {
		        				moveDiagonallyToTheRight();
		        			}
	        			}
	        		}
	        		else {
	        			if(direction =='s') {
		        			while(x!=obs_x && y!= obs_y+1) {
		        				moveDiagonallyToTheRight();
		        			}
	        			}
	        			else if(direction =='n') {
	        				while(x!=obs_x && y!= obs_y+1) {
		        				moveDiagonallyToTheLeft();
		        			}
	        			}
	        		}
	        	}
	        	
//	        	else if(obstacles.get(keyObstacle.get(i)) == 'S') {
//	        		//ask do u want to stop? if yes, stop. if not, keep moving
//	        	}
	        }
	      }
	        
	        /*

	        misal punya 2 list, lisst obstacle dan arraylist(x,y)
	            
	        

	        if liat S
	            call method print nanya mau berhenti atau tidak
	            if yes, stop
	            else, keep going
	        if liat P
	            call method print nanya mau berhenti atau tidak
	            park (ke pinggir)
	        if liat orang
	            slowdown
	        if ada lampu merah
	            berhenti
	        if lampu kuning
	            decelerate
	        if lampu hijau 
	            keep going
	        if depan ada mobil
	            cek distance difference(?)
	         */
	    } 
	}
	   //function to get key from given valu
	public static List<Coords> findKey(HashMap<Coords, Double> a, double value) {
		// iterate each entry of hashmap
		List<Coords> max = new ArrayList<Coords>(); 
		for(Entry<Coords, Double> entry: a.entrySet()) {

			// check whether the given value is equal to value from entry
			if(entry.getValue() == value) {
			max.add(entry.getKey());
		}
			}
		return max;
	   }

	public int getRightTurn(){
	    return whenRightTurn;
	}

	public void setRightTurn(int r){
	    whenRightTurn = r;
	}

	public int getLeftTurn(){
	    return whenLeftTurn;
	}

	public void setLeftTurn(int l){
	    whenLeftTurn = l;
	}
//
//	public void findTurns(){
//		 boolean[][] temp = censor.findRoad(x, y);
//	        if(direction == 'e') {
//	            for(int i = 3; i >= 0; i--){
//	                if(temp[0][i] && temp[1][i]){
//	                    setLeftTurn(i + 1);
//	                } else if(temp[3][i] && temp[4][i]){
//	                    setRightTurn(i + 1);
//	                }
//	            }
//	        } else if(direction == 'w') {
//	            ;
//	        } else if(direction == 'n') {
//	            ;
//	        } else {
//	            ;
//	        }
//	}

	public void accelerate(int n){
	    for(int i = 1; i < n; i++){
	        if(speedometer.getSpeed() < Speedometer.MAX_SPEED){
	            speedometer.setSpeed(speedometer.getSpeed() + 1);
	        } else { 
	            speedometer.setSpeed(200);
	            break;
	        }
	    }
	}

	public void accelerateToN(int n){
	    while(speedometer.getSpeed() < n){
	        speedometer.setSpeed(speedometer.getSpeed() + 1);
	        if(speedometer.getSpeed() > n){
	            speedometer.setSpeed(n);
	        }
	    }
	}

	public void decelerate(int n){
	    for(int i  = 1; i < n; i++){
	        if(speedometer.getSpeed() > 0){
	            speedometer.setSpeed(speedometer.getSpeed() - 1);
	        } else {
	            speedometer.setSpeed(0);
	            break;
	        }
	    }
	}
	public void decelerateToN(int n){
	    while(speedometer.getSpeed() > n){
	        speedometer.setSpeed(speedometer.getSpeed() - 1);
	        if(speedometer.getSpeed() < n){
	            speedometer.setSpeed(n);
	        }
	    }
	}
    
	
	
}
