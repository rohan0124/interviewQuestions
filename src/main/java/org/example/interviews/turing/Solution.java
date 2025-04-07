package org.example.turing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    int solve (String s){
        int n = s.length();
        char[] arr = s.toCharArray();
        int ans=0;
        for( int i =0;i<n;i++){
            for( int j=i+1;j<=n;j++){
                ans+=getCount(arr,i,j);
            }
        }
        return ans;

    }
    int getCount(char[] arr, int start , int end){
        int count = 0;
        Map<Character,Integer> map = new HashMap<>();
        Set<Character> notUnique = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(int i=start;i<end;i++){
            sb.append(arr[i]);
            if(map.containsKey(arr[i])){
                notUnique.add(arr[i]);
            }
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);

        }
        System.out.println(sb.toString());
        return map.keySet().size() - notUnique.size();
    }
}
