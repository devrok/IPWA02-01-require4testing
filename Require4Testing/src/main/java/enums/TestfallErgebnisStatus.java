package enums;

public enum TestfallErgebnisStatus {
	
	NICHT_AUSGEFUEHRT("Nicht ausgeführt", "info"),
    BESTANDEN("Bestanden", "success"),
    FEHLGESCHLAGEN("Fehlgeschlagen", "danger");
	
    private final String label;
    private final String severity;

    TestfallErgebnisStatus(String label, String severity) {
        this.label = label;
		this.severity = severity;
        
    }

    public String getLabel() {
        return label;
    }

	public String getSeverity() {
		return severity;
	}
}
