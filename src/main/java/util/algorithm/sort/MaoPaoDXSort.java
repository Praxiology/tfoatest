package util.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

//定向冒泡排序(双向排序)
public class MaoPaoDXSort {

	/**
	 * 每次外循环都将相对最大最小的值向两端移动
	 * 直到先前已经确定的元素数组位置
	 * 比基本冒泡排序，减少了循环次数和，数组元素的复制次数
	 * */
	public static int[] sortToBg(int[] or) {
		int ol = or.length;
		for (int o = 0; o < ol; o++) {
			int temp = 0;
			// 冒泡从左到右
			int irs = o + 1;// 从左到右比较的开始位置
			int ire = ol - o;// 从左到右比较的结束位置
			if (irs >= ire) {
				System.out.println("1");
				break;
			}
			for (int ir = irs; ir < ire; ir++) {
				if (or[ir - 1] > or[ir]) {
					temp = or[ir];
					or[ir] = or[ir - 1];
					or[ir - 1] = temp;
				}
			}
			// 冒泡从到右到左
			int ils = ol - o - 2;// 从右到左比较的开始位置
			int ile = o;// 从右到左比较的结束位置
			if (ils <= ile) {
				System.out.println("2");
				break;
			}
			for (int il = ils; il > ile; il--) {
				if (or[il - 1] > or[il]) {
					temp = or[il];
					or[il] = or[il - 1];
					or[il - 1] = temp;
				}
			}
		}
		return or;
	}
	@Test
	public void testMP() {
		int [] rel = MaoPaoDXSort.sortToBg(new int[] {9,5,10,7,3,4,11,2,1,0,8});
		System.out.println(Arrays.toString(rel));
	}
}
