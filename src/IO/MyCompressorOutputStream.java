package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream o){
        this.out = o;
    }

    @Override
    public void write(int b) throws IOException {

    }

    public void write(byte b) throws IOException{

    }
}
