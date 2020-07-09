package trafficlight;

public class Simulator {
    
    public int[] timeOfStop;
    public int summ;
    
    public Simulator(Bin bin){
        timeOfStopContent(bin);
    }
    
    private void timeOfStopContent(Bin bin) {
        timeOfStop = new int[bin.bin.length];
        summ = 0;
        for(int i = 0; i < bin.bin.length; i++) {
            timeOfStop[i] = 0;
            for(int j = 0; j < bin.bin[i].length; j++) {
                timeOfStop[i] += bin.bin[i][j]; 
            }
            summ += timeOfStop[i];
        }
        //sumToScreen();
    }
    
    private void sumToScreen() {
        System.out.println("Summ = " + summ);
    }
}
