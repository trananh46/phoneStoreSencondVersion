package com.example.demo.DTO;

public class StatisticBudgetDTO {

	private int time;
	
	private Long totalMoney;
	
	private Long quantity;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public StatisticBudgetDTO(int time, Long totalMoney, Long quantity) {
		super();
		this.time = time;
		this.totalMoney = totalMoney;
		this.quantity = quantity;
	}

	public StatisticBudgetDTO() {
		
	}
	
	
}


