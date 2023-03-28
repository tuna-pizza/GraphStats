package GraphStats.Algorithm;

import GraphStats.Data.Coordinate;

import java.util.LinkedList;
import java.util.List;

import GraphStats.Graph.*;

public class Utils
{
	static Utils utils;
	{
		if (utils == null)
		{
			utils = new Utils();
		}
	}
	public static double lineAt(double a, double b, double x)
	{
		return a*x +b;
	}
	public static double distance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
	}
	public static double distance(Coordinate p, Coordinate q)
	{
		return distance(p.getX(),p.getY(),q.getX(),q.getY());
	}
	public static Coordinate computeCrossing(Edge e, Edge c)
	{
		Coordinate e1 = e.getV1().getCoordinate();
		Coordinate e2 = e.getV2().getCoordinate();
		double leftE = Math.min(e1.getX(), e2.getX());
		double rightE = Math.max(e1.getX(), e2.getX());
		double bottomE = Math.min(e1.getY(), e2.getY());
		double topE = Math.max(e1.getY(), e2.getY());
		double aE=0;
		double bE=0;
		boolean verticalE = false;
		if (leftE != rightE)
		{
			aE = (e1.getY()-e2.getY())/(e1.getX()-e2.getX());
			bE = e1.getY() - aE * e1.getX();
		}
		else
		{
			verticalE = true;
		}
		Coordinate c1 = c.getV1().getCoordinate();
		Coordinate c2 = c.getV2().getCoordinate();
		double leftC = Math.min(c1.getX(), c2.getX());
		double rightC = Math.max(c1.getX(), c2.getX());
		double bottomC = Math.min(c1.getY(), c2.getY());
		double topC = Math.max(c1.getY(), c2.getY());
		double aC = 0;
		double bC = 0;
		boolean verticalC = false;
		if (leftC != rightC)
		{
			aC = (c1.getY()-c2.getY())/(c1.getX()-c2.getX());
			bC = c1.getY() - aC * c1.getX();
		}
		else
		{
			verticalC = true;
		}
		if (verticalC && verticalE)
		{
			return null;
		}
		if (verticalC)
		{
			double leftSwap = leftC;
			double rightSwap = rightC;
			double aSwap = aC;
			double bSwap = bC;
			leftC = leftE;
			rightC = rightE;
			aC = aE;
			bC= bE;
			leftE = leftSwap;
			rightE = rightSwap;
			bottomE = bottomC;
			topE = topC;
			aE = aSwap;
			bE = bSwap;
			verticalE = true;
		}
		if (verticalE)
		{
			if (leftC <= leftE && rightC >= leftE)
			{
				double y = lineAt(aC,bC,leftE);
				if (topE >= y && y >= bottomE)
				{
					return new Coordinate(leftE,y);
				}
			}
		}
		else
		{
			double x = (bC-bE)/(aE-aC);
			if (leftC <= x && x <= rightC)
			{
				if (leftE <= x && x <= rightE)
				{
					double y = lineAt(aC,bC,x);
					return new Coordinate(x,y);
				}
			}
		}
		return null;
	}

	public static double computeAngle(Edge e, Edge c)
	{
		Coordinate e1 = e.getV1().getCoordinate();
		Coordinate e2 = e.getV2().getCoordinate();
		double leftE = Math.min(e1.getX(), e2.getX());
		double rightE = Math.max(e1.getX(), e2.getX());
		double bottomE = Math.min(e1.getY(), e2.getY());
		double topE = Math.max(e1.getY(), e2.getY());
		double aE=0;
		double bE=0;
		boolean verticalE = false;
		if (leftE != rightE)
		{
			aE = (e1.getY()-e2.getY())/(e1.getX()-e2.getX());
			bE = e1.getY() - aE * e1.getX();
		}
		else
		{
			verticalE = true;
		}
		Coordinate c1 = c.getV1().getCoordinate();
		Coordinate c2 = c.getV2().getCoordinate();
		double leftC = Math.min(c1.getX(), c2.getX());
		double rightC = Math.max(c1.getX(), c2.getX());
		double bottomC = Math.min(c1.getY(), c2.getY());
		double topC = Math.max(c1.getY(), c2.getY());
		double aC = 0;
		double bC = 0;
		boolean verticalC = false;
		if (leftC != rightC)
		{
			aC = (c1.getY()-c2.getY())/(c1.getX()-c2.getX());
			bC = c1.getY() - aC * c1.getX();
		}
			else
		{
			verticalC = true;
		}
		if (verticalC && verticalE)
		{
			return 0;
		}
		if (verticalC)
		{
			double leftSwap = leftC;
			double rightSwap = rightC;
			double aSwap = aC;
			double bSwap = bC;
			leftC = leftE;
			rightC = rightE;
			aC = aE;
			bC= bE;
			leftE = leftSwap;
			rightE = rightSwap;
			bottomE = bottomC;
			topE = topC;
			aE = aSwap;
			bE = bSwap;
			verticalE = true;
		}
		if (verticalE)
		{
			double angle = Math.asin(aC);
			if (angle < 0)
			{
				angle =  -angle;
			}
			return Math.PI/2 - angle;
		}
		else
		{
			double angle = Math.atan((aC-aE)/(1+(aC*aE)));
			if (angle < 0)
			{
				angle = angle+Math.PI;
			}
			return angle;
		}
	}
	public static LinkedList<Edge> getCrossingFreeEdges(List<Edge> edges)
	{
		LinkedList<Edge> crossingFreeEdges = new LinkedList<>();
		for (Edge e1 : edges)
		{
			boolean isCrossing = false;
			for (Edge e2 : edges)
			{
				if (!e1.equals(e2))
				{
					if (Utils.computeCrossing(e1, e2) != null)
					{
						isCrossing = true;
					}
				}
			}
			if (!isCrossing)
			{
				crossingFreeEdges.add(e1);
			}
		}
		return crossingFreeEdges;
	}

	public static boolean areIncidentEdges(Edge e1, Edge e2)
	{
		if (e1.getV1().equals(e2.getV1()))
		{
			return true;
		}
		if (e1.getV2().equals(e2.getV1()))
		{
			return true;
		}
		if (e1.getV1().equals(e2.getV2()))
		{
			return true;
		}
		if (e1.getV2().equals(e2.getV2()))
		{
			return true;
		}
		return false;
	}
}
