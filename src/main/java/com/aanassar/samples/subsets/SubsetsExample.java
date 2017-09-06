package com.aanassar.samples.subsets;

import java.util.stream.LongStream;

public class SubsetsExample {
	
	private static LongStream recurse(int n, long accumulation, long start, int remaining) {
		if (remaining == 0) 
			return LongStream.of(accumulation);
		// This variable indicates potential bit positions.
		LongStream j = LongStream.rangeClosed (start, n - remaining);
		return j.flatMap(shift -> {
			final long flipped = 1L << shift;
			return recurse(n, accumulation | flipped, shift + 1, remaining - 1);
		});
	}

	public static LongStream create(int n, int k) {
		if (n < 0)
			throw new IllegalArgumentException("n must be >= 0.");
		if (n > Long.SIZE) 
			throw new IllegalArgumentException("n cannot be greater than the size of long integer.");
		return recurse(n, 0, 0, k);
	}
}
