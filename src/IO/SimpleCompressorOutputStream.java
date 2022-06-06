package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream outputStream){
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {

    }
}
