package Knapsack.Knapsack01;

import java.util.Arrays;

public class H_TargetSum {

    public int findTargetSumWays(int[] nums, int d) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If difference is not possible
        if (Math.abs(d) > sum || (sum - d) % 2 != 0) {
            return 0;
        }

        int t = (sum + d) / 2; // subset sum target

        int[][] dp = new int[n + 1][t + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return countSubsetDiff(nums, n, t, dp);
    }

    public int countSubsetDiff(int[] arr, int n, int target, int[][] dp) {
        if (n == 0) {
            return (target == 0) ? 1 : 0;
        }
        if (dp[n][target] != -1) return dp[n][target];

        if (arr[n - 1] <= target) {
            return dp[n][target] =
                    countSubsetDiff(arr, n - 1, target - arr[n - 1], dp) +
                            countSubsetDiff(arr, n - 1, target, dp);
        } else {
            return dp[n][target] = countSubsetDiff(arr, n - 1, target, dp);
        }
    }

    public static void main(String[] args) {
        H_TargetSum ts = new H_TargetSum();

        int[] nums = {1, 1, 2, 3};
        int target = 1;

        int ways = ts.findTargetSumWays(nums, target);
        System.out.println("Number of ways: " + ways);
    }
}
