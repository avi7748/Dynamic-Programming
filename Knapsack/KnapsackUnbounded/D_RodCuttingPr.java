package Knapsack.KnapsackUnbounded;

import java.util.Arrays;

public class D_RodCuttingPr {

    public static int rodCutRecursive(int[] price, int N, int i) {
        if (i == 0 || N == 0) return 0;
        if (i <= N) {
            return Math.max(
                    price[i - 1] + rodCutRecursive(price, N - i, i),
                    rodCutRecursive(price, N, i - 1)
            );
        } else {
            return rodCutRecursive(price, N, i - 1);
        }
    }

    public static int rodCutMemo(int[] price, int N, int i, int[][] dp) {
        if (i == 0 || N == 0) return 0;
        if (dp[i][N] != -1) return dp[i][N];
        if (i <= N) {
            return dp[i][N] = Math.max(
                    price[i - 1] + rodCutMemo(price, N - i, i, dp),
                    rodCutMemo(price, N, i - 1, dp)
            );
        } else {
            return dp[i][N] = rodCutMemo(price, N, i - 1, dp);
        }
    }

    public static int rodCutTabulation(int[] price, int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i <= j) {
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - i], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        int n = price.length;

        int maxRecursive = rodCutRecursive(price, n, n);
        System.out.println("Recursive: " + maxRecursive);

        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int maxMemo = rodCutMemo(price, n, n, dp);
        System.out.println("Memoization: " + maxMemo);

        int maxTab = rodCutTabulation(price, n);
        System.out.println("Tabulation: " + maxTab);
    }
}
