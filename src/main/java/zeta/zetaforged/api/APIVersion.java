package zeta.zetaforged.api;


public class APIVersion {
    private String string;
    public APIVersion(String version) {
        this.string = version;
    }

    public String APIVersionGet() {
        if(string.startsWith("1.0")) {
            return "0.0.1";
        } else if (string.startsWith("1.1")) {
            return "1.0.0";
        } else {
            return null;
        }

    }
}
