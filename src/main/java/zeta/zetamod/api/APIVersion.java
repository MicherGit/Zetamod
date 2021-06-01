package zeta.zetamod.api;

@Deprecated
public class APIVersion {
    private String string;
    public APIVersion(String version) {
        this.string = version;
    }

    public String APIVersionGet() {
        if(string.startsWith("1.0")) {
            return "0.0.2";
        } else if (string.startsWith("1.1.")) {
            return "1.0.0";
        } else if (string.startsWith("0.50.0")) {
            return "0.0.0";
        } else if (string.startsWith("0.50.1")) {
            return "0.0.1";
        } else if (string.startsWith("1.1.1")) {
            return "1.0.1";
        } else {
            return null;
        }

    }
}
