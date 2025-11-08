package Miscellaneous;
import java.util.*;

public class A_PalindromicSubstring {

    // --------------------- Approach 1: Simple Recursion ---------------------
    // T.C : O(n^3)
    // S.C : O(n)
    public int countSubstringsRecursive(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindromeRecursive(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindromeRecursive(String s, int i, int j) {
        if (i >= j) return true;
        if (s.charAt(i) == s.charAt(j))
            return isPalindromeRecursive(s, i + 1, j - 1);
        return false;
    }


    // --------------------- Approach 2a: Memoization (Recursive) ---------------------
    // T.C : O(n^2)
    // S.C : O(n^2)
    public int countSubstringsMemoRecursive(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindromeMemoRecursive(s, i, j, dp)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindromeMemoRecursive(String s, int i, int j, int[][] dp) {
        if (i >= j) return true;

        if (dp[i][j] != -1) return dp[i][j] == 1;

        if (s.charAt(i) == s.charAt(j)) {
            boolean val = isPalindromeMemoRecursive(s, i + 1, j - 1, dp);
            dp[i][j] = val ? 1 : 0;
            return val;
        }

        dp[i][j] = 0;
        return false;
    }


    // --------------------- Approach 2b: Memoization + While Loop ---------------------
    // Uses while loop for palindrome check but caches results
    // T.C : O(n^2)
    // S.C : O(n^2)
    public int countSubstringsMemoWhile(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindromeMemoWhile(s, i, j, dp)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindromeMemoWhile(String s, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) return dp[i][j] == 1;

        int start = i, end = j;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                dp[i][j] = 0;
                return false;
            }
            start++;
            end--;
        }
        dp[i][j] = 1;
        return true;
    }


    // --------------------- Approach 3: Bottom-Up DP ---------------------
    // T.C : O(n^2)
    // S.C : O(n^2)
    public int countSubstringsBottomUp(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        for (int L = 1; L <= n; L++) {
            for (int i = 0; i + L <= n; i++) {
                int j = i + L - 1;

                if (i == j)
                    dp[i][i] = true; //str length 1 i.e. only one character is present is always palindromic
                else if (i + 1 == j)
                    dp[i][j] = (s.charAt(i) == s.charAt(j)); //palindromic sub of len 2
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);

                if (dp[i][j]) count++;
            }
        }
        return count;
    }


    // --------------------- Approach 4: Center Expansion (Optimal) ---------------------
    // T.C : O(n^2)
    // S.C : O(1)
    public int countSubstringsCenterExpand(String s) {
        int n = s.length();
        int count = 0;

        for (int center = 0; center < n; center++) {
            count += expandFromCenter(s, center, center, n);     // Odd-length palindromes
            count += expandFromCenter(s, center, center + 1, n); // Even-length palindromes
        }
        return count;
    }

    private int expandFromCenter(String s, int left, int right, int n) {
        int cnt = 0;
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            cnt++;
            left--;
            right++;
        }
        return cnt;
    }


    // --------------------- Main method to test all approaches ---------------------
    public static void main(String[] args) {
        A_PalindromicSubstring obj = new A_PalindromicSubstring();
        String s = "aaa";

        System.out.println("Approach 1 (Recursive): " + obj.countSubstringsRecursive(s));
        System.out.println("Approach 2a (Memo Recursive): " + obj.countSubstringsMemoRecursive(s));
        System.out.println("Approach 2b (Memo While): " + obj.countSubstringsMemoWhile(s));
        System.out.println("Approach 3 (Bottom-Up DP): " + obj.countSubstringsBottomUp(s));
        System.out.println("Approach 4 (Center Expansion): " + obj.countSubstringsCenterExpand(s));
    }
}
