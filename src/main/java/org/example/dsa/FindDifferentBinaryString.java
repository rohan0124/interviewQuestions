package org.example.dsa;

import java.util.*;

class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>(List.of(nums));
        int max =(int) Math.pow(2,n) - 1;
        for( int i=max;i>=0;i--){
            String binary = getBinaryString(i,n);
            if(!set.contains(binary)){
                return binary;
            }
        }
        return "";

    }
    String getBinaryString(int x, int n){
        String binary = Integer.toBinaryString(x);
        while(binary.length()<n){
            binary = "0"+binary;
        }
        return binary;

    }
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        runTestCases();
    }
    public static void runTestCases(){
        Solution solution = new Solution();
        String[] input = new String[] {"1111","0101","0010","1100"};
        System.out.println(solution.findDifferentBinaryString(input));

    }
}