
public class Book {
    String bName;
    String authorName;
    double price;
    String type;
    long ISBN;

    public Book(String bName, String authorName, double price, String type) {
        this.bName = bName;
        this.authorName = authorName;
        this.price = price;
        this.type = type;
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

    static Node getHead() {
        return Book.head;
    }

    public static void setHead(Node head) {
        Book.head = head;
    }

    public String getbName() {

        return bName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long l) {
        this.ISBN = l;
    }

}
