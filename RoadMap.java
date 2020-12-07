package CarSimulator;
public class RoadMap {

	    public static final int MAP_WIDTH = 35;
		public static final int MAP_HEIGHT = 5;
		private int tLightX ;
		private int tLightY ;
		private int counter;
		//						  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34    
		private char[][] road= {{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
	                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
								{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
								{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
	                            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}};
	                            
	
	   public RoadMap(){
		   tLightX = 0;
		   tLightY = 0;
		   counter = 0;
		   setObstacleCone();
		   setRedTrafficLight();
		   setParkSignAndDestination();
	   }

	   public char[][] getRoad(){
		     return road;
	   }
	   public void setParkSignAndDestination(){
			int a,b;
			int count = 0;
			while(count<1){
				a = getRandomInt(0, 5); //baris ke 1-3
				b = MAP_WIDTH-1;		//kolom terakhir
				if(road[a][b] == '#'){
					road[a][b] = 'P';
					count++;
				}
			}
	    }
	   
	   public char getPoint(int x, int y){
	     return road[x][y];
	   }

	   public void setObstacleCone(){
			int count = 0;
			int a,b;
			while(count<4){
				a = getRandomInt(0, MAP_HEIGHT);
				b = getRandomInt(0, MAP_WIDTH);
				if(road[a][b] == ' '){
				road[a][b] = 'C';
				count++;
				}
			}
	   }
	   public void setRedTrafficLight(){
			int count = 0;
			while(count<1){
				tLightX = getRandomInt(0, MAP_HEIGHT);
				tLightY = getRandomInt(0, MAP_WIDTH);
				if(road[tLightX][tLightY] == '#'){
					road[tLightX][tLightY] = 'R';
					++count;
				}
			}
	   }

	   public void setPoint(int x,int y, char input){
		   this.road[x][y] = input;
	   }
		public int getRandomInt(int min, int max) {
			return (int) Math.floor(Math.random() * (max - min) + min); //The maximum is exclusive and the minimum is inclusive
		}
	   
		public void switchTrafficLight(){
			++counter; 
			if((counter/3)%2 == 0)
				setPoint(tLightX, tLightY, 'G');
			else
				setPoint(tLightX, tLightY, 'R');
		}

		public void printMap(){
			for(int i = 0; i < MAP_HEIGHT;i++) {
				for(int j = 0; j < MAP_WIDTH; j++) {
				System.out.print(road[i][j]);
			}
				System.out.println();
			}
		}

		public static void clearScreen() {   
		   System.out.print("\033[H\033[2J");   
			System.out.flush();   
		   }

		public void update(Car car){
			if(car.getX()<MAP_HEIGHT && car.getTempY()<MAP_WIDTH){
				setPoint(car.getTempX(), car.getTempY(), ' ');
				setPoint(car.getX(), car.getY(), 'M');
			}
		
		}

}

