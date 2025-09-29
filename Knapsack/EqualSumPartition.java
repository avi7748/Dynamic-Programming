package Knapsack;

public class EqualSumPartition {

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        Boolean[][] dp = new Boolean[n + 1][target + 1];
        return recursive(nums, target, dp, n);
    }

    private boolean recursive(int[] nums, int target, Boolean[][] dp, int n) {
        if (target == 0) return true;
        if (n == 0) return false;

        if (dp[n][target] != null) return dp[n][target];

        if (nums[n - 1] <= target) {
            boolean include = recursive(nums, target - nums[n - 1], dp, n - 1);
            boolean exclude = recursive(nums, target, dp, n - 1);
            return dp[n][target] = include || exclude;
        } else {
            return dp[n][target] = recursive(nums, target, dp, n - 1);
        }
    }

    public static void main(String[] args) {
        EqualSumPartition solution = new EqualSumPartition();

        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Can partition [1, 5, 11, 5]? " + solution.canPartition(nums1));

        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Can partition [1, 2, 3, 5]? " + solution.canPartition(nums2));
    }
}
