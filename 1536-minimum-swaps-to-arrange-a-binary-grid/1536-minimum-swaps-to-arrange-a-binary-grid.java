class Solution {
    public int minSwaps(int[][] grid) {
        int n=grid.length;
        int []temp=new int[n];
        for(int i=0;i<n;i++)
        {
            int c=0;
            for(int j=n-1;j>=0;j--)
            {
                if(grid[i][j]==0) c++;
                else break;
            }
            temp[i]=c;
        }
        int res=0;
        for(int i=0;i<n;i++)
        {
            int need=n-i-1;
            int j=i;
            while(j<n && temp[j]<need) j++;
            if (j==n) return -1;
            while(j>i)
            {
                int t=temp[j];
                temp[j]=temp[j-1];
                temp[j-1]=t;
                res++;
                j--;
            }
        }
        return res;
    }
}