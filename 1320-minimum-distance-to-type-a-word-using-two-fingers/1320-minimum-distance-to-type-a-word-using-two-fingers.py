from functools import lru_cache

class Solution:
    def minimumDistance(self, word: str) -> int:

        def dist(a, b):
            if a == -1:
                return 0
            r1, c1 = divmod(a, 6)
            r2, c2 = divmod(b, 6)
            return abs(r1 - r2) + abs(c1 - c2)

        @lru_cache(None)
        def dp(i, f1, f2):
            if i == len(word):
                return 0

            cur = ord(word[i]) - ord('A')

            move_f1 = dist(f1, cur) + dp(i + 1, cur, f2)
            move_f2 = dist(f2, cur) + dp(i + 1, f1, cur)

            return min(move_f1, move_f2)

        return dp(0, -1, -1)