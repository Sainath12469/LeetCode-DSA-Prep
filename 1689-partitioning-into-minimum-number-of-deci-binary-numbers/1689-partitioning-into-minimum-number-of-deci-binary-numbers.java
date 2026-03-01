class Solution {
    public int minPartitions(String n) {
        int res=0;
        for(int i=0;i<n.length();i++)
        {
            if(n.charAt(i)=='9') return 9;
            res=Math.max(res,n.charAt(i)-'0');
        }
        return res;
    }
}