class Solution {
    int n;
    int m;
    Integer[][][] dp;

    public int solve(int[][] grid, int i, int j, int cost){
        if(i>=n||j>=m){
            return Integer.MAX_VALUE;
        }
        cost ^= grid[i][j];
        if(i==n-1&&j==m-1){
            return cost;
        }
        if(dp[i][j][cost]!=null){
            return dp[i][j][cost];
        }

        int down = solve(grid,i+1,j,cost);
        int right = solve(grid,i,j+1,cost);

        return dp[i][j][cost] = Math.min(down,right);
    }
    public int minCost(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        dp = new Integer[n][m][1024];
        return solve(grid,0,0,0);
        
    }
}