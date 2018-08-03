package util.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

//内存排序：冒泡排序
public class MaoPaoSort {
	
	/**
	 * 最基本的排序
	 * 每次外循环都将最大的元素向右移动
	 * 直到循环结束，循环数组所有元素长度的次数
	 * **/
	public static int[] sortToBg(int[] or) {
		int orl = or.length;
		int temp = 0;//中间变量
		int il = 0 ;//内循环长度
		for ( int o = 0; o < orl ; o++ ) {
			//内循环长度，内次冒泡一个，下次比较的长度减一
			il = orl - o;
			//每次都从第一个和第二个开始比较
			for ( int i = 1; i < il ; i++) {
				//如果数组前一个下标值大于后一个值，则交换；
				if( or[i-1] > or[i] ) {
				    temp = or[i];
				    or[i] = or[i-1];
				    or[i-1] = temp;
				} 
			}
		}
		return or;
	}
	
	@Test
	public void testMP() {
		int [] rel = MaoPaoSort.sortToBg(new int[] {9,5,7,3,4,2,1,0,8});
		System.out.println(Arrays.toString(rel));
	}
	
}
