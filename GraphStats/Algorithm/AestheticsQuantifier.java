package GraphStats.Algorithm;

import GraphStats.Graph.Edge;
import GraphStats.Graph.Graph;

import java.util.Iterator;

public class AestheticsQuantifier
{
	private final Graph g;
	public AestheticsQuantifier (Graph g)
	{
		this.g = g;
	}
	public double getStress()
	{
		return -1;
	}
	public double getIdealEdgeLength()
	{
		return -1;
	}
	public double getNeighborhoodPreservation()
	{
		return -1;
	}
	public int getCrossingNumber()
	{
		int crossingNumber = 0;
		Iterator<Edge> e_it1 = g.getEdges();
		while (e_it1.hasNext())
		{
			Edge e1 = e_it1.next();
			Iterator<Edge> e_it2 = g.getEdges();
			while (e_it2.hasNext())
			{
				Edge e2 = e_it2.next();
				if (e1.equals(e2) || Utils.areIncidentEdges(e1,e2))
				{
					continue;
				}
				if(Utils.computeCrossing(e1,e2) != null)
				{
					crossingNumber++;
				}
			}
		}
		return crossingNumber/2;
	}
	public double getCrossingResolution()
	{
		double crossingResolution = Math.PI/2;
		Iterator<Edge> e_it1 = g.getEdges();
		while (e_it1.hasNext())
		{
			Edge e1 = e_it1.next();
			Iterator<Edge> e_it2 = g.getEdges();
			while (e_it2.hasNext())
			{
				Edge e2 = e_it2.next();
				if (e1.equals(e2) || Utils.areIncidentEdges(e1,e2))
				{
					continue;
				}
				if(Utils.computeCrossing(e1,e2) != null)
				{
					crossingResolution = Math.min(crossingResolution,Utils.computeAngle(e1,e2));
				}
			}
		}
		return Math.toDegrees(crossingResolution);
	}
	public double getAspectRatio()
	{
		double width = g.getMaxX()-g.getMinX();
		double height = g.getMaxY()-g.getMinY();
		return Math.min(width/height,height/width);
	}
	public double getAngularResolution()
	{
		double angularResolution = 2*Math.PI;
		Iterator<Edge> e_it1 = g.getEdges();
		while (e_it1.hasNext())
		{
			Edge e1 = e_it1.next();
			Iterator<Edge> e_it2 = g.getEdges();
			while (e_it2.hasNext())
			{
				Edge e2 = e_it2.next();
				if (e1.equals(e2) || !Utils.areIncidentEdges(e1,e2))
				{
					continue;
				}
				angularResolution = Math.min(angularResolution,Utils.computeAngle(e1,e2));
			}
		}
		return Math.toDegrees(angularResolution);
	}

	public double getNodeResolution()
	{
		return -1;
	}

	public double getGabrielGraphProperty()
	{
		return -1;
	}
}
