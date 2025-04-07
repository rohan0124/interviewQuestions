package org.example.saviynt;
import java.util.TreeMap;
public class CodingRound {
/*
find the minimum of every window of size 3 in an array. Among those minimum, later find the maximum value
Example: [10, 20, 30, 50, 10, 70, 30]
 consider all windows: [10, 20, 30], [20, 30, 50], [30, 50, 10], [50, 10, 70], [10, 70, 30]. The minimum values are 10, 20, 10, 10, 10 — the maximum of these is 20.
* */
    public static void main(String[] args) {
        // [ 10,10,20,50,40]
        // { 10:1 ,20:2  }

        CodingRound codingRound = new CodingRound();
        int[] arr = {10, 20, 30, 5, 10, 70, 30};
        int windowSize= 3;
        int result = codingRound.solve(arr, windowSize);
        System.out.println(Integer.lowestOneBit(19));
        System.out.println("ans: "+ result);

    }
    int solve(int[] arr, int k){
        //max of min of windows of size k
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int start=0,end=0,length=arr.length;
        int ans =Integer.MIN_VALUE;
        int curr=0;
        for( end=0 ; end<length;end++){

            map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);
            while(end-start+1>k){
                map.put(arr[start], map.getOrDefault(arr[start], 0) - 1);
                if(map.get(arr[start]) == 0){
                    map.remove(arr[start]);
                }
                start++;
            }
            if(end-start+1==k && !map.isEmpty()){
                curr=map.firstKey();
                ans=Math.max(ans,curr);
            }
        }
        return ans; // incase of windosize <=0 or >= arr.length ; default value Integer.MIN_VALUE
    }
}
