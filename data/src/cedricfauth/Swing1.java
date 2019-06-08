/*
Copyright © 2017 Cedric Fauth

    Swing1.java is part of CaTra.

    CaTra is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CaTra is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CaTra.  If not, see <http://www.gnu.org/licenses/>.

*/

package cedricfauth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;


public class Swing1 extends JFrame {
	
	/**
	 * testing
	 */
	private static final long serialVersionUID = -8194450516192069288L;
	
	private JTextArea console = new JTextArea();
	private Timer t1 = null;
	private int i = 100;
	private String e = "";
	private int fails;
	private int score;


	private boolean startCalc = false;
	
	public Swing1(){
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("CaTra");
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());
		initComponents();
	}
	
	private void initComponents(){
		
		ArrayList<String> lastCommands = new ArrayList<>();
		JProgressBar progress = new JProgressBar(0, 100); 
		JTextField input = new JTextField("");
		
		/*
		Alt + 60 <
		Alt + 62 >
		Alt + 124 |
		 */
		progress.setValue(100);
		t1 = new Timer(250, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i >= 0){
					if(i >= 80){
						progress.setForeground(new Color(0, 219, 7));
					}else if(i >= 60){
						progress.setForeground(new Color(131, 227, 0));
					}else if(i >= 40){
						progress.setForeground(new Color(247, 239, 0));
					}else if(i >= 20){
						progress.setForeground(new Color(238, 155, 0));
					}else if(i >= 00){
						progress.setForeground(new Color(231, 0, 0));
					}
					progress.setValue(i);
					i--;
				}else{
					((Timer) e.getSource()).stop();
					console.setText(console.getText() + "\n-----------------Ende-----------------\n\n\n");
					i = 100;
					progress.setValue(0);
					startCalc = false;
					console.setText(console.getText() + "Score: " + score + "      Wrongs: " + fails);
				}
			}
		});
		
		Action ActionSetText = new AbstractAction() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 5577435280227237830L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!input.getText().equals("")){
					lastCommands.add(input.getText());
					console.setText(console.getText() + "\n" + input.getText());
					inputCommand(input.getText());
					//size1 = lastCommands.size();
				}
				input.setText("");
			}
		};
		
		ActionListener ActionExitItem = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		};
		
		//ContentPane
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		//Menu
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		JMenu menu2 = new JMenu("View");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem openCMD = new JMenuItem("See commands");
		
		menu1.add(exitItem);
		exitItem.addActionListener(ActionExitItem);
		menu2.add(openCMD);
		menubar.add(menu1);
		//menubar.add(menu2);
		
		//Console
		JPanel consolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		console.setFont(new Font("Arial", Font.BOLD, 14));
		console.setForeground(new Color(0, 255, 0));
		console.setBackground(Color.BLACK);
		console.setEditable(false);
		console.setPreferredSize(new Dimension(668, 10000));
		//Scrollpane
		JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//South
		JPanel bottomPanel = new JPanel();
		JLabel footerText = new JLabel("Copyright © 2017 Cedric Fauth");
		footerText.setHorizontalAlignment(SwingConstants.RIGHT);
		footerText.setPreferredSize(new Dimension(680, 18));
		footerText.setForeground(Color.WHITE);
		footerText.setFont(new Font("Arial", Font.BOLD, 10));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
		bottomPanel.setBackground(Color.GRAY);
		bottomPanel.setPreferredSize(new Dimension(400, 52));
		//input.addKeyListener(KeyListenerUp);
		input.setPreferredSize(new Dimension(692, 30));
		input.addActionListener(ActionSetText);
		input.setOpaque(true);
		input.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		input.setFont(new Font("Arial", Font.BOLD, 14));
		input.setForeground(new Color(0, 255, 0));
		input.setBackground(Color.BLACK);
		bottomPanel.add(input);
		bottomPanel.add(footerText);
		
		//adding components
		setJMenuBar(menubar);
		pane.add(progress, BorderLayout.NORTH);
		pane.add(consolePanel, BorderLayout.CENTER);
		pane.add(bottomPanel, BorderLayout.SOUTH);
		pane.add(scroll);
		
		console.setText("\n\n Calculation Training\n\n\n\n   <start>    <clear>\n");
	}

	private void inputCommand(String text) {
		if(startCalc){
			if(text.equals(e)) {
				if(i+20 <= 100){
					i += 20;
				}else{
					i = 100;
				}
				score++;
				console.setText(console.getText() + "\nRIGHT\n\n");
			} else {
				fails++;
				console.setText(console.getText() + "\nWRONG\n\n");
				i -= 20;
			}
			e = Integer.toString(calculateQuest());
		}
		
		if (text.equals("clear")) {
			console.setText("");
		} else if(text.equals("start")) {
			score = 0;
			fails = 0;
			console.setText("");
			e = Integer.toString(calculateQuest());
			t1.start();
			startCalc = true;
		}
		
	}
	
	private int calculateQuest(){
		Random rand = new Random();
		Integer[] randomNumbers = new Integer[5];
		String term = "";
		int solution = 0;
		for (int i = 0; i < randomNumbers.length; i++) {
			randomNumbers[i] = rand.nextInt(10) + 1;
		}
		for(int j = 0; j <= rand.nextInt(4) + 1; j++){
			term += Integer.toString(randomNumbers[j]) + " + ";
			solution += randomNumbers[j]; 
		}
		//System.out.println(term);
		console.setText(console.getText() + "\n" + term);
		return solution;
	}

}
