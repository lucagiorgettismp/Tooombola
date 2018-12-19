package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.ControllerImpl;


public class ViewImpl implements View{
	private ControllerImpl controller;
	
	private static final JFrame mainFrame = new JFrame();
	private static JLabel txtTombolaByGiobbe = new JLabel();
	int riga;
	int colonna;
	static final JLabel[][] matrix = new JLabel[9][10];
	static final JLabel[] old = new JLabel[3];
	static final JLabel numEstratto = new JLabel();
	static final Color START_COLOR = new Color(13, 141, 43);
	static final Color START_FORE = new Color(5, 74, 22);
	static final Color EXTRACTED_COLOR = Color.YELLOW;;
	static final Color BACK_COLOR = new Color(30, 169, 78);
	static final Color LABEL_COLOR = new Color(251, 242, 128);
	static final Color BTN_COLOR = new Color(253, 220, 84);
	static final Color BTN_BORDER_COLOR = new Color(206, 141, 44);


	@Override
	public void setNewNumber(final int number) {
		for (int r = 0; r < 9; ++r) {
			for (int c = 0; c < 10; ++c) {
				if (Integer.parseInt(ViewImpl.matrix[r][c].getText()) == number) {
					ViewImpl.matrix[r][c].setBackground(ViewImpl.EXTRACTED_COLOR);
					ViewImpl.matrix[r][c].setForeground(Color.BLACK);
				}
			}
		}
		ViewImpl.old[2].setText(ViewImpl.old[1].getText());
		ViewImpl.old[1].setText(ViewImpl.old[0].getText());
		ViewImpl.old[0].setText(ViewImpl.numEstratto.getText());
		ViewImpl.numEstratto.setText(String.valueOf(number));
	}

	@Override
	public void restartView() {
		for (int r = 0; r < 9; ++r) {
			for (int c = 0; c < 10; ++c) {
				ViewImpl.matrix[r][c].setBackground(ViewImpl.START_COLOR);
				ViewImpl.matrix[r][c].setForeground(ViewImpl.START_FORE);
			}
		}
		for (int i = 0; i < 3; ++i) {
			ViewImpl.old[i].setText("");
			ViewImpl.numEstratto.setText("");
		}
	}

