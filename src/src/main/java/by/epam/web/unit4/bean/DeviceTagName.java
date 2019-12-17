package by.epam.web.unit4.bean;

public enum DeviceTagName {
    NAME, ORIGIN, PRICE, CRITICAL, PERIPHERAL, USE_POWER, COOLER, GROUP, PORT, TYPES, DEVICE, DEVICES;

    public static DeviceTagName getElementTagName(String localName) {
        switch (localName){
            case "device": return DEVICE;
            case "name": return NAME;
            case "origin": return ORIGIN;
            case "price":return PRICE;
            case "critical":return CRITICAL;
            case "peripheral":return PERIPHERAL;
            case "use-power":return USE_POWER;
            case "cooler":return COOLER;
            case "group":return GROUP;
            case "port":return PORT;
            case "types":return TYPES;
            case "devices":return DEVICES;
            default: throw new EnumConstantNotPresentException(DeviceTagName.class, localName);

        }
    }
}
