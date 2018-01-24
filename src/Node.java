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
   public static Node findleft(Node n){
        if(Character.isDigit(n.getLeft().getValue().charAt(0)))
            return n.getLeft();
            return findleft(n.getLeft());
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
