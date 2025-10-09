class Solution {
    private boolean isvalid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c=='.';
    }
    public String solution(String new_id) {
        //빈문자열 넣었을 때 에러남(런타임 에러..)



        //1단계
        char[] arr = new_id.toLowerCase().toCharArray();

        int len_2 = 0;
        //2단계
        for(int i =0; i<arr.length; i++){
            char tmp = arr[i];
            if(isvalid(tmp)){
                arr[len_2++] = tmp;
            }
        }

        //3단계
        int len_3 = 0;
        for(int i =0; i<len_2; i++) {
            char tmp = arr[i];
            //2개 이상의 '.' + 유효한 인덱스일때
            if (tmp == '.'  && i + 1 < len_2 && arr[i + 1] == '.'){
                while (i + 1 < len_2 && arr[i + 1] == '.') {
                    i++;
                }
            }
            arr[len_3] = arr[i];
            len_3++;
        }


        //4단계
        if(len_3> 0 && arr[0] == '.'){
            for(int i = 0; i<len_3-1; i++) {
                arr[i] = arr[i+1];
            }
            len_3--;
        }

        if(len_3> 0 && arr[len_3-1] == '.'){
            len_3--;
        }

        //런타임에러 발생지점..
        //초기부터 3개보다 작은 배열이 오면 연산과정에서 Index 밖을 참조하게됨.
        if(arr.length < 3) {
            char[] newArr = new char[Math.max(new_id.length(), 3)];
            for(int i = 0; i < len_3; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }

        //5단계
        if(len_3 == 0){
            arr[0] = 'a';
            len_3 = 1;
        }

        //6단계
        if(len_3 >= 16){
            len_3 = 15;
            if(arr[len_3-1] == '.'){
                len_3--;
            }
        }

        //7단계
        while(len_3<=2) {
            arr[len_3] = arr[len_3-1];
            len_3++;
        }

        String answer = new String(arr, 0, len_3);
        return answer;
    }
}