class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> st = new HashSet<>();
        int n = nums.length;
        for(String str: nums)
            st.add(Integer.parseInt(str,2));
        int val = (int)Math.pow(2,n);
        for(int i=0; i<val; i++){
            if(!st.contains(i)){
                String s= Integer.toBinaryString(i);
                if(s.length()==n) return s;
                int len = s.length();
                for(int j=0; j<n-len; j++) s="0"+s;
                return s;
            }
        }
        return "";
    }
}