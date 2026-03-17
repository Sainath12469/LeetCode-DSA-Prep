class Solution {
    int solve(int []a)
    {
        Stack<Integer> st=new Stack<>();
        int n=a.length;
        int left[]=new int[n];
        for(int i=0;i<n;i++)
        {
            while(!st.isEmpty() && a[st.peek()]>=a[i])
                st.pop();
            left[i]=st.isEmpty()?-1:st.peek();
            st.push(i);
        }
        st=new Stack<>();
        int right[]=new int[n];
        for(int i=n-1;i>=0;i--)
        {
            while(!st.isEmpty() && a[st.peek()]>=a[i])
                st.pop();
            right[i]=st.isEmpty()?n:st.peek();
            st.push(i);
        }
        int res=0;
        for(int i=0;i<n;i++)
        {
            res=Math.max(res,a[i]*(right[i]-left[i]-1));
        }
        return res;
    }
    public int largestSubmatrix(int[][] matrix) {
        int res=0;
        int n=matrix.length,m=matrix[0].length;
        int []a=new int [m];
        for(int i=0;i<n;i++)
        {
            int[] temp=new int[m];
            for(int j=0;j<m;j++)
            {
                a[j]=matrix[i][j]==0 ? 0:a[j]+1;
            }
            for(int j=0;j<m;j++)
                temp[j]=a[j];
            Arrays.sort(temp);
            res=Math.max(res,solve(temp));
        }
        return res;
    }
}