// 2021-12-27 Sort with Indexes

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class codeabbey29 {
	public static void main(String[] args) {
		Scanner readline = new Scanner(System.in);
		int cases = Integer.valueOf(readline.nextLine());
		List<Integer> num_list = new ArrayList<>();
		Map<Integer,Integer> index_map = new HashMap<>();
		String[] raw_list = readline.nextLine().split(" ");
		int counter = 1;
		for (String i:raw_list) {
			num_list.add(Integer.valueOf(i));
			index_map.put(Integer.valueOf(i), counter);
			counter++;
		}
		while(true) {
			boolean check = false;
			int i = 0;
			for (i = 0; i<num_list.size()-1;i++) {
				if (num_list.get(i)>num_list.get(i+1)) {
					int temp = num_list.get(i);
					num_list.set(i, num_list.get(i+1));
					num_list.set(i+1, temp);
					check = true;
				}
			}
			if (check == false) {
				break;
			}
		}
		for(int a:num_list) {
			System.out.print(index_map.get(a)+" ");
		}
		readline.close();
	}
}
