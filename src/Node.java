public class Node {
    private Node left,right;
    private String value;

    Node(){
        left = null;
        right = null;
        value = new String();
    }
   @Override
   public String toString() {
       return value;
   }


    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getValue() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
