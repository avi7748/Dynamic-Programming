package Knapsack.KnapsackUnbounded;

import java.util.Arrays;

public class F_CoinChange_II {

    static int minCoinsRecursive(int[] coins, int sum, int n) {
        if (sum == 0) return 0;
        if (n == 0) return Integer.MAX_VALUE;

        if (coins[n - 1] > sum)
            return minCoinsRecursive(coins, sum, n - 1);

        int include = minCoinsRecursive(coins, sum - coins[n - 1], n);
        int exclude = minCoinsRecursive(coins, sum, n - 1);

        if (include != Integer.MAX_VALUE)
            include = 1 + include;

        return Math.min(include, exclude);
    }

    static int minCoinsMemo(int[] coins, int sum, int n, int[][] dp) {
        if (sum == 0) return 0;
        if (n == 0) return Integer.MAX_VALUE;

        if (dp[n][sum] != -1) return dp[n][sum];

        if (coins[n - 1] > sum)
            dp[n][sum] = minCoinsMemo(coins, sum, n - 1, dp);
        else {
            int include = minCoinsMemo(coins, sum - coins[n - 1], n, dp);
            int exclude = minCoinsMemo(coins, sum, n - 1, dp);

            if (include != Integer.MAX_VALUE)
                include = 1 + include;

            dp[n][sum] = Math.min(include, exclude);
        }

        return dp[n][sum];
    }

    static int minCoinsTabulation(int[] coins, int sum) {
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];
        int INF = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) dp[i][0] = 0;
        for (int j = 1; j <= sum; j++) dp[0][j] = INF;

        for (int j = 1; j <= sum; j++) {
            if (j % coins[0] == 0)
                dp[1][j] = j / coins[0];
            else
                dp[1][j] = INF;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (coins[i - 1] <= j) {
                    int include = dp[i][j - coins[i - 1]];
                    if (include != INF) include = 1 + include;
                    dp[i][j] = Math.min(include, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return (dp[n][sum] == INF) ? -1 : dp[n][sum];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int sum = 11;
        int n = coins.length;

        int ans1 = minCoinsRecursive(coins, sum, n);
        System.out.println("Recursive: " + (ans1 == Integer.MAX_VALUE ? -1 : ans1));

        int[][] dp = new int[n + 1][sum + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans2 = minCoinsMemo(coins, sum, n, dp);
        System.out.println("Memoization: " + (ans2 == Integer.MAX_VALUE ? -1 : ans2));

        int ans3 = minCoinsTabulation(coins, sum);
        System.out.println("Tabulation: " + ans3);
    }


}