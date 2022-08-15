package com.hifumi123.ga4j.benchmark;

import java.util.List;

import com.hifumi123.ga4j.examples.ChromosomeGenerator;

public abstract class AbstractChromosomeGenerator implements ChromosomeGenerator {
	
	protected List<boolean[]> genesList;

	protected int lengthOfChromosome;
	
	protected int indexOfGenesInList;
	
	public void resetIndex() {
		indexOfGenesInList = 0;
	}

	public void setGenesList(List<boolean[]> genesList) {
		this.genesList = genesList;
	}

	public void setLengthOfChromosome(int lengthOfChromosome) {
		this.lengthOfChromosome = lengthOfChromosome;
	}
}
