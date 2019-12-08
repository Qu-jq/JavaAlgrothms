package sort;


import java.util.Arrays;

/**
 * 选择排序算法
 * 1、假设数组第一个数为最小
 * 2、查找整个数组，看是否有比这个数更小的，
 * 如果有，则互相调换数据，循坏过后，找到的数据即为整个数组最小的数
 * 3、再假设第二个数据为最小的，此时从第二个数据开始查找最小的数
 * 4、依次循环
 * 核心思想：
 * 先找到n个元素中最小的数，再找n-1个元素中最小的数，依次往下......
 * 时间复杂度为O(n^2)
 * @author qjq
 *2019年12月5日10:01:38
 */
public class SelectionSort {

	/*
	 * 从小到大排序
	 */
	public static void selectionSort(int[] Arr) {

		int flush;//中间缓存数据
		for(int i=0;i<Arr.length-1;i++) {
			int minIndex = i ;//最小数的索引
			for(int j=i+1; j<Arr.length; j++) {
				if(Arr[j]<=Arr[minIndex]) {
					minIndex = j;
				}
			}
			//如果有比Arr[minIndex]更小的数，互相替换
			if(minIndex != i) {
				flush = Arr[minIndex];
				Arr[minIndex] = Arr[i];
				Arr[i] = flush;
			}
		}
	}
	//测试
	public static void main(String[] args) {
		int[] Arr = {4,3,2,5,67,342,23,234,65,3};
		selectionSort(Arr) ;
		System.out.println(Arrays.toString(Arr));//[2, 3, 3, 4, 5, 23, 65, 67, 234, 342]
	}

}
