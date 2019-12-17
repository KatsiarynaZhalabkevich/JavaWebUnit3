package by.epam.web.unit4.dom;

import by.epam.web.unit4.bean.Device;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public class Dom {
    private final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException, SAXException {
        DOMParser parser = new DOMParser();
        parser.parse("src\\main\\java\\by\\epam\\web\\unit4\\devices.xml");
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Device> devices;
        NodeList deviceNodes = root.getElementsByTagName("device");
        DomHelper helper = new DomHelper();

        devices =helper.takeDevices(deviceNodes);


        for (Device d : devices) {
            logger.info(d);
        }


    }


}
