package model;

import java.util.Random;

public class ModelImpl implements Model {

	private static boolean[] estratti;

	static {
		ModelImpl.estratti = new boolean[90];
	}

	@Override
	public void newGame() {
		for (int i = 0; i < 90; ++i) {
			ModelImpl.estratti[i] = false;
		}
	}

	@Override
	public void estrai() {

	}

	@Override
	public int getNewValue() {
		int variable = 0;
		for (int i = 0; i < 90; ++i) {
			if (!ModelImpl.estratti[i]) {
				++variable;
			}
		}
		if (variable > 0) {
			Random rand;
			int number;
			for (rand = new Random(), number = rand.nextInt(90); ModelImpl.estratti[number]; number = rand.nextInt(90)) {
			}
			ModelImpl.estratti[number] = true;
			variable = number + 1;
		} else {
			variable = -1;
		}
		
		return variable;
	}

}
