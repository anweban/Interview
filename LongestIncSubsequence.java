package CodeChef;

import java.util.*;

public class LongestIncSubsequence {
    //using dp(n^2 time)
    public static int lis(List<Integer> arr){
        int n = arr.size();
        int[] lisArray = new int[n];
        int max = 1;
        Arrays.fill(lisArray,1);
        int i = 0;
        for(int j=1; j<n; j++){
            for(; i<j; i++){
                if(arr.get(j) > arr.get(i)){
                    int lis = lisArray[j]+1;
                    if(lis > lisArray[j]){
                        lisArray[j] = lis;
                    }
                }
            }
            i = 0;
            if(lisArray[j] > max){
                max = lisArray[j];
            }
        }
        return max;
    }

    //nlogn time
    public static int lis2(List<Integer> arr){
        List<ArrayList<Integer>> activeLists = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i < arr.size(); i++){
            //System.out.println("i--->"+i);
            //System.out.println("before--->"+activeLists);
            int pos = binSearch(activeLists, arr.get(i));
            //System.out.println("pos--->"+pos);
            ArrayList<Integer> tempList = new ArrayList<>();
            if(pos == -1){
                tempList.add(arr.get(i));
                if(activeLists.size() == 0){
                    activeLists.add(tempList);
                }else{
                    activeLists.set(0,tempList);
                }
            }else{
                tempList.addAll(activeLists.get(pos));
                tempList.add(arr.get(i));
                if(pos+1 == activeLists.size()){
                    activeLists.add(tempList);
                }else{
                    activeLists.set(pos+1,tempList);
                }
            }
            //System.out.println("after--->"+activeLists);
        }
        return activeLists.get(activeLists.size()-1).size();
    }

    public static int binSearch(List<ArrayList<Integer>> activeLists, int num){
        if(activeLists.isEmpty()){
            return -1;
        }
        int first = 0;
        int last = activeLists.size()-1;
        while(first <= last){
            int m = (first+last)/2;
            if(activeLists.get(m).get(activeLists.get(m).size()-1) >= num){
                if(m == 0){
                    return -1;
                }else if(activeLists.get(m-1).get(activeLists.get(m-1).size()-1) < num){
                    return m-1;
                }else{
                    last = m-1;
                }
            }else if(activeLists.get(m).get(activeLists.get(m).size()-1) < num){
                if(m == activeLists.size() - 1 || activeLists.get(m+1).get(activeLists.get(m+1).size()-1) >= num){
                    return m;
                }else{
                    first = m+1;
                }
            }
        }
        return -2;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        System.out.println("--->"+lis2(arr));

    }
}
