 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;


// Java implementation of First - Fit algorithm
class GFG2{
    // Method to allocate memory to
    // blocks as per First fit algorithm
    public static int[] firstFit(int blockSize[], int m,
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
    
        return allocation;
    }
    
    // Method to allocate memory to blocks as per Best fit
    // algorithm
    static int[] bestFit(int blockSize[], int m, int processSize[],
                                                     int n)
    {
        // Stores block id of the block allocated to a
        // process
        int allocation[] = new int[n];
      
        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
      
     // pick each process and find suitable blocks
        // according to its size ad assign to it
        for (int i=0; i<n; i++)
        {
            // Find the best fit block for current process
            int bestIdx = -1;
            for (int j=0; j<m; j++)
            {
                if (blockSize[j] >= processSize[i])
                {
                    if (bestIdx == -1)
                        bestIdx = j;
                    else if (blockSize[bestIdx] > blockSize[j])
                        bestIdx = j;
                }
            }
      
            // If we could find a block for current process
            if (bestIdx != -1)
            {
                // allocate block j to p[i] process
                allocation[i] = bestIdx;
      
                // Reduce available memory in this block.
                blockSize[bestIdx] -= processSize[i];
            }
        }
        
        return allocation;
    }
    
    // Method to allocate memory to blocks as per worst fit
    // algorithm
    static int[] worstFit(int blockSize[], int m, int processSize[],
                                                     int n)
    {
        // Stores block id of the block allocated to a
        // process
        int allocation[] = new int[n];
      
        // Initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
      
        // pick each process and find suitable blocks
        // according to its size ad assign to it
        for (int i=0; i<n; i++)
        {
            // Find the best fit block for current process
            int wstIdx = -1;
            for (int j=0; j<m; j++)
            {
                if (blockSize[j] >= processSize[i])
                {
                    if (wstIdx == -1)
                        wstIdx = j;
                    else if (blockSize[wstIdx] < blockSize[j])
                        wstIdx = j;
                }
            }
      
            // If we could find a block for current process
            if (wstIdx != -1)
            {
                // allocate block j to p[i] process
                allocation[i] = wstIdx;
      
                // Reduce available memory in this block.
                blockSize[wstIdx] -= processSize[i];
            }
        }
        
        return allocation;
    }
    
    
    // Driver Code
    public static void main(String[] args){
        int blockSize[] = {100, 500, 200, 300, 600, 150, 200, 125, 327, 298, 103, 298, 123, 43, 29, 384, 429, 291, 92, 183};
        int processSize[] = {212, 417, 112, 426, 150, 238, 192, 382, 382, 92, 192, 82, 292, 248, 489, 921, 129, 382, 482, 291};
        int m = blockSize.length;
        int n = processSize.length;
        
        
        
        /** GUI Parts */
        JFrame frame = new JFrame("Project 2: Memory Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,800);

        JPanel pj = new JPanel();
        pj.setLayout(new GridLayout());
        frame.getContentPane().add(pj); //not visible

        //borders
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3); 
        Font font = new Font("Courier", Font.PLAIN, 18);
        Font font2 = new Font("Courier", Font.PLAIN, 14);

        //JLabels
        JLabel lab = new JLabel("First Fit");
        lab.setBounds(250, 700, 150, 50);
        lab.setBorder(border);
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setBackground(Color.CYAN);
        lab.setOpaque(true);
        lab.setFont(font2);
        frame.getContentPane().add(lab);


        JLabel bes = new JLabel("Best Fit");
        bes.setBounds(425, 700, 150, 50);
        bes.setBorder(border);
        bes.setHorizontalAlignment(JLabel.CENTER);
        bes.setBackground(Color.GREEN);
        bes.setOpaque(true);
        bes.setFont(font2);
        frame.getContentPane().add(bes);


        JLabel wor = new JLabel("Worst Fit");
        wor.setBounds(600, 700, 150, 50);
        wor.setBorder(border);
        wor.setHorizontalAlignment(JLabel.CENTER);
        wor.setBackground(Color.ORANGE);
        wor.setOpaque(true);
        wor.setFont(font2);
        frame.getContentPane().add(wor);

        JLabel proNum = new JLabel("Next Process to be allocated");
        proNum.setBounds(25, 50, 200, 50);
        proNum.setBorder(border);
        proNum.setHorizontalAlignment(JLabel.CENTER);
        proNum.setBackground(Color.LIGHT_GRAY);
        proNum.setOpaque(true);
        proNum.setFont(font2);
        frame.getContentPane().add(proNum);
        
        String processText = "";
        for (int i = 0; i < blockSize.length; i++) {
            processText = Integer.toString(blockSize[i]);
        }
        
        
            String firstText = "First: " + processText;
            JLabel firstTxt = new JLabel(firstText);
            firstTxt.setBounds(25, 100, 200, 50);
            firstTxt.setBorder(border);
            firstTxt.setOpaque(true);
            firstTxt.setFont(font2);
            firstTxt.setBackground(Color.CYAN);
            frame.getContentPane().add(firstTxt);
        
        
            String bestText = "Best: " + processText;
            JLabel bestTxt = new JLabel(bestText);
            bestTxt.setBounds(25, 150, 200, 50);
            bestTxt.setBorder(border);
            bestTxt.setOpaque(true);
            bestTxt.setFont(font2);
            bestTxt.setBackground(Color.GREEN);
            frame.getContentPane().add(bestTxt);
        
        
            String worstText = "Worst: " + processText;
            JLabel worstTxt = new JLabel(worstText);
            worstTxt.setBounds(25, 200, 200, 50);
            worstTxt.setBorder(border);
            worstTxt.setOpaque(true);
            worstTxt.setFont(font2);
            worstTxt.setBackground(Color.ORANGE);
            frame.getContentPane().add(worstTxt);
        
        

        JLabel proSize = new JLabel("Time Units Passed");
        proSize.setBounds(25, 300, 200, 150);
        proSize.setBorder(border);
        proSize.setHorizontalAlignment(JLabel.CENTER);
        proSize.setBackground(Color.LIGHT_GRAY);
        proSize.setOpaque(true);
        proSize.setFont(font2);
        frame.getContentPane().add(proSize);
        
        int [] first = firstFit(blockSize, m, processSize, n);
        int [] best = bestFit(blockSize, m, processSize, n);
        int [] worst = worstFit(blockSize, m, processSize, n);
        
       
        
        for (int i = 0; i < first.length; i++) {
            
                String blockText = Integer.toString(first[i]);
                if(first[i] == -1) {
                    blockText = "Not allocated";
                }
                JLabel txt = new JLabel(blockText);
                txt.setBounds(250, 25 * i, 150, 25);
                txt.setBorder(border);
                //txt.setHorizontalAlignment(JLabel.CENTER);
                txt.setBackground(Color.CYAN);
                txt.setOpaque(true);
                txt.setFont(font2);
                frame.getContentPane().add(txt);
                
        }

        
        for (int i = 0; i < best.length; i++) {
                String blockText = Integer.toString(best[i]);
                if(best[i] == -1) {
                    blockText = "Not allocated";
                }
                JLabel txt = new JLabel(blockText);
                txt.setBounds(425, 25 * i, 150, 25);
                txt.setBorder(border);
                //txt.setHorizontalAlignment(JLabel.CENTER);
                txt.setBackground(Color.GREEN);
                txt.setOpaque(true);
                txt.setFont(font2);
                frame.getContentPane().add(txt);
        }
        
        for (int i = 0; i < worst.length; i++) {
                String blockText = Integer.toString(worst[i]);
                if(worst[i] == -1) {
                    blockText = "Not allocated";
                }
                JLabel txt = new JLabel(blockText);
                txt.setBounds(600, 25 * i, 150, 25);
                txt.setBorder(border);
                //txt.setHorizontalAlignment(JLabel.CENTER);
                txt.setBackground(Color.ORANGE);
                txt.setOpaque(true);
                txt.setFont(font2);
                frame.getContentPane().add(txt);
                
              
                
        }
        
        //This label is here to align the others, should be put in last to preserve label order!
        JLabel tes = new JLabel();
        frame.getContentPane().add(tes);

        frame.setVisible(true);
        //testing....?
    }
}
