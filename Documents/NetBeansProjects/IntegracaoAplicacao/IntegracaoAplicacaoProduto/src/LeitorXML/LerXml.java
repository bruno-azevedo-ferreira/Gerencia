package LeitorXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class LerXml {

    public LerXml() {
    }
    
    public ArrayList<Prod> lerXml(){
        
        ArrayList<Prod> listaProdutos = new ArrayList<>();
        
        
        try {
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource("xml.xml"));            
            Element raiz = doc.getDocumentElement();
            NodeList produtoList = raiz.getElementsByTagName("prod");
            Element produtoElement;
            
            for(int i=0; i<produtoList.getLength(); i++){
                produtoElement = (Element) produtoList.item(i);
                Prod produto = new Prod();
                produto.setCprod(Long.parseLong(getChildTagValue(produtoElement, "cprod")));
                produto.setXprod(getChildTagValue(produtoElement, "xprod"));
                produto.setQcom(Integer.parseInt(getChildTagValue(produtoElement, "qcom")));
                produto.setVuncom(Double.parseDouble(getChildTagValue(produtoElement, "vuncom")));
                produto.setVprod(Double.parseDouble(getChildTagValue(produtoElement, "vprod")));
                listaProdutos.add(produto);                
            }
            
        } catch (ParserConfigurationException ex) {            
            Logger.getLogger(LerXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {            
            Logger.getLogger(LerXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
            //Logger.getLogger(LerXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {            
            Logger.getLogger(LerXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaProdutos;
    } 
       
    private static String getChildTagValue(Element elem, String tagName) throws Exception {
        NodeList children = elem.getElementsByTagName(tagName);
        String result = null;
        if (children == null) {
            return result;
        }
        Element child = (Element) children.item(0);

        if (child == null) {
            return result;
        }
        result = child.getTextContent();

        return result;

    }
    
}
