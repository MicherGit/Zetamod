package zeta.zetamod.api;

@Deprecated
public class API extends APIVersion {
    private String version;
    public API(String version) {
        super(version);
        this.version = version;
    }
    @Override
    public String APIVersionGet() {
        return super.APIVersionGet();
    }
}
