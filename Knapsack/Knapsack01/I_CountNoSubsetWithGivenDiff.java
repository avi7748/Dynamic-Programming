package Knapsack.Knapsack01;

import java.util.Arrays;

public class I_CountNoSubsetWithGivenDiff {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int d = 1;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Difference: " + d);
        System.out.println("-----------------------------------");
        System.out.println("1. Result (Memoization): " + countPartitionsMemoization(arr, d));
        System.out.println("2. Result (Tabulation): " + countPartitionsTabulation(arr, d));
        System.out.println("3. Result (Space Optimized): " + countPartitionsOptimized(arr, d));
    }

    public static int countPartitionsMemoization(int[] arr, int d) {
        int n = arr.length;
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        if (totalSum - d < 0 || (totalSum - d) % 2 != 0) {
            return 0;
        }

        int target = (totalSum - d) / 2;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, - 1);
        }

        return solveMemo(arr, target, n - 1, dp);
    }

    private static int solveMemo(int[] arr, int sum, int n, int[][] dp) {
        if (n < 0) {
            return (sum == 0) ? 1 : 0;
        }

        if (dp[n][sum] != -1) {
            return dp[n][sum];
        }

        int exclude = solveMemo(arr, sum, n - 1, dp);
        int include = 0;
        if (arr[n] <= sum) {
            include = solveMemo(arr, sum - arr[n], n - 1, dp);
        }

        return dp[n][sum] = include + exclude;
    }

    public static int countPartitionsTabulation(int[] arr, int d) {
        int n = arr.length;
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        if (totalSum - d < 0 || (totalSum - d) % 2 != 0) {
            return 0;
        }

        int target = (totalSum - d) / 2;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {

                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                }else{

                    dp[i][j] = dp[i - 1][j];

                }
            }
        }

        return dp[n][target];
    }

    public static int countPartitionsOptimized(int[] arr, int d) {
        int n = arr.length;
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        if (totalSum - d < 0 || (totalSum - d) % 2 != 0) {
            return 0;
        }

        int target = (totalSum - d) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int num : arr) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] + dp[j - num];
            }
        }

        return dp[target];
    }
}