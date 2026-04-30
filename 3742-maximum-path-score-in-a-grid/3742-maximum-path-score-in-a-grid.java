class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int n=grid.length,m=grid[0].length;
        int [][][]dp=new int[n][m][k+1];
        for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                        {
                        Arrays.fill(dp[i][j],-1);
                    }
            }
        dp[0][0][0]=0;
        for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                    {
                        for(int cost=0;cost<=k;cost++)
                            {
                                if(dp[i][j][cost]==-1) continue;
                                if(j+1 <m)
                                {
                                    int rval=grid[i][j+1];
                                    int add=rval==0? 0 : 1;
                                    int nc=cost+add;
                                    int ns=dp[i][j][cost]+rval;
                                    if(nc<=k && ns> dp[i][j+1][nc])
                                        dp[i][j+1][nc]=ns;
                                }
                                if(i+1<n)
                                {
                                    int dval=grid[i+1][j];
                                    int add=dval==0?0:1;
                                    int nc=cost+add;
                                    int ns=dp[i][j][cost]+dval;
                                    if(nc<=k && ns>dp[i+1][j][nc])
                                        dp[i+1][j][nc]=ns;
                                }
                            }
                        
                    }
            }
        int res=-1;
        for(int cost=0;cost<=k;cost++)
            res=Math.max(res,dp[n-1][m-1][cost]);
        return res;
    }
}