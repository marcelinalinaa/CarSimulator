public class Main {

    public static void main(String[] args) {
        
        printMap();
        
    }
    
    public static void printMap(){
        
        RoadMap map = new RoadMap();
        char[][] road;
        road = map.getRoad();
        
        for(int i = 0; i <15;i++) {
            for(int j = 0; j <35; j++) {
                System.out.print(road[i][j]);
            }
        System.out.println();
        }
    }
    }
