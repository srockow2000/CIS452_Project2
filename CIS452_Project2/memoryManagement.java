package CIS452_Project2;

 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

class memoryManagement{
    //**  First Fit algorithm */
    public static int[] firstFit(int memory[], int runTime[]){
        // Stores block id for a process
        int allocation[] = new int[runTime.length]; 
    
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
    
        // picks process and finds suitable blocks
        for (int i = 0; i < runTime.length; i++){
            for (int j = 0; j < memory.length; j++){
                if (memory[j] >= runTime[i]){
                    // allocates block a process
                    allocation[i] = j;
    
                    // Reduces memory after allocating space
                    memory[j] -= runTime[i];
    
                    break; 
                }
            }
        }
    
        return allocation;
    }
    
    /** Best Fit Algorithm */
    public static int[] bestFit(int memory[], int runTime[])
    {
        // Stores block id 
        int allocation[] = new int[runTime.length];
      
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
      
        // picks each process 
        for (int i=0; i< memory.length; i++){
            // Find the best fit block 
            int bestIdx = -1;
            for (int j=0; j< memory.length; j++)
            {
                if (memory[j] >= runTime[i])
                {
                    if (bestIdx == -1)
                        bestIdx = j;
                    else if (memory[bestIdx] > memory[j])
                        bestIdx = j;
                }
            }
      
            // If block found
            if (bestIdx != -1){
                // allocate block to a process
                allocation[i] = bestIdx;
      
                // Reduce memory
                memory[bestIdx] -= runTime[i];
            }
        }
        
        return allocation;
    }
    
    //** Worst Fit Algorithm */
    static int[] worstFit(int memory[], int runTime[]){
        // Stores block id 
        int allocation[] = new int[runTime.length];
      
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
      
        // picks each process 
        for (int i=0; i<runTime.length; i++) {
            // Find the worst fit block for process
            int wstIdx = -1;
            for (int j=0; j<memory.length; j++)
            {
                if (memory[j] >= runTime[i])
                {
                    if (wstIdx == -1)
                        wstIdx = j;
                    else if (memory[wstIdx] < memory[j])
                        wstIdx = j;
                }
            }
      
            // If blcok found
            if (wstIdx != -1) {
                // allocate block to process
                allocation[i] = wstIdx;
      
                // Reduce memory
                memory[wstIdx] -= runTime[i];
            }
        }
        
        return allocation;
    }

    /** Main Method *///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        int blockSize[] = {100, 500, 200, 300, 600, 150, 200, 125, 327, 298, 103, 298, 123, 43, 29, 384, 429, 291, 92, 183};
        int processSize[] = {212, 417, 112, 426, 150, 238, 192, 382, 382, 92, 192, 82, 292, 248, 489, 921, 129, 382, 482, 291};
        
        int [] first = firstFit(blockSize, processSize);
        int [] best = bestFit(blockSize, processSize);
        int [] worst = worstFit(blockSize, processSize);
        

