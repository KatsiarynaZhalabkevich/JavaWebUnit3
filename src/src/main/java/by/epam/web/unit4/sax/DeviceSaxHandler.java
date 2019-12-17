package by.epam.web.unit4.sax;

import by.epam.web.unit4.bean.Device;
import by.epam.web.unit4.bean.DeviceTagName;
import by.epam.web.unit4.builder.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DeviceSaxHandler extends DefaultHandler {
    final static Logger logger = LogManager.getLogger();
    private List<Device> deviceList = new ArrayList<>();
    private Builder builder;
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
            builder = new Builder();
            builder.buildId(Integer.parseInt(attributes.getValue("id")));
        }

    }
    public void endElement(String uri, String localName, String qName) throws SAXException{
        DeviceTagName tagName = DeviceTagName.valueOf(qName.toUpperCase().replace("-","_"));
        switch (tagName){
            case NAME: builder.buildName(text.toString());break;
            case PRICE: builder.buildPrice(Integer.parseInt(text.toString())); break;
            case ORIGIN:builder.buildOrigin(text.toString()); break;
            case CRITICAL:builder.buildCritical(Boolean.parseBoolean(text.toString()));break;
            case PORT: builder.buildGetType().setPort(text.toString());break;
            case GROUP: builder.buildGetType().setGroup(text.toString()); break;
            case COOLER: builder.buildGetType().setCooler(Boolean.parseBoolean(text.toString())); break;
            case USE_POWER: builder.buildGetType().setUsePower(Integer.parseInt(text.toString())); break;
            case PERIPHERAL: builder.buildGetType().setPeripheral(Boolean.parseBoolean(text.toString())); break;
            case DEVICE: deviceList.add(builder.buildDevice()); builder = null; break;

            case TYPES: break;
            case DEVICES: break;
            default:
                logger.info("Tag isn't exist"); break;


        }
    }
}
