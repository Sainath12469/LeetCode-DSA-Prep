class Solution:
    def minMirrorPairDistance(self, nums: List[int]) -> int:
        def rev(n):
            r=0
            while n>0:
                r=r*10+n%10
                n//=10
            return r
        lasti={}
        ans=float('inf')
        for  i in range(len(nums)-1,-1,-1):
            r=rev(nums[i])
            if r in lasti:
                    ans=min(ans,lasti[r]-i)
            lasti[nums[i]]=i
        return -1 if ans==float('inf') else ans
        