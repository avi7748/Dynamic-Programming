package Knapsack.Knapsack01;

import java.util.Arrays;

public class CountSubsetSum {


    public static int countSubsetRec(int[] arr,int sum, int n, int[][] dp){
        if (n == 0) {
            return (sum == 0) ? 1 : 0;
        }


        if(dp[n][sum] != -1){
            return dp[n][sum];
        }

        if(arr[n - 1] <= sum){
            return dp[n][sum] = countSubsetRec(arr, sum - arr[n - 1], n - 1, dp) +
                    countSubsetRec(arr, sum, n - 1, dp);
        }
        else{
            return dp[n][sum] = countSubsetRec(arr, sum, n - 1, dp);
        }
    }
    public static int countSubsetTab(int[] arr,int sum, int n){
        int[][] dp = new int[n+1][sum+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if(arr[i-1] <= j) {
                    dp[i][j] =  dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {


        int[] arr = {2, 3, 5, 6, 8, 10};
        int n = arr.length;
        int S = 10;

//      Iterative : Bottom-up approach :

        System.out.println("count "+countSubsetTab(arr, S, 6));

//      Recursive : Top-Down approach :

        int[][] dp = new int[n+1][S+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println("count " + countSubsetRec(arr,S,n,dp));

    }
}
