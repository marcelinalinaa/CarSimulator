
package CarSimulator;

import java.util.*;
public class Main {

 static RoadMap map = new RoadMap();
 static char[][] road = map.getRoad();
 
    public static void main(String[] args){
        Car car = new Car();
        map.setPoint(car.getX(), car.getY(), 'M');  //setting the car's position
        /*printMap();
        System.out.println("Do you want to start now?");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        if(answer == "Ya"){
            car.run();
        }
        input.close();
        */
        int count = 0;
        //belum di buat boolean destinasinya
        boolean jalan = true;
        while(jalan){
            printMap();
            System.out.println(car.carPosition());
            System.out.println(car.getCameraCensor().status());
            slowdown();
            clearScreen();
            map.setPoint(car.getX(), car.getY(), ' ');
            car.run1();
            map.setPoint(car.getX(), car.getY(), 'M');
            map.switchTrafficLight(count++);
        }
        printMap();
        System.out.println("the simulation is done");
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }    
}
