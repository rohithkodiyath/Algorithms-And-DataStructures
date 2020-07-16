package week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

	private LineSegment[] lineSegments = new LineSegment[0];
	private int numberOfLineSegments;

	public FastCollinearPoints(Point[] points) {
		validateForNull(points);
		points = Arrays.copyOf(points, points.length);
		Arrays.sort(points);
		checkForDuplicates(points);
		ArrayList<LineSegment> collinearSegments = new ArrayList<LineSegment>();
		for (int i = 0; i < points.length - 3; i++) {
			// sort remaining points wrt selected point based on the angle
			Point selectedPoint = points[i];
			Point[] selectedPoints = getSubArray(i + 1, points);
			Arrays.sort(selectedPoints, selectedPoint.slopeOrder());
			collinearSegments.addAll(getCollinearSegments(selectedPoints, selectedPoint));
		}
		this.lineSegments = collinearSegments.toArray(this.lineSegments);
		this.numberOfLineSegments =  this.lineSegments.length;
	}

	private Point[] getSubArray(int begIndex, Point[] points) {
		return Arrays.copyOfRange(points, begIndex, points.length);
	}

//	private ArrayList<LineSegment> getCollinearSegments(Point[] points, Point currentOrigin) {
//		ArrayList<LineSegment> retElements = new ArrayList<LineSegment>();
//		for (int i = 0; (i+3) <=  points.length; i = i + 3) {
//			if (checkForCollinearity(currentOrigin, points[i], points[i + 1], points[i + 2])) {
//				retElements.add(new LineSegment(currentOrigin, points[i + 2]));
//			}
//		}
//		return retElements;
//	}
	
	private ArrayList<LineSegment> getCollinearSegments(Point[] points, Point currentOrigin) {
		ArrayList<LineSegment> retElements = new ArrayList<LineSegment>();
		int consecutiveCounts = 0;
		double angle = 0;
		for (int i = 0; i < points.length ; i++) {
			Point selectedPoint = points[i];
			double slope = currentOrigin.slopeTo(selectedPoint);
			if(i == 0) {
				consecutiveCounts = 1;
				angle = slope;
				continue;
			}
			if( slope != angle) {
				consecutiveCounts = 1;
				continue;
			}
			//here slope == angle
			if(consecutiveCounts == 2) {
				retElements.add(new LineSegment(currentOrigin, selectedPoint));
				consecutiveCounts = 1;
				continue;
			}
			consecutiveCounts ++;
		}
		return retElements;
	}

//	private boolean checkForCollinearity(Point a, Point b, Point c, Point d) {
//		return a.slopeTo(b) == a.slopeTo(b) && a.slopeTo(b) == a.slopeTo(c) && a.slopeTo(b) == a.slopeTo(d);
//	}

	public int numberOfSegments() {
		return this.numberOfLineSegments;
	}

	public LineSegment[] segments() {
		return this.lineSegments;
	}

	private void validateForNull(Point[] points) {
		if(points == null) throw new IllegalArgumentException("Null Element In the constructor");
		for (Point point : points) {
			if (point == null)
				throw new IllegalArgumentException("Null Element Found");
		}
	}

	private void checkForDuplicates(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			if (points[i].compareTo(points[i + 1]) == 0)
				throw new IllegalArgumentException("Duplicate points found");
		}
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
	
//	public static void main(String[] args) {
//		Point p1 = new Point(10, 10);
//		Point p2 = new Point(20, 20);
//		Point p3 = new Point(30, 30);
//		Point p4 = new Point(40, 40);
//		Point p5 = new Point(50, 50);
//		Point p6 = new Point(60, 60);
//		Point p7 = new Point(70, 70);
//		Point[] ar = {p1,p2,p3,p4,p5,p6,p7};
//		FastCollinearPoints fc = new FastCollinearPoints(ar);
//		
//		System.out.println(Arrays.toString(fc.segments()));
//	}
}
