package pl.zdjavapol140.carrental.model;


public enum Position {

    MANAGER("manager"),
    EMPLOYEE("employee");

    private String displayPosition;

    Position(String displayPosition) {
        this.displayPosition = displayPosition;
    }

    public String getDisplayPosition() {
        return displayPosition;
    }
}
