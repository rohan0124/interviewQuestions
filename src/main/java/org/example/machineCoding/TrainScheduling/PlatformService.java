package org.example.machineCoding.TrainScheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlatformService {
    // numberOf
    List<Integer> platformList;
    int numberOfPlatforms;
    int numberOfTrains;
    SchedulerAlgo schedulerAlgo;
    Map<Integer, TreeMap<Integer, Integer[]>> platformMap;

    PlatformService(int numberOfPlatforms, int[][] trains) {
        this.numberOfPlatforms = numberOfPlatforms;
        this.numberOfTrains = trains.length;
        this.schedulerAlgo = new FirstComeFirstServePlatformAlgorithm();
        this.platformMap = new HashMap<>();
        this.platformList = IntStream.range(1, numberOfPlatforms + 1).boxed().collect(Collectors.toList());
        // using default naming  1 to n;
        this.init(trains);
    }

    void init(int[][] trains) {
        this.schedulerAlgo.schedule(trains, platformMap, platformList);
    }


    String getTrainOnPlatform(int platformId, int time) {
        if (!platformMap.containsKey(platformId)) {
            throw new InvalidPlatformException("Invalid platform Id");
        }
        Map.Entry<Integer, Integer[]> entry = platformMap.get(platformId).floorEntry(time);
        if (entry == null || entry.getValue()[2] < time) {
            System.out.println("Platform " + platformId + " is free at the given time");
            return "";
        }
        return "Train" + entry.getValue()[0];

    }

    void printPlatformMap() {
        for (Map.Entry<Integer, TreeMap<Integer, Integer[]>> entry : platformMap.entrySet()) {
            System.out.println("Platform " + entry.getKey());
            entry.getValue().forEach((startTime, train) ->
                    System.out.println(" Train: " + train[0] + " Start : " + train[1] + " End " + train[2]));

        }
    }

}
