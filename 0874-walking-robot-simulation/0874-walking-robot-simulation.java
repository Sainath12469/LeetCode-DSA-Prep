class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> obs = new HashSet<>();
        for (int[] o : obstacles) {
            obs.add((o[0] + 30000L) * 100000L + (o[1] + 30000L));
        }
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int x = 0;
        int y = 0;
        int maxDist = 0;
        
        for (int c : commands) {
            if (c == -1) {
                d = (d + 1) % 4;
            } else if (c == -2) {
                d = (d + 3) % 4;
            } else {
                for (int i = 0; i < c; i++) {
                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];
                    long nextPos = (nx + 30000L) * 100000L + (ny + 30000L);
                    
                    if (obs.contains(nextPos)) {
                        break;
                    }
                    x = nx;
                    y = ny;
                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }
        
        return maxDist;
    }
}