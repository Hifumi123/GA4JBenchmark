package com.hifumi123.ga4j.benchmark;

import java.util.List;
import java.util.Random;

import com.hifumi123.ga4j.AbstractGeneticAlgorithm;
import com.hifumi123.ga4j.examples.djf1.CustomEvaluator;
import com.hifumi123.ga4j.examples.djf1.CustomIndividualGenerator;

/**
 * 计算 De Jong 函数 F1 的最小值。<br>
 * De Jong 函数 F1：<br>
 * f1(x1, x2, x3) = x1^2 + x2^2 + x3^3<br>
 * 条件为：<br>
 * -5.12 <= xi <= 5.12 (i = 1, 2, 3)
 * 
 * @author Hifumi123
 *
 */
public class DJF1 implements Item {
	
	private int lengthOfChromosome;
	
	private Random random;
	
	public DJF1() {
		lengthOfChromosome = 30;
		random = new Random();
	}
	
	@Override
	public boolean[] generateGenes() {
		boolean[] genes = new boolean[lengthOfChromosome];
		for (int i = 0; i < genes.length; i++)
			genes[i] = random.nextBoolean();
		
		return genes;
	}

	@Override
	public long run(List<boolean[]> genesList, Scheme scheme, BenchmarkDataCollector dataCollector) {		
		AbstractChromosomeGenerator chromosomeGenerator = scheme.getChromosomeGenerator();
		chromosomeGenerator.resetIndex();
		chromosomeGenerator.setLengthOfChromosome(lengthOfChromosome);
		chromosomeGenerator.setGenesList(genesList);
		
		CustomIndividualGenerator individualGenerator = new CustomIndividualGenerator(chromosomeGenerator);
		CustomEvaluator evaluator = new CustomEvaluator();
		
		AbstractGeneticAlgorithm ga = scheme.getGeneticAlgorithmGenerator().generateGeneticAlgorithm(scheme.getPopulationSize(), scheme.getMaxGeneration(), scheme.getProbabilityOfCrossover(), scheme.getProbabilityOfMutation(), individualGenerator, evaluator, scheme.getSelection(), scheme.getCrossover(), scheme.getMutation());
		ga.setDataCollector(dataCollector);
		
		long startTime = System.currentTimeMillis();
		ga.run();
		long duration = System.currentTimeMillis() - startTime;
		
		return duration;
	}

	@Override
	public String getName() {
		return "DJF1";
	}
}
