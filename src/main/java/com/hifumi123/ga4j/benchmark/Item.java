package com.hifumi123.ga4j.benchmark;

import java.util.List;

public interface Item {
	
	public int getNX();
	
	public int generateInt();
	
	public long run(List<int[]> oxsList, Scheme scheme, BenchmarkDataCollector dataCollector);
	
	public String getName();
}
