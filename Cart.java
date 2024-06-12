
public class Cart {
    Book book;
    Cart cart;
    static int cart_id;

    public Cart(int cart_id) {
        Cart.cart_id = cart_id;
    }

    class Node {
        Node next;
        Node prev;
        Book book;
        Customer customer;

        Node(Book book) {
            this.book = book;
            this.next = null;
            this.prev = null;
        }

    }

    static Node head;

    public static int getCart_id() {
        return cart_id;
    }

    Node getHead(int id) {
        return Cart.head;
    }

    public void setHead(Node cartHead) {
        Cart.head = cartHead;
    }

    public static void setCart_id(int cart_id) {
        Cart.cart_id = cart_id;
    }

}
