package GraphStats.IO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import GraphStats.Graph.*;
public class GraphMLReader
{
	private final String file;
	public enum InputType {yEdOld, yEdNew};
	private InputType inputType;
	private String dataFieldID;
	public GraphMLReader(String file, InputType inputType)
	{
		this.file = file;
		this.dataFieldID = "d6";
		this.inputType = inputType;
	}
	public void setDataFieldID(String dataFieldID)
	{
		this.dataFieldID = dataFieldID;
	}
	public Graph read()
	{
		Graph g = new Graph();
		Document document;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(new File(file));
			Element root = document.getDocumentElement();

			Node graph = root.getFirstChild();
			boolean graphFound = false;
			while (graph != null && !graphFound)
			{
				if(!graph.getNodeName().equals("graph"))
				{
					graph = graph.getNextSibling();
				}
				else
				{
					graphFound = true;
				}
			}
			if (graph != null)
			{
				readVertices(graph, g);
				readEdges(graph, g);
			}

		}
		catch (ParserConfigurationException | SAXException | IOException ex)
		{
			System.err.println(ex.getMessage());
		}
		return g;
	}

	private void readEdges(Node graph, Graph g)
	{
		Node edge = graph.getFirstChild();
		while (edge != null)
		{
			if (edge.getNodeName().equals("edge"))
			{
				NamedNodeMap edgeAttributes = edge.getAttributes();
				String sourceID = edgeAttributes.getNamedItem("source").getTextContent();
				String targetID = edgeAttributes.getNamedItem("target").getTextContent();
				Vertex v1 = g.getVertex(sourceID);
				Vertex v2 = g.getVertex(targetID);
				if (v1 != null && v2 != null)
				{
					Edge e = new Edge(v1,v2);
					g.addEdge(e);
				}
			}
			edge = edge.getNextSibling();
		}
	}

	private void readVertices(Node graph, Graph g)
	{
		Node vertex = graph.getFirstChild();
		while (vertex != null)
		{
			if (vertex.getNodeName().equals("node"))
			{
				NamedNodeMap vertexAttributes = vertex.getAttributes();
				NamedNodeMap vertexColor = vertex.getAttributes();
				String id = vertexAttributes.getNamedItem("id").getTextContent();
				double x,y;
				if (inputType == InputType.yEdOld)
				{
					Node data = vertex.getFirstChild();
					while (data != null)
					{
						if (data.getNodeName().equals("data"))
						{
							NamedNodeMap dataAttributes = data.getAttributes();
							if (dataAttributes.getNamedItem("key") != null)
							{
								if (dataAttributes.getNamedItem("key").getTextContent().equals(dataFieldID))
								{
									Node yShapeNode = data.getFirstChild();
									while (yShapeNode != null)
									{
										if (yShapeNode.getNodeName().equals("y:ShapeNode"))
										{
											Node yGeometry = yShapeNode.getFirstChild();
											while (yGeometry != null)
											{
												if (yGeometry.getNodeName().equals("y:Geometry"))
												{
													vertexAttributes = yGeometry.getAttributes();
												}
												if (yGeometry.getNodeName().equals("y:Fill"))
												{
													vertexColor = yGeometry.getAttributes();
												}
												yGeometry = yGeometry.getNextSibling();
											}
										}
										yShapeNode = yShapeNode.getNextSibling();
									}
									break;
								}
							}
						}
						data = data.getNextSibling();
					}
				}
				if (inputType == InputType.yEdNew)
				{
					Node data = vertex.getFirstChild();
					while (data != null)
					{
						if (data.getNodeName().equals("data"))
						{
							NamedNodeMap dataAttributes = data.getAttributes();
							if (dataAttributes.getNamedItem("key") != null)
							{
								if (dataAttributes.getNamedItem("key").getTextContent().equals(dataFieldID))
								{
									Node yShapeNode = data.getFirstChild();
									while (yShapeNode != null)
									{
										if (yShapeNode.getNodeName().equals("y:SVGNode"))
										{
											Node yGeometry = yShapeNode.getFirstChild();
											while (yGeometry != null) {
												if (yGeometry.getNodeName().equals("y:Geometry"))
												{
													vertexAttributes = yGeometry.getAttributes();
													break;
												}
												yGeometry = yGeometry.getNextSibling();
											}
											break;
										}
										yShapeNode = yShapeNode.getNextSibling();
									}
									break;
								}
							}
						}
						data = data.getNextSibling();
					}
				}
				if (vertexAttributes.getNamedItem("x") != null)
				{
					x = Double.parseDouble(vertexAttributes.getNamedItem("x").getTextContent());
				}
				else
				{
					x = 0;
				}
				if (vertexAttributes.getNamedItem("y") != null)
				{
					y = Double.parseDouble(vertexAttributes.getNamedItem("y").getTextContent());
				}
				else
				{
					y = 0;
				}
				if (vertexColor.getNamedItem("color") != null)
				{
					g.addVertex(new Vertex(id, x, y,vertexColor.getNamedItem("color").getTextContent()));
				}
				else
				{
					g.addVertex(new Vertex(id, x, y));
				}
			}
			vertex = vertex.getNextSibling();
		}
	}
}

