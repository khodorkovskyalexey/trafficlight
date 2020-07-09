package trafficlight;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class TaskFileIO {
    
    public static Bin read() throws 
            FileNotFoundException{
        Scanner scan = new Scanner(new File("generation.txt"));
        int vCount = Integer.valueOf(scan.nextLine());
        Bin bin = new Bin(vCount);
        for (int i = 0; i < vCount; i++) {
            String[] arr = scan.nextLine().split("<next>");
            bin.setText(i, 0, Integer.parseInt(arr[0]));
            bin.setText(i, 1, Integer.parseInt(arr[1]));
        }
        return bin;
    }
    
    public static void write(Bin bin) {     
        try {
        File file = new File("generation.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            byte[] next = bin.setUTF8("<next>");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] str = bin.setUTF8(bin.bin.length);
            bos.write(str);
            for(int i = 0; i < bin.bin.length; i++) {
                bos.write(13);
                byte[] i0 = bin.setUTF8(bin.bin[i][0]);
                bos.write(i0);
                bos.write(next);
                byte[] i1 = bin.setUTF8(bin.bin[i][1]);
                bos.write(i1);
            }
            bos.close();
        } catch (IOException e){
            System.err.toString();
        }
    }
}
