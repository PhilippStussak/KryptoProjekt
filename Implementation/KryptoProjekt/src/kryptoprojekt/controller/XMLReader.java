package kryptoprojekt.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author LiTTle
 *
 *
 * XML-Files need to have following structure:
 *           ------------
 * -------------------------------------------
 * <?xml version='1.0' encoding='UTF-8'?>
 *
 * <languagefile>
 *      <gui-related>
 *          <textforwhatitstandsfor1>translatedText1</textforwhatitstandsfor1>
 *          <textforwhatitstandsfor2>translatedText2</textforwhatitstandsfor2>
 *      </gui-related>
 *      <exception-related>
 *          <textforwhatitstandsfor1>translatedText3</textforwhatitstandsfor1>
 *          <textforwhatitstandsfor2>translatedText4</textforwhatitstandsfor2>
 *      </exception-related>
 *	...
 *      ...
 * </languagefile>
 * -------------------------------------------
 *
 * Example to get value "translatedText3":
 * ----
 * XMLReader xml = XMLReader.getInstance(filePath);
 * String val = xml.getTagElement("exception-related","textforwhatitstandsfor1");
 */
public class XMLReader {

    private static XMLReader instance;
    private Document doc;

    /**
     * Creates object to read new *.xml-file from given filePath.
     * @param filePath path to file which should be read
     * @throws ParserConfigurationException  if a DocumentBuilder cannot be created which satisfies the configuration requested
     * @throws SAXException If any parse errors occur
     * @throws IOException If any IO errors occur
     */
    private XMLReader(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex2) {
            ex2.printStackTrace();
        } catch (IOException ex3) {
            ex3.printStackTrace();
        }
    }
    /**
     * Returns the instance of the XMLReader. If there is just no instance of XMLReader a new instance will be created,
     * otherwise the one and only existing instance will be returned.
     * Consequently {@code filePath} should be correct for creating the first instance, otherwise {@code filePath} will be disregarded
     * and {@code null} will be accepted as parameter, too.
     *
     * @param filePath path to *.xml-language file. Can be {@code null} if XMLReader-instance was created before.
     * @return instance of XMLReader
     */
    public synchronized static XMLReader getInstance(String filePath) {
        if (instance == null) {
            if (filePath == null || filePath.isEmpty())
                throw new IllegalArgumentException("First instance-creation of XMLReader needs a correct path to a *.xml-language file.");
            instance = new XMLReader(filePath);
        }
        return instance;
    }

    /**
     * Returns the value, specified by the given node and its element/subnode.
     * @param node Node in the opened *.xml-file which has as subnode the given element
     * @param element a subnode of the given node, which describes the wanted value
     * @return value of the element in the specified node.
     *         Returns {@code null} if the element was not found.
     */
    public String getTagElement(String node, String element) {
        //selects all nodes within the "node"-Tags
        NodeList nodeLst = doc.getElementsByTagName(node);

        for (int i = 0; i < nodeLst.getLength(); i++) {
                Node fstNode = nodeLst.item(i);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(element);
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    //returns value of element if found
                    if (fstNm.item(0) != null)
                        return fstNm.item(0).getNodeValue();
                }
        }
        //returns name of the element if no translation found
        return element;
    }
}
