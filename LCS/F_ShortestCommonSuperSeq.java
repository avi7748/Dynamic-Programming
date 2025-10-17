package LCS;

public class F_ShortestCommonSuperSeq {
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

    public static void main(String[] args) {
        String X = "ABCD";
        String Y = "ABCD";
        int m = X.length();
        int n = Y.length();

        Integer[][] dp = new Integer[m+1][n+1];

        System.out.println(m + n - LCS(X,Y,m,n,dp));
    }
}
