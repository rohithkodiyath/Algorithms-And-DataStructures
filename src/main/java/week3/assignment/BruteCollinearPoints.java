package week3.assignment;

import java.util.Arrays;

public class BruteCollinearPoints {

	private LineSegment[] lineSegments = new LineSegment[0];
	private int numberOfLineSegments;

	public BruteCollinearPoints(Point[] points) {
		validateForNull(points);
		points = Arrays.copyOf(points, points.length);
		Arrays.sort(points);
		
		checkForDuplicates(points);
		for (int i = 0; i < points.length - 3; i++) {
			for (int j = i + 1; j < points.length - 2; j++) {
				for (int k = j + 1; k < points.length - 1; k++) {
					for (int m = k + 1; m < points.length; m++) {
						if (checkForCollinearity(points[i], points[j], points[k], points[m])) {
							addLineSegments(new LineSegment(points[i], points[m]));
						}
					}
				}
			}
		}
	}

	private void addLineSegments(LineSegment seg) {
		if (lineSegments.length == numberOfLineSegments) {
			int newLength = lineSegments.length == 0 ? 1 : 2 * lineSegments.length;
			LineSegment[] aux = new LineSegment[newLength];
			for (int i = 0; i < lineSegments.length; i++) {
				aux[i] = lineSegments[i];
			}
			lineSegments = aux;
		}
		lineSegments[numberOfLineSegments] = seg;
		numberOfLineSegments++;
	}

	private boolean checkForCollinearity(Point a, Point b, Point c, Point d) {
		return a.slopeTo(b) == a.slopeTo(b) && a.slopeTo(b) == a.slopeTo(c) && a.slopeTo(b) == a.slopeTo(d);
	}

	public int numberOfSegments() {
		return this.numberOfLineSegments;
	}

	public LineSegment[] segments() {
		return this.lineSegments != null ? Arrays.copyOf(this.lineSegments, this.numberOfLineSegments) : null;
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
}
