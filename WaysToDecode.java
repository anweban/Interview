package CodeChef;

import java.util.*;

public class WaysToDecode {
    public static int ways(String st){
        char[] str = st.toCharArray();
        if(str[0] == '0'){
            return 0;
        }
        int[][] numOfTranslations = new int[str.length][2];
        numOfTranslations[0][0] = 1;
        numOfTranslations[0][1] = 0;
        for(int i=1; i<str.length; i++){
            if(Integer.parseInt(Character.toString(str[i])) >= 7 && Integer.parseInt(Character.toString(str[i])) <=9) {
                if (Integer.parseInt(Character.toString(str[i - 1])) == 1) {
                    numOfTranslations[i][0] = numOfTranslations[i - 1][0] + numOfTranslations[i - 1][1];
                    numOfTranslations[i][1] = numOfTranslations[i - 1][0];
                    continue;
                }
            }
            if(Integer.parseInt(Character.toString(str[i])) >= 1 && Integer.parseInt(Character.toString(str[i])) <=6) {
                if (Integer.parseInt(Character.toString(str[i - 1])) == 1 || Integer.parseInt(Character.toString(str[i - 1])) == 2) {
                    numOfTranslations[i][0] = numOfTranslations[i - 1][0] + numOfTranslations[i - 1][1];
                    numOfTranslations[i][1] = numOfTranslations[i - 1][0];
                    continue;
                }
            }
            if(Integer.parseInt(Character.toString(str[i])) == 0){
                if(Integer.parseInt(Character.toString(str[i-1])) >= 1 && Integer.parseInt(Character.toString(str[i-1])) <=2) {
                    numOfTranslations[i][0] = 0;
                    numOfTranslations[i][1] = numOfTranslations[i - 1][0];
                    continue;
                }else{
                    return 0;
                }
            }
            numOfTranslations[i][0] = numOfTranslations[i-1][0]+numOfTranslations[i-1][1];
            numOfTranslations[i][1] = 0;
        }
        for(int i=0; i<str.length; i++){
            for(int j = 0; j<2; j++){
                System.out.print(numOfTranslations[i][j] + "      ");
            }
            System.out.println();
        }
        int total = numOfTranslations[str.length-1][0]+numOfTranslations[str.length-1][1];
        return total;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String st = sc.next();
        System.out.println(ways(st));
    }

}
