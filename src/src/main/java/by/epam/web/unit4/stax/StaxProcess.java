package by.epam.web.unit4.stax;

import by.epam.web.unit4.bean.Device;
import by.epam.web.unit4.bean.DeviceTagName;
import by.epam.web.unit4.builder.Builder;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaxProcess {

    private Builder builder;
    private List<Device> devices = new ArrayList<>();

    private DeviceTagName elName = null;

    public List<Device> process(XMLStreamReader reader) throws XMLStreamException {

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elName = DeviceTagName.getElementTagName(reader.getLocalName());
                    switch (elName) {
                        case DEVICE:
                            builder = new Builder();

                            Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                           builder.buildId(id);

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
                            builder.buildName(text);

                            break;
                        case PRICE:
                            builder.buildPrice(Integer.parseInt(text));
                            break;
                        case ORIGIN:
                            builder.buildOrigin(text);
                            break;
                        case CRITICAL:
                            builder.buildCritical(Boolean.parseBoolean(text));
                            break;
                        case PORT:
                            builder.buildGetType().setPort(text);
                            break;
                        case GROUP:
                            builder.buildGetType().setGroup(text);
                            break;
                        case COOLER:
                            builder.buildGetType().setCooler(Boolean.parseBoolean(text));
                            break;
                        case USE_POWER:
                            builder.buildGetType().setUsePower(Integer.parseInt(text));
                            break;
                        case PERIPHERAL:
                            builder.buildGetType().setPeripheral(Boolean.parseBoolean(text));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elName = DeviceTagName.getElementTagName(reader.getLocalName());
                    switch (elName) {
                        case DEVICE:
                            devices.add(builder.buildDevice());
                    }
            }
        }
        return devices;
    }
}
