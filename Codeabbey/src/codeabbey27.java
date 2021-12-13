import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//2021-12-13 Bubble Sort
//Time: 30 mins
public class codeabbey27 {

	public static void main(String[] args) {
		Scanner readline = new Scanner(System.in);
		int len = Integer.valueOf(readline.nextLine());
		String[] raw_s = readline.nextLine().split(" ");
		List<Integer> int_array = new ArrayList<>();
		for (String s:raw_s) {
			int_array.add(Integer.valueOf(s));
		}
		int count_swaps = 0;
		int count_passes = 0;
		while(true) {
			boolean check = false;
			count_passes++;
			int i = 0;
			for (i = 0; i<len-1;i++) {
				if (int_array.get(i)>int_array.get(i+1)) {
					int temp = int_array.get(i);
					int_array.get(i);
					int_array.set(i, int_array.get(i+1));
					int_array.set(i+1, temp);
					count_swaps++;
					check = true;
				}
			}
			if (check == false) {
				break;
			}
		}
		readline.close();
		System.out.println(count_passes+" "+count_swaps);
	}

}
