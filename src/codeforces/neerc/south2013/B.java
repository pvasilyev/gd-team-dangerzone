package codeforces.neerc.south2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class B {
    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public B() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new B().solve();
    }

    private void solve() throws IOException {
        int m = nextInt();
        int n = nextInt();
        int r = nextInt();

        double[] x = new double[n];
        double[] c = new double[m];

        for (int i = 0; i < m; i++) {
              c[i] = nextDouble();
        }
        for (int i = 0; i < n; i++) {
            x[i] = nextDouble();
        }
        int currentCameraPosition = 0;
        double totalDist = 0D;
        for (int i = 0; i < n; ++i) {
            double d = Math.abs(c[currentCameraPosition] - x[i]);
            while (gt(d * d + 1 , r * r)) {
                int newCameraPosition;
                if (c[currentCameraPosition] < x[i]) {
                    newCameraPosition = findLeftMostPosition(x[i], c, currentCameraPosition, c.length-1, r);
                } else {
                    newCameraPosition = findRightMostPosition(x[i], c, 0, currentCameraPosition, r);
                }
                totalDist += Math.abs(c[currentCameraPosition] - c[newCameraPosition]);
                currentCameraPosition = newCameraPosition;
                d = Math.abs(c[currentCameraPosition] - x[i]);
            }
        }

        out.printf(Locale.US, "%.3f", totalDist);
        out.close();
    }

    final double eps = 1e-6;
    boolean eq(double a, double b) {
        return Math.abs(a - b) < eps;
    }
    boolean ge(double a, double b) {
        return a > b || eq(a, b);
    }
    boolean le(double a, double b) {
        return ge(b, a);
    }
    boolean gt(double a, double b) {
        return a > b && !eq(a, b);
    }

    private int findRightMostPosition(double v, double[] c, int left, int right, int r) {
        if (right - left == 1) {
            return left;
        }
        int mid = (right + left) / 2;
        if (c[mid] < v) {
            return findRightMostPosition(v, c, mid, right, r);
        }
        double d = Math.abs(c[mid] - v);
        if (le(d * d + 1 , r * r)) {
            return findRightMostPosition(v, c, mid, right, r);
        } else {
            return findRightMostPosition(v, c, left, mid, r);
        }
    }

    private int findLeftMostPosition(double v, double[] c, int left, int right, int r) {
        if (right - left == 1) {
            return right;
        }
        int mid = (right + left) / 2;
        if (c[mid] > v) {
            return findLeftMostPosition(v, c, left, mid, r);
        }
        double d = Math.abs(c[mid] - v);
        if (le(d * d + 1 , r * r)) {
            return findLeftMostPosition(v, c, left, mid, r);
        } else {
            return findLeftMostPosition(v, c, mid, right, r);
        }
    }

    public String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
