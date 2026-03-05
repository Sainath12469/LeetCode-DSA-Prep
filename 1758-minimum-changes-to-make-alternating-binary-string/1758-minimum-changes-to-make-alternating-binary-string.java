class Solution {
    public int minOperations(String s) {
        int res1=0,res2=0;
        for(int i=0;i<s.length();i++)
        {
            if(i%2==0)
            {
                if(s.charAt(i)=='0') res1++;
                else  res2++;
            }
            else
            {
                if(s.charAt(i)=='1') res1++;
                else res2++;
            }
        }
        return Math.min(res1,res2);        
    }
}