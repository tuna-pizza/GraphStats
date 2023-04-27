package GraphStats.Graph;

import java.util.*;

public class RandomKPartiteGraphGenerator
{
	private final double probability;
	private final int parts;
	private final double distance;
	public RandomKPartiteGraphGenerator(double probability, int parts, double distance)
	{
		if (parts < 2)
		{
			this.parts = 2;
		}
		else
		{
			this.parts = parts;
		}
		if (probability > 1 || probability < 0)
		{
			this.probability = 0.5;
		}
		else
		{
			this.probability = probability;
		}
		this.distance = distance;
	}

	public Graph generateRandomGraph(List<Integer> numbersOfVertices)
	{
		Graph g = new Graph();
		if (numbersOfVertices.size() != parts)
		{
			return null;
		}
		int maxN = numbersOfVertices.get(0);
		for (int i = 1; i < numbersOfVertices.size();i++)
		{
			maxN = Math.max(maxN,numbersOfVertices.get(i));
		}
		double maxCoordinate = (maxN+1) * distance;
		List<List<Vertex>> vertices = new ArrayList<List<Vertex>>();
		for (int k = 0; k < parts; k++)
		{
			vertices.add(k,new LinkedList<>());
			double currentDistance = maxCoordinate / (numbersOfVertices.get(k)+1);
			for (int n = 0; n < numbersOfVertices.get(k); n++)
			{
				System.out.println(k);
				String id = Integer.toString((k+1)*1000+n);
				double x;
				double y;
				switch (k%4)
				{
					case 0:
					{
						y = 0;
						x = currentDistance/2 + n*currentDistance;
						break;
					}
					case 1:
					{
						y = maxCoordinate;
						x = currentDistance/2 + n*currentDistance;
						break;
					}
					case 2:
					{
						x = 0;
						y = currentDistance/2 + n*currentDistance;
						break;
					}
					default:
					{
						x = maxCoordinate;
						y = currentDistance/2 + n*currentDistance;
					}
				}
				Vertex v = new Vertex(id,x,y);
				g.addVertex(v);
				vertices.get(k).add(v);
			}
		}
		for (int k1 = 0; k1 < vertices.size(); k1++)
		{
			for (int k2 = k1+1; k2 < vertices.size(); k2++)
			{
				for (int n1 = 0; n1 < vertices.get(k1).size(); n1++)
				{
					for (int n2 = 0; n2 < vertices.get(k2).size(); n2++)
					{
						Vertex v1 = vertices.get(k1).get(n1);
						Vertex v2 = vertices.get(k2).get(n2);
						if (probability > Math.random())
						{
							Edge e = new Edge(v1,v2);
							g.addEdge(e);
						}
					}
				}
			}
		}
		return g;
	}
}
