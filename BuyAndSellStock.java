package CodeChef;

import java.util.*;

/*

Say you have an array, A, for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit.

You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/
public class BuyAndSellStock {
    public static int maxProfit(final List<Integer> A){
        int maxProfit = 0;
        for(int i=1; i < A.size(); i++){
            if(A.get(i)>A.get(i-1)){
                maxProfit += A.get(i)-A.get(i-1);
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
