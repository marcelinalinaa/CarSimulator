
package CarSimulator;

public class Main {

    public static RoadMap map = new RoadMap();
    public static void main(String[] args){
        Car car = new Car();
        map.setPoint(car.getX(), car.getY(), 'M');  //setting the car's position
        boolean jalan = true;
        map.printMap();
        while(jalan){
            RoadMap.clearScreen();
            map.switchTrafficLight();
            jalan = car.run();
            map.update(car);
            map.printMap();
            System.out.println(car.getCarReport());
            slowdown();
        }
        map.printMap();
        System.out.println("The simulation is done.");
    }

    public static void slowdown(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   
    public static RoadMap getMap(){
        return map;
    } 
}
