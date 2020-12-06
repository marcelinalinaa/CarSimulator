package CarSimulator;

public class Speedometer {
        int speed;
        private int miles;
        
        public static final int MAX_SPEED = 200;
        
        public Speedometer() {
         speed = 0;
         miles = 0;
        }
        public Speedometer(int speed, int miles) {
         setSpeed(speed);
         setMiles(miles);
        }
        
        public void setSpeed(int speed) {
            if(speed<=MAX_SPEED && speed>=0)
                this.speed = speed;
        }
        
        public int getSpeed() {
         return this.speed;
        }
        
        public int getMiles() {
         return miles;
        }
        
        public void setMiles(int miles) {
         this.miles = miles;
        }
        
        public String displaySpeed() {
            return "Your car's current speed is" + this.getSpeed() + "km/ h";
        }

    /*  // to calculate miles
        public int calculateMiles(){

        }
    */
           
    
}
