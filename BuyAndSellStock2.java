package CodeChef;

import java.util.*;

public class BuyAndSellStock2 {
    public static int maxProfit(final List<Integer> A){
        if(A.size() == 0){
            return 0;
        }
        int maxProfit = 0;
        int min = A.get(0);
        int[] left = new int[A.size()];
        left[0] = 0;
        for(int i=1; i<A.size(); i++){
            min = Math.min(min,A.get(i));
            left[i] = Math.max(left[i-1], A.get(i)- min);
        }

        int max = A.get(A.size()-1);
        int[] right = new int[A.size()];
        right[A.size()-1] = 0;
        max = A.get(A.size()-1);
        for(int i=A.size()-2; i>=0; i--){
            max = Math.max(max,A.get(i));
            right[i] = Math.max(right[i+1], max - A.get(i));
        }

        for(int i = 0; i<A.size(); i++){
            int profit = left[i]+right[i];
            if(profit > maxProfit){
                maxProfit = profit;
            }
        }

        return maxProfit;

    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        System.out.println(maxProfit(arr));
    }
}
