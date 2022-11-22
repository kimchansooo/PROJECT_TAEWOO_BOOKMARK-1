package kr.or.kosa.dto;

public class Statistics {
	//통계(차트)용 DTO
	private String statistics_name; //차트 데이터 이름 ex) 1월 매출
	private double statistics_num; // 차트 데이터		          1000
	public Statistics(String statistics_name, double statistics_num) {
		super();
		this.statistics_name = statistics_name;
		this.statistics_num = statistics_num;
	}
	public Statistics() {}
	public String getStatistics_name() {
		return statistics_name;
	}
	public void setStatistics_name(String statistics_name) {
		this.statistics_name = statistics_name;
	}
	public double getStatistics_num() {
		return statistics_num;
	}
	public void setStatistics_num(double statistics_num) {
		this.statistics_num = statistics_num;
	}
	@Override
	public String toString() {
		return "Statistics [statistics_name=" + statistics_name + ", statistics_num=" + statistics_num + "]";
	}
	
}
