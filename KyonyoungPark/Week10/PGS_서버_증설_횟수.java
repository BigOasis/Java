import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int totalAdditions = 0;
        List<Integer> expire = new ArrayList<>();

        for (int hour = 0; hour < 24; hour++) {
            for (int i = expire.size() - 1; i >= 0; i--) {
                if (expire.get(i) <= hour) {
                    expire.remove(i);
                }
            }
            int activeServers = expire.size();
            int requiredServers = players[hour]/ m;

            if (activeServers < requiredServers) {
                int newServers = requiredServers - activeServers;
                totalAdditions += newServers;

                for (int i = 0; i < newServers; i++) {
                    expire.add(hour + k);
                }
            }
        }

        return totalAdditions;
    }
}