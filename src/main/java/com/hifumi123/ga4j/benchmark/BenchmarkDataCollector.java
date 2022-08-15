package com.hifumi123.ga4j.benchmark;

import java.util.ArrayList;
import java.util.List;

import com.hifumi123.ga4j.AbstractIndividual;
import com.hifumi123.ga4j.DataCollector;

public class BenchmarkDataCollector implements DataCollector {
	
	private List<Double> bestFitnessList;
	
	private List<Double> meanFitnessList;
	
	private List<Double> onlinePerformanceList;
	
	private List<Double> offlinePerformanceList;
	
	public BenchmarkDataCollector() {
		bestFitnessList = new ArrayList<Double>();
		meanFitnessList = new ArrayList<Double>();
		onlinePerformanceList = new ArrayList<Double>();
		offlinePerformanceList = new ArrayList<Double>();
	}
	
	private double computeOnlinePerformance(int generation) {
		double sum = 0;
		for (int i = 1; i <= generation; i++)
			sum += meanFitnessList.get(i);
		
		return sum / generation;
	}
	
	private double computeOfflinePerformance(int generation) {
		double bestSoFar = bestFitnessList.get(1);
		double sum = bestSoFar;
		
		for (int i = 2; i <= generation; i++) {
			double current = bestFitnessList.get(i);
			
			if (current > bestSoFar)
				bestSoFar = current;
			
			sum += bestSoFar;
		}
		
		return sum / generation;
	}

	@Override
	public void collectData(List<AbstractIndividual> individuals, int generation) {
		AbstractIndividual first = individuals.get(0);
		double sumFitness = first.getFitness();
		AbstractIndividual best = first;
		
		
		for (int i = 1; i < individuals.size(); i++) {
			AbstractIndividual ai = individuals.get(i);
			
			if (ai.getFitness() > best.getFitness())
				best = ai;
			
			sumFitness += ai.getFitness();
		}
		
		bestFitnessList.add(best.getFitness());
		meanFitnessList.add(sumFitness / individuals.size());
		
		if (generation > 0) {
			onlinePerformanceList.add(computeOnlinePerformance(generation));
			offlinePerformanceList.add(computeOfflinePerformance(generation));
		} else {
			onlinePerformanceList.add(meanFitnessList.get(0));
			offlinePerformanceList.add(bestFitnessList.get(0));
		}
	}

	public List<Double> getBestFitnessList() {
		return bestFitnessList;
	}

	public List<Double> getMeanFitnessList() {
		return meanFitnessList;
	}

	public List<Double> getOnlinePerformanceList() {
		return onlinePerformanceList;
	}

	public List<Double> getOfflinePerformanceList() {
		return offlinePerformanceList;
	}
}
