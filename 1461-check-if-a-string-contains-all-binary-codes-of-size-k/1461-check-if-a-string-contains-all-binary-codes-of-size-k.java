class Solution {
    public boolean hasAllCodes(String s, int k) {
        HashSet<String> seen =new HashSet<>();
        int total=1<<k;
        for(int i=0;i<=s.length()-k;i++)
        {
            String part=s.substring(i,i+k);
            seen.add(part);
            if(seen.size()==total)  return true;
        }
        return seen.size()==total;
    }
}