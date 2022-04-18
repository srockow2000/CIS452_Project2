package CIS452_Project2;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;  

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
        frame.setSize(700,700);
		//JButton button1 = new JButton("Press");
		//frame.getContentPane().add(button1);
		JPanel pj = new JPanel();
		frame.getContentPane().add(pj); //not visible

		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);	

		JLabel title = new JLabel("Memory Management");
		frame.getContentPane().add(title);
		title.setBounds(300, 50, 150, 50);
		//title.setBorder(border);
		title.setHorizontalAlignment(JLabel.CENTER);

		JLabel wor = new JLabel("Worst Fit");
		frame.getContentPane().add(wor);
		wor.setBounds(450, 600, 50, 100);

		JLabel lab = new JLabel("First Fit");
		frame.getContentPane().add(lab);
		lab.setBounds(150, 600, 50, 100);

		JLabel bes = new JLabel("Best Fit");
		frame.getContentPane().add(bes);
		bes.setBounds(300, 600, 50, 100);


		frame.setVisible(true);
		
	}
}
