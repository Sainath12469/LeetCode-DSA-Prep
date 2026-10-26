class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, ans = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    long total = dfs(grid, i, j, m, n);
                    if (total % k == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    
    private long dfs(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        
        long sum = grid[i][j];
        grid[i][j] = 0; // Sink the island
        
        sum += dfs(grid, i + 1, j, m, n);
        sum += dfs(grid, i - 1, j, m, n);
        sum += dfs(grid, i, j + 1, m, n);
        sum += dfs(grid, i, j - 1, m, n);
        
        return sum;
    }
}