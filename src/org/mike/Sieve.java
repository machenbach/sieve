package org.mike;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Iterator;

public class Sieve implements Iterable<Integer>, Iterator<Integer>{
	BitSet sv;
	int n;
	int ndx = 2;
	
	@Override
	public boolean hasNext() {
		for (int k = ndx; k < n; k++) {
			if (!sv.get(k)) return true;
		}
		return false;
	}

	@Override
	public Integer next() {
		// find the next prime number
		while (ndx < n && sv.get(ndx)) ndx ++;
		
		// now mark mulitples of this number as not prime
		for (int k = ndx; k < n; k += ndx) sv.set(k);
		
		return Integer.valueOf(ndx);
	}

	@Override
	public void remove() {
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return this;
	}

	public Sieve(int n) {
		this.n = n;
		// sv is initialized to all zeros
		sv = new BitSet(n);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 10000000;
		double logn = Math.log10(n);
		double t = Math.ceil(logn);
		int width =  (int)Math.ceil(Math.log10(n));
		String fmtString = String.format("%%%dd ", width);
		System.out.println("Spaces = " + Math.ceil(Math.log10(n)));
		try {
			PrintWriter p = new PrintWriter("primes.txt");
			Sieve s = new Sieve(n);
			
			int reset = 80 / (width + 1);
			int cnt = 0;
			long lineCount = 0;
			for (int i : s) {
				p.format(fmtString, i);
				if (cnt++ > reset) {
					p.println();
					cnt=0;
					lineCount++;
				}
			}
			p.close();

			System.out.println("Done with " + lineCount + " lines");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
