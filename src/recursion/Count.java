package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 利用递归思想求列表数组元素总个数
 * 1、基线条件：列表为空时
 * 2、缩小规模：每次数组缩小一个规模
 * @author qjq
 *2019年12月5日19:31:16
 */
public class Count {

	public static int countArr(int[] Arr) {
		if(Arr.length==0) {
			return 0;
		}else {
			return 1+countArr(Arrays.copyOfRange(Arr, 1, Arr.length));
		}
	}
	public static int countList(List list) {
		if(list.size()==0) {
			return 0;
		}else {
			return 1+countList(list.subList(1, list.size()));
		}
	}
	//测试
	public static void main(String[] args) {
		int[] Arr = {1,2,3,4,5,6,7,8};
		System.out.println(countArr(Arr));
		List list = new ArrayList();
		list.add("h");
		list.add(3);
		list.add("f");
		System.out.println(countList(list));
	}
}
