package com.hifumi123.ga4j.benchmark;

import com.hifumi123.ga4j.chromosome.Chromosome;
import com.hifumi123.ga4j.chromosome.GrayCodeChromosome;

public class GrayCodeChromosomeGenerator extends AbstractChromosomeGenerator {
	
	private void binaryToGrayCode(boolean[] bin, boolean[] gray, int begin, int end) {
		//二进制码转成格雷码
		gray[begin] = bin[begin];
		for (int i = begin + 1; i < end; i++)
			gray[i] = bin[i - 1] ^ bin[i];
	}
	
	@Override
	public Chromosome generateChromosome() {
		int[] oxs = oxsList.get(indexOfOxsInList++);
		
		boolean[] genes = new boolean[oxs.length * lengthOfOxCode];
		
		for (int i = 0; i < oxs.length; i++) {
			String oxStr = Integer.toBinaryString(oxs[i]);
			
			for (int j = 0; j < oxStr.length(); j++) {
				int indexOfGenes = i * lengthOfOxCode + lengthOfOxCode - oxStr.length() + j;
				
				genes[indexOfGenes] = oxStr.charAt(j) == '1';
			}
		}
		
		boolean[] bs = new boolean[genes.length];
		for (int i = 0; i < oxs.length; i++) {
			int begin = lengthOfOxCode * i;
			int end = lengthOfOxCode * (i + 1);
			
			binaryToGrayCode(genes, bs, begin, end);
		}
		
		return new GrayCodeChromosome(bs);
	}
}
