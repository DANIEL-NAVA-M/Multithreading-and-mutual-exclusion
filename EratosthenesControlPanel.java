/*
 *	SWE30001, Problem Set 2, 2024
 *
 *	Multi-threaded Sieve of Eratosthenes
 * 
 */

package sieve;

import java.awt.Color;
//import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import edu.swin.rt.MakeButton;
import edu.swin.rt.TitledNumberCanvas;

public class EratosthenesControlPanel extends JPanel implements IControl
{
    private static final long serialVersionUID = 1L;

    
    private ISieve fSieve;
    private TitledNumberCanvas fActive;
    private TitledNumberCanvas fThreads;
    private TitledNumberCanvas fPrimesFound;
    private TitledNumberCanvas fNumbersTested;
    private JButton fGo;

    private NumberGenerator fGenerator;
    
    public EratosthenesControlPanel(int aLimit, ISieve aSieve)
    {
        super(new GridBagLayout());
        fSieve = aSieve;
        fGenerator = new NumberGenerator(aLimit, fSieve);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 5, 20, 5);
        
        fActive = new TitledNumberCanvas("Active Numbers");
        fActive.setBackground(Color.BLUE);

        fThreads = new TitledNumberCanvas("Active Threads");
        fThreads.setBackground(Color.RED);

        fPrimesFound = new TitledNumberCanvas("Primes Found");
        fPrimesFound.setBackground(Color.GREEN);

        fNumbersTested = new TitledNumberCanvas("Numbers Tested");
        fNumbersTested.setBackground(Color.ORANGE);
      
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        add(fActive, gbc);
        
        gbc.gridx = 1;
        add(fThreads, gbc);
        
        gbc.gridx = 2;
        add(fPrimesFound, gbc);
        
        gbc.gridx = 3;
        add(fNumbersTested, gbc);
        
        
        fGo = MakeButton.createButton("Go", event -> go());
        gbc.gridx = 4;
        add(fGo, gbc);
    }

    private synchronized void go()
    {
        fActive.setValue(0);
        fThreads.setValue(0);
        fPrimesFound.setValue(0);
        fNumbersTested.setValue(0);
        
        fGo.setEnabled(false);
        
        fGenerator.start();
    }

    @Override
    public synchronized int getFiltered() {
        return fNumbersTested.getValue();
    }

    @Override
    public synchronized int getThreads() {
        return fThreads.getValue();
    }

    @Override
    public synchronized void incrementActive() {
        fActive.setValue(fActive.getValue() + 1);
    }

    @Override
    public synchronized void decrementActive() {
        fActive.setValue(fActive.getValue() - 1);
    }

    @Override
    public synchronized void incrementThreads() {
        fThreads.setValue(fThreads.getValue() + 1);
    }

    @Override
    public synchronized void decrementThreads() {
        fThreads.setValue(fThreads.getValue() - 1);
    }

    @Override
    public synchronized void incrementFiltered() {
        fNumbersTested.setValue(fNumbersTested.getValue() + 1);
    }

    @Override
    public synchronized void incrementPrimes() {
        fPrimesFound.setValue(fPrimesFound.getValue() + 1);
    }

    @Override
    public void enableControl() {
        fGo.setEnabled(true);
    }

    @Override
    public synchronized void reset() {
        fActive.setValue(0);
        fThreads.setValue(0);
        fPrimesFound.setValue(0);
        fNumbersTested.setValue(0);
    }
}
