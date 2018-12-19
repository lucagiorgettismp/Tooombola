package controller;

import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;

public final class Application {
	
	public static void main(final String[] args) throws Exception {
		// application starter
		final ControllerImpl controller = new ControllerImpl();
		final View view = new ViewImpl();
		final Model model = new ModelImpl();
		
		view.setController(controller);
		controller.setView(view);
		controller.setModel(model);
		
		controller.start();
	}
}