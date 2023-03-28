import GraphStats.Algorithm.AestheticsQuantifier;
import GraphStats.Graph.Graph;
import GraphStats.IO.GraphMLReader;

public class Main
{
	public static void main(String[] args)
	{
		GraphMLReader r = new GraphMLReader("/home/foersth/GraphStats/cutvertex_circular.graphml", GraphMLReader.InputType.yEdOld);
		Graph g = r.read();
		AestheticsQuantifier a = new AestheticsQuantifier(g);
		System.out.println("Crossing Number:\t" + a.getCrossingNumber());
		System.out.println("Aspect Ratio:\t" + a.getAspectRatio());
		System.out.println("Crossing Resolution:\t" + a.getCrossingResolution());
		System.out.println("Angular Resolution:\t" + a.getAngularResolution());
		System.out.println("Jaccard Index:\t" + a.getJaccardIndex());
		System.out.println("Stress:\t" + a.getStress());
		//GraphMLWriter w = new GraphMLWriter("/home/foersth/GraphStats/test2.graphml");
		//w.write(g);
	}
}