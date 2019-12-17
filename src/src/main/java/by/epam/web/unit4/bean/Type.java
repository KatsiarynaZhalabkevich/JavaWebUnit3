package by.epam.web.unit4.bean;

public class Type {
    private boolean peripheral;
    private int usePower;
    private boolean cooler;
    private String group ;
    private String port ;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }

    public int getUsePower() {
        return usePower;
    }

    public void setUsePower(int usePower) {
        this.usePower = usePower;
    }

    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    @Override
    public String toString() {
        return "Type{" +
                "peripheral=" + peripheral +
                ", usePower=" + usePower +
                ", cooler=" + cooler +
                ", group='" + group + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
