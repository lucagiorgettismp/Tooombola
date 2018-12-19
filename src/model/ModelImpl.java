package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelImpl implements Model {
	
	private List<Integer> numbers = new ArrayList<>();
	
	@Override
	public void newGame() {
		numbers.clear();
		
		for (int i = 1; i <= 90; i++) {
			numbers.add(i);
		}
		
		Collections.shuffle(numbers);
	}

	@Override
	public int getNewValue() {
		int number = -1;
		
		if (numbers.size() != 0) {
			number = numbers.get(0);
			numbers.remove(0);
		}

		return number;
	}

}
