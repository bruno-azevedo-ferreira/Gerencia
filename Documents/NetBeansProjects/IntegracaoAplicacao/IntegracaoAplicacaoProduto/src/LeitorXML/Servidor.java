package LeitorXML;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Servidor extends Thread{

    private ServerSocket serverSocket;
    private static Servidor instance;

    public Servidor(int port){
        try {        
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Server(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Servidor getInstancia(int port) {
        if (instance == null) {
            instance = new Servidor(port);
        }
        return instance;
    }

    private void Server(Socket clientSock) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fileOutputStream = new FileOutputStream("meuxml.xml");
        byte[] bytes = new byte[4096];

        int count;
        while((count = dataInputStream.read(bytes)) > 0){
            fileOutputStream.write(bytes, 0, count);
        }
        
        fileOutputStream.close();
        dataInputStream.close();

        if (new File("xml.xml").exists()) {
            JOptionPane.showMessageDialog(null, "Arquivo recebido do cliente", "Observação", JOptionPane.INFORMATION_MESSAGE);
        }

    }
        
}
