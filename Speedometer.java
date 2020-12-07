package CarSimulator;

public class Speedometer {
        private int speed;
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
        
        public String status() {
            return "Your car's current speed is " + this.speed + " mph and have traveled " + this.miles + " miles";
        }

        public void calculateMiles(){
            this.miles += this.speed/10;
        }
           
    
}
