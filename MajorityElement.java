package CodeChef;

import java.util.*;

public class MajorityElement {
    public static int majorityElement(final List<Integer> A) {
        int m = 0;
        int i = 0;
        for(int num : A){
            if(i == 0){
                i = 1;
                m = num;
            }else{
                if(num == m){
                    i++;
                }else{
                    i--;
                }
            }
        }
        return m;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for(int i=0; i < n; i++){
            arr.add(sc.nextInt());
        }
        System.out.println(majorityElement(arr));
    }
}
