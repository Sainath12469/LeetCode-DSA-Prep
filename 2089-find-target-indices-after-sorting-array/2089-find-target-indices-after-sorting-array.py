class Solution:
    def targetIndices(self, nums: List[int], target: int) -> List[int]:
        c=0
        smallcount=0
        for i in nums:
            if i==target:
                c+=1
            if i<target:
                smallcount+=1
            res=[]
        for _ in range(c):
            res.append(smallcount)
            smallcount+=1
        return res


        