        /** GUI Parts */
        JFrame frame = new JFrame("- Project 2: Memory Management -");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,800);

        JPanel pj = new JPanel();
        pj.setLayout(new GridLayout());
        frame.getContentPane().add(pj); //not visible

        //borders
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3); 
        Font font = new Font("Courier", Font.PLAIN, 18);
        Font font2 = new Font("Courier", Font.PLAIN, 14);

        Timer timer = new Timer("Timer");

        //BUTTON
        JButton button = new JButton("Button");
        button.setBounds(40, 500, 150, 50);
        button.setBorder(border);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(Color.PINK);
        button.setOpaque(true);
        button.setFont(font);
        frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimerTask task = new TimerTask() {
                    int inny = 0;
                    public void run(){
                        /** First Fit */
                        String blockText = Integer.toString(first[inny]);
                        if(first[inny] == -1) {
                            blockText = "Not Allocated";
                        }
                        else{ 
                            blockText = Integer.toString(inny);
                        }
                        JLabel txt = new JLabel(blockText);
                        txt.setBounds(250, 33 * inny, 150, 25);
                        txt.setBorder(border);
                        txt.setHorizontalAlignment(JLabel.CENTER);
                        txt.setBackground(Color.CYAN);
                        txt.setOpaque(true);
                        txt.setFont(font2);
                        frame.getContentPane().add(txt);

                    /** Best Fit */
                        String blockTextx = Integer.toString(best[inny]);
                        if(best[inny] == -1) {
                            blockText = "Not Allocated";
                        }
                        JLabel txtx = new JLabel(blockTextx);
                        txtx.setBounds(425, 33 * inny, 150, 25);
                        txtx.setBorder(border);
                        txtx.setHorizontalAlignment(JLabel.CENTER);
                        txtx.setBackground(Color.GREEN);
                        txtx.setOpaque(true);
                        txtx.setFont(font2);
                        frame.getContentPane().add(txtx);

                        /** Worst Fit */
                        String blockTexts = Integer.toString(worst[inny]);
                        if(worst[inny] == -1) {
                            blockTexts = "Not allocated";
                        }
                        JLabel txts = new JLabel(blockTexts);
                        txts.setBounds(600, 33 * inny, 150, 25);
                        txts.setBorder(border);
                        txts.setHorizontalAlignment(JLabel.CENTER);
                        txts.setBackground(Color.ORANGE);
                        txts.setOpaque(true);
                        txts.setFont(font2);
                        frame.getContentPane().add(txts);
                                
                        JLabel tes = new JLabel();
                        frame.getContentPane().add(tes);
                        frame.setVisible(true);
                        inny++;
                    }
                };
                JLabel tes = new JLabel();
                frame.getContentPane().add(tes);
                frame.setVisible(true);
                timer.schedule(task, 1500, 1000);
            }
        });

        //JLabels
        JLabel lab = new JLabel("First Fit");
        lab.setBounds(250, 700, 150, 50);
        lab.setBorder(border);
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setBackground(Color.CYAN);
        lab.setOpaque(true);
        lab.setFont(font);
        frame.getContentPane().add(lab);

        JLabel bes = new JLabel("Best Fit");
        bes.setBounds(425, 700, 150, 50);
        bes.setBorder(border);
        bes.setHorizontalAlignment(JLabel.CENTER);
        bes.setBackground(Color.GREEN);
        bes.setOpaque(true);
        bes.setFont(font);
        frame.getContentPane().add(bes);

        JLabel wor = new JLabel("Worst Fit");
        wor.setBounds(600, 700, 150, 50);
        wor.setBorder(border);
        wor.setHorizontalAlignment(JLabel.CENTER);
        wor.setBackground(Color.ORANGE);
        wor.setOpaque(true);
        wor.setFont(font);
        frame.getContentPane().add(wor);

        JLabel proNum = new JLabel("Next Allocation");
        proNum.setBounds(25, 50, 200, 50);
        proNum.setBorder(border);
        proNum.setHorizontalAlignment(JLabel.CENTER);
        proNum.setBackground(Color.LIGHT_GRAY);
        proNum.setOpaque(true);
        proNum.setFont(font);
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
            firstTxt.setHorizontalAlignment(JLabel.CENTER);
            firstTxt.setBackground(Color.CYAN);
            frame.getContentPane().add(firstTxt);
        
            String bestText = "Best: " + processText;
            JLabel bestTxt = new JLabel(bestText);
            bestTxt.setBounds(25, 150, 200, 50);
            bestTxt.setBorder(border);
            bestTxt.setOpaque(true);
            bestTxt.setFont(font2);
            bestTxt.setHorizontalAlignment(JLabel.CENTER);
            bestTxt.setBackground(Color.GREEN);
            frame.getContentPane().add(bestTxt);
        
            String worstText = "Worst: " + processText;
            JLabel worstTxt = new JLabel(worstText);
            worstTxt.setBounds(25, 200, 200, 50);
            worstTxt.setBorder(border);
            worstTxt.setOpaque(true);
            worstTxt.setFont(font2);
            worstTxt.setHorizontalAlignment(JLabel.CENTER);
            worstTxt.setBackground(Color.ORANGE);
            frame.getContentPane().add(worstTxt);
        
        JLabel proSize = new JLabel("Time Units Passed");
        proSize.setBounds(25, 300, 200, 150);
        proSize.setBorder(border);
        proSize.setHorizontalAlignment(JLabel.CENTER);
        proSize.setBackground(Color.LIGHT_GRAY);
        proSize.setOpaque(true);
        proSize.setFont(font);
        frame.getContentPane().add(proSize);
        
        //This label is here to align the others, should be put in last to preserve label order!
        JLabel tes = new JLabel();
        frame.getContentPane().add(tes);

        frame.setVisible(true);
        //testing....?
    }
}