package GUI;

import images.ImageManager;
import main.FileManager;
import mandelbrot.Parallel;
import mandelbrot.Serial;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


/**
 * This class implements the GUI for the program. Note that there are methods which are created by the Intellij IDE in this class and they should not be altered.
 *
 * @author Robert Tatoian
 * @version 1.0
 * @since 02/15/2017
 */
public class UserInterface implements ActionListener {

	private boolean isSerial = true;
	private static final int imageWidth = 1000;
	private static final int imageHeight = 1000;

	private JFrame frame;
	private JPanel rootPanel;
	private MandelbrotViewer mandelbrotViewer1;
	private JButton savePlot;
	private JButton plotButton;
	private JTextField panXValue;
	private JTextField panYValue;
	private JTextField zoomValue;

	private ImageManager imageManager;

	public UserInterface() {

		imageManager = new ImageManager(imageWidth, imageHeight);

		JFrame.setDefaultLookAndFeelDecorated(true);

		frame = new JFrame("Serial Set");
		JMenuBar menuBar = new JMenuBar();

		//Adding functionality to the JMenuBar
		JMenu implementation = new JMenu("Implementation");
		menuBar.add(implementation);

		ButtonGroup implementationGroup = new ButtonGroup();
		JRadioButtonMenuItem serialItem = new JRadioButtonMenuItem("Serial");
		serialItem.setSelected(true);
		implementationGroup.add(serialItem);
		serialItem.setActionCommand("serial");
		serialItem.addActionListener(this);
		implementation.add(serialItem);

		JRadioButtonMenuItem parallelItem = new JRadioButtonMenuItem("Parallel");
		parallelItem.setSelected(false);
		implementationGroup.add(parallelItem);
		parallelItem.setActionCommand("parallel");
		parallelItem.addActionListener(this);
		implementation.add(parallelItem);

		JMenu size = new JMenu("Image Size");
		menuBar.add(size);

		ButtonGroup sizeGroup = new ButtonGroup();
		JRadioButtonMenuItem oneThousand = new JRadioButtonMenuItem("1000 x 1000");
		oneThousand.setSelected(true);
		sizeGroup.add(oneThousand);
		oneThousand.setActionCommand("1000");
		oneThousand.addActionListener(this);
		size.add(oneThousand);

		JRadioButtonMenuItem twoThousand = new JRadioButtonMenuItem("2000 x 2000");
		twoThousand.setSelected(true);
		sizeGroup.add(twoThousand);
		twoThousand.setActionCommand("2000");
		twoThousand.addActionListener(this);
		size.add(twoThousand);

		JRadioButtonMenuItem fiveThousand = new JRadioButtonMenuItem("5000 x 5000");
		fiveThousand.setSelected(true);
		sizeGroup.add(fiveThousand);
		fiveThousand.setActionCommand("5000");
		fiveThousand.addActionListener(this);
		size.add(fiveThousand);

		JRadioButtonMenuItem tenThousand = new JRadioButtonMenuItem("6000 x 6000");
		tenThousand.setSelected(true);
		sizeGroup.add(tenThousand);
		tenThousand.setActionCommand("6000");
		tenThousand.addActionListener(this);
		size.add(tenThousand);

		JRadioButtonMenuItem eightThousand = new JRadioButtonMenuItem("8000 x 8000");
		eightThousand.setSelected(true);
		sizeGroup.add(eightThousand);
		eightThousand.setActionCommand("8000");
		eightThousand.addActionListener(this);
		size.add(eightThousand);

		//Auto-Created function by Intellij
		$$$setupUI$$$();

		frame.setContentPane(rootPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		frame.setJMenuBar(menuBar);

		savePlot.setActionCommand("save");
		savePlot.addActionListener(this);

		plotButton.setActionCommand("plot");
		plotButton.addActionListener(this);
	}

	private void createUIComponents() {
		mandelbrotViewer1 = new MandelbrotViewer(imageManager);
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param e - The action event sent.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			case "serial":
				isSerial = true;
				mandelbrotViewer1.repaint();
				break;
			case "parallel":
				isSerial = false;
				mandelbrotViewer1.repaint();
				break;
			case "save":
				imageManager.writeImage();
				mandelbrotViewer1.repaint();
				break;
			case "plot":

				for (int i = 0; i < 60; i++) {
					if (isSerial) {
						serialImplementation();
					}
					else {
						parallelImplementation();
					}
				}
				mandelbrotViewer1.repaint();
				break;
			case "1000":

				try {
					imageManager.setDimensions(1000, 1000);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				mandelbrotViewer1.repaint();
				break;
			case "2000":

				try {
					imageManager.setDimensions(2000, 2000);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				mandelbrotViewer1.repaint();

				break;
			case "5000":

				try {
					imageManager.setDimensions(5000, 5000);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				mandelbrotViewer1.repaint();

				break;
			case "6000":

				try {
					imageManager.setDimensions(6000, 6000);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				mandelbrotViewer1.repaint();

				break;
			case "8000":

				try {
					imageManager.setDimensions(8000, 8000);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

				mandelbrotViewer1.repaint();

				break;

			default:
				System.err.println("There was an uncovered action command.");
				break;
		}

	}

	private double parseInput(String sender, String input) {

		double number;

		if (sender.equals("zoomValue")) {
			number = 1;
		}
		else {
			number = 0;
		}

		try {
			number = Double.parseDouble(input);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "The input entered into the text field is not a number, using default value.", "Invalid Number", JOptionPane.WARNING_MESSAGE);
		}

		return number;
	}

	private void serialImplementation() {

		FileManager serialWriter = new FileManager("Serial Times 2000.txt");

		long t = System.currentTimeMillis();

		Serial mandelbrot = new Serial(imageManager);

		mandelbrot.iterateMandelbrot(parseInput(zoomValue.getName(), zoomValue.getText()), parseInput(panXValue.getName(), panXValue.getText()), parseInput(panYValue.getName(), panYValue.getText()));

		serialWriter.writeFile(Math.toIntExact((System.currentTimeMillis() - t)));

	}

	private void parallelImplementation() {

		FileManager parallelWriter = new FileManager("Parallel Times 2000.txt");

		Parallel t1, t2, t3, t4, t5, t6;

		int imageSlice = imageManager.getImageHeight() / 3;

		Parallel.setScale(parseInput(zoomValue.getName(), zoomValue.getText()));
		Parallel.setPanX(parseInput(panXValue.getName(), panXValue.getText()));
		Parallel.setPanY(parseInput(panYValue.getName(), panYValue.getText()));

		t1 = new Parallel("daemon", imageManager, 0, 0, imageSlice, imageManager.getImageHeight() / 2);
		t2 = new Parallel("daemon", imageManager, imageSlice, 0, imageSlice * 2, imageManager.getImageHeight() / 2);
		t3 = new Parallel("daemon", imageManager, imageSlice * 2, 0, imageSlice * 3, imageManager.getImageHeight() / 2);

		t4
				= new Parallel("daemon", imageManager, 0, imageManager.getImageHeight() / 2, imageSlice, imageManager.getImageHeight());
		t5
				= new Parallel("daemon", imageManager, imageSlice, imageManager.getImageHeight() / 2, imageSlice * 2, imageManager.getImageHeight());
		t6
				= new Parallel("daemon", imageManager, imageSlice * 2, imageManager.getImageHeight() / 2, imageSlice * 3, imageManager.getImageHeight());

		long t = System.currentTimeMillis();

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		parallelWriter.writeFile(Math.toIntExact((System.currentTimeMillis() - t)));

		imageManager.setFinishedDrawingImage(false);

		BufferedImage finalImage
				= new BufferedImage(imageManager.getImageWidth(), imageManager.getImageHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(t1.slice, 0, 0, null);
		graphics.drawImage(t2.slice, 0, 0, null);
		graphics.drawImage(t3.slice, 0, 0, null);
		graphics.drawImage(t4.slice, 0, 0, null);
		graphics.drawImage(t5.slice, 0, 0, null);
		graphics.drawImage(t6.slice, 0, 0, null);

		imageManager.setBufferedImage(finalImage);

		imageManager.setFinishedDrawingImage(true);
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		createUIComponents();
		rootPanel = new JPanel();
		rootPanel.setLayout(new GridBagLayout());
		rootPanel.setMaximumSize(new Dimension(1024, 1024));
		rootPanel.setMinimumSize(new Dimension(400, 325));
		rootPanel.setOpaque(false);
		rootPanel.setPreferredSize(new Dimension(512, 512));
		mandelbrotViewer1.setMaximumSize(new Dimension(450, 450));
		mandelbrotViewer1.setMinimumSize(new Dimension(300, 250));
		mandelbrotViewer1.setPreferredSize(new Dimension(400, 400));
		mandelbrotViewer1.setRequestFocusEnabled(true);
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 8;
		gbc.insets = new Insets(5, 5, 0, 0);
		rootPanel.add(mandelbrotViewer1, gbc);
		final JPanel spacer1 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.fill = GridBagConstraints.VERTICAL;
		rootPanel.add(spacer1, gbc);
		savePlot = new JButton();
		savePlot.setText("Save Plot");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(savePlot, gbc);
		plotButton = new JButton();
		plotButton.setText("Plot");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(plotButton, gbc);
		final JLabel label1 = new JLabel();
		label1.setText("Pan on Real:");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		rootPanel.add(label1, gbc);
		final JLabel label2 = new JLabel();
		label2.setText("Zoom:");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		rootPanel.add(label2, gbc);
		panXValue = new JTextField();
		panXValue.setName("panX");
		panXValue.setText("-0.2");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(panXValue, gbc);
		final JLabel label3 = new JLabel();
		label3.setText("Pan on Imaginary:");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		rootPanel.add(label3, gbc);
		panYValue = new JTextField();
		panYValue.setName("panY");
		panYValue.setText("0.01");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(panYValue, gbc);
		zoomValue = new JTextField();
		zoomValue.setName("zoomValue");
		zoomValue.setText("1.00");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(zoomValue, gbc);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return rootPanel;
	}
}
