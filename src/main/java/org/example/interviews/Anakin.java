package org.example.interviews;

public class Anakin {
    public static void main(String[] args) {
        int[] arr ={5,8,6};
        int k = 4;
        System.out.println(" 1st Question");
        System.out.println("ans "+solution1(arr,k));

        System.out.println(" 2nd Question");
        int[] arr2 ={7,6,4,3,1};

        System.out.println("ans "+solution2(arr2));

    }

    static int solution2(int[] arr){
        int ans =0;
        for( int i =1;i<arr.length;i++){

            ans += Math.max(0, (arr[i]-arr[i-1]));
        }
        return ans;
    }


    static int solution1(int[] arr, int k){
        int n = arr.length;
        int l=1,r=1;
        int ans=0;
        for(int i=0;i<arr.length;i++){
            r=Math.max(r,arr[i]);
        }
        while(l<=r){
            int mid = (l+r)/2;
            if(isValid(arr,mid,k)){
                l=mid+1;
                ans=Math.max(ans,mid);
            }else{
                r=mid-1;
            }
        }

        return ans;

    }
    static boolean isValid(int[] arr, int val, int k){
        int count=0;
        for( int i :arr){
            count+=i/val;
            if(count>=k)return true;
        }
        return false;
    }
}
