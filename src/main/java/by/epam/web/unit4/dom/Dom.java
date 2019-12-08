package by.epam.web.unit4.dom;

import by.epam.web.unit4.bean.Device;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dom {
    public static void main(String[] args) throws IOException, SAXException {
        DOMParser parser = new DOMParser();
        parser.parse("src\\main\\java\\by\\epam\\web\\unit4\\devices.xml");
        Document document = parser.getDocument();
        Element root = document.getDocumentElement();
        List<Device> devices = new ArrayList<>();
        NodeList deviceNodes = root.getElementsByTagName("device");
        Device device = null;

        for (int i = 0; i < deviceNodes.getLength(); i++) {
            device = new Device();
            Element devElement = (Element) deviceNodes.item(i);
            device.setId(Integer.parseInt(devElement.getAttribute("id")));
            device.setName(getSingleChild(devElement,"name").getTextContent().trim());
            device.setOrigin(getSingleChild(devElement, "origin").getTextContent().trim());
            device.setPrice(Integer.parseInt(getSingleChild(devElement, "price").getTextContent().trim()));
            device.setCritical(Boolean.parseBoolean(getSingleChild(devElement, "critical").getTextContent().trim()));
            device.getType().setCooler(Boolean.parseBoolean(getSingleChild(devElement,"cooler").getTextContent().trim()));
            device.getType().setGroup(getSingleChild(devElement,"group").getTextContent().trim());
            device.getType().setPort(getSingleChild(devElement,"port").getTextContent().trim());
            device.getType().setPeripheral(Boolean.parseBoolean(getSingleChild(devElement,"peripheral").getTextContent().trim()));
            device.getType().setUsePower(Integer.parseInt(getSingleChild(devElement, "use-power").getTextContent().trim()));
            devices.add(device);
        }

        for (Device d: devices){
            System.out.println(d);
        }


    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nList = element.getElementsByTagName(childName);
        Element child = (Element) nList.item(0);

        return child;
    }
}
