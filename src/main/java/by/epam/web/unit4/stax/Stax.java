package by.epam.web.unit4.stax;

import by.epam.web.unit4.bean.Device;
import by.epam.web.unit4.bean.DeviceTagName;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Stax {
    public static void main(String[] args) throws FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream("src\\main\\java\\by\\epam\\web\\unit4\\devices.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Device> devices = process(reader);

            for (Device d : devices) {
                System.out.println(d);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static List<Device> process(XMLStreamReader reader) throws XMLStreamException {
        List<Device> devices = new ArrayList<>();
        Device device = null;
        DeviceTagName elName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elName = DeviceTagName.getElementTagName(reader.getLocalName());
                    switch (elName) {
                        case DEVICE:
                            device = new Device();
                            Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                            device.setId(id);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elName) {
                        case NAME:
                            device.setName(text);
                            break;
                        case PRICE:
                            device.setPrice(Integer.parseInt(text));
                            break;
                        case ORIGIN:
                            device.setOrigin(text);
                            break;
                        case CRITICAL:
                            device.setCritical(Boolean.parseBoolean(text));
                            break;
                        case PORT:
                            device.getType().setPort(text);
                            break;
                        case GROUP:
                            device.getType().setGroup(text);
                            break;
                        case COOLER:
                            device.getType().setCooler(Boolean.parseBoolean(text));
                            break;
                        case USE_POWER:
                            device.getType().setUsePower(Integer.parseInt(text));
                            break;
                        case PERIPHERAL:
                            device.getType().setPeripheral(Boolean.parseBoolean(text));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elName = DeviceTagName.getElementTagName(reader.getLocalName());
                    switch (elName) {
                        case DEVICE:
                            devices.add(device);
                    }
            }
        }
        return devices;
    }
}
