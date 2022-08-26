package com.hifumi123.ga4j.benchmark;

import com.hifumi123.ga4j.chromosome.BinaryChromosome;
import com.hifumi123.ga4j.chromosome.Chromosome;

public class BinaryChromosomeGenerator extends AbstractChromosomeGenerator {

	@Override
	public Chromosome generateChromosome() {
		int[] oxs = oxsList.get(indexOfOxsInList++);
		
		boolean[] genes = new boolean[oxs.length * lengthOfOxCode];
		
		for (int i = 0; i < oxs.length; i++) {
			String oxStr = Integer.toBinaryString(oxs[i]);
			
			int begin = i * lengthOfOxCode + lengthOfOxCode - oxStr.length();
			
			for (int j = 0; j < oxStr.length(); j++) {
				int indexOfGenes = begin + j;
				
				genes[indexOfGenes] = oxStr.charAt(j) == '1';
			}
		}
		
		return new BinaryChromosome(genes);
	}
}
