package CarSimulator;

import java.util.*;
import java.util.Map.Entry;
public class Car {
    
	private Speedometer speedometer;
	private int x, y;
	//private boolean isVisible;
	private int whenLeftTurn, whenRightTurn;
	private CameraCensor censor;
	//private HashMap<Coords, Character> obstacles;
	private char direction;
	//private Pedals pedals;

	//Car default constructor
	public Car (){
		int a = (int) Math.floor(Math.random() * (3 - 1) + 1);
        int b = 0;
		setCoords(a, b);
		this.direction = 'e'; //the default direction is east
		speedometer = new Speedometer(10,0);
	    censor = new CameraCensor();
	}

	//another car constructor
	public Car (int x, int y, char direction){
		setCoords(x, y);
		this.direction = direction;
		speedometer = new Speedometer(10,10);
	    censor = new CameraCensor();
	}
	public CameraCensor getCameraCensor(){
		return this.censor;
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

	public boolean run1(){
		censor.doScanRoad(x, y, direction);

		if(censor.isThereRedTrafficLight(direction)){
			if(censor.getObsCol() == 0){
				decelerateToN(0);
				return true;
			}
			else
				moveForward();
				return true;
		}
		else if(censor.isThereAnyObstacleinFront(x, y, direction)){
			if(censor.isPossibleToMoveDiagonallyToTheLeft(direction)==true){
				moveDiagonallyToTheLeft();
				return true;	
			}
			else if(censor.isPossibleToMoveDiagonallyToTheRight(direction)){
				moveDiagonallyToTheRight();
				return true;
			} 
			else{
				decelerateToN(0);
				return false;} //game over, karena sudah tidak bisa belok kemanapun
		}
		else if (censor.isThereParkingSignOnTheLeft(direction)){
			if(censor.reachedDestination(direction)){
				decelerateToN(0);
				return false;
			}
			else{
				//cek serong depan kiri mobil, 
					if(censor.isPossibleToMoveDiagonallyToTheLeft(direction)) {
						moveDiagonallyToTheLeft();
						return true;
					}
					//kalo misal ada obstacle dikiri, kita maju lurus
					else {
						moveForward();
						return true;
					}
				}
		}
		else if (censor.isThereParkingSignOnTheRight(direction)){
			if(censor.reachedDestination(direction)){
				decelerateToN(0);
				return false;
			}
			else{
				if(censor.isPossibleToMoveDiagonallyToTheRight(direction)) {
					moveDiagonallyToTheRight();
					return true;
				}
				else{
					moveForward();
					return true;
				}
			}	
		}				
			//else if //apakah bisa geser kanan
			//else if//apakah bisa geser kiri
		else if (censor.isThereGreenTrafficLight(direction)){
			accelerateToN(10);
			moveForward();
			return true;
		}		
		moveForward();
		return true;
	}
	
	public void moveForward(){
		int temp_x = getX();
		int temp_y = getY();
        
		if(direction == 'w'){
			temp_y = temp_y - (int)(speedometer.getSpeed()/10);
		} else if(direction == 'e'){
			temp_y = temp_y + (int)(speedometer.getSpeed()/10);
		} else if(direction == 'n'){
			temp_x = temp_x - (int)(speedometer.getSpeed()/10);
		} else if(direction == 's'){
			temp_x = temp_x + (int)(speedometer.getSpeed()/10);
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
			setCoords(temp_x, temp_y);
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
	
	public String carPosition(){
		if(direction == 'e')
			return "Car is driving on ("+x+", "+y+") axis and going to east direction.";
		if(direction == 'n')
			return "Car is driving on ("+x+", "+y+") axis and going to north direction.";
		if(direction == 'w')
			return "Car is driving on ("+x+", "+y+") axis and going to west direction.";
		else
			return "Car is driving on ("+x+", "+y+") axis and going to south direction.";
	}



}


