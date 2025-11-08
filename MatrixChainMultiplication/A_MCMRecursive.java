package MatrixChainMultiplication;

public class A_MCMRecursive {
    public static int MCMRec(int[] arr, int i, int j){
        if(i >= j) return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = MCMRec(arr, i, k) + MCMRec(arr, k + 1, j) + arr[i - 1] * arr[k]*arr[j];
            if(temp < min){
                min = temp;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        int[] arr = {10, 20, 10, 30};
        int n = arr.length;
        int ans = MCMRec(arr, 1, n-1);
        System.out.println(ans);
    }
}
