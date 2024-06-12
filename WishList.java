
public class WishList {
    Book book;
    Cart cart;
    int w_id;

    public WishList(int w_id) {
        this.w_id = w_id;
    }

    class Node {
        Node next;
        Node prev;
        Book book;

        Node(Book book) {
            this.book = book;
            this.next = null;
            this.prev = null;
        }

    }

    static Node head;

    public static void setHead(Node wHead) {
        WishList.head = wHead;
    }

    static Node getHead(int id) {
        return WishList.head;
    }

}
