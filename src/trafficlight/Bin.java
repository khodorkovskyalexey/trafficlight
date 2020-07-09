package trafficlight;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Bin {
    
    public int[][] bin;
    
    public Bin(int vCount) {
        setSize(vCount, 2);
    }
    
    public Bin() throws FileNotFoundException {
        this.bin = TaskFileIO.read().bin;
    }
    
    private void setSize(int sizeX, int sizeY) {
        this.bin = new int[sizeX][sizeY];
    }
    
    public void setText(int i, int j, int txt) {
        this.bin[i][j] = txt;
    }
    
    public byte[] setUTF8(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    byte[] setUTF8(int value) throws UnsupportedEncodingException {
        return String.valueOf(value).getBytes("UTF-8");    
    }
    
}
