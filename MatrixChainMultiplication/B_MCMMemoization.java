package MatrixChainMultiplication;

import java.util.Arrays;

public class B_MCMMemoization {
    public static int MCMRec(int[] arr, int i, int j, int[][] dp){
        if(i >= j) return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = MCMRec(arr, i, k, dp) + MCMRec(arr, k + 1, j, dp) + arr[i - 1] * arr[k]*arr[j];
            if(temp < min){
                min = temp;
            }
        }
        return dp[i][j] = min;
    }
    public static void main(String[] args) {
        int[] arr = {10, 20, 10, 30};
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = MCMRec(arr, 1, n-1, dp);
        System.out.println("Memoization ans : " + ans);
    }
}
