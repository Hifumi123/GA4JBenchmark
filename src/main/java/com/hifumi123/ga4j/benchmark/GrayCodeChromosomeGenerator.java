package com.hifumi123.ga4j.benchmark;

import com.hifumi123.ga4j.chromosome.Chromosome;
import com.hifumi123.ga4j.chromosome.GrayCodeChromosome;

public class GrayCodeChromosomeGenerator extends AbstractChromosomeGenerator {
	
	@Override
	public Chromosome generateChromosome() {
		return new GrayCodeChromosome(lengthOfChromosome);
	}
}
