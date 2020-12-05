package carSimulator;

public class Coords {
    public int x;
    public int y;
    public Coords(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean equals(Object o) {
        Coords c = (Coords) o;
        return c.x == x && c.y == y;
    }
    /*public int hashCode() {
        return new Integer(x + "0" + y);
    }
    */

    public Coords getCoords(int a, int b){
        return new Coords(a, b);
    }
    public int getCoordsX() {
		return this.x;
	}
	
	public int getCoordsY() {
		return this.y;				
	}

}
