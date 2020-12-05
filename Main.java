
package CarSimulator;

public class Main {

 static RoadMap map = new RoadMap();
 static char[][] road = map.getRoad();
 
    public static void main(String[] args){
        
       
        printMap();
        slowdown();
        clearScreen(); 
        /*
            while()


        */
    }
    public static void printMap(){
    
        for(int i = 0; i <15;i++) {
            for(int j = 0; j <35; j++) {
            System.out.print(road[i][j]);
        }
            System.out.println();
        }
    }
    public static void clearScreen() {   
       System.out.print("\033[H\033[2J");   
        System.out.flush();   
       } 
    public static void slowdown(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
   
}
