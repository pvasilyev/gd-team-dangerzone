package timus.neerc.eastern2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// @todo WA 9
public class H {

    private BufferedReader br;
    private PrintWriter out;
    private StringTokenizer st;

    public H() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

    }

    public static void main(String[] args) throws IOException {
        new H().solve();
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

    private void solve() throws IOException {
        final int a = nextInt();
        final int b = nextInt();
        final int nn = nextInt();
        if(nn % 2 == 1) {
             out.println("Liar");
            out.flush();;
            return;
        }
        final int n = nn / 2;
        final int[] enter = new int[n];
        final int[] exit = new int[n];
        int pos1 = 0;
        int pos2 = 0;
        Set<Integer> times = new HashSet<>();
        for (int i = 0; i < n * 2; ++i) {
            int time = nextInt();
            int type = nextInt();
            times.add(time);
            if (type == 0) {
                if(pos1 == n) {
                    out.println("Liar");
                    out.flush();;
                    return;
                }
                enter[pos1++] = time;
            } else {
                if(pos2 == n) {
                    out.println("Liar");
                    out.flush();;
                    return;
                }
                exit[pos2++] = time;
            }
        }
        if(times.size() != 2 * n) {
            String x = null;
            out.println(x.length());
        }
        class Utils {
            int[] prev = new int[n];
            boolean[] used = new boolean[n];

            boolean findChain(int u) {
                used[u] = true;
                for (int v = 0; v < n; ++v) {
//                    if (exit[v] < enter[u])
//                        continue;
//                    if (exit[v] - enter[u] > b) {
//                        break;
//                    }
                    int spent = exit[v] - enter[u];
                    if(spent < 0 ||  spent > b && spent < a) {
                        continue;
                    }
                    if (prev[v] == -1 || !used[prev[v]] && findChain(prev[v])) {
                        prev[v] = u;
                        return true;
                    }
                }
         /*       for (int v = n - 1; v >= 0; --v) {
                    if (exit[v] - enter[u] < a) {
                        break;
                    }
                    if (prev[v] == -1 || !used[prev[v]] && findChain(prev[v])) {
                        prev[v] = u;
                        return true;
                    }
                }*/
                return false;
            }

            boolean maxMatch() {
                Arrays.fill(prev, -1);
                for (int u = 0; u < n; ++u) {
                    Arrays.fill(used, false);
                    if (!findChain(u)) {
                        return false;
                    }
                }
                return true;
            }
        }
        Utils u = new Utils();
        if (!u.maxMatch()) {
            out.println("Liar");
        } else {
            out.println("No reason");
            for (int i = 0; i < n; ++i) {
                out.println(enter[i] + " " + exit[u.prev[i]]);
            }
        }
        out.flush();
    }

}
