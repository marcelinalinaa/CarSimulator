package CarSimulator;

public class Car {
    
	private Speedometer speedometer;
	private int x, y;
	private int tempX, tempY;
	private CameraCensor censor;
	private String direction;

	//Car default constructor
	public Car (){
		x = (int) Math.floor(Math.random() * (3 - 1) + 1);
        y = 0;
		this.direction = "e"; //the default direction is east
		speedometer = new Speedometer(10,0);
		censor = new CameraCensor();
		tempX = x;
		tempY = y;
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
	public int getTempX(){
		return tempX;
	}
	public int getTempY(){
		return tempY;
	}

	public boolean run(){
		censor.doScanRoad(x, y, direction);
		if(censor.isThereRedTrafficLight(direction)){
			if(censor.getObsCol() == 0){
				decelerateToN(0);
				return true;
			}
		}
		if(censor.isThereAnyObstacleinFront(x, y, direction)){
			if(censor.isPossibleToMoveDiagonallyToTheLeft(direction)==true){
				moveDiagonallyToTheLeft();
				return true;	
			}
			else if(censor.isPossibleToMoveDiagonallyToTheRight(direction)){
				moveDiagonallyToTheRight();
				return true;
			}
			else if(censor.isPossibleMoveToTheLeft(direction)){
				moveLeft();
				return true;
			} 
			else if(censor.isPossibleMoveToTheRight(direction)){
				moveRight();
				return true;
			}
			else{
				decelerateToN(0);
				return false;} //game over, karena sudah tidak bisa belok kemanapun
		}
		if (censor.isThereParkingSignOnTheLeft(direction)){
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
					if(censor.isPossibleMoveToTheLeft(direction)){
						moveLeft();
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
				if(censor.isPossibleMoveToTheRight(direction)){
					moveRight();
					return true;
				}
			}	
		}				
		if (censor.isThereGreenTrafficLight(direction)){
			accelerateToN(10);
		} 
		moveForward();
		return true;
	}
	
	public void moveForward(){
		tempX = getX();
		tempY = getY();
		int temp_x = getX();
		int temp_y = getY();
		if(direction.equals("e")){
			temp_y = temp_y + (int)(speedometer.getSpeed()/10);
			speedometer.calculateMiles();
		}
		setCoords(temp_x, temp_y);
	}
	
	public void moveRight(){
		tempX = getX();
		tempY = getY();
		int temp_x = getX();
		int temp_y = getY();
		if(direction.equals("e")){
			temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			speedometer.calculateMiles();
		}
			setCoords(temp_x, temp_y);
	}


	public void moveLeft(){
		tempX = getX();
		tempY = getY();
		int temp_x = getX();
		int temp_y = getY();
		if(direction.equals("e")){
			temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			speedometer.calculateMiles();
		}
		setCoords(temp_x, temp_y);
	}
	
	public void moveDiagonallyToTheLeft() {
		tempX = getX();
		tempY = getY();
		int temp_x = getX();
		int temp_y = getY();
		if(direction.equals("e")) {
			temp_x = temp_x - (int) (speedometer.getSpeed()/10);
			temp_y = temp_y + (int) (speedometer.getSpeed()/10);
			speedometer.calculateMiles();
		}
		setCoords(temp_x, temp_y);
	}


	public void moveDiagonallyToTheRight() {
		tempX = getX();
		tempY = getY();
		int temp_x = getX();
		int temp_y = getY();
		if(direction.equals("e")) {
			temp_x = temp_x + (int) (speedometer.getSpeed()/10);
			temp_y = temp_y + (int) (speedometer.getSpeed()/10);
			speedometer.calculateMiles();
		}
		setCoords(temp_x, temp_y);
	}

	public void accelerateToN(int n){
	    while(speedometer.getSpeed() < n){
	        speedometer.setSpeed(speedometer.getSpeed() + 1);
	        if(speedometer.getSpeed() > n){
	            speedometer.setSpeed(n);
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
	
	public String getCarReport(){ 
		return (carPosition() + "\n" + speedometer.status() + "\n" + censor.status());
	}

	public String carPosition(){
		if(direction.equals("e"))
			return "Car is driving on ("+x+", "+y+") axis and heading to east direction.";
		if(direction.equals("n"))
			return "Car is driving on ("+x+", "+y+") axis and heading to north direction.";
		if(direction.equals("w"))
			return "Car is driving on ("+x+", "+y+") axis and heading to west direction.";
		else
			return "Car is driving on ("+x+", "+y+") axis and heading to south direction.";
	}

}


