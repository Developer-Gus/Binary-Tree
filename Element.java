public class Element<KIND> {
    private KIND value;
    private Element left;
    private Element right;

    public Element (KIND newValue){
        this.value = newValue;
        this.right = null;
        this.left = null;
    }

    public KIND getValue() {
        return value;
    }

    public void setValue(KIND value) {
        this.value = value;
    }

    public Element getLeft() {
        return left;
    }

    public void setLeft(Element left) {
        this.left = left;
    }

    public Element getRight() {
        return right;
    }

    public void setRight(Element right) {
        this.right = right;
    }
}
