//Name: Frank Yue Ying (yying2)
package lab6;

public class Company implements Comparable<Company>{
	static int overallHiredCount;
	int rank;
	String name;
	int hiredCount;
	
	Company(int rank,String name, int hirecount){
		this.name = name;
		this.rank = rank;
		this.hiredCount = hirecount;
	}
	
	@Override
	public int compareTo(Company company) {
		return (rank-company.rank);
	}
	
}
