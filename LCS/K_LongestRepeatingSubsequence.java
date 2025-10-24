package LCS;

public class K_LongestRepeatingSubsequence {

    public static String longestRepeatingSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = n, j = n;
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                sb.append(s.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }


        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        String s = "AABEBCDD";
        String lrs = longestRepeatingSubsequence(s);
        System.out.println("Input String: " + s);
        System.out.println("Longest Repeating Subsequence: " + lrs);
        System.out.println("Length of LRS: " + lrs.length());
    }
}

