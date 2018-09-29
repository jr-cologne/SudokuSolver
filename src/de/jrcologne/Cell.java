package de.jrcologne;

class Cell {

    private String value;
    private boolean fixed;

    Cell() {
        this.value = " ";
        this.fixed = false;
    }

    String getValue() {
        return this.value;
    }

    void setValue(String value) {
        if (!this.fixed) {
            this.value = value;
        }
    }

    void fix() {
        this.fixed = true;
    }

}
