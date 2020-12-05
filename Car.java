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

	public void action(){
	    Coords carCoords = new Coords(x,y);
	    obstacles = null;
	    obstacles = censor.getObstacles();
	    HashMap<Coords, Double> distance = censor.getCoordsDistance();
	    List<Coords> keyObstacle = new ArrayList<>(distance.keySet());
	    if(obstacles != null){
	        for(int i = 0; i<obstacles.size(); i++)
	        {
	        	if(obstacles.get(keyObstacle.get(i)) == 'C' && carCoords.getCoordsX()== keyObstacle.get(i).getCoordsX()) {
	        		for(int j = 0; j < obstacles.size();j++) {
	        			if(keyObstacle.get(j).getCoordsY() == carCoords.getCoordsY()) {
	        				// cek sebelah kiri mobil, jika mobil menghadap ke east
	        				if(keyObstacle.get(j).getCoordsX() == carCoords.getCoordsX()-1) {
	        					if(keyObstacle.get(j).getCoordsX() == carCoords.getCoordsX()+1) {
	        						pedals.decelerateToN(0);
	        					}
	        					else {
	        						//belok ke kanan
	        					}
	        				}
	        				else {
	        					//belok ke kiri
	        				}
	        				}
	        			}
	        		}
	        	
	        	else if(obstacles.get(keyObstacle.get(i)) == 'R'){
	        		pedals.decelerateToN(0);
	        	}
	        	else if(obstacles.get(keyObstacle.get(i)) == 'G') {
	        		pedals.accelerateToN(20);
	        	}
	        	else if(obstacles.get(keyObstacle.get(i)) == 'P' ) {
	        		int car_x = carCoords.getCoordsX();
	        		int car_y = carCoords.getCoordsY();
	        		int obs_x = keyObstacle.get(i).getCoordsX();
	        		int obs_y = keyObstacle.get(i).getCoordsY();
	        		if(car_x > obs_x) {
	        			car_x--;
	        			car_y++;
	        		}
	        		else if(car_x < obs_x) {
	        			car_x++;
	        			car_y++;
	        		}
	        		else if(car_y<obs_y) {
	        			car_x++;
	        			car_y++;
	        		}
	        		else {
	        			car_x++;
	        			car_y--;
	        		}
	        	}
	        	
//	        	else if(obstacles.get(keyObstacle.get(i)) == 'S') {
//	        		//ask do u want to stop? if yes, stop. if not, keep moving
//	        	}
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

	public void findTurns(){
		 boolean[][] temp = censor.findRoad(x, y);
	        if(direction == 'e') {
	            for(int i = 3; i >= 0; i--){
	                if(temp[0][i] && temp[1][i]){
	                    setLeftTurn(i + 1);
	                } else if(temp[3][i] && temp[4][i]){
	                    setRightTurn(i + 1);
	                }
	            }
	        } else if(direction == 'w') {
	            ;
	        } else if(direction == 'n') {
	            ;
	        } else {
	            ;
	        }
	}

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
