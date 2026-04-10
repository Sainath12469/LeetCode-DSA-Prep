class Solution {
    public int minimumDistance(int[] nums) {
        int res=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++)
            {
                for(int j=0;j<nums.length;j++)
                    {
                        for(int k=0;k<nums.length;k++)
                            {
                                if(nums[i]==nums[j] && nums[j]==nums[k] && nums[i]==nums[k] && i!=j && j!=k && i!=k)
                                    res=Math.min(res,Math.abs(i-j)+Math.abs(j-k)+Math.abs(k-i));
                            }
                    }
            }
        if (res==Integer.MAX_VALUE) return -1;
        else return res;
        
    }
}