package LCS;

public class E_PrintLCS {
    private static String[][] dp;  // store computed LCS strings

    private static String printLCS(String X, String Y, int n, int m) {
        // Base case
        if (n == 0 || m == 0)
            return "";

        // Return memoized result if available
        if (dp[n][m] != null)
            return dp[n][m];

        // If characters match, include in LCS
        if (X.charAt(n - 1) == Y.charAt(m - 1)) {
            dp[n][m] = printLCS(X, Y, n - 1, m - 1) + X.charAt(n - 1);
        }
        // Otherwise, pick the longer of two possibilities
        else {
            String lcs1 = printLCS(X, Y, n - 1, m);
            String lcs2 = printLCS(X, Y, n, m - 1);
            dp[n][m] = (lcs1.length() > lcs2.length()) ? lcs1 : lcs2;
        }

        return dp[n][m];
    }

    private static String printLCS(String X, String Y){
        int n = X.length();
        int m = Y.length();
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(X.charAt(i-1) == Y.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int i = n, j = m;
        while(i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                sb.append(X.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i -= 1;
                } else {
                    j -= 1;
                }
            }
        }
        return new String(sb.reverse());
    }

    public static void main(String[] args) {
        System.out.println(printLCS("AXBCDSXXXX", "ABdadfadaCDS"));

        String X = "AXBCDSXXXX";
        String Y = "ABCDS";

        dp = new String[X.length() + 1][Y.length() + 1];
        String lcs = printLCS(X, Y, X.length(), Y.length());
        System.out.println("LCS: " + lcs);
    }
}
