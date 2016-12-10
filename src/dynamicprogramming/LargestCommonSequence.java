package dynamicprogramming;

public class LargestCommonSequence {

	public static int[][] c;
	public static int[][] b;

	public static final int UP = 1;
	public static final int LEFT = 0;
	public static final int DIAG = 2;
	public static final int BOTH = 3;
	public static final int NONE = -1;

	public static String[] getLargestCommonSequence(String X, String Y) {
		String[] ans = new String[1];
		c = new int[X.length() + 1][Y.length() + 1];
		b = new int[X.length() + 1][Y.length() + 1];
		for (int i = 0; i <= X.length(); i++) {
			for (int j = 0; j <= Y.length(); j++) {
				c[i][j] = NONE;
				b[i][j] = NONE;
			}
		}

		int solution = getLargestSequenceLength(X, X.length(), Y, Y.length());
		ans[0] = getLargestSequences(X, Y);

		return ans;
	}

	private static int getLargestSequenceLength(String X, int i, String Y, int j) {

		if (c[i][j] != -1) {
			// do nothing, you are done
		} else if (i == 0 || j == 0) {
			c[i][j] = 0;
			b[i][j] = NONE;
		} else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
			c[i][j] = 1 + getLargestSequenceLength(X, i - 1, Y, j - 1);
			b[i][j] = DIAG;
		} else {
			int a = getLargestSequenceLength(X, i - 1, Y, j);
			int d = getLargestSequenceLength(X, i, Y, j - 1);
			if (a > d) {
				c[i][j] = a;
				b[i][j] = UP;
			} else if (d > a) {
				c[i][j] = d;
				b[i][j] = LEFT;
			} else {
				c[i][j] = a;
				b[i][j] = UP;
			}
		}

		return c[i][j];
	}

	private static String getLargestSequences(String X, String Y) {
		int i = X.length();
		int j = Y.length();

		String ans = getLargestSequences(i, j, X);
		System.out.println(ans);

		return ans;
	}

	private static String getLargestSequences(int i, int j, String X) {
		String ans = "";

		switch (b[i][j]) {
			case NONE:
				return "";
			case UP:
				return getLargestSequences(i - 1, j, X);
			case LEFT:
				return getLargestSequences(i, j - 1, X);
			case BOTH:
				ans = getLargestSequences(i - 1, j, X);
				//ans[1] = getLargestSequences(i, j-1, X);
				break;
			case DIAG:
				ans = getLargestSequences(i - 1, j - 1, X) + X.charAt(i);
				break;
		}
		return ans;
	}

	public static void main(String[] args) {
		String X = "ABCCBABAABBCBBABBABABABCABBB";
		String Y = "ABBBBAABCBABCBCBBCABAABA";

		String[] ans = getLargestCommonSequence(X, Y);
		System.out.println("LARGEST COMMON SEQUENCE LENGTH: " + ans[0].length());
		for (int i = 0; i < ans.length; i++) {
			System.out.println((i + 1) + ") " + ans[i]);
		}

		System.out.println("\nC TABLE:");
		for (int l = 0; l <= X.length(); l++) {
			for (int k = 0; k < Y.length(); k++) {
				if (c[l][k] == NONE) {
					System.out.print("  -");
				} else {
					System.out.format("%3d", c[l][k]);
				}
			}
			System.out.println();
		}

		System.out.println("\nB TABLE:");
		for (int l = 0; l <= X.length(); l++) {
			for (int k = 0; k < Y.length(); k++) {
				if (b[l][k] == NONE) {
					System.out.print("  -");
				} else {
					System.out.format("%3d", b[l][k]);
				}
			}
			System.out.println();
		}
	}
}
