import java.util.*;

class Solution {
    private List<int[]> candidateKeys = new ArrayList<>();

    private boolean isUnique(int[] idx, String[][] relation){
        for(int i = 0; i < relation.length; i++){
            for(int j = i + 1; j < relation.length; j++) {
                boolean isSame = true;
                for(int k = 0; k < idx.length; k++) {
                    if(!relation[i][idx[k]].equals(relation[j][idx[k]])) {
                        isSame = false;
                        break;
                    }
                }
                if(isSame) {
                    return false;
                }
            }
        }
        return true;
    }

    // 결국 사용했던 원소 배제가 아니라 최소성 판정 함수가 있었어야 함..
    private boolean isMinimal(int[] currentIdx) {
        for(int[] key : candidateKeys) {
            int cnt = 0;
            for(int keyCol : key) {
                for(int currentCol : currentIdx) {
                    if(keyCol == currentCol) {
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt == key.length) {
                return false; // 최소성 위반
            }
        }
        return true;
    }

    // 조합 생성 및 유일성 체킹
    private void generateAndCheck(String[][] relation, int[] idx, int start, int depth, int size) {
        if(depth == size) {
            if(isMinimal(idx) && isUnique(idx, relation)) {
                candidateKeys.add(idx.clone());
            }
            return;
        }

        for(int i = start; i < relation[0].length; i++) {
            idx[depth] = i;
            generateAndCheck(relation, idx, i + 1, depth + 1, size);
        }
    }

    public int solution(String[][] relation) {
        candidateKeys.clear();

        for(int size = 1; size <= relation[0].length; size++) {
            int[] idx = new int[size];
            generateAndCheck(relation, idx, 0, 0, size);
        }

        return candidateKeys.size();
    }
}