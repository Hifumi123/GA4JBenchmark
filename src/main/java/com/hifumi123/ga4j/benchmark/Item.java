package com.hifumi123.ga4j.benchmark;

import java.util.List;

public interface Item {
	
	public boolean[] generateGenes();

	public long run(List<boolean[]> genesList, Scheme scheme, BenchmarkDataCollector dataCollector);
	
	public String getName();
}
