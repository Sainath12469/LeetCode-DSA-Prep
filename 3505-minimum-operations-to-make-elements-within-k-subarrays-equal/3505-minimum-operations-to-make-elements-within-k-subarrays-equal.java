import java.util.*;

class Solution {

    class BIT {
        int n;
        int[] count;
        long[] sum;

        BIT(int n) {
            this.n = n;
            count = new int[n + 1];
            sum = new long[n + 1];
        }

        void update(int i, int c, long s) {
            for (; i <= n; i += i & -i) {
                count[i] += c;
                sum[i] += s;
            }
        }

        int queryCount(int i) {
            int res = 0;

            for (; i > 0; i -= i & -i) {
                res += count[i];
            }

            return res;
        }

        long querySum(int i) {
            long res = 0;

            for (; i > 0; i -= i & -i) {
                res += sum[i];
            }

            return res;
        }

        // Find index of the kth smallest element
        int findKth(int k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = idx + step;

                if (next <= n && count[next] < k) {
                    idx = next;
                    k -= count[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int size = 0;

        for (int num : sorted) {
            if (size == 0 || sorted[size - 1] != num) {
                sorted[size++] = num;
            }
        }

        int[] arr = Arrays.copyOf(sorted, size);

        BIT bit = new BIT(size);

        // Map each number to its compressed index
        int[] index = new int[n];

        for (int i = 0; i < n; i++) {
            index[i] = Arrays.binarySearch(arr, nums[i]) + 1;
        }

        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        int leftCount = (x + 1) / 2;

        // Sliding window
        for (int i = 0; i < n; i++) {

            bit.update(index[i], 1, nums[i]);
            windowSum += nums[i];

            if (i >= x) {
                bit.update(index[i - x], -1, -((long) nums[i - x]));
                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {

                // Find median
                int medianIndex = bit.findKth(leftCount);
                long median = arr[medianIndex - 1];

                // Sum of elements strictly smaller than median
                int smallerCount = bit.queryCount(medianIndex - 1);
                long smallerSum = bit.querySum(medianIndex - 1);

                // Sum of the smallest leftCount elements
                long leftSum = smallerSum
                        + (long) (leftCount - smallerCount) * median;

                // Minimum operations to make window equal
                long rightSum = windowSum - leftSum;

                cost[i - x + 1] =
                        median * leftCount - leftSum
                        + rightSum - median * (x - leftCount);
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 0; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {

            for (int i = 1; i <= n; i++) {

                // Skip current element
                dp[j][i] = dp[j][i - 1];

                // Select window ending at i - 1
                if (i >= x && dp[j - 1][i - x] != INF) {

                    long take = dp[j - 1][i - x]
                              + cost[i - x];

                    dp[j][i] = Math.min(dp[j][i], take);
                }
            }
        }

        return dp[k][n];
    }
}