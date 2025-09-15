import java.util.*;

class Solution {
    // DFS로 사이클 있는 지 파묘하기
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 그래프 구성 - 인접 리스트
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int preCourse = prereq[1];
            graph.get(preCourse).add(course); // preCourse -> course
        }

        // 방문 상태: 0=미방문, 1=방문중(현재 경로), 2=방문완료
        // 모든 풀이를 다 이런 식으로 3개의 상태를 관리하는 것 같음.
        int[] visited = new int[numCourses];

        // 모든 노드에서 사이클 검사하고 하나 발견 시 기각
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(graph, visited, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return true; // 현재 경로에서 다시 만남 = 사이클
        }

        if (visited[course] == 2) {
            return false; // 이미 완료된 노드
        }

        visited[course] = 1; // 방문중 표시

        // 인접탐색
        for (int nextCourse : graph.get(course)) {
            if (hasCycle(graph, visited, nextCourse)) {
                return true;
            }
        }

        visited[course] = 2; // 방문완료 표시
        return false;
    }
}