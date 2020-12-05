import java.util.*;

public class RoadMap {

    int x, y;
    public static final int MAP_WIDTH = 35;
    public static final int MAP_HEIGHT = 15;

    /*private List<Coords> startingPoints = new ArrayList<Coords>();
    private Coords carStartingPoint = new Coords();*/

    private char[][] road= {{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
                            {'.','.','.','.','.','.','.','.','.','.','.','.','.','#',' ',' ',' ','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'}};


    public void setObstacle(){
        int count = 0;
        int a,b;
        String cone = "\uD83D\uDEA7";
        String trafficLight = "\uD83D\uDEA6";
        int coneCodePoint = cone.codePointAt(cone.offsetByCodePoints(0,0));
        int trafficCodePoint = trafficLight.codePointAt(trafficLight.offsetByCodePoints(0, 0));

        int mysteryConeCodePoint = coneCodePoint + 1;
        int mysteryTrafficCodePoint = trafficCodePoint + 1;

        char mysteryCone[] = {Character.highSurrogate(mysteryConeCodePoint), Character.lowSurrogate(mysteryConeCodePoint)};
        char mysteryTraffic[] = {Character.highSurrogate(mysteryTrafficCodePoint), Character.lowSurrogate(mysteryTrafficCodePoint)};
        
        while(count<2){
            a = (int) Math.floor(Math.random() * 15);
            b = (int) Math.floor(Math.random() * 35);
            if(road[a][b] == ' '){
                System.out.println(mysteryCone);
            count++;
            }
        }
        count = 0;
        while(count<1){
            a = (int) Math.floor(Math.random() * 15);
            b = (int) Math.floor(Math.random() * 35);
            if(road[a][b] == '#'){
                road[a][b] = 'P';
            count++;
            }
        }
    
        count = 0;
        while(count<1){
            a = (int) Math.floor(Math.random() * 15);
            b = (int) Math.floor(Math.random() * 35);
            if(road[a][b] == '#'){
                road[a][b] = 'R';
            count++;
            }
        }

    }


/*
        double RoadMap[][] = new double[20][10];
    
    }
    Random getObstacle = new random();
    
*/	   
    public char[][] getRoad(){
        return road;
  } 
    
    public RoadMap(){
        setObstacle();
    }
}
