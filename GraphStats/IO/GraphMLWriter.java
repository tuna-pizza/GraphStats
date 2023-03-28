package GraphStats.IO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import GraphStats.Graph.*;
import org.w3c.dom.Text;

public class GraphMLWriter
{
	private int edgeID;
	private int vertexID;
	private final String file;
	public GraphMLWriter(String file)
	{
		this.file = file;
		this.edgeID = 0;
		this.vertexID = 0;
	}
	public void write(Graph g)
	{
		Document document;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();

			Element root = document.createElement("graphml");
			root.setAttribute("xmlns","http://graphml.graphdrawing.org/xmlns");
			root.setAttribute("xmlns:java","http://www.yworks.com/xml/yfiles-common/1.0/java");
			root.setAttribute("xmlns:sys","http://www.yworks.com/xml/yfiles-common/markup/primitives/2.0");
			root.setAttribute("xmlns:x","http://www.yworks.com/xml/yfiles-common/markup/2.0");
			root.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			root.setAttribute("xmlns:y","http://www.yworks.com/xml/graphml");
			root.setAttribute("xmlns:yed","http://www.yworks.com/xml/yed/3");
			root.setAttribute("xsi:schemaLocation","http://graphml.graphdrawing.org/xmlns http://www.yworks.com/xml/schema/graphml/1.1/ygraphml.xsd");
			writeHeader(root,document);

			// create data elements and place them under root
			Element graph = document.createElement("graph");
			graph.setAttribute("id","G");
			graph.setAttribute("edgedefault","undirected");
			//e.appendChild(document.createTextNode(role1));
			root.appendChild(graph);

			Element data0 = document.createElement("data");
			data0.setAttribute("key","d0");
			graph.appendChild(data0);

			Iterator<Vertex> v_it = g.getVertices();
			while (v_it.hasNext())
			{
				Vertex v = v_it.next();
				writeVertex(graph,document,v);
			}
			Iterator<Edge> e_it = g.getEdges();

			while (e_it.hasNext())
			{
				Edge e = e_it.next();
				writeEdge(graph,document,e);
			}

			Element data7 = document.createElement("data");
			data7.setAttribute("key","d7");
			Element yResources = document.createElement("y:Resources");
			data7.appendChild(yResources);
			root.appendChild(data7);

			document.appendChild(root);
			try
			{
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				// send DOM to file
				tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));

			}
			catch (TransformerException | IOException ex)
			{
				System.err.println(ex.getMessage());
			}
		}
		catch (ParserConfigurationException pce)
		{
			System.err.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
		}
	}

	private void writeEdge(Element graph, Document document, Edge e)
	{
		Element edge = document.createElement("edge");
		edge.setAttribute("id","e"+edgeID++);
		edge.setAttribute("source",e.getV1().getID());
		edge.setAttribute("target",e.getV2().getID());
		Element data9 = document.createElement("data");
		data9.setAttribute("key","d9");
		edge.appendChild(data9);
		Element data10 = document.createElement("data");
		data10.setAttribute("key","d10");
		Element yPolyLineEdge = document.createElement("y:PolyLineEdge");
		Element yPath = document.createElement("y:Path");
		yPath.setAttribute("sx","0.0");
		yPath.setAttribute("sy","0.0");
		yPath.setAttribute("tx","0.0");
		yPath.setAttribute("ty","0.0");
		yPolyLineEdge.appendChild(yPath);
		Element yLineStyle = document.createElement("y:LineStyle");
		yLineStyle.setAttribute("color",e.getColor());
		yLineStyle.setAttribute("type","line");
		yLineStyle.setAttribute("width","1.0");
		yPolyLineEdge.appendChild(yLineStyle);
		Element yArrows = document.createElement("y:Arrows");
		yArrows.setAttribute("source","none");
		yArrows.setAttribute("target","none");
		yPolyLineEdge.appendChild(yArrows);
		Element yBendStyle = document.createElement("y:BendStyle");
		yBendStyle.setAttribute("smoothed","false");
		yPolyLineEdge.appendChild(yBendStyle);
		data10.appendChild(yPolyLineEdge);
		edge.appendChild(data10);
		graph.appendChild(edge);
	}
	private void writeVertex(Element graph, Document document, Vertex v)
	{
		Element vertex = document.createElement("node");
		vertex.setAttribute("id",v.getID());
		Element data5 = document.createElement("data");
		data5.setAttribute("key","d5");
		vertex.appendChild(data5);
		Element data6 = document.createElement("data");
		data6.setAttribute("key","d6");
		Element yShapeNode = document.createElement("y:ShapeNode");
		Element yGeometry = document.createElement("y:Geometry");
		yGeometry.setAttribute("height","30.0");
		yGeometry.setAttribute("width","30.0");
		yGeometry.setAttribute("x",Double.toString(v.getX()));
		yGeometry.setAttribute("y",Double.toString(v.getY()));
		yShapeNode.appendChild(yGeometry);
		Element yFill = document.createElement("y:Fill");
		yFill.setAttribute("color",v.getColor());
		yFill.setAttribute("transparent","false");
		yShapeNode.appendChild(yFill);
		Element yNodeLabel = document.createElement("y:NodeLabel");
		yNodeLabel.setAttribute("alignment","center");
		yNodeLabel.setAttribute("autoSizePolicy","content");
		yNodeLabel.setAttribute("fontFamily","Dialog");
		yNodeLabel.setAttribute("fontSize","12");
		yNodeLabel.setAttribute("fontStyle","plain");
		yNodeLabel.setAttribute("hasBackgroundColor","false");
		yNodeLabel.setAttribute("hasLineColor","false");
		yNodeLabel.setAttribute("height","17.96875");
		yNodeLabel.setAttribute("horizontalTextPosition","center");
		yNodeLabel.setAttribute("iconTextGap","4");
		yNodeLabel.setAttribute("modelName","custom");
		yNodeLabel.setAttribute("textColor","000000");
		yNodeLabel.setAttribute("verticalTextPosition","bottom");
		yNodeLabel.setAttribute("visible","false");
		yNodeLabel.setAttribute("width","11.634765625");
		yNodeLabel.setAttribute("x","9.1826171875");
		yNodeLabel.setAttribute("y","6.015625");
		Text label = document.createTextNode(Integer.toString(vertexID++));
		yNodeLabel.appendChild(label);
		Element yLabelModel = document.createElement("y:LabelModel");
		Element ySmartNodeLabelModel = document.createElement("y:SmartNodeLabelModel");
		ySmartNodeLabelModel.setAttribute("distance","4.0");
		yLabelModel.appendChild(ySmartNodeLabelModel);
		yNodeLabel.appendChild(yLabelModel);
		Element yModelParameter = document.createElement("y:ModelParameter");
		Element ySmartNodeLabelModelParameter = document.createElement("y:SmartNodeLabelModelParameter");
		yModelParameter.appendChild(ySmartNodeLabelModelParameter);
		yNodeLabel.appendChild(yModelParameter);
		yShapeNode.appendChild(yNodeLabel);
		Element yShape = document.createElement("y:Shape");
		yShape.setAttribute("type","ellipse");
		yShapeNode.appendChild(yShape);
		data6.appendChild(yShapeNode);
		vertex.appendChild(data6);
		graph.appendChild(vertex);
	}

	private void writeHeader(Element root, Document document)
	{
		Element key1 = document.createElement("key");
		key1.setAttribute("attr.name","Beschreibung");
		key1.setAttribute("attr.type","string");
		key1.setAttribute("for","graph");
		key1.setAttribute("id","d0");
		root.appendChild(key1);

		Element key2 = document.createElement("key");
		key2.setAttribute("yfiles.type","portgraphics");
		key2.setAttribute("for","port");
		key2.setAttribute("id","d1");
		root.appendChild(key2);

		Element key3 = document.createElement("key");
		key3.setAttribute("yfiles.type","portgeometry");
		key3.setAttribute("for","port");
		key3.setAttribute("id","d2");
		root.appendChild(key3);

		Element key4 = document.createElement("key");
		key4.setAttribute("yfiles.type","portuserdata");
		key4.setAttribute("for","port");
		key4.setAttribute("id","d3");
		root.appendChild(key4);

		Element key5 = document.createElement("key");
		key5.setAttribute("attr.name","url");
		key5.setAttribute("attr.type","string");
		key5.setAttribute("for","node");
		key5.setAttribute("id","d4");
		root.appendChild(key5);

		Element key6 = document.createElement("key");
		key6.setAttribute("attr.name","description");
		key6.setAttribute("attr.type","string");
		key6.setAttribute("for","node");
		key6.setAttribute("id","d5");
		root.appendChild(key6);

		Element key7 = document.createElement("key");
		key7.setAttribute("yfiles.type","nodegraphics");
		key7.setAttribute("for","node");
		key7.setAttribute("id","d6");
		root.appendChild(key7);

		Element key8 = document.createElement("key");
		key8.setAttribute("yfiles.type","resources");
		key8.setAttribute("for","graphml");
		key8.setAttribute("id","d7");
		root.appendChild(key8);

		Element key9 = document.createElement("key");
		key9.setAttribute("attr.name","url");
		key9.setAttribute("attr.type","string");
		key9.setAttribute("for","edge");
		key9.setAttribute("id","d8");
		root.appendChild(key9);

		Element key10 = document.createElement("key");
		key10.setAttribute("attr.name","description");
		key10.setAttribute("attr.type","string");
		key10.setAttribute("for","edge");
		key10.setAttribute("id","d9");
		root.appendChild(key10);

		Element key11 = document.createElement("key");
		key11.setAttribute("yfiles.type","edgegraphics");
		key11.setAttribute("for","edge");
		key11.setAttribute("id","d10");
		root.appendChild(key11);
	}
}
