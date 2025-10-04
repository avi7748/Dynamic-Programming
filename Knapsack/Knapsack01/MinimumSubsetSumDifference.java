package Knapsack.Knapsack01;
//Problem Statement:
//Given an array of positive integers, divide it into two subsets such that
//the absolute difference between their sums is minimized.

import java.util.Arrays;

public class MinimumSubsetSumDifference {
    static int[][] dp;

    static boolean isSubsetPossible(int[] arr, int n, int target) {
        if (target == 0) return true;
        if (n == 0) return false;

        if (dp[n][target] != -1)
            return dp[n][target] == 1;

        if (arr[n - 1] <= target) {
            boolean take = isSubsetPossible(arr, n - 1, target - arr[n - 1]);
            boolean notTake = isSubsetPossible(arr, n - 1, target);
            dp[n][target] = (take || notTake) ? 1 : 0;
        } else {
            dp[n][target] = isSubsetPossible(arr, n - 1, target) ? 1 : 0;
        }
        return dp[n][target] == 1;
    }

    static int minDifferenceMemo(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int num : arr) sum += num;

        dp = new int[n + 1][sum / 2 + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        int s1 = 0;
        for (int j = sum / 2; j >= 0; j--) {
            if (isSubsetPossible(arr, n, j)) {
                s1 = j;
                break;
            }
        }
        return sum - 2 * s1;
    }

    static int minDifferenceTab(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int num : arr) sum += num;

        boolean[][] dp = new boolean[n + 1][sum / 2 + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int s1 = 0;
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[n][j]) {
                s1 = j;
                break;
            }
        }
        return sum - 2 * s1;
    }

    static int minDifferenceSpaceOptimized(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int num : arr) sum += num;

        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;

        for (int num : arr) {
            for (int j = sum / 2; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
                System.out.println("num: "+num+"\nj : "+j+"\n"+Arrays.toString(dp)+"\n");
            }
        }

        int s1 = 0;
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[j]) {
                s1 = j;
                break;
            }
        }
        return sum - 2 * s1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 11, 5};

        System.out.println("Minimum Subset Sum Difference (Memoization) = " + minDifferenceMemo(arr));
        System.out.println("Minimum Subset Sum Difference (Tabulation) = " + minDifferenceTab(arr));
        System.out.println("Minimum Subset Sum Difference (Space Optimized) = " + minDifferenceSpaceOptimized(arr));
    }
}
