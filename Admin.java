
import java.util.Scanner;

/*
 * ---- admin class ----
 * 
 * logIn()
 * addedBook()
 * addBook()
 * removeBook() -1.By Name -2.By ISBN
 * updateBook() -By Name
 * searchBook() -By Name
 * displayBook()
 * 
 */
public class 
Admin {

    String upperline = "\u203E";
    String underline = "\u001B[4m";
    String reset = "\u001B[0m";
    String green = "\u001B[32m";
    String yellow = "\u001B[33m";
    String blue = "\u001B[34m";
    String magenta = "\u001B[35m";
    String cyan = "\u001B[36m";
    String white = "\u001B[37m";
    String red = "\u001B[91m";
    String brightGreen = "\u001B[92m";

    Scanner sc = new Scanner(System.in);
    String a_name;
    String pass;
    final String admin_pass = "1";
    static long i = 978000001;

    // function for admin log in
    void logIn() {
        System.out.print("Enter Admin Name     : ");
        a_name = sc.nextLine();

        System.out.print("Enter Admin Password(Currently enter 1) : ");
        pass = sc.nextLine();
        if (pass.equals(admin_pass)) {
            System.out.println(yellow
                    + "*********************************************************************************************************\n");
            System.out.println(
                    "****************                   " + reset + " Welcome " + a_name + " To Your Online Shop    "
                            + yellow + "                ****************");
            System.out.println(
                    "\n*********************************************************************************************************"
                            + reset);

        } else {
            System.out.println(red + "INVALID PASSWORD!" + reset);
            logIn();
        }
    }

    Book.Node head = Book.getHead();

    void addedBook(String bName, String authorName, double price, String type) {
        Book book = new Book(bName, authorName, price, type);
        book.setISBN(i++);
        Book.Node newNode = book.new Node(book);

        if (head == null) {
            head = newNode;
        } else {
            Book.Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        Book.setHead(head);
    }

    // function to add book in store
    void addBook() {
        sc.nextLine();
        System.out.println(cyan + "\n------ ISBN No. : " + i + "------" + reset);
        System.out.print("Enter Book Name   : ");
        String name = sc.nextLine();
        System.out.print("Enter Author Name : ");
        String author = sc.nextLine();
        System.out.print("Enter Book Type   : ");
        String type = sc.nextLine();
        System.out.print("Enter Price       : ");
        double price = 0;
        try {
            price = sc.nextDouble();
        } catch (Exception e) {
            System.out.println(red + "\n-------------*Input must be of numeric type" + reset);
            addBook();
        }

        Book book = new Book(name, author, price, type);
        book.setISBN(i++);
        Book.Node newNode = book.new Node(book);

        if (head == null) {
            head = newNode;
        } else {
            Book.Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        Book.setHead(head);
    }

    // function to remove book from store
    void removeBook() {
        if (head == null) {
            System.out.println(red + "\n<<< There is no book to remove ... >>> " + reset);
        } else {
            System.out.println("What do You Want To Enter \n  1.Book Name\n  2.Book ISBN  ");
            System.out.println(cyan + "----Enter Your Choice : " + reset);
            int c = 0;
            try {
                c = sc.nextInt();
            } catch (Exception e) {
                System.out.println(red + "\n-------------*Input must be of integer type" + reset);
            }

            long no;
            String bookName;

            if (c == 1) {
                sc.nextLine();
                System.out.print("Enter Book Name : ");
                bookName = sc.nextLine();

                if (head == null) {
                    System.out.println(red + "<<< There is no book to remove ... >>> " + reset);
                } else {
                    if (head.book.getbName().equalsIgnoreCase(bookName)) {
                        if (head.next == null) {
                            head = null;
                        } else {
                            head = head.next;
                            head.prev = null;
                            head.next.prev = null;
                        }
                        Book.setHead(head);
                        System.out.println("head =set");
                    } else {
                        try {
                            Book.Node temp = Book.getHead();
                            while (temp != null && !temp.book.getbName().equalsIgnoreCase(bookName)) {
                                temp = temp.next;
                            }

                            if (temp != null) {
                                temp.prev.next = temp.next;
                                if (temp.next != null) {
                                    temp.next.prev = temp.prev;
                                } else {
                                    temp.prev = null;
                                }
                            } else {
                                System.out.println(red + "Book Is Not There In Your Shop!" + reset);
                                return;
                            }
                            System.out.println(green + "Book has been deleted successfully" + reset);
                        } catch (Exception e) {
                            System.out.println(red + " <<< Something Went Wrong !! Please Try Again! >>> " + reset);
                            removeBook();
                        }
                    }
                }
            } else {
                System.out.print("Enter ISBN : ");
                no = sc.nextLong();
                if (head.book.getISBN() == no) {
                    if (head.next == null) {
                        head = null;
                    } else {
                        head = head.next;
                        head.prev = null;
                    }
                    Book.setHead(head);
                } else {
                    try {
                        Book.Node temp = head;
                        while (temp != null && temp.book.getISBN() != no) {
                            temp = temp.next;
                        }
                        if (temp != null) {
                            temp.prev.next = temp.next;
                            if (temp.next != null) {
                                temp.next.prev = temp.prev;
                            } else {
                                temp.prev = null;
                            }
                        } else {
                            System.out.println(red + "Book Is Not There In Your Shop!" + reset);

                            return;
                        }
                        System.out.println(green + "Book has been deleted successfully" + reset);

                    } catch (Exception e) {
                        System.out.println(red + " <<< Something Went Wrong !! Please Try Again! >>> " + reset);
                        removeBook();
                    }
                }
            }
        }
    }

    void updateBook() {

        if (head == null) {
            System.out.println("<<< There is no book to update ... >>>");
        } else {
            sc.nextLine();
            System.out.print("Enter Book Name : ");
            String bookName = sc.nextLine();
            Book.Node temp = head;
            boolean found = false;
            while (temp != null) {
                if (bookName.equalsIgnoreCase(temp.book.getbName())) {
                    Book book = temp.book;
                    sc.nextLine();
                    System.out.println(cyan + "=====================================");
                    System.out.println("===" + reset + "  Enter Below Details Of Book  " + cyan + "===");
                    System.out.println("=====================================" + reset);

                    System.out.print("\nBook Name        : ");
                    book.bName = sc.nextLine();
                    System.out.print("Book Author Name : ");
                    book.authorName = sc.nextLine();
                    System.out.print("Book Type        : ");
                    book.type = sc.nextLine();
                    System.out.print("Book ISBN No.    : ");
                    book.ISBN = sc.nextLong();
                    System.out.print("Book Price       : ");
                    book.price = sc.nextDouble();

                    System.out.println(green + "<<< Book Details Has Been Updated Successfully >>>\n" + reset);
                    found = true;
                }

                temp = temp.next;
            }
            if (temp == head) {
                System.out.println(red + "<<< " + bookName + " is not found >>> " + reset);
            }
            if (!found) {
                System.out.println(red + "<<< " + bookName + " is not found >>> " + reset);

            }
        }
    }

    void searchBook() {
        sc.nextLine();
        if (head == null) {
            System.out.println(red + "<<< There is no book to search ... >>>" + reset);
        } else {
            boolean bookFound = false;
            System.out.print("Enter Book Name : ");
            String bookName = sc.nextLine();
            Book.Node temp = head;
            while (temp != null) {
                if (bookName.equalsIgnoreCase(temp.book.getbName())) {
                    Book book = temp.book;
                    bookFound = true;
                    System.out.println("Book Name        : " + book.bName);
                    System.out.println("Book Author Name : " + book.authorName);
                    System.out.println("Book Type        : " + book.type);
                    System.out.println("Book ISBN No.    : " + cyan + book.ISBN + reset);
                    System.out.println("Book Price       : " + book.price + "\n");
                }
                temp = temp.next;
            }
            if (!bookFound) {
                System.out.println(red + "<<< " + bookName + " is not available >>> " + reset);
            }
        }

    }

    void displayBooks() {
        int i = 1;
        if (head == null) {
            System.out.println(red + "<<< There is no book to display ... >>>" + reset);
        } else {
            Book.Node temp = Book.getHead();
            while (temp != null) {
                Book book = temp.book;
                System.out.println(
                        yellow + "<----------<<<---------------+" + reset + " Book " + i + yellow
                                + " +--------------->>>---------->\n" + reset);

                System.out.println("          Title       : " + book.bName);
                System.out.println("          Author Name : " + book.authorName);
                System.out.println("          Type        : " + book.type);
                System.out.println("          ISBN No.    : " + cyan + book.ISBN + reset);
                System.out.println("          Price       : " + book.price + "\n");
                i++;
                temp = temp.next;
            }
        }
    }
}