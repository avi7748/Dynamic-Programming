package Knapsack;

import java.util.Arrays;

class SubsetSum {
    static int[][] dp;

    static boolean SubsetSumRec(int[] arr, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;

        if (dp[n][sum] != -1)
            return dp[n][sum] == 1;

        if (arr[n - 1] > sum) {
            if (SubsetSumRec(arr, n - 1, sum)) {
                dp[n][sum] = 1;
            } else {
                dp[n][sum] = 0;
            }
        } else {
            boolean exclude = SubsetSumRec(arr, n - 1, sum);
            boolean include = SubsetSumRec(arr, n - 1, sum - arr[n - 1]);

            if (exclude || include) {
                dp[n][sum] = 1;
            } else {
                dp[n][sum] = 0;
            }
        }

        return dp[n][sum] == 1;
    }

    static boolean SubsetSumTab(int[] arr,int sum){
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];

    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = arr.length;

        dp = new int[n + 1][sum + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        if (SubsetSumRec(arr, n, sum))
            System.out.println("True");
        else
            System.out.println("False");

        System.out.println(SubsetSumTab(arr, sum));
    }
}
