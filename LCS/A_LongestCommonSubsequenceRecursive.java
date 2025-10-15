package LCS;

public class A_LongestCommonSubsequenceRecursive {
    public static void main(String[] args) {
        String X = "ABCDEFG";
        String Y = "ABCEFYG";
        System.out.println(recursiveLCS(X, Y, X.length(), Y.length()));
    }

    private static int recursiveLCS(String X, String Y, int n, int m){
        if(n == 0 || m == 0) return 0;

        if(X.charAt(n-1) == Y.charAt(m-1)){
            return 1 + recursiveLCS(X, Y, n-1, m-1);
        }else{
            return Math.max(
                    recursiveLCS(X, Y, n, m-1),
                    recursiveLCS(X, Y, n-1, m)
            );
        }
    }
}
