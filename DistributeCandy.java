package CodeChef;
import java.util.*;

public class DistributeCandy {

    // solution with time complexity O(n) and space complexity O(n)
    public static int candy2(ArrayList<Integer> A) {
        int[] C = new int[A.size()];
        C[0] = 1;
        for(int i=1; i<A.size(); i++){
            if(A.get(i) > A.get(i-1)){
                C[i] = C[i-1] + 1;
            }else{
                C[i] = 1;
            }
        }

        /*for(int i=0; i<C.size(); i++){
            System.out.print(C[i]+" ");
        }
        System.out.println();*/

        int sum = C[A.size() - 1];
        for(int i = A.size() - 2; i >=0; i--){
            if(A.get(i) > A.get(i+1)){
                C[i] = Math.max(C[i],C[i+1]+1);
            }
            sum += C[i];
        }

        /*for(int i=0; i<C.size(); i++){
            System.out.print(C[i]+" ");
        }
        System.out.println();*/

        return sum;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i < n; i++){
            int num = sc.nextInt();
            arr.add(num);
            //System.out.println(i+1+"---->"+num);
        }
        System.out.println(candy2(arr));
    }
}
