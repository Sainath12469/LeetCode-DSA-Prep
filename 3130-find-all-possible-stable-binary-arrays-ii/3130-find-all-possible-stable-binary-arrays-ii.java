class Solution {
    int mod = 1_000_000_007;

    public int numberOfStableArrays(int z0, int o1, int limit) {
        int[][][] memo = new int[z0 + 1][o1 + 1][2];
        for (int x = 1; x <= Math.min(z0, limit); x++){
            memo[x][0][0] = 1;
        }
        for (int y = 1; y <= Math.min(o1, limit); y++){
            memo[0][y][1] = 1;
        }


        for (int x = 1; x <= z0; x++) {
            for (int y = 1; y <= o1; y++) {
                memo[x][y][0] = (memo[x - 1][y][0] + memo[x - 1][y][1]) % mod;
                if (x > limit){
                    memo[x][y][0] = (memo[x][y][0] + mod - memo[x - limit - 1][y][1]) % mod;
                }
                
                memo[x][y][1] = (memo[x][y - 1][0] + memo[x][y - 1][1]) % mod;
                if (y > limit){
                    memo[x][y][1] = (memo[x][y][1] + mod - memo[x][y - limit - 1][0]) % mod;
                }
            }
        }
        return (memo[z0][o1][0] + memo[z0][o1][1]) % mod;
    }
}