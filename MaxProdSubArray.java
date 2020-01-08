package CodeChef;

import java.util.*;

public class MaxProdSubArray {
    public static int findMaxProd(List<Integer> A){
        int max = A.get(0);
        int positiveSoFar = 1;
        int negativeSoFar = 1;
        Boolean flag = false; //flag will be set to true if there is at least one positive value or two consecutive negative values
        for(int i=0;i<A.size();i++){
            if(A.get(i) > 0){
                negativeSoFar = Math.min(1,negativeSoFar*A.get(i));
                positiveSoFar = positiveSoFar*A.get(i);
                flag = true;
            }else if(A.get(i) < 0){
                int temp = negativeSoFar;
                negativeSoFar =  positiveSoFar*A.get(i);
                positiveSoFar = Math.max(1,temp*A.get(i));
                if(i > 0 && A.get(i-1)<0){
                    flag = true;
                }
            }else{
                negativeSoFar = 1;
                positiveSoFar = 1;
            }
            if(positiveSoFar > max) {
                max = positiveSoFar;
            }
        }
        if(flag){
            return max;
        }else{
            if(A.size() == 1){
                return A.get(0);
            }else{
                return 0;
            }
        }
    }

    //shorter
    public static int findMaxProdShorter(List<Integer> A){
        int max = A.get(0);
        int positiveSoFar = 1;
        int negativeSoFar = 1;
        Boolean flag = false;
        for(int i=0;i<A.size();i++){
            if(A.get(i) == 0){
                positiveSoFar = 1;
                negativeSoFar = 1;
                continue;
            }
            if(A.get(i) < 0){
                int temp = positiveSoFar;
                positiveSoFar = negativeSoFar;
                negativeSoFar = temp;
            }
            if(positiveSoFar*A.get(i) >= positiveSoFar){
                flag = true;
            }
            positiveSoFar = Math.max(positiveSoFar,positiveSoFar*A.get(i));
            negativeSoFar = Math.min(negativeSoFar, negativeSoFar*A.get(i));
            max = (positiveSoFar > max) ? positiveSoFar : max;
        }
        if(flag){
            return max;
        }else{
            if(A.size() == 1){
                return A.get(0);
            }else{
                return 0;
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        System.out.println(findMaxProd(arr));
    }
}
