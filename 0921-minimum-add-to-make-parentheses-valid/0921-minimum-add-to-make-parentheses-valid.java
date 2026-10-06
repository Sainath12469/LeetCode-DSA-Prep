class Solution {
    public int minAddToMakeValid(String s) {
        int res=0;
        int depth=0;
        for(int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(c=='(')
                depth++;
            else
                depth--;
            
            if(depth<0)
            {
                res++;
                depth=0;
            }
        }
        res+=depth;
        return res;
    }
}