package com.hifumi123.ga4j.benchmark;

import com.hifumi123.ga4j.AbstractGeneticAlgorithm;
import com.hifumi123.ga4j.ElitistModelGeneticAlgorithm;
import com.hifumi123.ga4j.Evaluator;
import com.hifumi123.ga4j.IndividualGenerator;
import com.hifumi123.ga4j.crossover.CrossoverOperator;
import com.hifumi123.ga4j.mutation.MutationOperator;
import com.hifumi123.ga4j.selection.SelectionOperator;

public class ElitistModelGeneticAlgorithmGenerator implements GeneticAlgorithmGenerator {

	@Override
	public AbstractGeneticAlgorithm generateGeneticAlgorithm(int populationSize, int maxGeneration, double probabilityOfCrossover, double probabilityOfMutation, IndividualGenerator individualGenerator, Evaluator evaluator, SelectionOperator selection, CrossoverOperator crossover, MutationOperator mutation) {
		return new ElitistModelGeneticAlgorithm(populationSize, maxGeneration, probabilityOfCrossover, probabilityOfMutation, individualGenerator, evaluator, selection, crossover, mutation);
	}

}
