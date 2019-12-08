package by.epam.web.unit4.sax;

import by.epam.web.unit4.bean.Device;
import by.epam.web.unit4.bean.DeviceTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DeviceSaxHandler extends DefaultHandler {
    private List<Device> deviceList = new ArrayList<>();
    private Device device;
    private StringBuilder text;

    public List<Device> getDeviceList(){
        return deviceList;
    }
    public void startDocument() throws SAXException {
        System.out.println("Parsing started");
    }
    public void endDocument() throws SAXException{
        System.out.println("Parsing ended");
    }
    public void characters (char[] buffer, int start, int length){
        text.append(buffer, start, length);
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        text = new StringBuilder();
        if(qName.equals("device")){
            device = new Device();
            device.setId(Integer.parseInt(attributes.getValue("id")));
        }

    }
    public void endElement(String uri, String localName, String qName) throws SAXException{
        DeviceTagName tagName = DeviceTagName.valueOf(qName.toUpperCase().replace("-","_"));
        switch (tagName){
            case NAME: device.setName(text.toString());break;
            case PRICE: device.setPrice(Integer.parseInt(text.toString())); break;
            case ORIGIN:device.setOrigin(text.toString()); break;
            case CRITICAL:device.setCritical(Boolean.parseBoolean(text.toString()));break;
            case PORT: device.getType().setPort(text.toString());break;
            case GROUP: device.getType().setGroup(text.toString()); break;
            case COOLER: device.getType().setCooler(Boolean.parseBoolean(text.toString())); break;
            case USE_POWER: device.getType().setUsePower(Integer.parseInt(text.toString())); break;
            case PERIPHERAL: device.getType().setPeripheral(Boolean.parseBoolean(text.toString())); break;
            case DEVICE: deviceList.add(device); device = null; break;

            case TYPES: break;
            case DEVICES: break;
            default:
                System.out.println("Tag isn't exist"); break;


        }
    }
}
