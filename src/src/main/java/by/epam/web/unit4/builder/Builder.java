package by.epam.web.unit4.builder;

import by.epam.web.unit4.bean.Device;
import by.epam.web.unit4.bean.Type;

public class Builder {
    private int id;
    private String name;
    private String origin;
    private int price;
    private boolean critical;
    private Type type;
    private Device device;

    public Builder(){
        type = new Type();
        device = new Device();
    }

    public Builder buildId(int id) {
        this.id = id;
        return this;
    }

    public Builder buildName(String name) {
        this.name = name;
        return this;
    }

    public Builder buildOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public Builder buildPrice(int price) {
        this.price = price;
        return this;
    }

    public Builder buildCritical (boolean critical){
        this.critical = critical;
        return  this;
    }
    public Builder buildType(Type type){
        this.type = type;
        return this;
    }
    public Type buildGetType(){
        return type;
    }

    public Device buildDevice(){

        device.setId(id);
        device.setName(name);
        device.setOrigin(origin);
        device.setPrice(price);
        device.setCritical(critical);
        device.setType(type);
        return device;
    }
}
