package GraphStats.Graph;

import java.util.HashSet;
import java.util.Iterator;

public class Edge
{
	private final Vertex v1;
	private final Vertex v2;

	private final String color;
	public Edge(Vertex v1,Vertex v2, String color)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.color = color;
	}
	public Edge(Vertex v1,Vertex v2)
	{
		this(v1,v2,"#000000");
	}
	public Vertex getV1()
	{
		return this.v1;
	}
	public Vertex getV2()
	{
		return this.v2;
	}
	public String getColor()
	{
		return this.color;
	}
}
