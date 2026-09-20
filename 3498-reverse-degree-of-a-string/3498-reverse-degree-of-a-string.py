class Solution:
    def reverseDegree(self, s: str) -> int:
        d={}
        c=26
        for i in range(26):
            d[chr(97+i)]=c
            c-=1
        res=0
        for i in range(len(s)):
            res+=(d[s[i]])*(i+1)
        return res
        