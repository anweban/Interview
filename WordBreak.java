package LeetCode;
import java.util.*;

public class WordBreak {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Set<String> allPrefixes = new HashSet<>();
        for(String word:wordDictSet){
            StringBuilder strbuilder = new StringBuilder("");
            for(int i = 0; i < word.length(); i++){
                strbuilder.append(word.charAt(i));
                allPrefixes.add(strbuilder.toString());
            }
        }
        List<List<String>> sentences = new ArrayList<List<String>>();
        List<String> emptyStrList = new ArrayList<>();
        emptyStrList.add("");
        sentences.add(emptyStrList);
        for(int i = 0; i < s.length(); i++){
            List<List<String>> tempSentences = new ArrayList<List<String>>();
            //System.out.println(Character.toString(s.charAt(i)));
            for(int j=0; j<sentences.size(); j++){
                List<String> sentence = sentences.get(j);
                String lastWord = sentence.get(sentence.size()-1);
                if(lastWord.length() == 0){
                    sentence.set(sentence.size()-1,Character.toString(s.charAt(i)));
                }else{
                    if(lastWord.charAt(lastWord.length()-1) == ' '){
                        String str = Character.toString(s.charAt(i));
                        sentence.add(str);
                    }else{
                        sentence.set(sentence.size()-1,lastWord+Character.toString(s.charAt(i)));
                    }
                }
                lastWord = sentence.get(sentence.size()-1);
                if(wordDictSet.contains(lastWord)){
                    if(i < s.length() - 1 && allPrefixes.contains(lastWord+Character.toString(s.charAt(i+1)))){
                        List<String> sentenceCopy = new ArrayList<>();
                        for(String word:sentence){
                            sentenceCopy.add(word);
                        }
                        tempSentences.add(sentenceCopy);
                    }
                    sentence.set(sentence.size()-1,lastWord+" ");
                }
            }
            sentences.addAll(tempSentences);
            //System.out.println(sentences.toString());

        }
        List<String> res = new ArrayList<>();
        for(int i=0; i<sentences.size(); i++){
            StringBuilder stb = new StringBuilder("");
            List<String> sentence = sentences.get(i);
            String lastWord = sentence.get(sentence.size()-1);
            if(lastWord.charAt(lastWord.length()-1) == ' '){
                for(int j=0; j<sentence.size(); j++){
                    stb.append(sentence.get(j));
                }
                res.add(stb.toString().trim());
            }
        }
        return res;
    }

    //better solution
    public static List<String> wordBreak2(String s, List<String> dict) {
        //create an array of ArrayList<String>
        List<String> dp[] = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<String>();

        for(int i=0; i<s.length(); i++){
            if( dp[i] == null )
                continue;

            for(String word:dict){
                int len = word.length();
                int end = i+len;
                if(end > s.length())
                    continue;

                if(s.substring(i,end).equals(word)){
                    if(dp[end] == null){
                        dp[end] = new ArrayList<String>();
                    }
                    dp[end].add(word);
                }
            }
        }

        List<String> result = new LinkedList<String>();
        if(dp[s.length()] == null)
            return result;

        ArrayList<String> temp = new ArrayList<String>();
        dfs(dp, s.length(), result, temp);

        return result;
    }

    public static void dfs(List<String> dp[],int end,List<String> result, ArrayList<String> tmp){
        if(end <= 0){
            String path = tmp.get(tmp.size()-1);
            for(int i=tmp.size()-2; i>=0; i--){
                path += " " + tmp.get(i) ;
            }

            result.add(path);
            return;
        }

        for(String str : dp[end]){
            tmp.add(str);
            dfs(dp, end-str.length(), result, tmp);
            tmp.remove(tmp.size()-1);
        }
    }
}
