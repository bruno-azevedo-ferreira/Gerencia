package GeradorXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {

    public void Client() {

        try {

            Socket socket = null;
            String host = "127.0.0.1";

            socket = new Socket(host, 5566);
            File file = new File("xml.xml");

            long length = file.length();
            byte[] bytes = new byte[16 * 1024];

            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
