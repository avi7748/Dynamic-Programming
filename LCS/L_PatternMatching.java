package LCS;

public class L_PatternMatching {

    public static boolean searchPattern(String txt, String pat) {
        int m = txt.length();
        int n = pat.length();
        return n == solveTab(txt, pat);
    }

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
        String txt = "abcdefh";
        String pat = "bcd";

        boolean result = searchPattern(txt, pat);
        System.out.println("Pattern Found: " + result);
    }
}

