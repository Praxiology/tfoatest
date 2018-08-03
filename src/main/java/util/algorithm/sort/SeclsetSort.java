package util.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

//选择排序
public class SeclsetSort {
	
	/**
	 * 选择排序
	 * 每次外循环时，内循环都会找出最小元素的下标，移动值当前外循环的位置；
	 * 减少复制交换的次数
	 */
	public static int[] sortToBg(int[] or) {
		int selectIndex = 0;//被选中的下标
		int ol = or.length;
		int temp = 0;//交换中间桥梁
		for (int o = 0 ; o < ol ; o ++) {
			int is = o + 1;//内循环起始位置
			selectIndex = o;//初始被选中比较的位置
			for (int i = is ; i < ol ; i ++) {
				if (or[selectIndex] > or[i]) {
					selectIndex = i;
				}
			}
			//如果下标不是初始位置，则交换
			if (selectIndex != o) {
				System.err.printf("change:select[%d],init[%d]\n",selectIndex,o);
				temp = or[o];
				or[o] = or[selectIndex];
				or[selectIndex] = temp;
			}
		}
		return or;
	}
	
	@Test
	public void sortTtest() {
		int[] or = {-1,9,5,10,7,3,4,11,2,1,0,8};
		System.err.printf("or[%s]\n",Arrays.toString(or));
		int[] rel = SeclsetSort.sortToBg(or);
		System.err.printf("rel[%s]\n",Arrays.toString(rel));
		
	}

}
