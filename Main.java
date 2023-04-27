import GraphStats.Algorithm.AestheticsQuantifier;
import GraphStats.Graph.CompleteGraphGenerator;
import GraphStats.Graph.Graph;
import GraphStats.Graph.RandomKPartiteGraphGenerator;
import GraphStats.IO.*;

import java.util.ArrayList;
import java.util.List;

public class Main
{
	public static void main(String[] args)
	{
		GraphMLReader r1 = new GraphMLReader("/home/foersth/GraphStats/complete1.graphml", GraphMLReader.InputType.yEdOld);
		Graph g1 = r1.read();
		IPENodeLinkWriter iw1 = new IPENodeLinkWriter("/home/foersth/GraphStats/complete1n.ipe");
		iw1.write(g1);
		GraphMLReader r2 = new GraphMLReader("/home/foersth/GraphStats/complete2.graphml", GraphMLReader.InputType.yEdOld);
		Graph g2 = r2.read();
		IPENodeLinkWriter iw2 = new IPENodeLinkWriter("/home/foersth/GraphStats/complete2n.ipe");
		iw2.write(g2);
		//GraphMLReader r3 = new GraphMLReader("/home/foersth/GraphStats/oc3.graphml", GraphMLReader.InputType.yEdOld);
		//Graph g3 = r3.read();
		//IPENodeLinkWriter iw3 = new IPENodeLinkWriter("/home/foersth/GraphStats/oc3n.ipe");
		//iw3.write(g3);
		/*GraphMLReader r1 = new GraphMLReader("/home/foersth/GraphStats/oc1-only.graphml", GraphMLReader.InputType.yEdOld);
		Graph g1 = r1.read();
		GraphMLReader r2 = new GraphMLReader("/home/foersth/GraphStats/oc2-only.graphml", GraphMLReader.InputType.yEdOld);
		Graph g2 = r2.read();
		GraphMLReader r3 = new GraphMLReader("/home/foersth/GraphStats/oc3-only.graphml", GraphMLReader.InputType.yEdOld);
		Graph g3 = r3.read();
		/*AestheticsQuantifier a1 = new AestheticsQuantifier(g1);
		AestheticsQuantifier a2 = new AestheticsQuantifier(g2);
		AestheticsQuantifier a3 = new AestheticsQuantifier(g3);
		System.out.println("Stress:\t" + a1.getStress() + "\t" + a2.getStress() + "\t" + a3.getStress());
		System.out.println("Crossing Number:\t" + a1.getCrossingNumber() + "\t" + a2.getCrossingNumber() + "\t" + a3.getCrossingNumber());
		System.out.println("Jaccard Index:\t" + a1.getJaccardIndex() + "\t" + a2.getJaccardIndex() + "\t" + a3.getJaccardIndex());
		System.out.println("Edge Length Ratio:\t" + a1.getEdgeLengthRatio() + "\t" + a2.getEdgeLengthRatio() + "\t" + a3.getEdgeLengthRatio());
		System.out.println("Node Resolution:\t" + a1.getNodeResolution() + "\t" + a2.getNodeResolution() + "\t" + a3.getNodeResolution());
		System.out.println("Aspect Ratio:\t" + a1.getAspectRatio() + "\t" + a2.getAspectRatio() + "\t" + a3.getAspectRatio());
		System.out.println("Crossing Resolution:\t" + a1.getCrossingResolution() + "\t" + a2.getCrossingResolution() + "\t" + a3.getCrossingResolution());
		System.out.println("Angular Resolution:\t" + a1.getAngularResolution() + "\t" + a2.getAngularResolution() + "\t" + a3.getAngularResolution());

		/*9AestheticsQuantifier a1 = new AestheticsQuantifier(g1);
		GraphMLReader r2 = new GraphMLReader("/home/foersth/GraphStats/complete2.graphml", GraphMLReader.InputType.yEdOld);
		Graph g2 = r2.read();
		AestheticsQuantifier a2 = new AestheticsQuantifier(g2);
		System.out.println("Stress:\t" + a1.getStress() + "\t" + a2.getStress());
		System.out.println("Crossing Number:\t" + a1.getCrossingNumber() + "\t" + a2.getCrossingNumber());
		System.out.println("Jaccard Index:\t" + a1.getJaccardIndex() + "\t" + a2.getJaccardIndex());
		System.out.println("Edge Length Ratio:\t" + a1.getEdgeLengthRatio() + "\t" + a2.getEdgeLengthRatio());
		System.out.println("Node Resolution:\t" + a1.getNodeResolution() + "\t" + a2.getNodeResolution());
		System.out.println("Aspect Ratio:\t" + a1.getAspectRatio() + "\t" + a2.getAspectRatio());
		System.out.println("Crossing Resolution:\t" + a1.getCrossingResolution() + "\t" + a2.getCrossingResolution());
		System.out.println("Angular Resolution:\t" + a1.getAngularResolution() + "\t" + a2.getAngularResolution());
		IPEMatrixWriter iw = new IPEMatrixWriter("/home/foersth/GraphStats/complete3.ipe");
		iw.write(g2);*/
		/*GraphMLReader r1 = new GraphMLReader("/home/foersth/GraphStats/color4.graphml", GraphMLReader.InputType.yEdOld);
		Graph g1 = r1.read();
		AestheticsQuantifier a1 = new AestheticsQuantifier(g1);
		System.out.println("Stress:\t" + a1.getStress());
		System.out.println("Crossing Number:\t" + a1.getCrossingNumber());
		System.out.println("Jaccard Index:\t" + a1.getJaccardIndex());
		System.out.println("Edge Length Ratio:\t" + a1.getEdgeLengthRatio());
		System.out.println("Node Resolution:\t" + a1.getNodeResolution());
		System.out.println("Aspect Ratio:\t" + a1.getAspectRatio());
		System.out.println("Crossing Resolution:\t" + a1.getCrossingResolution());
		System.out.println("Angular Resolution:\t" + a1.getAngularResolution());*/

		/*RandomKPartiteGraphGenerator rg1 = new RandomKPartiteGraphGenerator(0.5,2, 60);
		List<Integer> n1 = new ArrayList<>();
		n1.add(35);
		n1.add(60);
		Graph g1 = rg1.generateRandomGraph(n1);
		IPEMatrixWriter iw1 = new IPEMatrixWriter("/home/foersth/GraphStats/color1.ipe");
		iw1.write(g1);
		AdjacencyMatrixReader ar = new AdjacencyMatrixReader("/home/foersth/GraphStats/kPartite.adj");
		Graph g2 = ar.read();
		IPEMatrixWriter iw2 = new IPEMatrixWriter("/home/foersth/GraphStats/color2.ipe");
		iw2.write(g2);*/
		/*RandomKPartiteGraphGenerator rg3 = new RandomKPartiteGraphGenerator(0.05,4, 60);
		List<Integer> n3 = new ArrayList<>();
		n3.add(20);
		n3.add(35);
		n3.add(15);
		n3.add(25);
		Graph g3 = rg3.generateRandomGraph(n3);
		IPEMatrixWriter iw3 = new IPEMatrixWriter("/home/foersth/GraphStats/color3.ipe");
		iw3.write(g3);
		GraphMLWriter gw = new GraphMLWriter("/home/foersth/GraphStats/color4.graphml");
		gw.write(g3);*/
		/*AdjacencyMatrixReader ar = new AdjacencyMatrixReader("/home/foersth/GraphStats/hamiltonian.adj");
		Graph g = ar.read();
		IPEMatrixWriter iw = new IPEMatrixWriter("/home/foersth/GraphStats/hamiltonian.ipe");
		GraphMLWriter gw = new GraphMLWriter("/home/foersth/GraphStats/hamiltonian.graphml");
		iw.write(g);
		gw.write(g);*/
		/*GraphMLReader r1 = new GraphMLReader("/home/foersth/GraphStats/ham1.graphml", GraphMLReader.InputType.yEdOld);
		Graph g1 = r1.read();
		AestheticsQuantifier a1 = new AestheticsQuantifier(g1);
		GraphMLReader r2 = new GraphMLReader("/home/foersth/GraphStats/ham1-only.graphml", GraphMLReader.InputType.yEdOld);
		Graph g2 = r2.read();
		AestheticsQuantifier a2 = new AestheticsQuantifier(g2);
		GraphMLReader r3 = new GraphMLReader("/home/foersth/GraphStats/ham2.graphml", GraphMLReader.InputType.yEdOld);
		Graph g3 = r3.read();
		AestheticsQuantifier a3 = new AestheticsQuantifier(g3);
		GraphMLReader r4 = new GraphMLReader("/home/foersth/GraphStats/ham2-only.graphml", GraphMLReader.InputType.yEdOld);
		Graph g4 = r4.read();
		AestheticsQuantifier a4 = new AestheticsQuantifier(g4);
		//GraphMLReader r = new GraphMLReader("/home/foersth/GraphStats/nonBipartite1.graphml", GraphMLReader.InputType.yEdOld);
		//Graph g = r.read();
		//AestheticsQuantifier a = new AestheticsQuantifier(g);
		System.out.println("Stress:\t" + a1.getStress() + "\t" + a2.getStress() + "\t" + a3.getStress() + "\t" + a4.getStress());
		System.out.println("Crossing Number:\t" + a1.getCrossingNumber() + "\t" + a2.getCrossingNumber() + "\t" + a3.getCrossingNumber()  + "\t" + a4.getCrossingNumber());
		System.out.println("Jaccard Index:\t" + a1.getJaccardIndex() + "\t" + a2.getJaccardIndex() + "\t" + a3.getJaccardIndex() + "\t" + a4.getJaccardIndex());
		System.out.println("Edge Length Ratio:\t" + a1.getEdgeLengthRatio() + "\t" + a2.getEdgeLengthRatio() + "\t" + a3.getEdgeLengthRatio() + "\t" + a4.getEdgeLengthRatio());
		System.out.println("Node Resolution:\t" + a1.getNodeResolution() + "\t" + a2.getNodeResolution() + "\t" + a3.getNodeResolution() + "\t" + a4.getNodeResolution());
		System.out.println("Aspect Ratio:\t" + a1.getAspectRatio() + "\t" + a2.getAspectRatio() + "\t" + a3.getAspectRatio() + "\t" + a4.getAspectRatio());
		System.out.println("Crossing Resolution:\t" + a1.getCrossingResolution() + "\t" + a2.getCrossingResolution() + "\t" + a3.getCrossingResolution() + "\t" + a4.getCrossingResolution());
		System.out.println("Angular Resolution:\t" + a1.getAngularResolution() + "\t" + a2.getAngularResolution() + "\t" + a3.getAngularResolution() + "\t" + a4.getAngularResolution());
		//g.normalize(50);
		/*g1.normalizeToSideLength(1000);
		g2.normalizeToSideLength(1000);
		g3.normalizeToSideLength(1000);
		GraphMLWriter w1 = new GraphMLWriter("/home/foersth/GraphStats/cv1.graphml");
		w1.write(g1);
		GraphMLWriter w2 = new GraphMLWriter("/home/foersth/GraphStats/cv2.graphml");
		w2.write(g2);
		GraphMLWriter w3 = new GraphMLWriter("/home/foersth/GraphStats/cv3.graphml");
		w3.write(g3);*/
	}
}