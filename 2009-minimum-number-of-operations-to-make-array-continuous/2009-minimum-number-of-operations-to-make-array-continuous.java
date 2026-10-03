class Solution {
    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        int m=0;
        int n=nums.length;
        for(int i=0;i<n;i++)
        {
            if(i==0 || nums[i]!=nums[i-1])
                nums[m++]=nums[i];
        }
        int right=0;
        int maxkeep=1;
        for(int left=0;left<m;left++)
        {
            while(right<m && nums[right]-nums[left]<=n-1)
                right++;
            int validwindow=right-left;
            maxkeep=Math.max(maxkeep,validwindow);
        }
        return n-maxkeep;
    }
}