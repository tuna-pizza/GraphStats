package GraphStats.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import GraphStats.Algorithm.Utils;

public class Graph
{
	private final HashMap<String,Vertex> vertices;
	private final HashSet<Edge> edges;
	private final HashMap<String, HashSet<Edge>> adjacencyList;
	private double firstStart;
	private double lastEnd;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;

	public Graph()
	{
		this.vertices = new HashMap<>();
		this.edges = new HashSet<>();
		this.adjacencyList = new HashMap<>();
		this.minX = Double.MAX_VALUE;
		this.minY = Double.MAX_VALUE;
		this.maxX = Double.MIN_VALUE;
		this.maxY = Double.MIN_VALUE;
		this.firstStart = Double.MAX_VALUE;
		this.lastEnd = Double.MIN_VALUE;
	}
	public Vertex getVertex(String id)
	{
		if (vertices.containsKey(id))
		{
			return vertices.get(id);
		}
		else
		{
			return null;
		}
	}
	public Iterator<Edge> getIncidentEdges(String id)
	{
		if (vertices.containsKey(id))
		{
			return adjacencyList.get(id).iterator();
		}
		else
		{
			return null;
		}
	}
	public double getMinX()
	{
		return minX;
	}
	public double getMinY()
	{
		return minY;
	}
	public double getMaxX()
	{
		return maxX;
	}
	public double getMaxY()
	{
		return maxY;
	}
	public void addVertex(Vertex vertex)
	{
		if (vertex.getX()>maxX)
		{
			maxX = vertex.getX();
		}
		if (vertex.getY()>maxY)
		{
			maxY = vertex.getY();
		}
		if (vertex.getX()<minX)
		{
			minX = vertex.getX();
		}
		if (vertex.getY()<minY)
		{
			minY = vertex.getY();
		}
		if (!this.vertices.containsKey(vertex.getID()))
		{
			this.vertices.put(vertex.getID(),vertex);
			this.adjacencyList.put(vertex.getID(),new HashSet<>());
		}
	}
	public void addEdge(Edge edge)
	{
		if (this.vertices.containsKey(edge.getV1().getID()) && this.vertices.containsKey(edge.getV2().getID()))
		{
			this.edges.add(edge);
			this.adjacencyList.get(edge.getV1().getID()).add(edge);
			this.adjacencyList.get(edge.getV2().getID()).add(edge);
		}
	}
	public Iterator<Vertex> getVertices()
	{
		return this.vertices.values().iterator();
	}
	public Iterator<Edge> getEdges()
	{
		return this.edges.iterator();
	}
	public void normalize (double minVertexDistance)
	{
		double currentMinDistance = Double.MAX_VALUE;
		for (Vertex v1 : vertices.values())
		{
			for (Vertex v2 : vertices.values())
			{
				if (v1.equals(v2))
				{
					continue;
				}
				double distance = Utils.distance(v1.getX(), v1.getY(), v2.getX(), v2.getY());
				if (distance < currentMinDistance)
				{
					currentMinDistance = distance;
				}
			}
		}
		double scaleRatio = Math.max(1,minVertexDistance/currentMinDistance);
		double minimumX = Double.MAX_VALUE;
		double minimumY = Double.MAX_VALUE;
		for (Vertex v : vertices.values())
		{
			v.scale(scaleRatio);
			if (v.getX() < minimumX)
			{
				minimumX = v.getX();
			}
			if (v.getY() < minimumY)
			{
				minimumY = v.getY();
			}
		}
		minimumX=Math.min(0,minimumX);
		minimumY=Math.min(0,minimumY);
		for (Vertex v : vertices.values())
		{
			v.shift(-minimumX,-minimumY);
		}
		this.minX = Double.MAX_VALUE;
		this.minY = Double.MAX_VALUE;
		this.maxX = Double.MIN_VALUE;
		this.maxY = Double.MIN_VALUE;
		for (Vertex v : vertices.values())
		{
			if (v.getX() < this.minX)
			{
				this.minX = v.getX();
			}
			if (v.getX() > this.maxX)
			{
				this.maxX = v.getX();
			}
			if (v.getY() < this.minY)
			{
				this.minY = v.getY();
			}
			if (v.getY() > this.maxY)
			{
				this.maxY = v.getY();
			}
		}
	}
}
