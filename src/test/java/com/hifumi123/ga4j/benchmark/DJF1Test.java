package com.hifumi123.ga4j.benchmark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DJF1Test {

	@ParameterizedTest
	@ValueSource(ints = {10})
	public void generateIntTest(int n) {
		DJF1 djf1 = new DJF1();
		
		for (int i = 0; i < n; i++) {
			int x = djf1.generateInt();
			
			Assertions.assertTrue(x >= 0 && x < 1024);
		}
	}
}
