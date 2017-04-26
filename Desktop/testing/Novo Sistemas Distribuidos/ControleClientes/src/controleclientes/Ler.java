

package controleclientes;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Ler  {

 
public void Ler(){}
   

   

    public String AbrirS(String fileName)   {
if(!new File(fileName).exists()){try {
    new File(fileName).createNewFile();
} catch (IOException ex) {
    Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, null, ex);
}}
        BufferedReader in;
    try {
        in = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuffer buffer = new StringBuffer();

        while( (line = in.readLine()) != null ){
            buffer.append(line + "\n");
        }

        in.close();
            
        return buffer.toString();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, null, ex);
    return null;
    } catch (IOException ex) {
        Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, null, ex);
   return null;
    }
    

    }

    public String AbrirF(File file) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        StringBuffer buffer = new StringBuffer();

        while( (line = in.readLine()) != null ){
            buffer.append(line + "\n");
        }

        in.close();
        return buffer.toString();

    }

    protected void finalize() throws Throwable {
    }
    public  void pintaImg() throws IOException{  
             
        BufferedImage imagem = ImageIO.read(new File("C:\\imagem.jpg"));


        int w = imagem.getWidth();
int h = imagem.getHeight();
int[] pixels = imagem.getRGB(0, 0, w, h, null, 0, w);




imagem.setRGB(0, 0, w, h, pixels, 0, w);

ImageIO.write(imagem, "PNG", new File("arteabstrata.png"));
        
        
        
        
        
        
        
        
      }  

    



    public void Escrever(String fileName,String conteudo )   {

        PrintWriter out;
    try {
        out = new PrintWriter(new FileWriter(fileName));
         out.write(conteudo);
        out.close();
    } catch (IOException ex) {
        Logger.getLogger(Ler.class.getName()).log(Level.SEVERE, null, ex);
    }
      
       /// JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
      
       /// JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");

    }
   public static void Escrever(File file,String conteudo ) throws IOException {

        PrintWriter out = new PrintWriter(new FileWriter(file));
       out.write(conteudo);
        out.close();
       /// JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");

    }

   public static void main(String []args){
   
   
   
  
        

    }
}
