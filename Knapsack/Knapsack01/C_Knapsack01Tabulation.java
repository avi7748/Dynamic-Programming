package Knapsack.Knapsack01;

import java.util.Arrays;

public class C_Knapsack01Tabulation {
    public static int KnapSack(int[] wt, int[] val, int W, int n){

        int[][] dp = new int[n+1][W+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        if(dp[n][W] != -1) return dp[n][W];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
                else if(wt[i-1] <= j){
                    dp[i][j] = Math.max(
                            val[i-1] + dp[i-1][j-wt[i-1]],
                            dp[i-1][j]
                    );
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];

    }
    public static void main(String[] args) {
        int[] wt = {50, 80, 70, 60};
        int[] val = {2, 6, 3, 4};
        int n = 4;
        int W = 150;



        int val1 = KnapSack(wt,val,W,n);
        System.out.println("Using tabulation : " + val1);
    }
}
