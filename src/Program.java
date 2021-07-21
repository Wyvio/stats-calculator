import java.awt.*;
import javax.swing.*;

public class Program {
	JFrame frame;
	Container contentPane;

	JTextField textFieldMean;
	JTextField textFieldStdev;
	JButton buttonSolve;

	public Program() {
		frame = new JFrame("Stats Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = frame.getContentPane();

		// Add text fields for all the parameters
		contentPane.add(new JLabel("Sample Mean"), BorderLayout.PAGE_START);
		contentPane.add(textFieldMean = new JTextField("", 20), BorderLayout.PAGE_START);
		textFieldMean.setVisible(true);
		contentPane.add(new JLabel("Sample Standard Deviation"));
		contentPane.add(textFieldStdev = new JTextField("", 20), BorderLayout.CENTER);
		textFieldStdev.setVisible(true);
		
		// Add the solve button
		contentPane.add(buttonSolve = new JButton("Solve"), BorderLayout.PAGE_END);

		// Sets the size of the frame
		frame.setBounds(0, 0, 500, 800); // this is unnecessary since I use pack()
		frame.pack();
		frame.setVisible(true);
	}
}
