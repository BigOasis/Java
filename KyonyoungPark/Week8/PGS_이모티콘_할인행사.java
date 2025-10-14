class Solution {
    //1순위 : 가입자 많게 , 2순위 : 판매액 많게
    private int[] discounts = {10, 20, 30, 40};  // 가능한 할인율
    private int maxSubscribers = 0;  // 최대 가입자 수
    private int maxSales = 0;        // 최대 판매액

    // 현재 할인율 조합에 대한 가입자 수와 판매액 계산하기(1,2 순위 계산해야되서 필요함)
    private void calculate(int[] selectedDiscounts, int[][] users, int[] emoticons) {
        int subscribers = 0;  // 이모티콘 플러스 가입자 수
        int sales = 0;        // 이모티콘 판매액

        // 각 사용자별로 계산
        for (int[] user : users) {
            int userDiscountRate = user[0];  // 할인율 커트라인
            int userPriceLimit = user[1];    // 구매한도 커트라인

            int totalCost = 0;  // 이 사용자가 구매할 이모티콘 총 비용

            // 각 이모티콘 확인
            for (int i = 0; i < emoticons.length; i++) {
                int discount = selectedDiscounts[i];

                // 사용자의 기준 이상 할인되면 구매
                if (discount >= userDiscountRate) {
                    int price = emoticons[i] * (100 - discount) / 100;
                    totalCost += price;
                }
            }

            // 구매 한도를 넘으면 플러스 서비스 가입
            if (totalCost >= userPriceLimit) {
                subscribers++;
            } else {
                sales += totalCost;
            }
        }

        // 최적해 갱신 (가입자 수 우선, 그 다음 판매액)
        if (subscribers > maxSubscribers) {
            maxSubscribers = subscribers;
            maxSales = sales;
        } else if (subscribers == maxSubscribers) {
            if (sales > maxSales) {
                maxSales = sales;
            }
        }
    }


    // 모든 할인율 조합 시전
    private void dfs(int idx, int[] selectedDiscounts, int[][] users, int[] emoticons) {
        // 모든 이모티콘의 할인율이 결정되었으면
        if (idx == emoticons.length) {
            calculate(selectedDiscounts, users, emoticons);
            return;
        }

        // 현재 이모티콘에 대해 10-20-30-40 다 시도해보기
        for (int discount : discounts) {
            selectedDiscounts[idx] = discount;
            dfs(idx + 1, selectedDiscounts, users, emoticons);
        }
    }



    public int[] solution(int[][] users, int[] emoticons) {
        int m = emoticons.length;
        int[] selectedDiscounts = new int[m];  // 각 이모티콘의 할인율

        // 모든 할인율 조합 탐색
        dfs(0, selectedDiscounts, users, emoticons);

        return new int[]{maxSubscribers, maxSales};
    }
}