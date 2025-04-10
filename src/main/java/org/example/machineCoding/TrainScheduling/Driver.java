package org.example.machineCoding.TrainScheduling;


// medium link :- 
// https://medium.com/@laxmankumarmegwal/uber-interview-experience-l4-sde2-backend-december-2024-da0f059c230e
/*
Second On site round :- machine coding + LLD
it was machine coding round where a problem will be given and candidate is expected to write a working code adhering to design patterns, OOPS concepts and SOLID principles.
do not remember exact question but it was
there are n platforms and m trains and now we have to schedule these trains on platforms
so below functionalities need to be implemented
we are given a time at which trains is arriving and duration for which it will halt on station so we are suppose to return at which platform it can be scheduled and return the platform number and time at which it will be scheduled.
2. how waiting will be handled, if all platform already has train scheduled on it, how we will schedule next train.
3. if platform number is provided with time mentioned, get the train scheduled on that platform.
 */
public class Driver {
    public static void main(String[] args){
        int n = 3 ;
        int[][] trains = {{1,2},{2, 4},{3, 26},{4,12},{30,12}};
        PlatformService platformService = new PlatformService(n,trains);
        String train= platformService.getTrainOnPlatform(1,4);
        platformService.printPlatformMap();
        System.out.println(train);
    }

}
