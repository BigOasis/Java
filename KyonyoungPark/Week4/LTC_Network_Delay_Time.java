mport java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 이 값 굳이 Integer.MAX_VALUE로 해도 별 지장없을듯..
        int MAXIMUM = (n-1)*100 + 1;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            if (!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            graph.get(u).add(new int[]{v, w});
        }

        //다익스트라
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAXIMUM);

        //거리 순으로 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 시작점 초기화
        dist[k] = 0;
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int node = cur[1];

            if (d > dist[node]) continue;
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int neighbor = edge[0];
                    int weight = edge[1];
                    // 인접 노드랑 거리 계산 후 비교
                    int newDist = dist[node] + weight;
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        pq.offer(new int[]{newDist, neighbor});
                    }
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == MAXIMUM) {
                return -1; // 도달 불가능한 노드가 있음
            }
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}