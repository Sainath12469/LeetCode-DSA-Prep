class Solution {
    public int digit(int n)
    {
        int ans=0;
        while(n>0)
        {
            ans+=n%10;
            n/=10;
        }
        return ans;
    }
    public int minElement(int[] nums) {
        int Min=nums[0];
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]>9)
                nums[i]=digit(nums[i]);
            if(nums[i]<Min)
                Min=nums[i];
        }
        return Min;
        
    }
}