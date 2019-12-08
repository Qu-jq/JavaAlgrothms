package recursion;
/**
 * 递归实现阶乘的运算
 * tips: 递归&while
 * 如果使用循环，程序的性能可能更高；如果使用递归，程序可能
 * 更容易理解。如何选择要看什么对你来说更重要
 * @author qjq
 *2019年12月5日15:54:22
 */
public class Factorial {
	//递归
	public static int  factorial(int n) {
		if(n == 1) {
			return 1;
		}else {
			return n*factorial(n-1);
		}
	}
	//测试
	public static void main(String[] args) {
		System.out.println(factorial(6));//720
	}

}

