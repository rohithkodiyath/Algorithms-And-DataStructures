package week2.assignment;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 */

/**
 * @author rohith
 *
 */
public class Permutation {

	public static void main(String[] args) {
		int k = Integer.valueOf(args[0]);
		if (k <= 0)
			return;
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			q.enqueue(item);
		}
		for (int i = 0; i < k; i++) {
			StdOut.println(q.dequeue());
		}

	}

}
