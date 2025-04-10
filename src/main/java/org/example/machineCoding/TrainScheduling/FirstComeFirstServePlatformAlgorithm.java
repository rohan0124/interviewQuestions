package org.example.machineCoding.TrainScheduling;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class FirstComeFirstServePlatformAlgorithm implements SchedulerAlgo {
    public FirstComeFirstServePlatformAlgorithm() {
    }

    @Override
    public void schedule(int[][] trains, Map<Integer, TreeMap<Integer, Integer[]>> platformMap, List<Integer> platformList) {
        Arrays.sort(trains, (a, b) -> a[0] - b[0]);
        Queue<Integer> freePlatforms = new PriorityQueue<>();
        Queue<Integer[]> assignedPlatforms = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        freePlatforms.addAll(platformList);
        int id = 1;
        for (int[] stop : trains) {
            while (!assignedPlatforms.isEmpty() && assignedPlatforms.peek()[1] < stop[0]) {
                freePlatforms.offer(assignedPlatforms.poll()[0]);
            }
            Integer selectedPlatForm = null;
            Integer entryTime = null;
            if (freePlatforms.isEmpty()) {
                selectedPlatForm = assignedPlatforms.peek()[0];
                entryTime = assignedPlatforms.peek()[1]; //

            } else {
                selectedPlatForm = freePlatforms.poll();
                entryTime = stop[0];
            }
            assignedPlatforms.offer(new Integer[]{selectedPlatForm, entryTime + stop[1]});
            // possible to add min safety gap but that can change requirement so discuss
            platformMap.putIfAbsent(selectedPlatForm, new TreeMap<Integer, Integer[]>());
            platformMap.get(selectedPlatForm).put(entryTime, new Integer[]{id++, entryTime, entryTime + stop[1]});
        }

    }
}
