package vor;

import java.awt.Color;
import java.awt.color.*;
import java.util.Arrays;
import java.*;

public class Voronoi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int width = 1000;
		int height = 1000;

		int nSites = 100;
		Point2D[] sites = new Point2D[nSites];
		for(int i = 0; i < nSites; i++) {
			sites[i] = new Point2D((int)(Math.random() * ((width) + 1)), (int)(Math.random() * ((height) + 1)));
		}

		Color[] colors_list = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow};
		Color[] colors = new Color[nSites];
		for(int i = 0; i < nSites; i++) {
			colors[i] = colors_list[(int)(Math.random() * (colors_list.length))];
		}

		int[][] map = new int[height][width];
		map = buildVoronoiMap(sites, width, height);
		plotVoronoiMap(sites, colors, map);
	}

	public static double pointDist(Point2D p1, Point2D p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}

	public static int findClosestPoint(Point2D p, Point2D[] sites) {
		int closest_i = -1;
		double closest_dist = 999;
		for (int i = 0; i < sites.length; i++) {
			if (pointDist(p, sites[i]) < closest_dist) {
				closest_i = i;
				closest_dist = pointDist(p, sites[i]);
			}
		}
		return closest_i;
	}

	public static int[][] buildVoronoiMap(Point2D[] sites, int w, int h) {
		int[][] map = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int k = 0; k < w; k++) {
				Point2D temp_site = new Point2D(k, i);
				map[i][k] = findClosestPoint(temp_site, sites);
			}
		}
		return map;
	}

	public static void plotVoronoiMap(Point2D[] sites, Color[] colors, int[][] map) {
		int w = map.length;
		int h = map[0].length;

		StdDraw.setCanvasSize(w, h);
		StdDraw.setXscale(0, w);
		StdDraw.setYscale(0, h);

		for (int i = 0; i < h; i++) {
			for (int n = 0; n < w; n++) {
				StdDraw.setPenColor(colors[map[i][n]]);
				StdDraw.point(n, i);
			}
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < sites.length; i++) {
			int temp_x = sites[i].getX();
			int temp_y = sites[i].getY();
			StdDraw.filledCircle(temp_x, temp_y, 2);
		}
	}
}
