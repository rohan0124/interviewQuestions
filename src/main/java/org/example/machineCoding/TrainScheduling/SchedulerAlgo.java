package org.example.machineCoding.TrainScheduling;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface SchedulerAlgo {
    void schedule(int[][] trains, Map<Integer, TreeMap<Integer, Integer[]>> platformMap, List<Integer> platformList);
}
