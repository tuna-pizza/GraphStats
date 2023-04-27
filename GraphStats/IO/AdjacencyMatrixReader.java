package GraphStats.IO;

import GraphStats.Graph.Edge;
import GraphStats.Graph.Graph;
import GraphStats.Graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyMatrixReader
{
	private final String file;
	public AdjacencyMatrixReader(String file)
	{
		this.file = file;
	}
	public Graph read()
	{
		BufferedReader reader;
		Graph g = new Graph();
		List<Vertex> vertices = new ArrayList<>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			int lineno = 0;
			for (int i = 0; i < line.length(); i++)
			{
				Vertex v = new Vertex(Integer.toString(1000+i),0,0);
				g.addVertex(v);
				vertices.add(i,v);
			}
			while (line != null)
			{
				for (int i = lineno+1; i < line.length(); i++)
				{
					if (line.charAt(i) == '1')
					{
						Edge e = new Edge(vertices.get(lineno),vertices.get(i));
						g.addEdge(e);
					}
					if (line.charAt(i) == 'X')
					{
						Edge e = new Edge(vertices.get(lineno),vertices.get(i),"#FF0000");
						g.addEdge(e);
					}
				}
				line = reader.readLine();
				lineno++;
			}
			reader.close();
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		return g;
	}
}
