class Solution {
    static int p[];
    int findparent (int u)
    {
        if (p[u]==u)
            return u;
        return p[u]=findparent(p[u]);
    }
    void union(int u,int v)
    {
        int pu=findparent(u);
        int pv=findparent(v);
        if(pu!=pv)
        {
            p[pu]=pv;
        }
    }
    void dfs(ArrayList<ArrayList<Integer>> adj,int v,boolean []vis)
    {
        vis[v]=true;
        for(int nei:adj.get(v))
        {
            if(vis[nei]==false)
                dfs(adj,nei,vis);
        }
    }
    public int makeConnected(int n, int[][] connections) {
        p=new int[n];
        for(int i=0;i<n;i++)
            p[i]=i;
        int cnt=0;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            adj.add(new ArrayList<>());
        }
        for(int []edge: connections)
        {
            int u=edge[0],v=edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean []vis=new boolean[n];
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                dfs(adj,i,vis);
                cnt++;
            }
        }

        int extraedges=0;
        for(int [] edge: connections)
        {
            if(findparent(edge[0])==findparent(edge[1]))
                extraedges++;
            else
                union(edge[0],edge[1]);
        }
        if (extraedges>=(cnt-1))
            return cnt-1;
        return -1;
        

    }
}