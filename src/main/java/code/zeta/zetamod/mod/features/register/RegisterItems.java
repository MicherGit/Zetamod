package code.zeta.zetamod.mod.features.register;

public class RegisterItems {
    public RegisterItems(){}
    public void registerItems() {
        // Register keystone item
        RegisterKeystone keystone = new RegisterKeystone();
        keystone.registerItem();
    }
}
