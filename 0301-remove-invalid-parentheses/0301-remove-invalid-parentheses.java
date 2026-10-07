class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res=new ArrayList<>();
        Queue<String> q=new LinkedList<>();
        Set<String> visited=new HashSet<>();

        q.offer(s);
        visited.add(s);
        boolean found=false;

        while(!q.isEmpty())
        {
            String curr=q.poll();
            if(valid(curr))
            {
                res.add(curr);
                found=true;
            }
            if(found) continue;
            for(int i=0;i<curr.length();i++)
            {
                if(curr.charAt(i)!='(' && curr.charAt(i)!=')') continue;
                String next=curr.substring(0,i)+curr.substring(i+1);
                if(!visited.contains(next))
                {
                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        return res;
    }
    boolean valid(String s)
    {
        int d=0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='(')
                d++;
            else if(s.charAt(i)==')')
            {
                d--;
                if(d<0) return false;
            }
        }
        return d==0;
    }
}