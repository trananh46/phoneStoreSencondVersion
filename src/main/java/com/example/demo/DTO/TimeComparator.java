package com.example.demo.DTO;

import java.util.Comparator;

public class TimeComparator implements Comparator<StatisticBudgetDTO>{

	public int compare(StatisticBudgetDTO s1 , StatisticBudgetDTO s2) {
		
		return s1.getTime() - s2.getTime();
	}
}
