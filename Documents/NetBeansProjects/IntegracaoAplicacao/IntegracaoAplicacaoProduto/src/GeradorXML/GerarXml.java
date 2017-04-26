package GeradorXML;


import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GerarXml {

    public void geraXml(){
        
        try {
            
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document documentoXML = documentBuilder.newDocument();
        
            Element root = documentoXML.createElement("root");
            documentoXML.appendChild(root);
            
            int i=0;
            ArrayList<String> listCProd = new ArrayList<>();
            listCProd.add("00001");listCProd.add("00002");listCProd.add("00003");
            listCProd.add("00004");listCProd.add("00005");listCProd.add("00006");
            listCProd.add("00007");listCProd.add("00008");listCProd.add("00009");
            listCProd.add("00010");listCProd.add("00011");listCProd.add("00012");
            listCProd.add("00013");listCProd.add("00014");listCProd.add("00015");
            listCProd.add("00016");listCProd.add("00017");listCProd.add("00018");
            listCProd.add("00019");listCProd.add("00020");listCProd.add("00021");
            listCProd.add("00022");listCProd.add("00023");listCProd.add("00024");
            listCProd.add("00025");listCProd.add("00026");listCProd.add("00027");
            listCProd.add("00028");listCProd.add("00029");listCProd.add("00030");
            
            ArrayList<String> listXProd = new ArrayList<>();
            listXProd.add("Sabonete");listXProd.add("Perfume");listXProd.add("Achocolatado");
            listXProd.add("Omo");listXProd.add("Sorvete");listXProd.add("Veja");
            listXProd.add("Danoninho");listXProd.add("Café");listXProd.add("Detergente");
            listXProd.add("Açucar ");listXProd.add("Rinco Cola");listXProd.add("Sabão");
            listXProd.add("Extrato de tomate");listXProd.add("Macarrão");listXProd.add("Zero-Cal");
            listXProd.add("Suco em pó");listXProd.add("Arroz");listXProd.add("Bolacha");
            listXProd.add("Chocolate");listXProd.add("Esponja de Aço");listXProd.add("Nescal");
            listXProd.add("Coca-Cola");listXProd.add("Shampoo");listXProd.add("Leite");
            listXProd.add("Farinha Biju");listXProd.add("Condicionador");listXProd.add("Batatinha");
            listXProd.add("Extrato de tomate");listXProd.add("Cerveja");listXProd.add("Feijao");
            
            ArrayList<String> listvUnCom = new ArrayList<>();
            listvUnCom.add("6");listvUnCom.add("90");listvUnCom.add("12");
            listvUnCom.add("23");listvUnCom.add("45");listvUnCom.add("57");
            listvUnCom.add("34");listvUnCom.add("18");listvUnCom.add("4");
            listvUnCom.add("6");listvUnCom.add("56");listvUnCom.add("6");
            listvUnCom.add("1");listvUnCom.add("68");listvUnCom.add("356");
            listvUnCom.add("2");listvUnCom.add("52");listvUnCom.add("2");
            listvUnCom.add("4");listvUnCom.add("43");listvUnCom.add("63");
            listvUnCom.add("18");listvUnCom.add("10");listvUnCom.add("20");
            listvUnCom.add("45");listvUnCom.add("28");listvUnCom.add("25");
            listvUnCom.add("63");listvUnCom.add("2");listvUnCom.add("15");
            
            for(i=0; i<listCProd.size(); i++){
            
            Element produto = documentoXML.createElement("prod");
            Attr id = documentoXML.createAttribute("id");
            id.setValue(""+(i+1));
            produto.setAttributeNode(id);
            root.appendChild(produto);
            
            Element cProd = documentoXML.createElement("cprod");
            cProd.appendChild(documentoXML.createTextNode(listCProd.get(i)));
            produto.appendChild(cProd);
            
            Element xProd = documentoXML.createElement("xprod");
            xProd.appendChild(documentoXML.createTextNode(listXProd.get(i)));
            produto.appendChild(xProd);
            
            Element qCom = documentoXML.createElement("qcom");
            qCom.appendChild(documentoXML.createTextNode("10"));
            produto.appendChild(qCom);
            
            Element vUnCom = documentoXML.createElement("vuncom");
            vUnCom.appendChild(documentoXML.createTextNode(listvUnCom.get(i)));
            produto.appendChild(vUnCom);
            
            Element vProd = documentoXML.createElement("vprod");            
            vProd.appendChild(documentoXML.createTextNode(Integer.toString(Integer.parseInt(listvUnCom.get(i))*100)));
            produto.appendChild(vProd);
                        
            }
            
            try {
                
                TransformerFactory transformeFactory = TransformerFactory.newInstance();
                Transformer transformer = transformeFactory.newTransformer();
                
                DOMSource documentoFone = new DOMSource(documentoXML);
                
                StreamResult documentoFinal = new StreamResult(new File("xml.xml"));
                
                try {
                    
                    transformer.transform(documentoFone, documentoFinal);                    
                    
                } catch (TransformerException ex) {
                    Logger.getLogger(GerarXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                                               
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(GerarXml.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GerarXml.class.getName()).log(Level.SEVERE, null, ex);
        }                
        
    }
    
}
