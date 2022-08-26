package com.hifumi123.ga4j.benchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.hifumi123.ga4j.crossover.OnePointCrossover;
import com.hifumi123.ga4j.mutation.SimpleMutation;
import com.hifumi123.ga4j.selection.ElitistProportionalModelSelection;
import com.hifumi123.ga4j.selection.ProportionalModelSelection;

public class Benchmark {
	
	private static void performItem(Item item, int totalTurn, List<Scheme> schemeList) {
		List<List<BenchmarkDataCollector>> dataCollectorListEveryTurn = new ArrayList<List<BenchmarkDataCollector>>();
		List<List<Long>> durationListEveryScheme = new ArrayList<List<Long>>();
		for (int i = 0; i < schemeList.size(); i++)
			durationListEveryScheme.add(new ArrayList<Long>());
		
		for (int i = 0; i < totalTurn; i++) {
			List<BenchmarkDataCollector> dataCollectorList = new ArrayList<BenchmarkDataCollector>();
			
			List<int[]> oxsList = new ArrayList<int[]>();
			for (int j = 0; j < schemeList.get(0).getPopulationSize(); j++) {//TODO 后续再改
				int[] oxs = new int[item.getNX()];
				for (int k = 0; k < oxs.length; k++)
					oxs[k] = item.generateInt();
				
				oxsList.add(oxs);
			}
			
			for (int j = 0; j < schemeList.size(); j++) {
				BenchmarkDataCollector dataCollector = new BenchmarkDataCollector();
				
				long duration = item.run(oxsList, schemeList.get(j), dataCollector);
				
				dataCollectorList.add(dataCollector);
				durationListEveryScheme.get(j).add(duration);
			}
			
			dataCollectorListEveryTurn.add(dataCollectorList);
		}
		
		String onlinePerformanceFilepath = "OnlinePerformance.csv";
		String offlinePerformanceFilepath = "OfflinePerformance.csv";
		String durationFilepath = "Duration.csv";
		String fitnessFilepath = "Fitness.csv";
		
		try {
			CSVPrinter printer1 = new CSVPrinter(new FileWriter(item.getName() + onlinePerformanceFilepath), CSVFormat.EXCEL);
			CSVPrinter printer2 = new CSVPrinter(new FileWriter(item.getName() + offlinePerformanceFilepath), CSVFormat.EXCEL);
			CSVPrinter printer3 = new CSVPrinter(new FileWriter(item.getName() + durationFilepath), CSVFormat.EXCEL);
			CSVPrinter printer4 = new CSVPrinter(new FileWriter(item.getName() + fitnessFilepath), CSVFormat.EXCEL);
			
			for (int i = 0; i < dataCollectorListEveryTurn.size(); i++)
				for (int j = 0; j < dataCollectorListEveryTurn.get(i).size(); j++) {
					BenchmarkDataCollector dataCollector = dataCollectorListEveryTurn.get(i).get(j);
					
					printer1.printRecord(dataCollector.getOnlinePerformanceList());
					printer2.printRecord(dataCollector.getOfflinePerformanceList());
					printer4.printRecord(dataCollector.getBestFitnessList());
					printer4.printRecord(dataCollector.getMeanFitnessList());
				}
			
			for (int i = 0; i < durationListEveryScheme.size(); i++)
				printer3.printRecord(durationListEveryScheme.get(i));
			
			printer1.close();
			printer2.close();
			printer3.close();
			printer4.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {		
		int populationSize = 80;
		int maxGeneration = 200;
		double probabilityOfCrossover = 0.6;
		double probabilityOfMutation = 0.001;
		
		BinaryChromosomeGenerator binaryChromosomeGenerator = new BinaryChromosomeGenerator();
		GrayCodeChromosomeGenerator grayCodeChromosomeGenerator = new GrayCodeChromosomeGenerator();
		
		ProportionalModelSelection proportionalModelSelection = new ProportionalModelSelection();
		ElitistProportionalModelSelection elitistProportionalModelSelection = new ElitistProportionalModelSelection();
		
		OnePointCrossover onePointCrossover = new OnePointCrossover();
		
		SimpleMutation simpleMutation = new SimpleMutation();
		
		SimpleGeneticAlgorithmGenerator simpleGeneticAlgorithmGenerator = new SimpleGeneticAlgorithmGenerator();
		
		int totalTurn = 100;
		
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(new DJF1());
		itemList.add(new DJF2());
		
		List<Scheme> schemeList = new ArrayList<Scheme>();
		schemeList.add(new Scheme(populationSize, maxGeneration, probabilityOfCrossover, probabilityOfMutation, binaryChromosomeGenerator, proportionalModelSelection, onePointCrossover, simpleMutation, simpleGeneticAlgorithmGenerator));
		schemeList.add(new Scheme(populationSize, maxGeneration, probabilityOfCrossover, probabilityOfMutation, binaryChromosomeGenerator, elitistProportionalModelSelection, onePointCrossover, simpleMutation, simpleGeneticAlgorithmGenerator));
		schemeList.add(new Scheme(populationSize, maxGeneration, probabilityOfCrossover, probabilityOfMutation, grayCodeChromosomeGenerator, proportionalModelSelection, onePointCrossover, simpleMutation, simpleGeneticAlgorithmGenerator));
		schemeList.add(new Scheme(populationSize, maxGeneration, probabilityOfCrossover, probabilityOfMutation, grayCodeChromosomeGenerator, elitistProportionalModelSelection, onePointCrossover, simpleMutation, simpleGeneticAlgorithmGenerator));
		
		for (int i = 0; i < itemList.size(); i++)
			performItem(itemList.get(i), totalTurn, schemeList);
	}
}
