package MatrixChainMultiplication;

import java.util.Arrays;

public class C_PalindromePartition {
    static int recursive(String S, int i, int j){
        if(i >= j) return 0;

        if(isPalindromic(S, i, j)) return 0;

        int mn = Integer.MAX_VALUE;

        for(int k = i; k < j; k++){
            int temp = recursive(S, i, k) + recursive(S, k + 1, j) + 1;
            mn = Math.min(mn, temp);
        }
        return mn;

    }

    static int memoization(String S, int i, int j, int[][] dp){
        if(i >= j) return 0;

        if(isPalindromic(S, i, j)) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int mn = Integer.MAX_VALUE;

        for(int k = i; k < j; k++){

            int temp = memoization(S, i, k, dp) + memoization(S, k + 1, j, dp) + 1;

            mn = Math.min(mn, temp);
        }
        return dp[i][j] = mn;

    }

    static int optimized(String S, int i, int j, int[][] dp){
        if(i >= j) return 0;

        if(isPalindromic(S, i, j)) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int mn = Integer.MAX_VALUE;

        for(int k = i; k < j; k++){
            int left = 0, right = 0;
             //if already solved return from here only rather than calling
            if(dp[i][k] != -1) {
                left = dp[i][k];
            }
            else {
                left = optimized(S, i, k, dp);
                dp[i][k] = left;
            }

            if(dp[k+1][j] != -1) {
                right = dp[k+1][j];
            }
            else {
                right = optimized(S, k + 1, j, dp);
                dp[k+1][j] = right;
            }

            int temp = left + right + 1;

            mn = Math.min(mn, temp);
        }
        return dp[i][j] = mn;

    }


    static boolean isPalindromic(String str, int i, int j){
        while(i <= j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {

        String s = "abbcdcbbssssssgf    ";

        int n = s.length();

        //Recursive
        System.out.println("Recursive: " + recursive( s, 0, n-1));

        //Memoization
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
//        System.out.println("Memoization: " + memoization( s, 0, n-1, dp));
        System.out.println("Optimized: " + optimized( s, 0, n-1, dp));

    }
}
