package org.example.dsa;
import org.w3c.dom.Node;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SubstringWithConcatenation {

    int n;
    int m;
    Map<String, Integer> hashMap;

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        n = words.length;
        m = words[0].length();
        hashMap = new HashMap<>();
        for (String word : words) {
            Long hash = getPolyHash(word);
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        int[] id = new int[]{0};

        while(id[0]<s.length()){
            if (isValid(s, id)) {
                result.add(id[0]);
            }
        }
        return result;

    }

    Long getPolyHash(String str) {
        Long val = 0L;
        Long mod = (long) 1e9 + 7;
        Integer p = 31;
        for (char ch : str.toCharArray()) {
            val = (val * p + (ch - 'a')) % mod;
        }
        return val;

    }

    boolean isValid(String words, int[] idArr) {
        int id= idArr[0];
        if (id + n * m > words.length())
            return false;
        Map<String, Integer> temp = new HashMap<>(hashMap);
        int count = n;
        int index = id;
        while (count-- > 0) {
            // Long hash = getPolyHash(words.substring(index,index+m));
            String word = words.substring(index, index + m);
            if (temp.containsKey(word)) {
                temp.put(word, temp.getOrDefault(word, 1) - 1);
            } else {
                idArr[0]=index;
                return false;
            }
            if (temp.get(word) == 0) {
                temp.remove(word);
            }
            index += m;
        }
        if (temp.isEmpty()){
            idArr[0]++;
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        SubstringWithConcatenation object = new SubstringWithConcatenation();
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        List<Integer> result = object.findSubstring(s,words);
        System.out.println(result.toString());
        ConcurrentHashMap<Integer, Node> map;
    }
    public void incrementVisitCount(int pageIndex) {
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();

        map.putIfAbsent(pageIndex,map.getOrDefault(pageIndex,0)+1);

    }
}
