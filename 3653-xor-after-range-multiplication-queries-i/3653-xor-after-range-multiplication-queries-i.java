class Solution {
    public int xorAfterQueries(int[] a, int[][] q) {
        final int MOD = 1000000007;
        for (int[] x : q) {
            int l = x[0], r = x[1], k = x[2], v = x[3];
            for (int i = l; i <= r; i += k) {
                a[i] = (int)((long)a[i]*v%MOD);
            }
        }
        int ans=0;
        for (int n : a) ans^=n;
        return ans;
    }
}
