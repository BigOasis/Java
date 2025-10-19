// 과거 풀이법
class Solution {
    public static int convert_alpha(String s) {
        switch (s) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return 10;
        }
    }

    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb_ans = new StringBuilder();
        for(int i = 0; i< s.length(); i++){
            char tmp = s.charAt(i);
            if(tmp>=48 && tmp<=57) sb_ans.append(tmp);
            else {
                sb.append(tmp);
                if(convert_alpha(sb.toString()) != 10){
                    sb_ans.append(convert_alpha(sb.toString()));
                    sb = new StringBuilder();
                }
            }
//           System.out.println("sb_ans: "+sb_ans.toString());
        }
        int answer = Integer.parseInt(sb_ans.toString());
        return answer;
    }
}

// HashMap을 적용한 해법

class Solution {
    public int solution(String s) {
        java.util.Map<String, String> map = new java.util.HashMap<>();
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            //isDigit 쓰면 알아먹기쉬움 <- 피드백사항
            if (Character.isDigit(ch)) {
                result.append(ch);
            } else {
                word.append(ch);

                if (map.containsKey(word.toString())) {
                    result.append(map.get(word.toString()));
                    word = new StringBuilder();
                }
            }
        }

        return Integer.parseInt(result.toString());
    }
}