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
        // Create new array to hold sorted firstFit array
        int first[] = new int[runTime.length]; 
    
        
        for (int i = 0; i < first.length; i++)
            first[i] = -1;
    
        // looks through the runTime to find the first process that will fit
        for (int i = 0; i < runTime.length; i++){
            for (int j = 0; j < memory.length; j++){
                if (memory[j] >= runTime[i]){
                    // assigns position to process
                    first[i] = j;
    
                    // reduces memory after assigning process
                    memory[j] -= runTime[i];
    
                    break; 
                }
            }
        }
    
        return first;
    }
    
    /** Best Fit Algorithm */
    public static int[] bestFit(int memory[], int runTime[])
    {
        // Create new array to hold sorted bestFit array
        int best[] = new int[runTime.length];
      
        for (int i = 0; i < best.length; i++)
            best[i] = -1;
      
        // look through memory twice to find the best possible fit
        for (int i=0; i< memory.length; i++){
            
            int bestIndex = -1;
            for (int j=0; j< memory.length; j++)
            {
                if (memory[j] >= runTime[i])
                {
                    if (bestIndex == -1)
                        bestIndex = j;
                    else if (memory[bestIndex] > memory[j])
                        bestIndex = j;
                }
            }
      
            // If block found
            if (bestIndex != -1){
                // assign process
                best[i] = bestIndex;
                memory[bestIndex] -= runTime[i];
            }
        }
        
        return best;
    }
    
    //** Worst Fit Algorithm */
    static int[] worstFit(int memory[], int runTime[]){
        // Create new array to hold sorted worstFit array
        int worst[] = new int[runTime.length];
      
        for (int i = 0; i < worst.length; i++)
            worst[i] = -1;
      
        // look through memory twice to find the worst possible fit
        for (int i=0; i<runTime.length; i++) {
            
            int worstIndex = -1;
            for (int j=0; j<memory.length; j++)
            {
                if (memory[j] >= runTime[i])
                {
                    if (worstIndex == -1)
                        worstIndex = j;
                    else if (memory[worstIndex] < memory[j])
                        worstIndex = j;
                }
            }
      
            // set worst[i] equal to the worst fit found
            if (worstIndex != -1) {
                worst[i] = worstIndex;
                memory[worstIndex] -= runTime[i];
            }
        }
        
        return worst;
    }

    /** Main Method *///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args){
        int memory[] = {100, 500, 200, 300, 600, 150, 200, 125, 327, 298, 103, 298, 123, 43, 29, 384, 429, 291, 92, 183};
        int runTime[] = {212, 417, 112, 426, 150, 238, 192, 382, 382, 92, 192, 82, 292, 248, 489, 921, 129, 382, 482, 291};
        
        int [] first = firstFit(memory, runTime);
        int [] best = bestFit(memory, runTime);
        int [] worst = worstFit(memory, runTime);
        

        /** GUI Parts */
        JFrame frame = new JFrame("- Project 2: Memory Management -");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(825,800);

        JPanel pj = new JPanel();
        pj.setLayout(new GridLayout());
        frame.getContentPane().add(pj); //not visible

        //borders
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3); 
        Font font = new Font("Courier", Font.PLAIN, 18);
        Font font2 = new Font("Courier", Font.PLAIN, 14);

        Timer timer = new Timer("Timer");

        //BUTTON
        JButton button = new JButton("- Start Algorithms -");
        button.setBounds(30, 500, 200, 50);
        button.setBorder(border);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(Color.PINK);
        button.setOpaque(true);
        button.setFont(font2);
        frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setBackground(Color.WHITE);
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
                        txt.setBounds(270, 33 * inny, 150, 25);
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
                        txtx.setBounds(445, 33 * inny, 150, 25);
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
                        txts.setBounds(620, 33 * inny, 150, 25);
                        txts.setBorder(border);
                        txts.setHorizontalAlignment(JLabel.CENTER);
                        txts.setBackground(Color.MAGENTA);
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
        lab.setBounds(270, 700, 150, 50);
        lab.setBorder(border);
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setBackground(Color.CYAN);
        lab.setOpaque(true);
        lab.setFont(font);
        frame.getContentPane().add(lab);

        JLabel bes = new JLabel("Best Fit");
        bes.setBounds(445, 700, 150, 50);
        bes.setBorder(border);
        bes.setHorizontalAlignment(JLabel.CENTER);
        bes.setBackground(Color.GREEN);
        bes.setOpaque(true);
        bes.setFont(font);
        frame.getContentPane().add(bes);

        JLabel wor = new JLabel("Worst Fit");
        wor.setBounds(620, 700, 150, 50);
        wor.setBorder(border);
        wor.setHorizontalAlignment(JLabel.CENTER);
        wor.setBackground(Color.MAGENTA);
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
        for (int i = 0; i < memory.length; i++) {
            processText = Integer.toString(memory[i]);
        }
        
            String firstText = "First: " + processText;
            JLabel firstTxt = new JLabel(firstText);
            firstTxt.setBounds(25, 100, 200, 50);
            firstTxt.setBorder(border);
            firstTxt.setOpaque(true);
            firstTxt.setFont(font);
            firstTxt.setHorizontalAlignment(JLabel.CENTER);
            firstTxt.setBackground(Color.CYAN);
            frame.getContentPane().add(firstTxt);
        
            String bestText = "Best: " + processText;
            JLabel bestTxt = new JLabel(bestText);
            bestTxt.setBounds(25, 150, 200, 50);
            bestTxt.setBorder(border);
            bestTxt.setOpaque(true);
            bestTxt.setFont(font);
            bestTxt.setHorizontalAlignment(JLabel.CENTER);
            bestTxt.setBackground(Color.GREEN);
            frame.getContentPane().add(bestTxt);
        
            String worstText = "Worst: " + processText;
            JLabel worstTxt = new JLabel(worstText);
            worstTxt.setBounds(25, 200, 200, 50);
            worstTxt.setBorder(border);
            worstTxt.setOpaque(true);
            worstTxt.setFont(font);
            worstTxt.setHorizontalAlignment(JLabel.CENTER);
            worstTxt.setBackground(Color.MAGENTA);
            frame.getContentPane().add(worstTxt);
        
        JLabel proSize = new JLabel("Time Units Passed: ");
        proSize.setBounds(25, 300, 200, 150);
        proSize.setBorder(border);
        proSize.setHorizontalAlignment(JLabel.CENTER);
        proSize.setBackground(Color.LIGHT_GRAY);
        proSize.setOpaque(true);
        proSize.setFont(font);
        frame.getContentPane().add(proSize);

        frame.getContentPane().setBackground(Color.DARK_GRAY);
        
        //This label is here to align the others, should be put in last to preserve label order!
        JLabel tes = new JLabel();
        frame.getContentPane().add(tes);
        
        frame.setVisible(true);
        //testing....?
    }
}
