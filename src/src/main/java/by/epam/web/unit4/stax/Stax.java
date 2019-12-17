package by.epam.web.unit4.stax;

import by.epam.web.unit4.bean.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class Stax {
   final static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        StaxProcess staxProcess = new StaxProcess();
        try {
            InputStream input = new FileInputStream("src\\main\\java\\by\\epam\\web\\unit4\\devices.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Device> devices = staxProcess.process(reader);

            for (Device d : devices) {
                logger.info(d);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }


}
