package com.hifumi123.ga4j.benchmark;

import com.hifumi123.ga4j.crossover.CrossoverOperator;
import com.hifumi123.ga4j.mutation.MutationOperator;
import com.hifumi123.ga4j.selection.SelectionOperator;

public class Scheme {

	private int populationSize;
	
	private int maxGeneration;
	
	private double probabilityOfCrossover;
	
	private double probabilityOfMutation;
	
	private AbstractChromosomeGenerator chromosomeGenerator;
	
	private SelectionOperator selection;
	
	private CrossoverOperator crossover;
	
	private MutationOperator mutation;
	
	private GeneticAlgorithmGenerator geneticAlgorithmGenerator;

	public Scheme(int populationSize, int maxGeneration, double probabilityOfCrossover, double probabilityOfMutation, AbstractChromosomeGenerator chromosomeGenerator, SelectionOperator selection, CrossoverOperator crossover, MutationOperator mutation, GeneticAlgorithmGenerator geneticAlgorithmGenerator) {
		this.populationSize = populationSize;
		this.maxGeneration = maxGeneration;
		this.probabilityOfCrossover = probabilityOfCrossover;
		this.probabilityOfMutation = probabilityOfMutation;
		this.chromosomeGenerator = chromosomeGenerator;
		this.selection = selection;
		this.crossover = crossover;
		this.mutation = mutation;
		this.geneticAlgorithmGenerator = geneticAlgorithmGenerator;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public int getMaxGeneration() {
		return maxGeneration;
	}

	public double getProbabilityOfCrossover() {
		return probabilityOfCrossover;
	}

	public double getProbabilityOfMutation() {
		return probabilityOfMutation;
	}

	public AbstractChromosomeGenerator getChromosomeGenerator() {
		return chromosomeGenerator;
	}

	public SelectionOperator getSelection() {
		return selection;
	}

	public CrossoverOperator getCrossover() {
		return crossover;
	}

	public MutationOperator getMutation() {
		return mutation;
	}

	public GeneticAlgorithmGenerator getGeneticAlgorithmGenerator() {
		return geneticAlgorithmGenerator;
	}
}
