package com.hifumi123.ga4j.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.hifumi123.ga4j.chromosome.GrayCodeChromosome;

public class GrayCodeChromosomeGeneratorTest {
	
	@ParameterizedTest
	@MethodSource
	public void generateChromosomeTest(List<int[]> oxsList, int lengthOfOxCode, int[] begins, int[] ends, int[] result) {
		GrayCodeChromosomeGenerator gccg = new GrayCodeChromosomeGenerator();
		gccg.resetIndex();
		gccg.setLengthOfOxCode(lengthOfOxCode);
		gccg.setOxsList(oxsList);
		
		GrayCodeChromosome chromosome = (GrayCodeChromosome) gccg.generateChromosome();
		
		for (int i = 0; i < result.length; i++) {
			int begin = begins[i];
			int end = ends[i];
			
			Assertions.assertEquals(result[i], chromosome.decode(begin, end));
		}
	}

	private static Stream<Arguments> generateChromosomeTest() {
		Random random = new Random();
		
		int lengthOfOxCode = 10;
		
		int[] oxs = new int[3];
		for (int i = 0; i < oxs.length; i++)
			oxs[i] = random.nextInt((int) Math.pow(2, lengthOfOxCode));
		
		List<int[]> oxsList = new ArrayList<int[]>();
		oxsList.add(oxs);
		
		int[] begins = {0, 10, 20};
		int[] ends = {10, 20, 30};
		
		return Stream.of(Arguments.of(oxsList, lengthOfOxCode, begins, ends, oxs));
	}
}
