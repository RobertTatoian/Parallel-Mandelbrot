package GUI;

import images.ImageManager;
import mandelbrot.ComplexNumber;
import mandelbrot.Parallel;
import mandelbrot.Serial;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


/**
 * Created by roberttatoian on 2/15/17.
 */
public class UserInterface implements ActionListener {

	private boolean isSerial = true;

	private JFrame frame;
	private JPanel rootPanel;
	private MandelbrotViewer mandelbrotViewer1;
	private JButton savePlot;
	private JButton zoomIn;
	private JButton zoomOut;
	private JButton plotButton;

	private ImageManager imageManager;

	public UserInterface(ImageManager imageManager) {

		JFrame.setDefaultLookAndFeelDecorated(true);

		this.imageManager = imageManager;

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


		//Auto-Created function by Intellij
		$$$setupUI$$$();

		frame.setContentPane(rootPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		frame.setJMenuBar(menuBar);

		zoomIn.setActionCommand("zoomin");
		zoomIn.addActionListener(this);

		zoomOut.setActionCommand("zoomout");
		zoomOut.addActionListener(this);

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
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			case "zoomin":
				mandelbrotViewer1.repaint();
				break;
			case "zoomout":
				mandelbrotViewer1.repaint();
				break;
			case "serial":
				isSerial = true;
				mandelbrotViewer1.repaint();
				break;
			case "parallel":
				isSerial = false;
				mandelbrotViewer1.repaint();
				break;
			case "save":
				mandelbrotViewer1.repaint();
				break;
			case "plot":

				if (isSerial) {
					serialImplementation();
				}
				else {
					parallelImplementation();
				}

				mandelbrotViewer1.repaint();
				break;
			default:
				System.err.println("There was an uncovered action command.");
				break;
		}

	}


	public boolean isSerial() {
		return isSerial;
	}

	private void serialImplementation() {
		long t = System.currentTimeMillis();

		Serial mandelbrot = new Serial(imageManager);

		mandelbrot.iterateMandelbrot(1, 1, 0, 0);

		System.out.println("Total time serial execution: " + (System.currentTimeMillis() - t));

		imageManager.writeImage();
	}

	private void parallelImplementation() {
		Parallel t1, t2, t3;

		int imageSlice = imageManager.getImageHeight() / 3;
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(imageSlice);


		t1 = new Parallel("daemon", imageManager, 0, 0, imageSlice, imageManager.getImageHeight());
		t2 = new Parallel("daemon", imageManager, imageSlice, 0, imageSlice * 2, imageManager.getImageHeight());
		t3 = new Parallel("daemon", imageManager, imageSlice * 2, 0, imageSlice * 3, imageManager.getImageHeight());


		long t = System.currentTimeMillis();

		t1.start();
		t2.start();
		t3.start();


		try {
			t1.join();
			t2.join();
			t3.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Total time parallel execution: " + (System.currentTimeMillis() - t));

		imageManager.setFinishedDrawingImage(false);

		BufferedImage finalImage
				= new BufferedImage(imageManager.getImageWidth(), imageManager.getImageHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(t1.slice, 0, 0, null);
		graphics.drawImage(t2.slice, 1, 0, null);
		graphics.drawImage(t3.slice, 2, 0, null);

		imageManager.setBufferedImage(finalImage);

		imageManager.setFinishedDrawingImage(true);

		imageManager.writeImage(finalImage);
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
		gbc.gridheight = 7;
		gbc.insets = new Insets(5, 5, 0, 0);
		rootPanel.add(mandelbrotViewer1, gbc);
		final JPanel spacer1 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(spacer1, gbc);
		final JPanel spacer2 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.VERTICAL;
		rootPanel.add(spacer2, gbc);
		zoomIn = new JButton();
		zoomIn.setMaximumSize(new Dimension(30, 27));
		zoomIn.setMinimumSize(new Dimension(28, 27));
		zoomIn.setPreferredSize(new Dimension(28, 27));
		zoomIn.setText("+");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(zoomIn, gbc);
		zoomOut = new JButton();
		zoomOut.setMaximumSize(new Dimension(30, 27));
		zoomOut.setMinimumSize(new Dimension(28, 27));
		zoomOut.setPreferredSize(new Dimension(28, 27));
		zoomOut.setText("-");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(zoomOut, gbc);
		savePlot = new JButton();
		savePlot.setText("Save Plot");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weighty = 0.25;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(savePlot, gbc);
		final JPanel spacer3 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weighty = 1.1;
		gbc.fill = GridBagConstraints.VERTICAL;
		rootPanel.add(spacer3, gbc);
		final JPanel spacer4 = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 0.17;
		gbc.fill = GridBagConstraints.VERTICAL;
		rootPanel.add(spacer4, gbc);
		plotButton = new JButton();
		plotButton.setText("Plot");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		rootPanel.add(plotButton, gbc);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return rootPanel;
	}
}
