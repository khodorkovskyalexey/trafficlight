package trafficlight;

import java.io.FileNotFoundException;

public class Iterate {

    private static int counter;
    private Simulator simulator;
    public int lastSumm;
    
    public Iterate() throws FileNotFoundException {
        Bin bin = new Bin();
        simulator = new Simulator(bin);
        lastSumm = simulator.summ;
    }
    
    public Iterate(int count) throws FileNotFoundException {
        counter = count;
        Bin bin = new Bin();
        simulator = new Simulator(bin);
        lastSumm = simulator.summ;
        algoritmStep(bin);
    }
    
    public int getItem() { 
        return simulator.summ;
    }
    
    public void next() throws FileNotFoundException {
        Bin bin = new Bin();
        algoritmStep(bin);
    }
    
    private boolean isEnough() {
        boolean bool = true;
        if(simulator.summ < lastSumm) {
            bool = false;
            lastSumm = simulator.summ;
        } else {
            if(counter >= 25) {
                bool = false;
            } else {
                counter++;
            }
        }
        return bool;
    }
    
    private void algoritmStep(Bin bin) throws FileNotFoundException {
        GeneticAlgoritm GA = new GeneticAlgoritm(bin, simulator);
        TaskFileIO.write(GA.newBin);
        if(isEnough()) {
            simulator = new Simulator(bin);
            algoritmStep(GA.newBin);
        }
    }
}
