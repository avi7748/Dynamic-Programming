package Knapsack.Knapsack01;

import java.util.Arrays;

public class B_Knapsack01Memoization {
    public static int KnapSack(int[] wt, int[] val, int W, int n, int[][] dp){
        if(W == 0 || n == 0){
            return 0;
        }
        if(dp[n][W] != -1) return dp[n][W];

        if(wt[n-1] <= W){
            return dp[n][W] = Math.max(
                    val[n-1] + KnapSack(wt, val,W - wt[n-1],n-1,dp),
                    KnapSack(wt, val, W,n-1,dp)
            );
        } else{
            return dp[n][W] = KnapSack(wt,val,W,n-1,dp);
        }

    }
    public static void main(String[] args) {
        int[] wt = {50, 80, 70, 60};
        int[] val = {2, 6, 3, 4};
        int n = 4;
        int W = 150;

        int[][] dp = new int[n+1][W+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int val1 = KnapSack(wt,val,W,n,dp);
        System.out.println("Using dp : "+val1);
    }
}
