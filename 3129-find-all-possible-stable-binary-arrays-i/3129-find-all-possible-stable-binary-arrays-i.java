class Solution {
    static int MOD=(int)1e9+7;
    int solve(int zero,int one,int limit,int lastseen,int conscount,int [][][][]dp)
    {
        if(conscount>limit) return 0;
        if(zero==0 && one ==0) return 1;
        if(lastseen!=-1 && dp[zero][one][lastseen][conscount]!=-1) return dp[zero][one][lastseen][conscount];
        int res=0;
        if(zero>0)
        {
            int newlast=0;
            int newcount=(lastseen==0)? conscount+1:1;
            res=(res+solve(zero-1,one,limit,newlast,newcount,dp))%MOD;
        }
        if(one>0)
        {
            int newlast=1;
            int newcount=(lastseen==1)? conscount+1:1;
            res=(res+solve(zero,one-1,limit,newlast,newcount,dp))%MOD;
        }
        if(lastseen!=-1)
            dp[zero][one][lastseen][conscount]=res;
        return res;
    }
    public int numberOfStableArrays(int zero, int one, int limit) {
        int [][][][]dp=new int[zero+1][one+1][2][limit+1];
        for(int i=0;i<=zero;i++)
        {
            for(int j=0;j<=one;j++)
            {
                for(int k=0;k<2;k++)
                    Arrays.fill(dp[i][j][k],-1);
            }
        }
        return solve(zero,one,limit,-1,0,dp);
    }
}