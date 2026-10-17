class Solution {
    int digitSum(int n)
    {
        int res=0;
        while(n>0)
        {
            res+=n%10;
            n/=10;
        }
        return res;
    }
    public int minSwaps(int[] nums) {
        int n=nums.length;
        int arr[][]=new int[n][2];
        for(int i=0;i<n;i++)
        {
            arr[i][0]=nums[i];
            arr[i][1]=digitSum(nums[i]);
        }
        Arrays.sort(arr,(a,b)->{
            if(a[1]!=b[1])
                return Integer.compare(a[1],b[1]);
            return Integer.compare(a[0],b[0]);
        });

        HashMap<Integer,Integer> targetIndex=new HashMap<>();
        for(int i=0;i<n;i++)
            targetIndex.put(arr[i][0],i);
        
        boolean visited[]=new boolean[n];
        int swaps=0;

        for(int i=0;i<n;i++)
        {
            if(visited[i])
                continue;
            
            int cyclesize=0;
            int j=i;
            while(visited[j]==false)
            {
                cyclesize++;
                visited[j]=true;
                j=targetIndex.get(nums[j]);
            }
            swaps+=cyclesize-1;
        }
        return swaps;

        
                
    }
}