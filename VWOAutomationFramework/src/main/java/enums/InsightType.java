package enums;

public enum InsightType {
    CLICK_HEATMAP("Click Heatmap"),
    SCROLL_HEATMAP("Scroll Heatmap"),
    MOVE_HEATMAP("Move Heatmap"),
    SESSION_RECORDING("Session Recording"),
    FUNNEL_ANALYSIS("Funnel Analysis"),
    SURVEY("On-page Survey");

    private final String displayName;

    InsightType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
