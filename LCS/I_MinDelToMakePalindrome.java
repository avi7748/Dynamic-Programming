package LCS;

public class I_MinDelToMakePalindrome {
    private static int LCS(String X, String Y, int m, int n, Integer[][] dp) {
        if(m == 0 || n == 0) return 0;

        if(dp[m][n] != null){
            return dp[m][n];
        }

        if(X.charAt(m-1) == Y.charAt(n-1)) {
            return dp[m][n] = 1 + LCS(X,Y,m-1,n-1,dp);
        }
        else {
            return dp[m][n] = Math.max(LCS(X,Y,m,n-1,dp), LCS(X,Y,m-1,n,dp));
        }
    }

    private static int LCP(String X) {
        StringBuilder sb = new StringBuilder(X);
        String rev = sb.reverse().toString();
        int m = X.length();
        int n = rev.length();

        Integer[][] dp = new Integer[m+1][n+1];

        return LCS(X, rev, X.length(), rev.length(), dp);

    }

    public static void main(String[] args) {
        String X = "ABCDCBA";
        int n = X.length();
        System.out.println(n - LCP(X));
    }
}
