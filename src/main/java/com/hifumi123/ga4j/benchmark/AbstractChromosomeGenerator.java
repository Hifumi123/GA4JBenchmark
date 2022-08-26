package com.hifumi123.ga4j.benchmark;

import java.util.List;

import com.hifumi123.ga4j.examples.ChromosomeGenerator;

public abstract class AbstractChromosomeGenerator implements ChromosomeGenerator {
	
	protected List<int[]> oxsList;
	
	protected int indexOfOxsInList;
	
	protected int lengthOfOxCode;

	public void resetIndex() {
		indexOfOxsInList = 0;
	}

	public void setOxsList(List<int[]> oxsList) {
		this.oxsList = oxsList;
	}

	public void setLengthOfOxCode(int lengthOfOxCode) {
		this.lengthOfOxCode = lengthOfOxCode;
	}
}
