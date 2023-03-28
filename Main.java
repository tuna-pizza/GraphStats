import GraphStats.Algorithm.AestheticsQuantifier;
import GraphStats.Graph.Edge;
import GraphStats.Graph.Graph;
import GraphStats.Graph.Vertex;
import GraphStats.IO.GraphMLReader;
import GraphStats.IO.GraphMLWriter;

public class Main
{
	public static void main(String[] args)
	{
		GraphMLReader r = new GraphMLReader("/home/foersth/GraphStats/st_organic2.graphml", GraphMLReader.InputType.yEdOld);
		Graph g = r.read();
		AestheticsQuantifier a = new AestheticsQuantifier(g);
		System.out.println("Crossing Number:\t" + a.getCrossingNumber());
		System.out.println("Aspect Ratio:\t" + a.getAspectRatio());
		System.out.println("Crossing Resolution:\t" + a.getCrossingResolution());
		System.out.println("Angular Resolution:\t" + a.getAngularResolution());
		//GraphMLWriter w = new GraphMLWriter("/home/foersth/GraphStats/test2.graphml");
		//w.write(g);
	}
}