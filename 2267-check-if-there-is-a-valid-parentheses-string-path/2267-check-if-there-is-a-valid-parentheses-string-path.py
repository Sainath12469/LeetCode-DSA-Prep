class Solution:
    def hasValidPath(self, g: list[list[str]]) -> bool:
        m = len(g) 
        n = len(g[0])
        memo = {}
        b = 0

        if g[m-1][n-1] == '(':
            return False

        def dfs(i, j, b):
            if (i, j, b) in memo:
                return memo[(i, j, b)]

            if i >= m or j >= n:
                return False

            if g[i][j] == '(':
                b += 1
            else:
                b -= 1

            if i == m-1 and j == n-1 and b == 0:
                return True

            if b < 0:
                return False

            if b > ((m-i-1) + (n-j-1)):
                return False

            memo[(i+1, j, b)] = dfs(i+1, j, b)
            memo[(i, j+1, b)] = dfs(i, j+1, b)

            return memo[(i+1, j, b)] or memo[(i, j+1, b)]

        return dfs(0, 0, 0)