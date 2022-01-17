// 2022-01-17 Josephus Problem
// time: 58 mins

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class codeabbey32 {
	public static void main(String[] args) {
		Scanner readLine = new Scanner(System.in);
		String [] raw_input = readLine.nextLine().split(" ");
		int size = Integer.parseInt(raw_input[0]);
		int step = Integer.parseInt(raw_input[1]);
		//System.out.println(size);
		//System.out.println(step);
		List<Integer> circle = new ArrayList<>();
		int counter = 1;
		while (counter<= size) {
			circle.add(counter);
			counter++;
		}
		int tail = 0;
		while (circle.size() >1) {
			List<Integer> remove = new ArrayList<>();
			for (counter = 1; counter <= circle.size(); counter++) {
				if ((counter+tail)%step == 0) {
					remove.add(counter);
				}
			}
			tail = (tail+circle.size())%step;
			//System.out.println(remove);
			counter = 0;
			for (int item:remove) {
				circle.remove(item-1-counter);
				counter++;
			}
		}
		System.out.println(String.valueOf(circle.get(0)));
		readLine.close();
	}
}
