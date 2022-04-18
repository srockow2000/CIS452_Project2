package CIS452_Project2;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.GridLayout;

// Java implementation of First - Fit algorithm
class GFG{
	// Method to allocate memory to
	// blocks as per First fit algorithm
	static void firstFit(int blockSize[], int m,
						int processSize[], int n){
		// Stores block id of the
		// block allocated to a process
		int allocation[] = new int[n];
	
		// Initially no block is assigned to any process
		for (int i = 0; i < allocation.length; i++)
			allocation[i] = -1;
	
		// pick each process and find suitable blocks
		// according to its size ad assign to it
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				if (blockSize[j] >= processSize[i]){
					// allocate block j to p[i] process
					allocation[i] = j;
	
					// Reduce available memory in this block.
					blockSize[j] -= processSize[i];
	
					break; //ends loop after allocation
				}
			}
		}
	
		System.out.println("\nProcess No.\tProcess Size\tBlock no.");
		for (int i = 0; i < n; i++){
			System.out.print(" " + (i+1) + "\t\t" +
							processSize[i] + "\t\t");
			if (allocation[i] != -1)
				System.out.print(allocation[i] + 1);
			else
				System.out.print("Not Allocated");
			System.out.println();
		}
	}
	
	// Driver Code
	public static void main(String[] args){
		int blockSize[] = {100, 500, 200, 300, 600};
		int processSize[] = {212, 417, 112, 426};
		int m = blockSize.length;
		int n = processSize.length;
		
		firstFit(blockSize, m, processSize, n);
		
		/** GUI Parts */
		JFrame frame = new JFrame("Project 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,800);

		JPanel pj = new JPanel();
		pj.setLayout(new GridLayout());
		frame.getContentPane().add(pj); //not visible

		//borders
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);	

		//JLabels
		JLabel title = new JLabel("Memory Management");
		title.setBounds(400, 25, 225, 50);
		title.setBorder(border);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBackground(Color.PINK);
		title.setOpaque(true);
		frame.getContentPane().add(title);

		JLabel lab = new JLabel("First Fit");
		lab.setBounds(200, 700, 150, 50);
		lab.setBorder(border);
		lab.setHorizontalAlignment(JLabel.CENTER);
		lab.setBackground(Color.PINK);
		lab.setOpaque(true);
		frame.getContentPane().add(lab);


		JLabel bes = new JLabel("Best Fit");
		bes.setBounds(400, 700, 150, 50);
		bes.setBorder(border);
		bes.setHorizontalAlignment(JLabel.CENTER);
		bes.setBackground(Color.PINK);
		bes.setOpaque(true);
		frame.getContentPane().add(bes);


		JLabel wor = new JLabel("Worst Fit");
		wor.setBounds(600, 700, 150, 50);
		wor.setBorder(border);
		wor.setHorizontalAlignment(JLabel.CENTER);
		wor.setBackground(Color.PINK);
		wor.setOpaque(true);
		frame.getContentPane().add(wor);

		JLabel proNum = new JLabel("Process Number");
		proNum.setBounds(25, 100, 150, 50);
		proNum.setBorder(border);
		proNum.setHorizontalAlignment(JLabel.CENTER);
		proNum.setBackground(Color.LIGHT_GRAY);
		proNum.setOpaque(true);
		frame.getContentPane().add(proNum);

		JLabel proSize = new JLabel("Process Size");
		proSize.setBounds(25, 300, 150, 50);
		proSize.setBorder(border);
		proSize.setHorizontalAlignment(JLabel.CENTER);
		proSize.setBackground(Color.LIGHT_GRAY);
		proSize.setOpaque(true);
		frame.getContentPane().add(proSize);

		JLabel block = new JLabel("Block Number");
		block.setBounds(25, 500, 150, 50);
		block.setBorder(border);
		block.setHorizontalAlignment(JLabel.CENTER);
		block.setBackground(Color.LIGHT_GRAY);
		block.setOpaque(true);
		frame.getContentPane().add(block);

		//This label is here to align the others, should be put in last to preserve order!
		JLabel tes = new JLabel();
		frame.getContentPane().add(tes);

		frame.setVisible(true);
		
	}
}
