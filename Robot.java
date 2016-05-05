import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.Collection;

import sun.misc.Sort;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;




public class Robot {
		
	private static UltrasonicSensor sonar ;
	private static ArrayList<Node> Nodes = new ArrayList<Node>();
	static Node current;
	int NbTourMoteurG;
	int NbTourMoteurD;
	static int nbrot;
	boolean fin;
	
	Robot()
	{
		current = new Node();
	}

	public static void tournerGauche()
	{
		Mouvements.tournerGauche();
		SetOrient(true);
	}
	
	public static void tournerDroite()
	{
		Mouvements.tournerDroite();
		SetOrient(false);
	}
	
	public static void avancer()
	{
		Node temp = new Node();
	
		switch(current.getOrient())
		{
			case NORTH:
				temp.setX(current.getX()+1);
				break;
			case SOUTH:
				temp.setX(current.getX()-1);
				break;
			case WEST:
				temp.setY(current.getY()+1);
				break;
			case EAST:
				temp.setY(current.getY()-1);
				break;
		}
		Nodes.add(current);
		current = temp;
		Mouvements.avanceDUneCase(sonar, nbrot);
	}
	
	public static void DemiTour()
	{
		Mouvements.DemiTour();
		SetOrient(false);
		SetOrient(false);
		current.setMurDe(TRISTATE.UNDEFINED);
	}
	
	public static void test()
	{
		if(current.getMurDe() == TRISTATE.UNDEFINED) TestDevant();
		if(current.getMurG() == TRISTATE.UNDEFINED) TestGauche();
		/*if(current.getMurDe() == TRISTATE.UNDEFINED)
		{
			
		}
		if(current.getMurG() == TRISTATE.UNDEFINED)
		{
		}*/
	}
	
	
	private static void TestDevant() {
		sonar.ping();
		int dist = sonar.getDistance();
		if(dist<23)
		{
			current.setMurDe(TRISTATE.YES);
			if(current.getMurG()== TRISTATE.YES)
			{
				tournerDroite();
			}
		}
		else
		{
			current.setMurDe(TRISTATE.NO);
		}
	}

	public static boolean TestFin()
	{
		int dist;
		sonar.ping();
		dist = sonar.getDistance();
		LCD.drawString(Integer.toString(dist),0,0); 
		  if(dist >= 255)
		   {
			  return true; 
		   }
		  else
		  {
			  return false;
		  }
	}
	
	public static void TestGauche()
	{
		int dist;
		tournerGauche();
		sonar.ping();
		dist = sonar.getDistance();
		LCD.drawString(Integer.toString(dist),0,0); 
		if(dist < 23)
		{
			if(current.getMurDe() == TRISTATE.YES)
			{
					DemiTour();
					current.setMurG(TRISTATE.YES);
					TestDevant();
			}
			else
			{
				tournerDroite();
			}
			current.setMurG(TRISTATE.YES);
		}
		else
		{
			current.setMurDe(TRISTATE.NO);
		}
	}

	//test si la node est la node de départ
	private static boolean TestStart() {
		int i,j;
		for(i=0;i<4;i++)
		{
			tournerGauche();
			sonar.ping();
			dist = sonar.getDistance();
			LCD.drawString(Integer.toString(dist),0,0); 
			if(dist < 23)
			{
				j++;
				//si le robot est dans un cube
				if(j == 4 )
				{
					return true;
				}
			}
			if(TestFin())
			{
				return true;
			}
		}
		return false;
	}
	//peu être optimisé
	private static void Followchemin(ArrayList<Node> Nodes)
	{
		Node cuNode = Nodes.get(0);
		for(Node n : Nodes)
		{
			//on assume que les nodes sont cotes à cotes
			switch (n.getX()-cuNode.getX()) {
			case  -1:
				tournerDroite();
				avancer();
				cuNode =n;
				break;
				
			case 1:
				tournerGauche();
				avancer();
				cuNode =n;
				break;
			default:
				break;
			}
			switch (n.getY()-cuNode.getY()) {
			case  -1:
				avancer();
				cuNode =n;
				break;
			default:
				break;
			}
		}
	}
	
