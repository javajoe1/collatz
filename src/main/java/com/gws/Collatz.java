package com.gws;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class Collatz {
	private static Logger logger = Logger.getLogger(Collatz.class.getName());
	private static Map<Integer, List<Integer>> map = new TreeMap<>();
	private static int current;
	private static List<Integer> stack;

	public static void main(String... args) {
		int max = 100;

		if (args.length != 0) {
			max = Integer.parseInt(args[0]);
		}
		logger.info("Trying to solve the Collatz conjecture for " + max + " ...");
		for (int i = 1; i < max; i++) {
			current = i;
			stack = new LinkedList<Integer>();
			stack.add(i);
			solve(i);
		}
		int largest = 0;
		int largestKey = 0;
		int shortest = Integer.MAX_VALUE;
		int shortestKey = 0;
		for (Integer key : map.keySet()) {
			// logger.info(key + " size " + map.get(key).size() + " => " +
			// StringUtils.join(map.get(key), ","));
			if (map.get(key).size() > largest) {
				largest = map.get(key).size();
				largestKey = key;
			}
			if (map.get(key).size() < shortest) {
				shortest = map.get(key).size();
				shortestKey = key;
			}
		}
		logger.info(
				"Shortest was " + shortestKey + " at " + shortest + " => " + StringUtils.join(map.get(shortestKey)));
		logger.info("Largest  was " + largestKey + " at " + largest + " => " + StringUtils.join(map.get(largestKey)));
	}

	private static void solve(int i) {
		int next;
		if (i % 2 == 0) {
			next = i / 2;
		} else {
			next = (i * 3) + 1;
		}
		if (next == 1) {
			stack.add(next);
			map.put(current, stack);
			stack = new LinkedList<Integer>();
		} else {
			// TODO some logic to see if we have found this cycle
			stack.add(next);
			solve(next);
		}
	}
}
