package LCS;

public class M_MinimumInsertionToMakeStringEqual {

    public static int LCP(String s1) {
        int m = s1.length();
        StringBuilder sb = new StringBuilder(s1);
        String s2 = sb.reverse().toString();
        int[][] dp = new int[m+1][m+1];

        if(m == 0) return 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[m][m];
    }
    public static void main(String[] args) {
        String X = "ABCDCB";
        int n = X.length();
        System.out.println("Minimum insertion : " + (n - LCP(X)));
    }

}