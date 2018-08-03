package util.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

//希尔排序
public class ShellSort {

	/**
	 * 分組插入排序的思想
	 **/
	public static int[] sortToBg2(int[] arr) {
		int i, j, r, tmp;
		// r 增量
		// 划组排序
		for (r = arr.length / 2; r >= 1; r = r / 2) {
			for (i = r; i < arr.length; i++) {
				tmp = arr[i];//
				j = i - r;//
				while (j >= 0 && tmp < arr[j]) {
					arr[j + r] = arr[j];
					j -= r;// j=j-r
				}
				arr[j + r] = tmp;
			}
			System.out.println(i + ":" + Arrays.toString(arr));
		}

		return arr;
	}

	@Test
	public void sortTtest() {
		int[] or = { -1, 9, 5, 10, 7, 3, 4, 11, 2, 1, 0, 8,99,-10 };
		System.err.printf("or[%s]\n", Arrays.toString(or));
		int[] rel = ShellSort.sortToBg(or);
		System.err.printf("rel[%s]\n", Arrays.toString(rel));

	}

	// 希尔排序
	public static int[] sortToBg(int[] sortList) {
		int i, j, k, step, temp;
		int len = sortList.length;
		// 步长除以2
		for (step = len / 2; step > 0; step /= 2)
			/**
			 * 分别对每个分组进行直接插入排序
			 */
			for (i = 0; i < step; i++) {// 遍历多个分组
				for (j = i + step; j < len; j += step)// j=j+step，对单个分组，每+step，然后进行插入排序
				{
					k = j - step;// 前一个元素的下标
					while (k >= 0 && sortList[k + step] < sortList[k]) {
						temp = sortList[k + step];
						sortList[k + step] = sortList[k];
						sortList[k] = temp;
						k -= step;
					}
				}
			}
		return sortList;
	}
}
