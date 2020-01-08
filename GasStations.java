package CodeChef;
/*
If Summation(A[i]) > Summation(B[i])
 => Summation(A[i]) - Summation(B[i] > 0
 => Summation(A[i] - B[i]) > 0
 */
import java.util.*;
public class GasStations {
    // my method (T(n))
    public static int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
        List<Integer> C = new ArrayList<>();
        HashMap<Integer,Integer> cTostationMap = new HashMap<Integer,Integer>();
        C.add(0);
        int cNum = 0;
        int resultant = 0;
        for (int i = 0; i < B.size(); i++) {
            if (A.get(i) - B.get(i) < 0) {
                if(C.get(cNum) <= 0){
                    int sum = C.get(cNum) + (A.get(i) - B.get(i));
                    C.set(cNum,sum);
                }else{
                    C.add(A.get(i) - B.get(i));
                    cNum++;
                }
            }else if(A.get(i) - B.get(i) > 0){
                if(C.get(cNum) >= 0) {
                    int sum = C.get(cNum) + (A.get(i) - B.get(i));
                    C.set(cNum, sum);
                    if(i == 0){
                        cTostationMap.put(cNum,i);
                    }
                }else{
                    cNum++;
                    C.add(A.get(i) - B.get(i));
                    cTostationMap.put(cNum,i);
                }
            }else{
                int sum = C.get(cNum) + (A.get(i) - B.get(i));
                C.set(cNum, sum);
            }
            resultant = resultant + (A.get(i) - B.get(i));
        }
        System.out.println(C);
        System.out.println(cTostationMap);
        if(resultant < 0){
            return -1;
        }
        int j = 0;
        int k = C.size()-1;
        if(j == k){
            if(C.get(j) >= 0){
                return j;
            }else{
                return -1;
            }
        }
        if(C.get(0)<0){
            j = 1;
            k = 0;
        }
        int sumSoFar = C.get(j);
        int startPoint = j;
        while(j != k){
            System.out.println("j--->"+j+" k---->"+k);
            System.out.println("C[j]--->"+C.get(j)+" C[k]---->"+C.get(k));
            System.out.println("sumSoFar---->"+sumSoFar);
            if(sumSoFar >= 0) {
                j = (j+1)%(C.size());
            }else{
                int temp = j;
                j = (j+1)%(C.size());
                k = temp;
                sumSoFar = 0;
            }
            sumSoFar += C.get(j);

        }
        System.out.println("j--->"+j+" k---->"+k);
        System.out.println("C[j]--->"+C.get(j)+" C[k]---->"+C.get(k));
        System.out.println("sumSoFar---->"+sumSoFar);

        k = (k+1)%(C.size());

        System.out.println(k);
        return cTostationMap.get(k);

    }

    //Sliding Window
    public static int canCompleteCircuitSW(final List<Integer> A, final List<Integer> B) {
        Boolean endChargingFromBack = false;
        int start = 0, end = 0;
        int n = A.size();
        int total = 0;
        int current_gas = 0;
        while(start != end || !endChargingFromBack){
            total += A.get(end)-B.get(end);
            current_gas += A.get(end)-B.get(end);
            if(current_gas >= 0){
                end = (end+1)%n;
                if(end == 0){
                    if(total < 0){
                        return -1;
                    }
                    endChargingFromBack = true;
                }
            }else{
                start = (end+1)%n;
                end = (end+1)%n;
                if(end == 0 && total < 0) {
                    return -1;
                }
                current_gas = 0;
            }
        }
        return start;
    }

    //sliding window approach
    public static int gasStations(List<Integer> a, List<Integer> b){
        int n = a.size();
        int start = 0;
        int end = 0;
        int total = 0;
        int curr_gas = 0;
        while(end < n){
            curr_gas += a.get(end) - b.get(end);
            total += a.get(end) - b.get(end);
            if(curr_gas >= 0){
                end++;
            }else{
                start = end+1;
                end++;
                curr_gas = 0;
            }
        }
        if(total < 0){
            return -1;
        }else {
            return start;
        }
    }

    //even shorter
    public static int gasStationsSW(List<Integer> a, List<Integer> b){
        int n = a.size();
        int start = 0;
        int end = 0;
        int total = 0;
        int curr_gas = 0;
        while(end < n){
            curr_gas += a.get(end) - b.get(end);
            total += a.get(end) - b.get(end);
            if(curr_gas < 0){
                start = end+1;
                curr_gas = 0;
            }
            end++;
        }
        if(total < 0){
            return -1;
        }else {
            return start;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        for(int i=0; i<n; i++){
            A.add(sc.nextInt());
        }
        for(int i=0; i<n; i++){
            B.add(sc.nextInt());
        }
        System.out.println(gasStations(A,B));
    }
}
