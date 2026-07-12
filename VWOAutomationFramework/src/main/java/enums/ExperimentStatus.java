package enums;

public enum ExperimentStatus {
    DRAFT("Draft"),
    RUNNING("Running"),
    PAUSED("Paused"),
    STOPPED("Stopped"),
    WINNER_DECLARED("Winner Declared");

    private final String displayName;

    ExperimentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
