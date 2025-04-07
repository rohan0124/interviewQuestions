package org.example.dsa;
import java.util.*;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for( char ch:tasks){
            count[ch-'A']++;
        }
        List<int[]> list = new ArrayList<>();
        for( int i =0;i<26;i++){
            if(count[i]>0)
                list.add(new int[]{i,count[i],0});
        }
        Queue<int[]> cq = new PriorityQueue<>( (a,b)->b[1]-a[1]);
        cq.addAll(list);
        Queue<int[]> wq = new PriorityQueue<>((a,b)->a[2]-b[2]);
        int day=0;

        while (!cq.isEmpty() && !wq.isEmpty()){
            while(!wq.isEmpty() && wq.peek()[2]<=day){
                cq.offer(wq.poll());
            }
            int[] curr;
            if(cq.isEmpty() && !wq.isEmpty()){
                curr =wq.poll();
                day=curr[2];
            }else{
                curr = cq.poll();
            }
            curr[1]--;
            day++;
            curr[2] =day+n;
            if(curr[1]>0){
                wq.add(curr);
            }
        }
        return day;

    }
}
