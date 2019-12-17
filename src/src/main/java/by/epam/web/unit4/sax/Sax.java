package by.epam.web.unit4.sax;

import by.epam.web.unit4.bean.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class Sax {
    final static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        DeviceSaxHandler handler = new DeviceSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("src\\main\\java\\by\\epam\\web\\unit4\\devices.xml"));

        List<Device> devices = handler.getDeviceList();

        for (Device d:devices){
            logger.info(d);
        }
    }
}
