package CarSimulator;

public abstract class Censor {
    
    private String name;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract char[][] scan(int x, int y, String direction);
    public abstract String status();

    
}
