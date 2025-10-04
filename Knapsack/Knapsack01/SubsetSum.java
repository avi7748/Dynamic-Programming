package Knapsack.Knapsack01;

class SubsetSum {

    static boolean SubsetSumRec(int[] arr, int sum, int n, Boolean[][]dp) {
        if( n == 0 && sum != 0) return false;

        if(sum == 0) return true;

        if(dp[n][sum] != null) return dp[n][sum];

        if(arr[n-1]<= sum){
            return dp[n][sum] = SubsetSumRec(arr, sum-arr[n-1], n-1, dp) || SubsetSumRec(arr, sum, n-1, dp);
        }else{
            return dp[n][sum] = SubsetSumRec(arr, sum, n-1, dp);
        }
    }

    static boolean SubsetSumTab(int[] arr,int sum){
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];

    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = arr.length;

        Boolean[][] dp = new Boolean[n+1][sum+1];


        if (SubsetSumRec(arr, sum, n, dp))
            System.out.println("True");
        else
            System.out.println("False");

        System.out.println(SubsetSumTab(arr, sum));
    }
}
