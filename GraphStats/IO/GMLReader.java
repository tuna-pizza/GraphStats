package GraphStats.IO;

import GraphStats.Graph.Edge;
import GraphStats.Graph.Graph;
import GraphStats.Graph.Vertex;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GMLReader
{
	private final String file;
	public GMLReader(String file)
	{
		this.file = file;
	}

	public Graph read()
	{
		BufferedReader reader;
		Graph g = new Graph();
		HashMap<String, Vertex> vertices = new HashMap<>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			boolean parseEdge = false;
			boolean parseNode = false;
			String id = "";
			String source = "";
			String target = "";
			double x = 0.0;
			double y = 0.0;
			String color = "";
			while (line != null)
			{
				line = line.trim();
				String[] words = line.split(" ");
				if (line.startsWith("node"))
				{
					parseNode = true;
					parseEdge = false;
				}
				if (line.startsWith("edge"))
				{
					parseNode = false;
					parseEdge = true;
				}
				if (line.startsWith("id"))
				{
					id = words[1];
				}
				if (line.startsWith("type"))
				{
					if(words[1].equals("RED"))
					{
						color = "#FF0000";
					}
					else if (parseNode)
					{
						color = "#99CCFF";
					}
					else
					{
						color = "#000000";
					}
				}
				if (line.startsWith("x"))
				{
					x = Double.parseDouble(words[1]);
				}
				if (line.startsWith("y"))
				{
					y = Double.parseDouble(words[1]);
				}
				if (line.startsWith("source"))
				{
					source = words[1];
				}
				if (line.startsWith("target"))
				{
					target = words[1];
				}
				if (line.startsWith("]"))
				{
					if (parseNode)
					{
						Vertex v = new Vertex(id,x,y,color);
						vertices.put(id,v);
						g.addVertex(v);
					}
					else if (parseEdge)
					{
						Edge e = new Edge(vertices.get(source),vertices.get(target),color);
						g.addEdge(e);
					}
					parseNode = false;
					parseEdge = false;
				}
				line = reader.readLine();
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
