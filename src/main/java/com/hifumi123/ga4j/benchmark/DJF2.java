package com.hifumi123.ga4j.benchmark;

import java.util.List;
import java.util.Random;

import com.hifumi123.ga4j.AbstractGeneticAlgorithm;
import com.hifumi123.ga4j.examples.djf2.CustomEvaluator;
import com.hifumi123.ga4j.examples.djf2.CustomIndividualGenerator;

/**
 * 计算 De Jong 函数 F2（Rosenbrock 函数）的最大值。<br>
 * De Jong 函数 F2：<br>
 * f(x1, x2) = 100 * (x1^2-x2)^2 + (1 - x1)^2<br>
 * 条件为：<br>
 * -2.048 <= xi <= 2.048 (i = 1, 2)
 * 
 * @author Hifumi123
 *
 */
public class DJF2 implements Item {

	private int lengthOfChromosome;
	
	private Random random;
	
	public DJF2() {
		lengthOfChromosome = 20;
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
		return "DJF2";
	}
}
