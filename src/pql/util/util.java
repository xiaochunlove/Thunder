package pql.util;

public class util {
	public static double getDis(int x1, int y1, int x2, int y2) {
		double midd = (x1-x2) * (x1-x2) + (y1-y2) * (y1-y2);
		return Math.sqrt(midd);
	}
}
