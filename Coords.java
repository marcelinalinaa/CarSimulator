package carSimulator;

public class Coords {
	private int x;
	private int y;
	
	public Coords(int x, int y){
		setCoords(x,y);
	}
	
	public Coords() {
		setCoords(0,0);
	}
	
	public boolean equals(Object o) {
		Coords c = (Coords) o;
		return c.x == x && c.y == y;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getCoordsX() {
		return this.x;
	}
	
	public int getCoordsY() {
		return this.y;				
	}
	
	
}
