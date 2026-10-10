class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        long total = 0;
        for (int d : diff) {
            total += d;
        }

        if (k >= total) return 0;

        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int limit = low;
        long remaining = k;
        long ans = 0;

        for (int d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                d = limit;
            }
            ans += (long) d * d;
        }
        for (int i = 0; i < n && remaining > 0; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            if (d >= limit && d > 0) {
                ans -= (long) limit * limit
                     - (long) (limit - 1) * (limit - 1);
                remaining--;
            }
        }

        return ans;
    }
}