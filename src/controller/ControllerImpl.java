package controller;

import model.Model;
import view.View;

public class ControllerImpl {
	
	private View view;
	private Model model;

	public void start() {			
		view.initView();
		model.newGame();
	}

	public void newGame() {
		model.newGame();
		view.restartView();
	}

	public void extract() {
		int number = model.getNewValue();
		if (number > 0) {
			view.setNewNumber(number);
		} else {
			view.showGameEndNotification();
		}

	}

	public void setModel(Model model) {
		this.model = model;	
	}
	
	public void setView(View view) {
		this.view = view;	
	}

}
