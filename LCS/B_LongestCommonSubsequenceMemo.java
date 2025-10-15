package LCS;

import java.util.Arrays;

public class B_LongestCommonSubsequenceMemo {
    public static void main(String[] args) {
        String X = "ABCDEFG";
        String Y = "ABCEFYG";
        int n = X.length();
        int m = Y.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(memoizationLCS(X, Y, n, m, dp));
    }

    private static int memoizationLCS(String X, String Y, int n, int m, int[][] dp){
        if(n == 0 || m == 0) return 0;

        if(dp[n][m] != -1) return dp[n][m];

        if(X.charAt(n-1) == Y.charAt(m-1)){
            dp[n][m] = 1 + memoizationLCS(X, Y, n-1, m-1, dp);
        }else{
            dp[n][m] = Math.max(
                    memoizationLCS(X, Y, n, m-1, dp),
                    memoizationLCS(X, Y, n-1, m, dp)
            );
        }
        return dp[n][m];
    }
}
