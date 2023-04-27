package GraphStats.Graph;

import java.util.ArrayList;
import java.util.List;

public class CompleteGraphGenerator
{
	public static Graph getCompleteGraph(int n)
	{
		Graph g = new Graph();
		List<Vertex> vertices = new ArrayList<>();
		for (int i = 0; i < n; i++)
		{
			Vertex v = new Vertex(Integer.toString(i),0.0,0.0);
			vertices.add(v);
			g.addVertex(v);
		}
		for (int i = 0; i < n; i++)
		{
			for (int j = i+1; j < n; j++)
			{
				Vertex v1 = vertices.get(i);
				Vertex v2 = vertices.get(j);
				Edge e = new Edge(v1,v2);
				g.addEdge(e);
			}
		}
		return g;
	}
}
