import java.util.ArrayList;
import java.util.Collection;

public class Node {
	
	private int x, y;
	private Orientation orient;
	private TRISTATE MurG;
	private TRISTATE MurDe;
	Node prec;
	//Distance euclidienne 
	public double euclidienne;
	//Distance de Manhattan  : Moins couteuse et toujours en int
	public int Manhattan;
	Node()
	{
		x = 0;
		y = 0;
		MurG = TRISTATE.UNDEFINED;
		MurDe = TRISTATE.UNDEFINED;
		orient = Orientation.NORTH;
		prec = null;
	}
	Node(int i, int j, Node p, TRISTATE m)
	{
		x = i;
		y = j;
		prec = p;
		MurG = m;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	//origine
	public void setEuclide() {
		this.euclidienne =Math.sqrt((double)(this.x*this.x) +(double)(this.y*this.y));
	}
	//par rapport à une node
	public void setEuclide(int x,int y) {
		this.euclidienne =Math.sqrt(Math.pow((x - this.x),2) + Math.pow((y- this.y),2));
	}
	
	public double getEuclide() {
		return euclidienne;
	}
	//origine
	public void setManhattan() {
		this.Manhattan =(Math.abs(this.x) +Math.abs(this.y));
	}
	
	//par rapport à une node
	public int getManhattan(int x, int y) {
		return (Math.abs(x - this.x) +Math.abs( y - this.y));
	}
	
	public int getManhattan() {
		return Manhattan;
	}
	
	public Orientation getOrient() {
		return orient;
	}
	public void setOrient(Orientation orient) {
		this.orient = orient;
	}
	public TRISTATE getMurG() {
		return MurG;
	}
	public void setMurG(TRISTATE murG) {
		MurG = murG;
	}
	public TRISTATE getMurDe() {
		return MurDe;
	}
	public void setMurDe(TRISTATE murDe) {
		MurDe = murDe;
	}
	
	public ArrayList<Node> getVoisins(ArrayList<Node> Nodes) {
		ArrayList<Node> retour = new ArrayList<Node>();
		for(Node u : Nodes)
		{
			if(u.x-this.x == -1 ||u.x-this.x == 1 || u.x-this.y == -1 || u.x-this.y == 1)
			{
				retour.add(u);
			}
		}
		return retour;
	}
}
