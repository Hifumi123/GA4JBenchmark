package com.hifumi123.ga4j.benchmark;

import com.hifumi123.ga4j.chromosome.BinaryChromosome;
import com.hifumi123.ga4j.chromosome.Chromosome;

public class BinaryChromosomeGenerator extends AbstractChromosomeGenerator {

	@Override
	public Chromosome generateChromosome() {
		return new BinaryChromosome(genesList.get(indexOfGenesInList++));
	}
}
