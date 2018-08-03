package util.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

//插入排序
public class InsertSort {
	
	/**
	 *相对冒泡排序，方向相反，性能和时间一样
	 * **/
	public static int[] sortToBg(int[] or) {
		int il = or.length;
		int temp = 0;//交换媒介
		for (int i = 0 ; i < il ; i++) {
			for (int o = i; o >= 1 ; o--) {
				if (or[o] < or[o-1]) {
					//每次内循环都将当前外循环位置的元素向前移动（交换），
					//最坏的情况到直到起始位置
					System.err.printf("change:o[i:%d,o:%d]\n",i,o);
					temp = or[o];
					or[o] = or[o-1];
					or[o-1] = temp;
				} else if (or[o] >= or[o-1]) {
					break;
				}
			}
		}
		return or;
	}
	
	@Test
	public void sortTtest() {
		int[] or = {-1,9,5,10,7,3,4,11,2,1,0,8};
		System.err.printf("or[%s]\n",Arrays.toString(or));
		int[] rel = InsertSort.sortToBg(or);
		System.err.printf("rel[%s]\n",Arrays.toString(rel));
		
	}

}
