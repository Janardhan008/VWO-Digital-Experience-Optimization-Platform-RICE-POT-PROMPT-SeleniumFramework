package enums;

public enum AudienceType {
    GEOGRAPHY("Geography"),
    BEHAVIOR("Behavior"),
    DEVICE("Device Type"),
    CUSTOM_ATTRIBUTE("Custom Attribute"),
    URL("URL Pattern"),
    RECURRING("Returning Visitor");

    private final String displayName;

    AudienceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
