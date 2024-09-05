/*
 *	SWE30001, Problem Set 2, 2024
 *
 *	Multi-threaded Sieve of Eratosthenes
 * 
 */

package sieve;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import edu.swin.rt.FlashingNumberCanvas;

//import javax.swing.BorderFactory;
//import javax.swing.border.Border;

public class EratosthenesPrimePanel extends JPanel implements IPrimes
{
    private static final long serialVersionUID = 1L;

    private int fRowCount;
    private int fColumnCount;
    
    private FlashingNumberCanvas fNumbers[][];
    
    static
    {
        FlashingNumberCanvas.DefaultMaxValue = 100;   
    }
    public EratosthenesPrimePanel(int aRowCount, int aColumnCount) {
        this.fRowCount = aRowCount;
        this.fColumnCount = aColumnCount;
        
        // Configurar el layout del panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre los componentes
        gbc.fill = GridBagConstraints.BOTH;
        
        // Inicializar la matriz de FlashingNumberCanvas
        fNumbers = new FlashingNumberCanvas[fRowCount][fColumnCount];
        
        // Rellenar el panel con los componentes
        for (int i = 0; i < fRowCount; i++) {
            for (int j = 0; j < fColumnCount; j++) {
                fNumbers[i][j] = new FlashingNumberCanvas((i * fColumnCount) + j + 1);
                fNumbers[i][j].setBackground(Color.CYAN); // Fondo inicial de los números
                
                gbc.gridx = j;
                gbc.gridy = i;
                this.add(fNumbers[i][j], gbc); // Añadir el componente al panel
            }
        }
    }

    
    private FlashingNumberCanvas getNumberCanvas(int aNumber)
    {
        int row = (aNumber - 1) / fColumnCount;
        int col = (aNumber - 1) % fColumnCount;
        if (row < fRowCount && col < fColumnCount) {
            return fNumbers[row][col];
        }
        return null;
    }

    @Override
    public synchronized void flash(int aPrime)
    {
        FlashingNumberCanvas canvas = getNumberCanvas(aPrime);
        if (canvas != null) {
            canvas.flash();
        }
    }

    @Override
    public synchronized void setAsProcessed(int aNumber)
    {
        FlashingNumberCanvas canvas = getNumberCanvas(aNumber);
        if (canvas != null) {
            canvas.setBackground(Color.RED);
        }
    }

    @Override
    public synchronized void setAsAdded(int aNumber)
    {
        FlashingNumberCanvas canvas = getNumberCanvas(aNumber);
        if (canvas != null) {
            canvas.setBackground(Color.GREEN);
        }
    }

    @Override
    public synchronized void setAsPrime(int aNumber)
    {
        FlashingNumberCanvas canvas = getNumberCanvas(aNumber);
        if (canvas != null) {
            canvas.setBackground(Color.YELLOW);
        }
    }

    @Override
    public synchronized void reset()
    {
        for (int row = 0; row < fRowCount; row++) {
            for (int col = 0; col < fColumnCount; col++) {
                fNumbers[row][col].setBackground(Color.CYAN);
            }
        }
    }
}