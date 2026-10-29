import java.util.*;

class Solution {
    int LOG;
    int[][] up;
    int[] depth, tin, tout, rootMask;
    char[] chars;
    List<Integer>[] adj;
    Fenwick bit;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= val;
            }
        }

        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res ^= tree[i];
            }
            return res;
        }

        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            if (r + 1 <= n) {
                update(r + 1, val);
            }
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        chars = s.toCharArray();

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        rootMask = new int[n];

        int[] parent = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];
        int[] size = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(size, 1);

        // Iterative DFS to avoid stack overflow.
        int top = 0, timer = 0;
        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Calculate subtree sizes.
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            if (parent[u] != -1) {
                size[parent[u]] += size[u];
            }
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
        }

        // Binary lifting table.
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];

        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u] == -1 ? u : parent[u];
        }

        for (int k = 1; k < LOG; k++) {
            for (int u = 0; u < n; u++) {
                up[k][u] = up[k - 1][up[k - 1][u]];
            }
        }

        // Initial root-to-node character masks.
        for (int u : order) {
            int mask = 1 << (chars[u] - 'a');

            rootMask[u] = mask;
            if (parent[u] != -1) {
                rootMask[u] ^= rootMask[parent[u]];
            }
        }

        bit = new Fenwick(n);
        List<Boolean> ans = new ArrayList<>();

        for (String query : queries) {
            String[] p = query.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                if (delta != 0) {
                    // Update root-path masks for every node in u's subtree.
                    bit.rangeUpdate(
                        tin[u] + 1,
                        tout[u] + 1,
                        delta
                    );

                    chars[u] = c;
                }

            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int w = lca(u, v);

                int maskU = rootMask[u] ^ bit.query(tin[u] + 1);
                int maskV = rootMask[v] ^ bit.query(tin[v] + 1);

                int pathMask = maskU ^ maskV
                             ^ (1 << (chars[w] - 'a'));

                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }
}