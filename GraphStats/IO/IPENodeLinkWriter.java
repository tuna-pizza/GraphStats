package GraphStats.IO;

import GraphStats.Data.Coordinate;
import GraphStats.Graph.Edge;
import GraphStats.Graph.Graph;
import GraphStats.Graph.Vertex;
import GraphStats.Graph.VertexIDComparator;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class IPENodeLinkWriter
{
	private final String file;
	public IPENodeLinkWriter(String file)
	{
		this.file = file;
	}
	public void write(Graph g)
	{
		try
		{
			FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
			writer.write(prefix);
			Iterator<Edge> e_it = g.getEdges();
			while (e_it.hasNext())
			{
				Edge e = e_it.next();
				Coordinate p1 = e.getV1().getCoordinate();
				Coordinate p2 = e.getV2().getCoordinate();
				String color = "\"edge\"";
				if (e.getColor().equals("#ff0000") || e.getColor().equals("#FF0000"))
				{
					color = "\"red\"";
				}
				writer.write("<path stroke=");
				writer.write(color);
				writer.write(">\n");
				writer.write(Double.toString(p1.getX()));
				writer.write(" ");
				writer.write(Double.toString(840 - p1.getY()));
				writer.write(" m\n");
				writer.write(Double.toString(p2.getX()));
				writer.write(" ");
				writer.write(Double.toString(840 - p2.getY()));
				writer.write(" l\nh\n</path>\n");
			}
			Iterator<Vertex> v_it = g.getVertices();
			while (v_it.hasNext())
			{
				Vertex v = v_it.next();
				Coordinate p = v.getCoordinate();
				String color = "\"vertex\"";
				if (v.getColor().equals("#ff0000") || v.getColor().equals("#FF0000"))
				{
					color = "\"red\"";
				}
				writer.write("<use name=\"mark/fdisk(sfx)\" pos=\"");
				writer.write(Double.toString(p.getX()));
				writer.write(" ");
				writer.write(Double.toString(840 - p.getY()));
				writer.write("\" size=\"normal\" stroke=\"black\" fill=");
				writer.write(color);
				writer.write("/>\n");
			}
			writer.write(suffix);
			writer.close();
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	static String prefix = "<?xml version=\"1.0\"?>\n" +
			"<!DOCTYPE ipe SYSTEM \"ipe.dtd\">\n" +
			"<ipe version=\"70218\" creator=\"Ipe 7.2.21\">\n" +
			"<info created=\"D:20230329180240\" modified=\"D:20230329182028\"/>\n" +
			"<ipestyle name=\"basic\">\n" +
			"<symbol name=\"arrow/arc(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/farc(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/ptarc(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-0.8 0 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/fptarc(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-0.8 0 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/circle(sx)\" transformations=\"translations\">\n" +
			"<path fill=\"sym-stroke\">\n" +
			"0.6 0 0 0.6 0 0 e\n" +
			"0.4 0 0 0.4 0 0 e\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/disk(sx)\" transformations=\"translations\">\n" +
			"<path fill=\"sym-stroke\">\n" +
			"0.6 0 0 0.6 0 0 e\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/fdisk(sfx)\" transformations=\"translations\">\n" +
			"<group>\n" +
			"<path fill=\"sym-fill\">\n" +
			"0.5 0 0 0.5 0 0 e\n" +
			"</path>\n" +
			"<path fill=\"sym-stroke\" fillrule=\"eofill\">\n" +
			"0.6 0 0 0.6 0 0 e\n" +
			"0.4 0 0 0.4 0 0 e\n" +
			"</path>\n" +
			"</group>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/box(sx)\" transformations=\"translations\">\n" +
			"<path fill=\"sym-stroke\" fillrule=\"eofill\">\n" +
			"-0.6 -0.6 m\n" +
			"0.6 -0.6 l\n" +
			"0.6 0.6 l\n" +
			"-0.6 0.6 l\n" +
			"h\n" +
			"-0.4 -0.4 m\n" +
			"0.4 -0.4 l\n" +
			"0.4 0.4 l\n" +
			"-0.4 0.4 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/square(sx)\" transformations=\"translations\">\n" +
			"<path fill=\"sym-stroke\">\n" +
			"-0.6 -0.6 m\n" +
			"0.6 -0.6 l\n" +
			"0.6 0.6 l\n" +
			"-0.6 0.6 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/fsquare(sfx)\" transformations=\"translations\">\n" +
			"<group>\n" +
			"<path fill=\"sym-fill\">\n" +
			"-0.5 -0.5 m\n" +
			"0.5 -0.5 l\n" +
			"0.5 0.5 l\n" +
			"-0.5 0.5 l\n" +
			"h\n" +
			"</path>\n" +
			"<path fill=\"sym-stroke\" fillrule=\"eofill\">\n" +
			"-0.6 -0.6 m\n" +
			"0.6 -0.6 l\n" +
			"0.6 0.6 l\n" +
			"-0.6 0.6 l\n" +
			"h\n" +
			"-0.4 -0.4 m\n" +
			"0.4 -0.4 l\n" +
			"0.4 0.4 l\n" +
			"-0.4 0.4 l\n" +
			"h\n" +
			"</path>\n" +
			"</group>\n" +
			"</symbol>\n" +
			"<symbol name=\"mark/cross(sx)\" transformations=\"translations\">\n" +
			"<group>\n" +
			"<path fill=\"sym-stroke\">\n" +
			"-0.43 -0.57 m\n" +
			"0.57 0.43 l\n" +
			"0.43 0.57 l\n" +
			"-0.57 -0.43 l\n" +
			"h\n" +
			"</path>\n" +
			"<path fill=\"sym-stroke\">\n" +
			"-0.43 0.57 m\n" +
			"0.57 -0.43 l\n" +
			"0.43 -0.57 l\n" +
			"-0.57 0.43 l\n" +
			"h\n" +
			"</path>\n" +
			"</group>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/fnormal(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/pointed(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-0.8 0 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/fpointed(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-0.8 0 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/linear(spx)\">\n" +
			"<path stroke=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"-1 0.333 m\n" +
			"0 0 l\n" +
			"-1 -0.333 l\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/fdouble(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"-1 0 m\n" +
			"-2 0.333 l\n" +
			"-2 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/double(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"-1 0 m\n" +
			"-2 0.333 l\n" +
			"-2 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/mid-normal(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"0.5 0 m\n" +
			"-0.5 0.333 l\n" +
			"-0.5 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/mid-fnormal(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0.5 0 m\n" +
			"-0.5 0.333 l\n" +
			"-0.5 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/mid-pointed(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"0.5 0 m\n" +
			"-0.5 0.333 l\n" +
			"-0.3 0 l\n" +
			"-0.5 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/mid-fpointed(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"0.5 0 m\n" +
			"-0.5 0.333 l\n" +
			"-0.3 0 l\n" +
			"-0.5 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/mid-double(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"sym-stroke\" pen=\"sym-pen\">\n" +
			"1 0 m\n" +
			"0 0.333 l\n" +
			"0 -0.333 l\n" +
			"h\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<symbol name=\"arrow/mid-fdouble(spx)\">\n" +
			"<path stroke=\"sym-stroke\" fill=\"white\" pen=\"sym-pen\">\n" +
			"1 0 m\n" +
			"0 0.333 l\n" +
			"0 -0.333 l\n" +
			"h\n" +
			"0 0 m\n" +
			"-1 0.333 l\n" +
			"-1 -0.333 l\n" +
			"h\n" +
			"</path>\n" +
			"</symbol>\n" +
			"<pen name=\"heavier\" value=\"0.8\"/>\n" +
			"<pen name=\"fat\" value=\"1.2\"/>\n" +
			"<pen name=\"ultrafat\" value=\"2\"/>\n" +
			"<symbolsize name=\"large\" value=\"5\"/>\n" +
			"<symbolsize name=\"small\" value=\"2\"/>\n" +
			"<symbolsize name=\"tiny\" value=\"1.1\"/>\n" +
			"<arrowsize name=\"large\" value=\"10\"/>\n" +
			"<arrowsize name=\"small\" value=\"5\"/>\n" +
			"<arrowsize name=\"tiny\" value=\"3\"/>\n" +
			"<color name=\"red\" value=\"1 0 0\"/>\n" +
			"<color name=\"blue\" value=\"0 0 1\"/>\n" +
			"<color name=\"green\" value=\"0 1 0\"/>\n" +
			"<color name=\"yellow\" value=\"1 1 0\"/>\n" +
			"<color name=\"orange\" value=\"1 0.647 0\"/>\n" +
			"<color name=\"gold\" value=\"1 0.843 0\"/>\n" +
			"<color name=\"purple\" value=\"0.627 0.125 0.941\"/>\n" +
			"<color name=\"gray\" value=\"0.745\"/>\n" +
			"<color name=\"brown\" value=\"0.647 0.165 0.165\"/>\n" +
			"<color name=\"navy\" value=\"0 0 0.502\"/>\n" +
			"<color name=\"pink\" value=\"1 0.753 0.796\"/>\n" +
			"<color name=\"seagreen\" value=\"0.18 0.545 0.341\"/>\n" +
			"<color name=\"turquoise\" value=\"0.251 0.878 0.816\"/>\n" +
			"<color name=\"violet\" value=\"0.933 0.51 0.933\"/>\n" +
			"<color name=\"darkblue\" value=\"0 0 0.545\"/>\n" +
			"<color name=\"darkcyan\" value=\"0 0.545 0.545\"/>\n" +
			"<color name=\"darkgray\" value=\"0.663\"/>\n" +
			"<color name=\"darkgreen\" value=\"0 0.392 0\"/>\n" +
			"<color name=\"darkmagenta\" value=\"0.545 0 0.545\"/>\n" +
			"<color name=\"darkorange\" value=\"1 0.549 0\"/>\n" +
			"<color name=\"darkred\" value=\"0.545 0 0\"/>\n" +
			"<color name=\"lightblue\" value=\"0.678 0.847 0.902\"/>\n" +
			"<color name=\"lightcyan\" value=\"0.878 1 1\"/>\n" +
			"<color name=\"lightgray\" value=\"0.827\"/>\n" +
			"<color name=\"lightgreen\" value=\"0.565 0.933 0.565\"/>\n" +
			"<color name=\"lightyellow\" value=\"1 1 0.878\"/>\n" +
			"<color name=\"vertex\" value=\"0.6 0.8 1\"/>\n" +
			"<color name=\"edge\" value=\"0.6 0.6 0.6\"/>\n" +
			"<dashstyle name=\"dotted\" value=\"[1 3] 0\"/>\n" +
			"<dashstyle name=\"dashed\" value=\"[4] 0\"/>\n" +
			"<dashstyle name=\"dash dotted\" value=\"[4 2 1 2] 0\"/>\n" +
			"<dashstyle name=\"dash dot dotted\" value=\"[4 2 1 2 1 2] 0\"/>\n" +
			"<textsize name=\"large\" value=\"\\large\"/>\n" +
			"<textsize name=\"Large\" value=\"\\Large\"/>\n" +
			"<textsize name=\"LARGE\" value=\"\\LARGE\"/>\n" +
			"<textsize name=\"huge\" value=\"\\huge\"/>\n" +
			"<textsize name=\"Huge\" value=\"\\Huge\"/>\n" +
			"<textsize name=\"small\" value=\"\\small\"/>\n" +
			"<textsize name=\"footnote\" value=\"\\footnotesize\"/>\n" +
			"<textsize name=\"tiny\" value=\"\\tiny\"/>\n" +
			"<textstyle name=\"center\" begin=\"\\begin{center}\" end=\"\\end{center}\"/>\n" +
			"<textstyle name=\"itemize\" begin=\"\\begin{itemize}\" end=\"\\end{itemize}\"/>\n" +
			"<textstyle name=\"item\" begin=\"\\begin{itemize}\\item{}\" end=\"\\end{itemize}\"/>\n" +
			"<gridsize name=\"4 pts\" value=\"4\"/>\n" +
			"<gridsize name=\"8 pts (~3 mm)\" value=\"8\"/>\n" +
			"<gridsize name=\"16 pts (~6 mm)\" value=\"16\"/>\n" +
			"<gridsize name=\"32 pts (~12 mm)\" value=\"32\"/>\n" +
			"<gridsize name=\"10 pts (~3.5 mm)\" value=\"10\"/>\n" +
			"<gridsize name=\"20 pts (~7 mm)\" value=\"20\"/>\n" +
			"<gridsize name=\"14 pts (~5 mm)\" value=\"14\"/>\n" +
			"<gridsize name=\"28 pts (~10 mm)\" value=\"28\"/>\n" +
			"<gridsize name=\"56 pts (~20 mm)\" value=\"56\"/>\n" +
			"<anglesize name=\"90 deg\" value=\"90\"/>\n" +
			"<anglesize name=\"60 deg\" value=\"60\"/>\n" +
			"<anglesize name=\"45 deg\" value=\"45\"/>\n" +
			"<anglesize name=\"30 deg\" value=\"30\"/>\n" +
			"<anglesize name=\"22.5 deg\" value=\"22.5\"/>\n" +
			"<opacity name=\"10%\" value=\"0.1\"/>\n" +
			"<opacity name=\"30%\" value=\"0.3\"/>\n" +
			"<opacity name=\"50%\" value=\"0.5\"/>\n" +
			"<opacity name=\"75%\" value=\"0.75\"/>\n" +
			"<tiling name=\"falling\" angle=\"-60\" step=\"4\" width=\"1\"/>\n" +
			"<tiling name=\"rising\" angle=\"30\" step=\"4\" width=\"1\"/>\n" +
			"</ipestyle>\n" +
			"<page>\n" +
			"<layer name=\"alpha\"/>\n" +
			"<view layers=\"alpha\" active=\"alpha\"/>";
	static String suffix = "\n" +
			"</page>\n" +
			"</ipe>";
}
