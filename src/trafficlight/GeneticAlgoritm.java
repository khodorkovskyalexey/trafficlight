package trafficlight;

public class GeneticAlgoritm {
    
    private int firstCrossbreeding;
    private int secondCrossbreeding;
    public Bin newBin;
    
    public GeneticAlgoritm(Bin bin, Simulator simulator) {
        firstCrossbreeding = -1;
        secondCrossbreeding = -1;
        double[] p = p(bin, simulator.timeOfStop);
        int[][] luckyInt = roulette(bin, p);
        int[][] newGeneration = crossbreeding(bin, luckyInt);
        this.newBin = combining(bin, newGeneration);
    }
    
    private Bin combining(Bin bin, int[][] newInt) {
        for(int i = 0; i < 2; i++) {
            bin.bin[this.firstCrossbreeding][i] = newInt[0][i];
        }
        for(int i = 0; i < 2; i++) {
            bin.bin[this.secondCrossbreeding][i] = newInt[1][i];
        }
        return bin;
    }
    
    private int[][] crossbreeding(Bin bin, int[][] luckyInt) {
        final int pos = 5;
        int a, b;
        int[] masks = new int[2];
        int[][] newGeneration = new int[2][2];
        masks[0] = (1<<pos)-1;
        masks[1] = ~ masks[0];
        newGeneration[0][0] = (luckyInt[0][0] & masks[1])|(luckyInt[1][0] & 
                masks[0]);
        newGeneration[1][0] = (luckyInt[0][0] & masks[0])|(luckyInt[1][0] & 
                masks[1]);
        newGeneration[0][1] = (luckyInt[0][1] & masks[1])|(luckyInt[1][1] & 
                masks[0]);
        newGeneration[1][1] = ((luckyInt[1][1] & masks[1])|(luckyInt[0][1] &
                masks[0]));
        return newGeneration;
    }
    
    private double[] p(Bin bin, int[] timeOfStop) {
        int sum = sumMaker(timeOfStop);
        double summ = 0;
        double[] P = new double[timeOfStop.length + 1];
        for(int i = 1; i < bin.bin.length + 1; i++) {
            P[i] = 360 * timeOfStop[i - 1];
            P[i] /= sum ;
            P[i] += summ;
            summ = P[i];
        }
        P[0] = -1;
        return P;
    }
    
    private int sumMaker(int[] timeOfStop) {
        int sum = 0;
        for(int i : timeOfStop) {
            sum += i;
        }
    
        for(int i = 0; i < timeOfStop.length; i++) {
            timeOfStop[i] = sum - timeOfStop[i];
        }
        sum = 0;
        for(int i : timeOfStop) {
            sum += i;
        }
    
        return sum;
    }
    
    private void publicIntMaker(int num) {
        if(firstCrossbreeding == -1) {
            firstCrossbreeding = num;
        } else {
            secondCrossbreeding = num;
        }
    }
    
    private int[][] trueRoulette(int[][] luckyInt, Bin bin, double[] P) {
        if(secondCrossbreeding == firstCrossbreeding) {
            return roulette(bin, P);
        } else {
            return luckyInt;
        }
    }
    
    /*private int[][] roulette(Bin bin, double[] P) {
        int[][] luckyInt = new int[2][2];
        for(int x = 0; x < luckyInt.length; x++) {
            double random = Math.random() * 360;
            int i = 1;
            while(!((random > P[i - 1])&&(random <= P[i]))) {
                i++;
            }
            for(int y = 0; y < luckyInt[x].length; y++) {
                luckyInt[x][y] = bin.bin[i - 1][y];
                publicIntMaker(i - 1);
            }
        }
        return trueRoulette(luckyInt, bin, P);
    }*/
    
    private int [][] roulette(Bin bin, double[] P) {
        int[][] luckyInt = new int[2][2];
        for(int x = 0; x < 2; x++) {
            double randomInt = Math.random() * 360;
            for(int i = 1; i < P.length; i++) {
                if((randomInt > P[i - 1])&&(P[i] <= randomInt)) {
                    for(int y = 0; y < 2; y++) {
                        luckyInt[x][y] = bin.bin[i][y];
                        if(firstCrossbreeding == -1) {
                            firstCrossbreeding = i;
                        } else {
                            secondCrossbreeding = i;
                        }
                    }
                }
            }
        }
        return trueRoulette(luckyInt, bin, P);
    }
}
