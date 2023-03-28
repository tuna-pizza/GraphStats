package GraphStats.Algorithm;

import GraphStats.Graph.Edge;
import GraphStats.Graph.Graph;
import GraphStats.Graph.Vertex;

import java.util.*;

public class AestheticsQuantifier
{
	private final Graph g;
	private HashMap<Vertex,HashMap<Vertex,Integer>> graphTheoreticDistances;
	private int maxGraphTheoreticDistance;
	public AestheticsQuantifier (Graph g)
	{
		this.g = g;
		this.graphTheoreticDistances = new HashMap<Vertex, HashMap<Vertex, Integer>>();
		Iterator<Vertex> s_it = g.getVertices();
		while (s_it.hasNext())
		{
			Vertex s = s_it.next();
			graphTheoreticDistances.put(s,new HashMap<>());
			List<Vertex> toExplore = new LinkedList<>();
			HashSet<Vertex> finished = new HashSet<>();
			toExplore.add(0,s);
			graphTheoreticDistances.get(s).put(s,0);
			maxGraphTheoreticDistance=0;
			while (!toExplore.isEmpty())
			{
				Vertex v = toExplore.get(0);
				toExplore.remove(0);
				int vDist = graphTheoreticDistances.get(s).get(v);
				Iterator<Edge> vEdges = g.getIncidentEdges(v.getID());
				while (vEdges.hasNext())
				{
					Edge e = vEdges.next();
					Vertex t = e.getV1();
					if (t.equals(v))
					{
						t = e.getV2();
					}
					if (!toExplore.contains(t) && !finished.contains(t))
					{
						toExplore.add(toExplore.size(),t);
						graphTheoreticDistances.get(s).put(t,vDist+1);
						maxGraphTheoreticDistance = Math.max(maxGraphTheoreticDistance,vDist+1);
					}
				}
				finished.add(v);
			}
		}
	}
	public double getStress()
	{
		double L = getIdealEdgeLength();
		Iterator<Vertex> v1_it = g.getVertices();
		double stress = 0;
		while (v1_it.hasNext())
		{
			Vertex v1 = v1_it.next();
			Iterator<Vertex> v2_it = g.getVertices();
			while (v2_it.hasNext())
			{
				Vertex v2 = v2_it.next();
				if (!v1.equals(v2))
				{
					double euclideanDistance = Utils.distance(v1.getCoordinate(),v2.getCoordinate());
					int graphTheoreticDistance = graphTheoreticDistances.get(v1).get(v2);
					double vertexPairStress = Math.pow(euclideanDistance-graphTheoreticDistance*L,2)/Math.pow(graphTheoreticDistance,2);
					stress += vertexPairStress;
				}
			}
		}
		return stress/2;
	}
	private double getIdealEdgeLength()
	{
		double width = g.getMaxX()-g.getMinX();
		double height = g.getMaxY()-g.getMinY();
		double L0 = (width+height)/2;
		return L0/maxGraphTheoreticDistance;
	}
	public double getJaccardIndex()
	{
		HashMap<Vertex,HashSet<Vertex>> kNearestNeighbors = new HashMap<Vertex, HashSet<Vertex>>();
		Iterator<Vertex> v_it1 = g.getVertices();
		while (v_it1.hasNext())
		{
			Vertex v1 = v_it1.next();
			int k = g.getDegree(v1.getID());
			double kNearestDistance = Double.MAX_VALUE;
			Vertex farthestKNearestNeighbor = null;
			HashSet<Vertex> kNearestNeighborSet = new HashSet<Vertex>();
			kNearestNeighbors.put(v1,kNearestNeighborSet);
			Iterator<Vertex> v_it2 = g.getVertices();
			while (v_it2.hasNext())
			{
				Vertex v2 = v_it2.next();
				double distance = Utils.distance(v1.getCoordinate(),v2.getCoordinate());
				if (kNearestNeighborSet.size() < k || distance < kNearestDistance)
				{
					if (kNearestNeighbors.size() == k)
					{
						kNearestNeighborSet.remove(farthestKNearestNeighbor);
					}
					kNearestNeighborSet.add(v2);
					kNearestDistance = 0;
					for (Vertex n : kNearestNeighborSet)
					{
						double neighborDistance = Utils.distance(v1.getCoordinate(),n.getCoordinate());
						if (neighborDistance > kNearestDistance)
						{
							kNearestDistance = neighborDistance;
							farthestKNearestNeighbor = n;
						}
					}
				}
			}
		}
		Iterator<Vertex> i_it = g.getVertices();
		double denominator = 0;
		double nominator = 0;
		while (i_it.hasNext())
		{
			Vertex i = i_it.next();
			Iterator<Vertex> j_it = g.getVertices();
			while (j_it.hasNext())
			{
				Vertex j = j_it.next();
				boolean adjacent = g.areAdjacent(i,j);
				boolean kNearest = kNearestNeighbors.get(i).contains(j);
				if (adjacent && kNearest)
				{
					denominator++;
				}
				if (adjacent || kNearest)
				{
					nominator++;
				}
			}
		}
		if (nominator > 0)
		{
			return denominator/nominator;
		}
		else
		{
			return -1;
		}
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
}
