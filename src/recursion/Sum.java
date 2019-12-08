package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 利用递归思想编写求和函数
 * 1、基线条件：列表为空时
 * 2、缩小规模：每次将第一个数放在求和函数外面
 * @author qjq
 *2019年12月5日18:31:52
 */
public class Sum {

	public static int sumList(List<Integer> Arr) {
		if(Arr.size()==0) {
			return 0;//列表为空时，返回0
		}else {
			return (int) Arr.toArray()[0]+sumList(Arr.subList(1, Arr.size()));//不止一个数时，返回第一个数与剩下数的和
		}
	}

	public static int sumArr(int[] Arr) {
		if(Arr.length==0) {
			return 0;//列表为空时，返回0
		}else {
			return Arr[0]+ sumArr(Arrays.copyOfRange(Arr, 1, Arr.length));//不止一个数时，返回第一个数与剩下数的和
		}
	}
	//测试
	public static void main(String[] args) {
		List<Integer> Arr = new ArrayList<Integer>() ;
		Arr.add(5);
		Arr.add(8);
		Arr.add(2);
		Arr.add(12);
		System.out.println(sumList(Arr));//27

		int[] Arr2 = {3,6,4,10};
		System.out.println(sumArr(Arr2));//23
	}

}
