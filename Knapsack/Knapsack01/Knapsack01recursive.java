package Knapsack.Knapsack01;

public class Knapsack01recursive {

    public static int KnapSack(int[] wt, int[] val, int W, int n){
        if(W == 0 || n == 0){
            return 0;
        }
        if(wt[n-1] <= W){
            return Math.max(
                    val[n-1]+KnapSack(wt,val,W-wt[n-1],n-1),
                    KnapSack(wt,val,W,n-1)
            );
        } else{
            return KnapSack(wt,val,W,n-1);
        }

    }
    public static void main(String[] args) {
        int[] wt = {50, 80, 70, 60};
        int[] val = {2, 6, 3, 4};
        int val1 = KnapSack(wt,val,150,4);
        System.out.println(val1);
    }
}
