package search;

/**
 *二分查找
 * @author qjq
 *2019年12月4日21:56:40
 */
public class BinarySearch {
	public static int rank(int[] a , int key) {
		int low = 0;
		int high = a.length-1;
		int mid ;
		while(low<=high) {
			mid = (low+high)/2;
			if(a[mid] == key)
				return mid;
			if(a[mid]>key)
				high=mid-1;
			else
				low=mid+1;
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] a ={1,2,3,4,5};
		System.out.println(rank(a , 5));
	}
	

}
