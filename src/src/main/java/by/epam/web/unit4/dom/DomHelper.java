package by.epam.web.unit4.dom;

import by.epam.web.unit4.bean.Device;
import by.epam.web.unit4.builder.Builder;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DomHelper {
  private   Builder builder;
   private List<Device> devices = new ArrayList<>();

    public List<Device> takeDevices(NodeList deviceNodes) {
        for (int i = 0; i < deviceNodes.getLength(); i++) {
            builder = new Builder();

            Element devElement = (Element) deviceNodes.item(i);
            builder.buildId(Integer.parseInt(devElement.getAttribute("id")));
            builder.buildName(getSingleChild(devElement, "name").getTextContent().trim());
            builder.buildOrigin(getSingleChild(devElement, "origin").getTextContent().trim());
            builder.buildPrice(Integer.parseInt(getSingleChild(devElement, "price").getTextContent().trim()));
            builder.buildCritical(Boolean.parseBoolean(getSingleChild(devElement, "critical").getTextContent().trim()));

            builder.buildGetType().setCooler(Boolean.parseBoolean(getSingleChild(devElement, "cooler").getTextContent().trim()));
            builder.buildGetType().setGroup(getSingleChild(devElement, "group").getTextContent().trim());
            builder.buildGetType().setPort(getSingleChild(devElement, "port").getTextContent().trim());
            builder.buildGetType().setPeripheral(Boolean.parseBoolean(getSingleChild(devElement, "peripheral").getTextContent().trim()));
            builder.buildGetType().setUsePower(Integer.parseInt(getSingleChild(devElement, "use-power").getTextContent().trim()));
            devices.add(builder.buildDevice());
        }
        return  devices;
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nList = element.getElementsByTagName(childName);
        Element child = (Element) nList.item(0);

        return child;
    }
}
