package enums;

public enum ExperimentType {
    AB_TEST("A/B Test"),
    SPLIT_URL("Split URL"),
    MULTIVARIATE("Multivariate");

    private final String displayName;

    ExperimentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
