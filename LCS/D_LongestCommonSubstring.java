package LCS;

import java.util.*;

public class D_LongestCommonSubstring {

    // 1️⃣ Recursive Approach
    static int solveRec(String A, String B, int i, int j, int count) {
        if (i == 0 || j == 0)
            return count;

        int result = count;

        if (A.charAt(i - 1) == B.charAt(j - 1))
            result = solveRec(A, B, i - 1, j - 1, count + 1);

        int res1 = solveRec(A, B, i - 1, j, 0);
        int res2 = solveRec(A, B, i, j - 1, 0);

        return Math.max(result, Math.max(res1, res2));
    }

    // 2️⃣ Memoization (Top-Down DP)
    static int[][] dp;

    static int solveMemo(String A, String B, int i, int j, int[] maxLen) {
        if (i == 0 || j == 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (A.charAt(i - 1) == B.charAt(j - 1)) {
            dp[i][j] = 1 + solveMemo(A, B, i - 1, j - 1, maxLen);
            maxLen[0] = Math.max(maxLen[0], dp[i][j]);
        } else {
            dp[i][j] = 0;
        }

        solveMemo(A, B, i - 1, j, maxLen);
        solveMemo(A, B, i, j - 1, maxLen);

        return dp[i][j];
    }

    // 3️⃣ Tabulation (Bottom-Up DP)
    static int solveTab(String A, String B) {
        int n = A.length(), m = B.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String A = "abcde";
        String B = "abfce";

        // --- 1️⃣ Recursive
        int recAns = solveRec(A, B, A.length(), B.length(), 0);
        System.out.println("1️⃣ Recursive Result: " + recAns);

        // --- 2️⃣ Memoization
        dp = new int[A.length() + 1][B.length() + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int[] maxLen = {0};
        solveMemo(A, B, A.length(), B.length(), maxLen);
        System.out.println("2️⃣ Memoization Result: " + maxLen[0]);

        // --- 3️⃣ Tabulation
        int tabAns = solveTab(A, B);
        System.out.println("3️⃣ Tabulation Result: " + tabAns);
    }
}

