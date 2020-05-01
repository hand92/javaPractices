package hand.offfer;

import java.util.Arrays;

public class Solution {
    //给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
    // 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
    // （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        for (int i = 0; i < B.length; i++) {

            B[i] = 1;
            for (int j = 0; j < A.length; j++) {
                if (i != j) {
                    B[i] *= A[j];
                }
            }
        }
        return B;
    }

    //写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    public int Add(int num1, int num2) {
        return num2 != 0 ? Add((num1 ^ num2), (num1 & num2) << 1) : num1;
    }

    //一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloorII(int target) {
        if (target == 1) {
            return 1;
        }
        return 2 * JumpFloorII(target - 1);

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] A = {1, 2, 3, 4, 5};
//        System.out.println(Arrays.toString(solution.multiply(A)));

//        System.out.println(solution.Add(2,3));

        System.out.println(solution.JumpFloorII(5));
    }

}