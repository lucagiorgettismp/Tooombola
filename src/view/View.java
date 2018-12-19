package view;

import controller.ControllerImpl;

public interface View {
	void setNewNumber(final int p0);

	void restartView();

	void showGameEndNotification();

	void initView();

	void setController(ControllerImpl controller);
}
