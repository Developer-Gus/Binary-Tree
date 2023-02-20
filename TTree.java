public class TTree<KIND extends Comparable> {
        private Element<KIND> root;

        public TTree() {
            this.root = null;
        }

        public void add (KIND value) {
            //Creating an Element (KIND = generic)
            Element<KIND> newElement = new Element<KIND>(value);
            //If We don't have any item inside the root, We'll create a new element
            if (root == null) {
                this.root = newElement;
            }else{
                /* Actual is an element to verify the next biggest or smallest item and
                compare it with the root and after add to the right or the left */
                Element<KIND> actual = this.root;
                while(true) {
                    /* If there is an element on the left side to the root */
                    if (newElement.getValue().compareTo(actual.getValue()) == -1){
                        if (actual.getLeft() != null) {
                            actual = actual.getLeft();
                        }else{
                            /*If there isn't any value in the left actual,
                            It receives the new value (newElement)*/
                            actual.setLeft(newElement);
                            break;
                        }
                    }else{
                        if(actual.getRight() != null) {
                            actual = actual.getRight();
                        }else{
                            actual.setRight(newElement);
                            break;
                        }
                    }
                }
            }
        }

    public Element<KIND> getRoot() {
        return root;
    }

    public void inOrder(Element<KIND> actual) {
        if (actual != null) {
            inOrder(actual.getLeft());
            System.out.print(actual.getValue());
            inOrder(actual.getRight());
        }
    }

    public void preOrder(Element<KIND> actual) {
        if (actual != null) {
            System.out.print(actual.getValue());
            preOrder(actual.getLeft());
            preOrder(actual.getRight());
        }
    }
    
    public void postOrder(Element<KIND> actual) {
        if (actual != null) {
            postOrder(actual.getLeft());
            postOrder(actual.getRight());
            System.out.print(actual.getValue());
        }
    }

    public boolean remove(KIND value) {
            Element<KIND> actual = this.root;
            //When I would need to move the actual (root), I'll need to move his dad too (dadActual)
            Element<KIND> dadActual = null;
            while (actual != null) {
                if (actual.getValue().equals(value)) {
                    break;
                } else if (value.compareTo(actual.getValue()) == -1) { //value < actual
                    dadActual = actual;
                    actual.getLeft();
                } else {
                    dadActual = actual;
                    actual = actual.getRight();
                }
            }
            //Verify if there's an element
            if (actual == null) {

                //Verify if the tree has 2 leaf (children) or only has on the right
                if (actual.getRight() != null) {

                    Element<KIND> substitute = actual.getRight();
                    Element<KIND> dadSubstitute = actual;
                    //Searching for more leafs on the right part
                    while (substitute.getLeft() != null) {
                        dadSubstitute = substitute;
                        substitute = substitute.getLeft();
                    }
                    substitute.setLeft(actual.getLeft()); // That keeps the substitute
                    if (dadActual != null){
                        if (actual.getValue().compareTo(dadActual.getValue()) == -1) {
                            dadActual.setLeft(substitute);
                        } else {
                            dadActual.setRight(substitute);
                        }
                    } else { //If the tree doesn't have a dadActual, so it means that is the root
                        this.root = substitute;
                    }

                    //It removed the element of the tree
                    if (substitute.getValue().compareTo(dadSubstitute.getValue()) == -1) { //substitute < dadSubstitute
                        dadActual.setLeft(null);
                    } else {
                        dadSubstitute.setRight(null);
                    }

                } else if (actual.getLeft() != null) { //If it has leafs only on the left part
                    Element<KIND> substitute = actual.getLeft();
                    Element<KIND> dadSubstitute = actual;
                    //Searching for more leafs on the right part
                    while (substitute.getRight() != null) {
                        dadSubstitute = substitute;
                        substitute = substitute.getRight();
                    }
                    if (dadActual != null) {
                        if (actual.getValue().compareTo(dadActual.getValue()) == -1) { //Changing the substitute until We set(null) it
                            dadActual.setLeft(substitute);
                        } else {
                                dadActual.setRight(substitute);
                        }
                    } else {
                        this.root = substitute;
                    }

                    //It removed the element of the tree
                    if (substitute.getValue().compareTo(dadSubstitute.getValue()) == -1) { //substitute < dadSubstitute
                        dadActual.setLeft(null);
                    } else {
                        dadSubstitute.setRight(null);
                    }

                } else { //it doesn't have any leafs
                    if (dadActual != null) {
                        if (actual.getValue().compareTo(dadActual.getValue()) == -1) { //value < dadActual
                            dadActual.setLeft(null);
                        } else {
                            dadActual.setRight(null);
                        }
                    } else { //If the searching value of the tree has only the root
                        this.root = null;
                    }
                }
                return true;
            } else {
                return false;
            }
    }
}