	private static boolean TestBoucle()
	{
		if(current.getManhattan()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public static void main(String[] args){
	
		
		current = new Node();
		sonar = new UltrasonicSensor(SensorPort.S3);
		sonar.capture();
		 LCD.drawString("Calibrage",0,0);
		 Button.waitForAnyPress();
		  DemiTour();
		Button.waitForAnyPress();
		
		nbrot = Mouvements.Calibrage(sonar);
		 sonar.ping();
		  int dist = sonar.getDistance();
		LCD.drawString(Integer.toString(dist),0,0); 
		
		  
		  Button.waitForAnyPress();
			
		  
		boolean fin ;
		//cherche si la sortie se trouve autour de lui
		fin =TestStart();
		  
		  while(!fin)
		  {
			  
			  test();
			  fin = TestFin();
			  if(current.getMurDe()==TRISTATE.NO &&  !fin )
			  {				 
				  avancer();
				  fin =TestBoucle();
			  }
			  ArrayList<Node> cheminretour = astar(Nodes, objectif, depart);
			  Followchemin(cheminretour);
			
		  }
		  
		  
		Button.waitForAnyPress();	//1441
	}
	
	
	
	private static ArrayList<Node> astar(ArrayList<Node> Nodes, Node objectif, Node depart)
	{
				   Queue<Node> closedList;
				   Queue<Node> openList;
				   ArrayList<Node> cameFrom;
				   Node u;
			       openList.add(depart);
			       while(!openList.isEmpty())
			       {
			    	   Sort(openList);
			           u= openList.peek();
			           if( u.getX() == objectif.getX() && u.getY() == objectif.getY())
			           {
			               reconstituerChemin(u);
			               break;
			           }
			           else{

				           u = openList.poll();
				           closedList.add(u);
			              
			            for (Node v : u.getVoisins(Nodes))
			            {
	                   
	                       if(closedList.contains(v))
	                       {
	                    	   
	                       }
	                       
	                       if (!openList.contains(v))
			               {
			                 openList.add(v);
			               }
	                       else
	                       {
	                    	   
	                       }
			                   }
			            }
			           }
			            closedList.add(u);
			       }
	private class NodeComparator implements Comparator<Node>{
	
		@Override
		public int compare(Node n1,Node n2)
		{
			return n1.getManhattan() -(n2.getManhattan());
		}
		
	
	}

	private static void Sort(Queue<Node> openList) {
		ArrayList<Node> tempsort;
		while(!openList.isEmpty())
		{
			tempsort.add(openList.poll());
		}
		Collections.sort(tempsort,new NodeComparator());
		Collections.reverse(tempsort);
		for(Node n : tempsort)
		{
			openList.add(n);
		}
	}

	/*private static void reconstituerChemin(ArrayList<Node> viensde,Node u) {
		ArrayList<Node> totalpath;
		totalpath.add(u);
		
	}*/
	




	private static void SetOrient(boolean rot) {
		// TODO Auto-generated method stub
		//Gauche: VRAI Droite: FAUX
		if(rot)
		{
			switch(current.getOrient())
			{
				case NORTH:
					current.setOrient(Orientation.WEST);
					break;
				case SOUTH:
					current.setOrient(Orientation.EAST);
					break;
				case EAST:
					current.setOrient(Orientation.NORTH);
					break;
				case WEST:
					current.setOrient(Orientation.SOUTH);
					break;
			}
		}
		else
		{
			switch(current.getOrient())
			{
				case NORTH:
					current.setOrient(Orientation.EAST);
					break;
				case SOUTH:
					current.setOrient(Orientation.WEST);
					break;
				case EAST:
					current.setOrient(Orientation.SOUTH);
					break;
				case WEST:
					current.setOrient(Orientation.NORTH);
					break;
			}
		}
		
	}

}