	@Override
	public void showGameEndNotification() {
		final JOptionPane optionPane = new JOptionPane("Sono stati estratti tutti i numeri.\nNon giocava nessuno?\n",
				JOptionPane.WARNING_MESSAGE);

		final JDialog dialog = new JDialog(mainFrame, "Fine partita", true);

		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();

				if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
					dialog.setVisible(false);
				}
			}
		});
		dialog.pack();
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setVisible(true);

		int value = ((Integer) optionPane.getValue()).intValue();
		if (value == JOptionPane.OK_OPTION) {
			dialog.setVisible(false);
			controller.newGame();
		}
	}

	public void richiestaConferma() {
		final JOptionPane optionPane = new JOptionPane("Vuoi iniziare una nuova tombola?", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION);

		final JDialog dialog = new JDialog(mainFrame, "Nuova tombola", true);

		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();

				if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
					dialog.setVisible(false);
				}
			}
		});
		dialog.pack();
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setVisible(true);

		int value = ((Integer)optionPane.getValue()).intValue();
		if (value == JOptionPane.YES_OPTION) {
			dialog.setVisible(false);
			controller.newGame();
		} else if (value == JOptionPane.NO_OPTION) {
			dialog.setVisible(false);
		}
	}
	
	@Override
	public void initView() {
		final JPanel mainPanel = new JPanel();
		
		final JPanel titolo = new JPanel();
		titolo.setBounds(12, 15, 1000, 102);
		
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.getContentPane().add(mainPanel, "Center");
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		mainPanel.setLayout(null);
		mainPanel.add(titolo);
		mainPanel.setOpaque(true);
		mainPanel.setBackground(ViewImpl.BACK_COLOR);
		
		final Icon image = new ImageIcon(ViewImpl.class.getResource("logo.png"));
		
		txtTombolaByGiobbe = new JLabel(image);
		
		titolo.setOpaque(true);
		titolo.setBackground(ViewImpl.BACK_COLOR);
		titolo.add(txtTombolaByGiobbe);
		
		final JPanel cartellone = new JPanel();
		cartellone.setBounds(27, 130, 663, 702);
		mainPanel.add(cartellone);
		
		final GridLayout gl_cartellone = new GridLayout(9, 10);
		gl_cartellone.setVgap(5);
		gl_cartellone.setHgap(5);
		cartellone.setLayout(gl_cartellone);
		cartellone.setOpaque(true);
		cartellone.setBackground(ViewImpl.BACK_COLOR);
		
		final JButton btnEstrai = new JButton("Estrai");
		btnEstrai.setFont(new Font("Gill Sans MT", 3, 45));
		btnEstrai.setBounds(718, 657, 270, 78);
		btnEstrai.setOpaque(true);
		btnEstrai.setBackground(ViewImpl.BTN_COLOR);
		btnEstrai.setBorder(BorderFactory.createLineBorder(ViewImpl.BTN_BORDER_COLOR, 3, true));
		mainPanel.add(btnEstrai);
		
		final JButton btnReset = new JButton("Nuova Tombola");
		btnReset.setFont(new Font("Gill Sans MT", 1, 20));
		btnReset.setBounds(718, 783, 270, 46);
		btnReset.setBackground(ViewImpl.BTN_COLOR);
		btnReset.setBorder(BorderFactory.createLineBorder(ViewImpl.BTN_BORDER_COLOR, 3, true));
		mainPanel.add(btnReset);
		
		final JLabel info = new JLabel("Author: Luca Giorgetti, 2016");
		info.setFont(new Font("Gill Sans MT", 0, 18));
		info.setForeground(Color.WHITE);
		info.setBounds(802, 842, 219, 22);
		mainPanel.add(info);
		
		ViewImpl.numEstratto.setBackground(Color.WHITE);
		ViewImpl.numEstratto.setForeground(Color.BLACK);
		ViewImpl.numEstratto.setOpaque(true);
		ViewImpl.numEstratto.setHorizontalAlignment(0);
		ViewImpl.numEstratto.setFont(new Font("Tahoma", 0, 180));
		ViewImpl.numEstratto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		ViewImpl.numEstratto.setBounds(719, 130, 270, 221);
		mainPanel.add(ViewImpl.numEstratto);
		
		for (int i = 0; i < 3; ++i) {
			(ViewImpl.old[i] = new JLabel("")).setOpaque(true);
			ViewImpl.old[i].setHorizontalAlignment(0);
			ViewImpl.old[i].setFont(new Font("Tahoma", 0, 50));
			ViewImpl.old[i].setBackground(ViewImpl.LABEL_COLOR);
			ViewImpl.old[i].setForeground(Color.BLACK);
			ViewImpl.old[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			mainPanel.add(ViewImpl.old[i]);
		}
		
		ViewImpl.old[0].setBounds(812, 364, 89, 84);
		ViewImpl.old[1].setBounds(812, 463, 89, 84);
		ViewImpl.old[2].setBounds(812, 560, 89, 84);
		
		mainFrame.setDefaultCloseOperation(3);
		mainFrame.setSize(1024, 900);
		mainFrame.setResizable(true);
		mainFrame.setTitle("Tombola");
		
		for (int r = 0; r < 9; ++r) {
			for (int c = 0; c < 10; ++c) {
				(ViewImpl.matrix[r][c] = new JLabel()).setText(String.valueOf(r * 10 + (c + 1)));
				ViewImpl.matrix[r][c].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				ViewImpl.matrix[r][c].setFont(new Font("Tahoma", 1, 40));
				cartellone.add(ViewImpl.matrix[r][c]);
				ViewImpl.matrix[r][c].setBackground(ViewImpl.START_COLOR);
				ViewImpl.matrix[r][c].setOpaque(true);
				ViewImpl.matrix[r][c].setHorizontalAlignment(0);
				ViewImpl.matrix[r][c].setForeground(ViewImpl.START_FORE);
			}
		}
		
		mainFrame.setVisible(true);
		mainFrame.getRootPane().setDefaultButton(btnEstrai);
		mainFrame.getRootPane().getInputMap(2).put(KeyStroke.getKeyStroke(10, 0), "clickButton");
		btnEstrai.addActionListener(e -> controller.extract());
		btnReset.addActionListener(e -> richiestaConferma());
		mainFrame.getRootPane().getActionMap().put("clickButton", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent ae) {
				btnEstrai.doClick();
			}
		});
		
	}

	@Override
	public void setController(ControllerImpl controller) {
		this.controller = controller;
	}
	
}
