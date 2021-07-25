import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;

public class Program {
	JFrame frame;
	Container contentPane;
	JPanel panel;
	GridBagConstraints constraints;

	JTextField textFieldMean;
	JTextField textFieldStdev;
	JTextField textFieldSize;
	JTextField textFieldNull;
	JTextField textFieldAlt;
	JTextField textFieldSig;
	JButton buttonSolve;

	JTextArea log;
	JScrollPane logScrollPane;
	JButton buttonClear;
	
	Instant time;

	public Program() {
	}

	public void init(Distribution dist) {
		frame = new JFrame("Stats Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = frame.getContentPane();
		contentPane.add(panel = new JPanel(new GridBagLayout()));
		constraints = new GridBagConstraints();

		// Add text fields for all the parameters (left column)
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0.5;
		panel.add(new JLabel("Sample Mean"), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(textFieldMean = new JTextField("", 5), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.5;
		panel.add(new JLabel("Sample Standard Deviation"), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(textFieldStdev = new JTextField("", 5), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 0.5;
		panel.add(new JLabel("Sample Size"), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(textFieldSize = new JTextField("", 5), constraints);

		// Add small border between columns
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 2;
		constraints.gridy = 0;
		panel.add(new JLabel("  "), constraints);

		// Add text fields for all the parameters (right column)
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.weightx = 0.5;
		panel.add(new JLabel("Null Hypothesis"), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 4;
		constraints.gridy = 0;
		panel.add(textFieldNull = new JTextField("", 5), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.weightx = 0.5;
		panel.add(new JLabel("Alternate Hypothesis"), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 4;
		constraints.gridy = 1;
		panel.add(textFieldAlt = new JTextField("", 5), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.weightx = 0.5;
		panel.add(new JLabel("Significance Level"), constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 4;
		constraints.gridy = 2;
		panel.add(textFieldSig = new JTextField("0.05", 5), constraints);

		// Add the solve button
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(20, 0, 0, 0);
		constraints.ipady = 20;
		constraints.gridx = 0;
		constraints.gridwidth = 5;
		constraints.gridy = 4;
		panel.add(buttonSolve = new JButton("Solve"), constraints);
		buttonSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					log.append("SUCCESS: computing...\n");
					dist.setSampleMean(Double.parseDouble(textFieldMean.getText()));
					dist.setSampleStdev(Double.parseDouble(textFieldStdev.getText()));
					dist.setSampleSize(Integer.parseInt(textFieldSize.getText()));
					dist.setNullHypothesis(Double.parseDouble(textFieldNull.getText()));
					dist.setAltHypothesis(Integer.parseInt(textFieldAlt.getText()));
					dist.setSignificanceLevel(Double.parseDouble(textFieldSig.getText()));
					dist.solve();
					log.append("OUTPUT: " + dist.getConclusion() + "\n");
					log.append("SUCCESS: computed!\n");
				} catch (NumberFormatException ne) {
					log.append("ERROR: one or more parameters blank/incorrect type, cannot compute\n");
				} catch (Exception ee) {
					log.append("ERROR: an unknown error has occurred, cannot compute\n");
				}
			}
		});
		
		// Add a clear log button
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(20, 0, 0, 0);
		constraints.ipady = 0;
		constraints.gridx = 3;
		constraints.gridwidth = 1;
		constraints.gridy = 4;
		panel.add(buttonClear = new JButton("Clear"), constraints);
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.setText("");
			}
		});

		// Add output/log area area
		log = new JTextArea(20, 50);
		logScrollPane = new JScrollPane(log);
		log.setEditable(false);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(10, 0, 0, 0);
		constraints.ipady = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 5;
		constraints.gridy = 5;
		panel.add(logScrollPane, constraints);

		// Sets the size of the frame
		frame.setBounds(0, 0, 500, 800); // this is unnecessary since I use pack()
		frame.pack();
		frame.setVisible(true);
	}
}
