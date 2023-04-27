package GraphStats.Graph;

import java.util.Comparator;

public class VertexIDComparator implements Comparator<Vertex>
{
	@Override
	public int compare(Vertex v1, Vertex v2)
	{
		return v1.getID().compareTo(v2.getID());
	}
}
