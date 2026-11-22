class Solution {
    public boolean checkString(String s) {
        int b=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='a' && b!=0)
                return false;
            if(s.charAt(i)=='b')
                b++;
        }
        return true;
        
    }
}