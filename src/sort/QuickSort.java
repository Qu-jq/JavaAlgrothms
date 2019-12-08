package sort;


import java.util.Arrays;

/**
 * 快速排序    ---分而治之的思想
 * 1.以第一个元素为基准pivot，小于基准的在数组左边left，大于的在数组右边right，分成两个部分
 * left--pivot--right      这样就找到pivot所在位置；
 * 2. 再对上一步得到的两个区间分别使用第一步；
 * 3.知道划分到只剩一个元素为止，此时排序也就结束。
 * 核心思想：
 * 每划分一个确定一次元素的位置，一共需要划分logn次，而确定位置需要n个操作
 * 所以算法的复杂度为O(nlogn)
 * @author qjq
 *2019年12月5日20:28:09
 */
public class QuickSort {
	/**
	 * 快速排序API
	 * @param Arr
	 * @return
	 */
	public static int[] quikSort(int[] Arr) {
		doSort(Arr,0,Arr.length-1);
		return Arr;
	}
	/**
	 * 递归调用自身，不断划分  ----分而治之
	 * 每划分一次，确定一个元素的位置
	 * @param Arr
	 * @param start
	 * @param end
	 */
	public static void doSort(int[] Arr,int start,int end) {
		if (start < end) {
			int correctIndex = partition(Arr, start, end);//每次调用次函数，就将索引correctIndex上放置正确的数据
			doSort(Arr, start, correctIndex - 1);
			doSort(Arr, correctIndex+1, end);
		}
	}
	/**
	 * 找到基准数所在的正确位置
	 * @param Arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static int partition(int[] Arr,int start,int end) {
		int pivot = Arr[start];//取第一个数为基准
		int pivotIndex = start;
		while(start<end) {
			//start和end移动的顺序不能改变，如果反过来，就会把大于基准数的数交换到第一个数的位置
			while(Arr[end]>=pivot && end>start) {
				end--;//当满足右边的数大于基准的数时，指针往左偏移
			}
			while(Arr[start]<=pivot && end>start) {
				start++;//当满足左边的数小于基准的数时，指针往左偏移
			}
			//将不满足左右两边的大小的数据交换位置
			swap(Arr,start, end);
		}
		//start=end时，将start或者end处的数据与基准处的数据交换，这样就得到基准数据的正确位置
		swap(Arr,start, pivotIndex);
		return start;
	}
	/**
	 * 数据交换
	 * @param Arr
	 * @param idx
	 * @param idy
	 * @return
	 */
	static boolean swap(int[] Arr, int idx, int idy) {
		int swap = Arr[idx];
		Arr[idx] = Arr[idy];
		Arr[idy] = swap;
		return true;
	}
	//测试
	public static void main(String[] args) {
		int[] Arr = {3, 4, 1, 32, 0, 1, 5, 12, 2, 5, 7, 8, 9, 2, 44, 111, 5};

		QuickSort quickSort = new QuickSort();
		int[] sortedArr = quickSort.quikSort(Arr);
		System.out.println(Arrays.toString(sortedArr));
	}

}
