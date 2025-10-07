package Knapsack.KnapsackUnbounded;

import java.util.Arrays;
public class E_CoinChange_I {


    public static int countMemo(int coins[], int sum) {
        int n = coins.length;
        int dp[][] = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
        return countWays(coins, sum, n, dp);
    }

    private static int countWays(int[] coins, int sum, int n, int[][] dp) {
        if (n == 0) return (sum == 0) ? 1 : 0;
        if (dp[n][sum] != -1) return dp[n][sum];

        if (coins[n - 1] <= sum) {
            int inc = countWays(coins, sum - coins[n - 1], n, dp);
            int ex = countWays(coins, sum, n - 1, dp);
            return dp[n][sum] = inc + ex;
        } else {
            return dp[n][sum] = countWays(coins, sum, n - 1, dp);
        }
    }

    public static int countTab(int coins[], int sum) {
        int n = coins.length;
        int dp[][] = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (coins[i - 1] <= j) {
                    int inc = dp[i][j - coins[i - 1]];
                    int ex = dp[i - 1][j];
                    dp[i][j] = inc + ex;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 4;

        System.out.println("Using Memoization: " + countMemo(coins, sum));
        System.out.println("Using Tabulation : " + countTab(coins, sum));
    }

}
