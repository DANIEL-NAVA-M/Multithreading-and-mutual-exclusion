/*
 *	SWE30001, Problem Set 2, 2024
 *
 *	Multi-threaded Sieve of Eratosthenes
 * 
 */

package sieve;

import java.awt.BorderLayout;

import edu.swin.rt.ApplicationFrame;

public class EratosthenesFrame extends ApplicationFrame implements ISieve
{
    private static final long serialVersionUID = 1L;

    private EratosthenesPrimePanel fPrimes;
    private EratosthenesControlPanel fControls;

    public EratosthenesFrame(String aTitle) {
        super(aTitle);

        // Configurar layout
        setLayout(new BorderLayout());

        // Crear e inicializar los paneles
        fPrimes = new EratosthenesPrimePanel(10, 10);
        fControls = new EratosthenesControlPanel(100, this);

        // Añadir los paneles al frame
        add(fPrimes, BorderLayout.CENTER);
        add(fControls, BorderLayout.SOUTH);

        // Ajustar el tamaño del frame al contenido
        pack();
    }

    @Override
    public synchronized int getFiltered() {
        return fControls.getFiltered();
    }

    @Override
    public synchronized int getThreads() {
        return fControls.getThreads();
    }

    @Override
    public synchronized void scheduled(int aNumberTested) {
        fPrimes.setAsAdded(aNumberTested);
    }

    @Override
    public synchronized void flash(int aPrime, int aNumberTested) {
        fPrimes.flash(aPrime);
    }

    @Override
    public synchronized void incrementThreads(int aPrime) {
        fControls.incrementThreads();
    }

    @Override
    public synchronized void decrementThreads() {
        fControls.decrementThreads();
    }

    @Override
    public synchronized void incrementFiltered(int aNumberTested) {
        fPrimes.setAsProcessed(aNumberTested);
        fControls.incrementFiltered();
    }

    @Override
    public synchronized void incrementPrimes(int aPrime) {
        fPrimes.setAsPrime(aPrime);
        fControls.incrementPrimes();
    }

    @Override
    public void finished() {
        fControls.enableControl();
    }

    @Override
    public synchronized void reset() {
        fControls.reset();
        fPrimes.reset();
    }
}
