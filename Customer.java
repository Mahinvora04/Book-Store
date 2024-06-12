
/*
1.  View Books----------------------
2.  Search Book --------------------
3.  View My Cart--------------------
4.  Add Book To Cart ---------------
5.  To Update Cart------------------
6.  View My WishList ---------------
7.  Add Book To WishList------------
8.  To Update WishList--------------
9.  Place Order---------------------
10. View Profile -------------------
11. Update My Profile---------------
*/
import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Customer {
    static Scanner sc = new Scanner(System.in);
    String upperline = "\u203E";
    String underline = "\u001B[4m";
    static String reset = "\u001B[0m";
    static String red = "\u001B[31m";
    String green = "\u001B[32m";
    String yellow = "\u001B[33m";
    String blue = "\u001B[34m";
    String magenta = "\u001B[35m";
    static String cyan = "\u001B[36m";
    String white = "\u001B[37m";
    String brightRed = "\u001B[91m";
    String brightGreen = "\u001B[92m";

    static HashMap<Integer, Customer> customers = new HashMap<>();

    String c_name;
    String c_pass, confirm_pass;
    int c_id;
    int cartId;
    String c_address;
    String ph_no;
    String userID;
    int pincode;
    Book.Node head = Book.getHead();
    Cart.Node cartHeadCust;
    WishList.Node wHeadCust;
    static Cart cartCust;
    static WishList wCust;

    public Customer(String c_name, String c_pass, int c_id, int cartId, String c_address, int pincode, String ph_no,
            String userID, Cart cart, WishList wishlist) {
        this.c_name = c_name;
        this.c_pass = c_pass;
        this.c_id = c_id;
        this.cartId = cartId;
        this.c_address = c_address;
        this.pincode = pincode;
        this.ph_no = ph_no;
        this.userID = userID;
        Customer.cartCust = cart;
        Customer.wCust = wishlist;
    }

    public static Customer signIn(int c_id, Cart cart, WishList wishlist) {
        System.out.print(cyan + "\n***** Enter Your Deteils *****\n" + reset);
        System.out.print("Enter Name                 : ");
        String c_name = sc.nextLine();
        System.out.print("Enter Age                  : ");
        try {
            int age = sc.nextInt();
        } catch (Exception e) {
            System.out.println(red + "-------------*Input must be of integer type" + reset);
            sc.nextLine();
            signIn(c_id, cart, wishlist);
        }
        sc.nextLine();
        Console console = System.console();
        char[] c_passwordArray = console.readPassword("Enter Your Password        : ");
        String c_pass = new String(c_passwordArray);

        String confirm_pass = "";
        do {
            char[] cc_passwordArray = console.readPassword("Enter Confirm Password     : ");
            confirm_pass = new String(cc_passwordArray);

            if (!c_pass.equals(confirm_pass)) {
                System.out.println(red + "Your Password and Confirm Password Must Be Same !" + reset);
            }
        } while (!c_pass.equals(confirm_pass));
        System.out.print("Enter Address              : ");
        String c_address = sc.nextLine();
        System.out.print("Enter Pincode              : ");
        int pincode = sc.nextInt();
        System.out.print("Enter Phone Number         : ");
        String ph_no = sc.nextLine();
        String userID = c_name.split(" ")[0] + (int) (Math.random() * 100);
        System.out.println("\n\n------------------------------");
        System.out.println(cyan + "YOUR USERID : " + userID + reset);
        System.out.println("------------------------------\n");
        cartCust = cart;
        wCust = wishlist;

        Customer customer = new Customer(c_name, c_pass, c_id, c_id, c_address, pincode, ph_no, userID, cart, wishlist);

        customers.put(c_id, customer);

        return customer;
    }

    static Customer logIn(String uId, String pass) {
        for (Map.Entry<Integer, Customer> cust : customers.entrySet()) {
            Customer i = cust.getValue();
            if (uId.equalsIgnoreCase(i.userID) && pass.equals(i.c_pass)) {
                return i;
            }
        }
        return null;
    }

    public String getUserID() {
        return userID;
    }

    public void setC_id(int i) {
        this.c_id = i;

    }

    public String getC_name() {
        return c_name;
    }

    public String getC_pass() {
        return c_pass;
    }

    public int getC_id() {
        return c_id;
    }

    public String getC_address() {
        return c_address;
    }

    public int getPincode() {
        return pincode;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setCartId(int c_id) {
        this.cartId = c_id;
    }

    public int getCartID() {
        return cartId;
    }

    public Cart getCartCust() {
        return cartCust;
    }

    public void setCartCust(Cart cartCust) {
        Customer.cartCust = cartCust;
    }

    public Cart.Node getCartHeadCust() {
        return cartHeadCust;
    }

    public void setCartHeadCust(Cart.Node cartHeadCust) {
        this.cartHeadCust = cartHeadCust;
    }

    public static WishList getwCust() {
        return wCust;
    }

    public static void setwCust(WishList wCust) {
        Customer.wCust = wCust;
    }

    public void setwHeadCust(WishList.Node wHeadCust) {
        this.wHeadCust = wHeadCust;
    }

    private WishList.Node getwHeadCust() {
        return wHeadCust;
    }

    // viewBooks() --> Shows all books to customers
    public void viewBooks() {
        int i = 1;
        if (head == null) {
            System.out.println(red + "<<< There is no book to display ... >>>" + reset);
        } else {
            Book.Node temp = head;
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
            System.out.println(yellow
                    + "---------------------------------------------------------------------------------------------------------"
                    + reset);
        }
    }

    public void searchBook() {
        System.out.println(
                "\n1. Search By ISBN \n2. Search By Book Name \n3. Search by Author Name \n4. Search By Type \n5. Filter By Price");
        System.out.print(cyan + "----Enter Your Choice : " + reset);
        int c = 0;
        try {
            c = sc.nextInt();
        } catch (Exception e) {
            System.out.println(red + "\n-------------*Input must be of integer type" + reset);
            searchBook();
        }
        if (head == null) {
            System.out.println(red + "<<< There is no book to search ... >>>" + reset);
        } else {
            boolean bookFound = false;

            Book.Node temp = Book.getHead();

            switch (c) {
                case 1:
                    System.out.print("Enter ISBN of Book : ");
                    int isbn = 0;
                    try {
                        isbn = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println(red + "\n-------------*Input must be of integer type" + reset);
                        searchBook();
                    }
                    while (temp != null) {
                        if (isbn == temp.book.getISBN()) {
                            Book book = temp.book;
                            bookFound = true;
                            getBookDetails(book);
                        }
                        temp = temp.next;
                    }
                    if (!bookFound) {
                        System.out.println(red + "\n<<< " + isbn + " book is not available >>>\n " + reset);
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter Name of Book : ");
                    String bookName = sc.nextLine();
                    ;
                    while (temp != null) {
                        if (bookName.equalsIgnoreCase(temp.book.getbName())) {
                            Book book = temp.book;
                            bookFound = true;
                            getBookDetails(book);
                        }
                        temp = temp.next;
                    }
                    if (!bookFound) {
                        System.out.println(red + "\n<<< " + bookName + " is not available >>>\n " + reset);
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Enter Author Name of Book : ");
                    String auth_name = sc.nextLine();
                    ;
                    while (temp != null) {
                        if (auth_name.equalsIgnoreCase(temp.book.getAuthorName())) {
                            Book book = temp.book;
                            bookFound = true;
                            getBookDetails(book);
                        }
                        temp = temp.next;
                    }
                    if (!bookFound) {
                        System.out.println(red + "\n<<< " + auth_name + "'s book is not available >>>\n " + reset);
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Enter Type of Book : ");
                    String type = sc.nextLine();
                    ;
                    while (temp != null) {
                        if (type.equalsIgnoreCase(temp.book.getType())) {
                            Book book = temp.book;
                            bookFound = true;
                            getBookDetails(book);
                        }
                        temp = temp.next;
                    }
                    if (!bookFound) {
                        System.out.println(red + "\n<<< " + type + "s are not available >>>\n " + reset);
                    }
                    break;
                case 5:
                    System.out.print("Enter Starting Price of Book : ");
                    double st_price = sc.nextDouble();
                    System.out.print("Enter Ending Price of Book   : ");
                    double end_price = sc.nextDouble();

                    while (temp != null) {
                        if (temp.book.getPrice() > +st_price && temp.book.getPrice() <= end_price) {
                            Book book = temp.book;
                            bookFound = true;
                            getBookDetails(book);
                        }
                        temp = temp.next;
                    }
                    if (!bookFound) {
                        System.out
                                .println(red + "\n<<<  Book not available in " + st_price + " - " + end_price
                                        + " range >>>\n " + reset);
                    }
                    break;
                case 6:
                    System.out.println(red + "\n<<< Invalid choice! Please Try Again !! >>>\n" + reset);
                    searchBook();
                    break;
            }
        }

    }

    public void viewCart(Customer customer) {
        int i = 1;
        // Cart.Node cartHead = customer.getCartCust().getHead(customer.getCartID());
        Cart.Node cartHead = customer.getCartHeadCust();

        if (cartHead == null) {
            System.out.println(red + "\n<<< There is no book to display in your cart... >>>\n" + reset);
        } else {
            // for (Map.Entry<Integer, Cart> cart : carts.entrySet()) {

            // if (customer.getCartCust().getKey() == customer.getC_id()) {
            // System.out.println(cart.getKey() + "---------------------");
            // Cart.Node temp = customer.getCartCust().getHead(customer.getCartID()); //
            Cart.Node temp = customer.getCartHeadCust();
            // int q = Cart.getCart_id();
            // boolean found = true;
            // while (found) {
            // System.out.println(customer.getCartID());
            // System.out.println(q);
            // if (customer.getCartID() == Cart.getCart_id()) {
            while (temp != null) {
                Book book = temp.book;

                System.out.println(
                        yellow + "<----------<<<---------------+" + reset + " Book " + i + yellow
                                + " +--------------->>>---------->\n" + reset);

                System.out.println("          Title       : " + book.bName);
                System.out.println("          Author Name : " + book.authorName);
                System.out.println("          Type        : " + book.type);
                System.out.println("          ISBN No.    : " + cyan + book.ISBN + reset);
                System.out.println("          Price       : " + book.price);
                i++;
                // }
                temp = temp.next;
            }
            // found = false;
            // }
            // }
            // q = Cart.getCart_id() + 1;
            // }
        }
    }

    public boolean addToCart(Customer customer) {
        // customer.setCartId(customer.getCartID());
        sc.nextLine();

        boolean bookFound = false;
        System.out.print("Enter Book Name : ");
        String bookName = sc.nextLine();

        Book.Node temp = Book.getHead();
        // Cart.Node cartHead = cartCust.getHead(customer.getCartID());
        Cart.Node cartHead = customer.getCartHeadCust();

        while (temp != null) {
            if (bookName.equalsIgnoreCase(temp.book.getbName())) {
                Book book = temp.book;

                // Cart.Node temp1 = customer.getCartHeadCust();
                // while (temp1 != null) {
                // Book book1 = temp.book;
                // if (book1.getISBN() == book.getISBN()) {
                // book.++;
                // book.price = book.price + book.price;
                // return true;
                // }
                // temp1 = temp1.next;
                // }

                bookFound = true;
                System.out.println("\nBook Name        : " + book.bName);
                System.out.println("Book Author Name : " + book.authorName);
                System.out.println("Book Type        : " + book.type);
                System.out.println("Book ISBN No.    : " + cyan + book.ISBN + reset);
                System.out.println("Book Price       : " + book.price + "\n");
                System.out.println();
                Cart.Node newNode = cartCust.new Node(book);

                if (cartHead == null) {
                    cartHead = newNode;
                    // cartCust.setHead(cartHead);
                    customer.setCartHeadCust(cartHead);
                } else {
                    Cart.Node cartTemp = cartHead;
                    while (cartTemp.next != null) {
                        cartTemp = cartTemp.next;
                    }
                    cartTemp.next = newNode;
                    newNode.prev = cartTemp;
                }
                return true;
            }
            temp = temp.next;
        }
        if (!bookFound) {
            System.out.println(red + "\n<<< " + bookName + " is not available >>>\n " + reset);
        }

        return false;
    }

    // just printing particular book details
    public void getBookDetails(Book book) {
        System.out.println("\nBook Name        : " + book.bName);
        System.out.println("Book Author Name : " + book.authorName);
        System.out.println("Book Type        : " + book.type);
        System.out.println("Book ISBN No.    : " + cyan + book.ISBN + reset);
        System.out.println("Book Price       : " + book.price + "\n");
    }

    public boolean updateCart(Cart cart, Customer customer) {
        System.out.println("1. Add Book To Cart \n2. Remove Book From Cart \n3. Empty Cart");
        int c = 0;
        try {
            c = sc.nextInt();
        } catch (Exception e) {
            System.out.println(red + "\n-------------*Input must be of integer type" + reset);
            updateCart(cart, customer);
        }
        switch (c) {
            case 1:
                if (addToCart(customer)) {
                    System.out.println(green + "Book Has Been Added To Cart " + reset);
                    return true;
                }
                break;
            case 2:
                if (removeFromCart(cart, customer)) {
                    System.out.println(green + "Book Has Been Deleted From Cart " + reset);
                    return true;
                }
                break;
            case 3:
                // cart.setHead(null);
                customer.setCartHeadCust(null);
                System.out.println(green + "Your cart is empty now!" + reset);
                break;
            default:
                System.out.println(red + "Please Enter Valid Choice !" + reset);
                updateCart(cart, customer);
                break;

        }
        return false;
    }

    public boolean removeFromCart(Cart cart, Customer customer) {
        Cart.Node cartHead = customer.getCartHeadCust();
        if (cartHead == null) {
            System.out.println(red + "\n<<< There is no book to remove ... >>> " + reset);
        } else {
            System.out.println("What do You Want To Enter \n 1. Book Name \n  2. Book ISBN  ");
            System.out.print("----Enter Your Choice : ");
            int c = 0;
            try {
                c = sc.nextInt();
            } catch (Exception e) {
                System.out.println(red + "\n-------------*Input must be of integer type" + reset);
                removeFromCart(cart, customer);
            }

            long no;
            String bookName;

            if (c == 1) {
                sc.nextLine();
                System.out.print("Enter Book Name : ");
                bookName = sc.nextLine();

                if (head == null) {
                    System.out.println(red + "\n<<< There is no book to remove ... >>> " + reset);
                } else {
                    if (cartHead.book.getbName().equalsIgnoreCase(bookName)) {
                        if (cartHead.next == null) {
                            cartHead = null;
                        } else {
                            cartHead = cartHead.next;
                            cartHead.prev = null;
                            // cartHead.next.prev = null;
                        }
                        cart.setHead(cartHead);
                        customer.setCartHeadCust(cartHead);
                        System.out.println(green + "Book has been deleted successfully" + reset);
                        return true;
                    } else {
                        try {
                            Cart.Node temp = customer.getCartHeadCust();
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
                                System.out.println(red + "Book Is Not There In Your Cart!" + reset);
                                return false;
                            }
                            System.out.println(green + "Book has been deleted successfully" + reset);
                            return true;
                        } catch (Exception e) {
                            // e.printStackTrace();
                            // System.out.println(" <<< Book Not Found!! >>> ");
                        }
                    }
                }
            } else {
                System.out.print("Enter ISBN : ");
                no = sc.nextLong();
                if (cartHead.book.getISBN() == no) {
                    if (cartHead.next == null) {
                        cartHead = null;
                    } else {
                        cartHead = cartHead.next;
                        cartHead.prev = null;
                    }
                    cart.setHead(cartHead);
                    customer.setCartHeadCust(cartHead);
                    System.out.println(green + "Book has been deleted successfully" + reset);
                    return true;
                } else {
                    try {
                        Cart.Node temp = cartHead;
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
                            System.out.println(red + "Book Is Not There In Your Cart!" + reset);

                            return false;
                        }
                        System.out.println(green + "Book has been deleted successfully" + reset);
                        return true;

                    } catch (Exception e) {
                        System.out.println(red + " <<< Something Went Wrong !! Please Try Again! >>> " + reset);
                    }
                }
            }
        }
        return false;
    }

    // public void viewWishList(Customer customer) {
    // int i = 1;
    // WishList.Node wHead = customer.getwHeadCust();
    // if (wHead == null) {
    // System.out.println(red + "<<< There is no book in your wishlist to display
    // ... >>>" + reset);
    // } else {
    // WishList.Node temp = customer.getwHeadCust();
    // while (temp != null) {
    // Book book = temp.book;
    // System.out.println(
    // yellow + "<----------<<<---------------+" + reset + " Book " + i + yellow
    // + " +--------------->>>---------->\n" + reset);

    // System.out.println(" Title : " + book.bName);
    // System.out.println(" Author Name : " + book.authorName);
    // System.out.println(" Type : " + book.type);
    // System.out.println(" ISBN No. : " + cyan + book.ISBN + reset);
    // System.out.println(" Price : " + book.price + "\n");
    // i++;
    // temp = temp.next;
    // }
    // }
    // }

    public void viewWishList(Customer customer) {
        int i = 1;
        // Cart.Node cartHead = customer.getCartCust().getHead(customer.getCartID());
        WishList.Node wHead = customer.getwHeadCust();

        if (wHead == null) {
            System.out.println(red + "\n<<< There is no book to display in your cart... >>>\n" + reset);
        } else {
            // for (Map.Entry<Integer, Cart> cart : carts.entrySet()) {

            // if (customer.getCartCust().getKey() == customer.getC_id()) {
            // System.out.println(cart.getKey() + "---------------------");
            // Cart.Node temp = customer.getCartCust().getHead(customer.getCartID()); //
            WishList.Node temp = customer.getwHeadCust();
            // int q = Cart.getCart_id();
            // boolean found = true;
            // while (found) {
            // System.out.println(customer.getCartID());
            // System.out.println(q);
            // if (customer.getCartID() == Cart.getCart_id()) {
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
                // }
                temp = temp.next;
            }
            // found = false;
            // }
            // }
            // q = Cart.getCart_id() + 1;
            // }
        }
    }

    // public boolean addToWishList(Customer customer) {
    // sc.nextLine();

    // boolean bookFound = false;
    // System.out.print("Enter Book Name : ");
    // String bookName = sc.nextLine();

    // Book.Node temp = Book.getHead();
    // WishList.Node wHead = customer.getwHeadCust();

    // while (temp != null) {
    // if (bookName.equalsIgnoreCase(temp.book.getbName())) {
    // Book book = temp.book;
    // bookFound = true;
    // System.out.println("Book Name : " + book.bName);
    // System.out.println("Book Author Name : " + book.authorName);
    // System.out.println("Book Type : " + book.type);
    // System.out.println("Book ISBN No. : " + cyan + book.ISBN + reset);
    // System.out.println("Book Price : " + book.price + "\n");

    // WishList.Node newNode = customer.getwHeadCust();

    // if (wHead == null) {
    // wHead = newNode;
    // WishList.setHead(wHead);
    // customer.setwHeadCust(wHead);
    // System.out.println("Wishlist HEAD SET");
    // } else {
    // WishList.Node wTemp = wHead;
    // while (wTemp.next != null) {
    // wTemp = wTemp.next;
    // }
    // wTemp.next = newNode;
    // newNode.prev = wTemp;
    // }
    // return true;
    // }
    // temp = temp.next;
    // }
    // if (!bookFound) {
    // System.out.println("<<< " + bookName + " is not available >>> ");
    // }

    // return false;
    // }

    public boolean addToWishList(Customer customer) {
        // customer.setCartId(customer.getCartID());
        sc.nextLine();

        boolean bookFound = false;
        System.out.print("Enter Book Name : ");
        String bookName = sc.nextLine();

        Book.Node temp = Book.getHead();
        // Cart.Node cartHead = cartCust.getHead(customer.getCartID());
        WishList.Node wHead = customer.getwHeadCust();

        while (temp != null) {
            if (bookName.equalsIgnoreCase(temp.book.getbName())) {
                Book book = temp.book;
                bookFound = true;
                System.out.println("\nBook Name        : " + book.bName);
                System.out.println("Book Author Name : " + book.authorName);
                System.out.println("Book Type        : " + book.type);
                System.out.println("Book ISBN No.    : " + cyan + book.ISBN + reset);
                System.out.println("Book Price       : " + book.price + "\n");

                WishList.Node newNode = wCust.new Node(book);

                if (wHead == null) {
                    wHead = newNode;
                    // wCust.setHead(wHead);
                    customer.setwHeadCust(wHead);
                } else {
                    WishList.Node wishlistHead = wHead;
                    while (wishlistHead.next != null) {
                        wishlistHead = wishlistHead.next;
                    }
                    wishlistHead.next = newNode;
                    newNode.prev = wishlistHead;
                }
                return true;
            }
            temp = temp.next;
        }
        if (!bookFound) {
            System.out.println(red + "\n<<< " + bookName + " is not available >>>\n " + reset);
        }

        return false;
    }

    public boolean updateWishList(WishList wishlist, Customer customer) {
        System.out.println("1. Add Book To WishList \n2. Remove Book From WishList \n3. Empty WishList");
        int c = 0;
        try {
            c = sc.nextInt();
        } catch (Exception e) {
            System.out.println(red + "\n-------------*Input must be of integer type" + reset);
            addToWishList(customer);
        }
        switch (c) {
            case 1:
                if (addToWishList(customer)) {
                    System.out.println(green + "Book Has Been Added To WishList " + reset);
                    return true;
                }
                break;
            case 2:
                if (removeFromWishList(wishlist, customer)) {
                    System.out.println(green + "Book Has Been Deleted From WishList " + reset);
                    return true;
                }
                break;
            case 3:
                customer.setwHeadCust(null);
                System.out.println(green + "Your wishlist is empty now!" + reset);
                break;
            default:
                break;

        }
        return false;
    }

    public boolean removeFromWishList(WishList wishList, Customer customer) {
        WishList.Node wHead = customer.getwHeadCust();
        if (wHead == null) {
            System.out.println(red + "<<< There is no book to remove ... >>> " + reset);
        } else {
            System.out.println("What do You Want To Enter \n  1. Book Name \n  2. Book ISBN ");
            System.out.println(cyan + "----Enter Your Choice : " + reset);
            int c = 0;
            try {
                c = sc.nextInt();
            } catch (Exception e) {
                System.out.println(red + "\n-------------*Input must be of integer type" + reset);
                removeFromWishList(wishList, customer);
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
                    if (wHead.book.getbName().equalsIgnoreCase(bookName)) {
                        if (wHead.next == null) {
                            wHead = null;
                        } else {
                            wHead = wHead.next;
                            wHead.prev = null;
                            wHead.next.prev = null;
                        }
                        // WishList.setHead(wHead);
                        customer.setwHeadCust(wHead);
                    } else {
                        try {
                            WishList.Node temp = WishList.getHead(customer.getC_id());
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
                                System.out.println(red + "Book Is Not There In Your WishList!" + reset);
                                return false;
                            }
                            System.out.println(green + "Book has been removed successfully" + reset);
                            return true;
                        } catch (Exception e) {
                            System.out.println(red + " <<< Something Went Wrong !! Please Try Again! >>> " + reset);
                        }
                    }
                }
            } else {
                System.out.print("Enter ISBN : ");
                no = sc.nextLong();
                if (wHead.book.getISBN() == no) {
                    if (wHead.next == null) {
                        wHead = null;
                    } else {
                        wHead = wHead.next;
                        wHead.prev = null;
                    }
                    // WishList.setHead(wHead);
                    customer.setwHeadCust(wHead);
                } else {
                    try {
                        WishList.Node temp = wHead;
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
                            System.out.println(red + "Book Is Not There In Your WishList!" + reset);
                            return false;
                        }
                        System.out.println(green + "Book has been removed successfully" + reset);
                        return true;

                    } catch (Exception e) {
                        System.out.println(red + " <<< Something Went Wrong !! Please Try Again! >>> " + reset);
                    }
                }
            }
        }
        return false;
    }

    public boolean updateProfile() {
        System.out.println(cyan + "--- Enter Below Details To Update Your Profile ---\n" + reset);

        System.out.println("Enter Your UserID   : ");
        String u = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String p = sc.nextLine();

        if (u.equals(userID) && p.equals(c_pass)) {
            System.out.println(cyan + "-------------------------------------------" + reset);
            System.out.println("Enter Below Details To Update Your Profile");
            System.out.println(cyan + "-------------------------------------------" + reset);

            System.out.print("Enter Name                 : ");
            this.c_name = sc.nextLine();
            System.out.print("Enter Age                  : ");
            try {
                int age = 0;
                try {
                    age = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(red + "\n-------------*Input must be of integer type" + reset);
                    updateProfile();
                }
            } catch (Exception e) {
                System.out.println(red + "\n-------------*Input must be of integer type" + reset);

                sc.nextLine();
                updateProfile();
            }
            sc.nextLine();
            System.out.print("Enter Password             : ");
            this.c_pass = sc.nextLine();
            do {
                System.out.print("Enter Confirm Password     : ");
                this.confirm_pass = sc.nextLine();
                if (!c_pass.equals(confirm_pass)) {
                    System.out.println(red + "Your Password and Confirm Password Must Be Same !" + reset);
                }
            } while (!c_pass.equals(confirm_pass));
            System.out.print("Enter Address              : ");
            this.c_address = sc.nextLine();
            System.out.print("Enter Pincode              : ");
            this.pincode = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Phone Number         : ");
            this.ph_no = sc.nextLine();
            userID = c_name.split(" ")[0] + (int) (Math.random() * 100);
            System.out.println("\n\n------------------------------");
            System.out.println(cyan + "YOUR USERID : " + userID + reset);
            System.out.println("------------------------------\n");
            return true;
        } else {
            System.out.println(
                    red + "\n<<< Invalid user id or password... Please try again>>>" + reset);
            return false;
        }
    }

    public void viewProfile(Customer customer) {
        System.out.println("==============================================================");
        System.out.println("                         YOUR PROFILE                         ");
        System.out.println("==============================================================");
        System.out.println("\n\tID              :  " + customer.c_id);
        System.out.println("\tNAME            :  " + customer.c_name);
        System.out.println("\tUSERNAME        :  " + customer.userID);
        System.out.println("\tCart ID         :  " + customer.cartId);
        System.out.println("\tAddress         :  " + customer.c_address);
        System.out.println("\tPincode         :  " + cyan + customer.pincode + reset);
        System.out.println("\tCONTACT NO.     :  " + cyan + customer.ph_no + reset + "\n");
        System.out.println("==============================================================");
        System.out.println("==============================================================");

    }

    public void placeOrder(Cart cart, Customer customer) {
        double total_amt = 0;
        int i = 1;
        if (head == null) {
            System.out.println(red + "<<< There is no book to display ... >>>" + reset);
        } else {
            Cart.Node temp = customer.getCartHeadCust();
            while (temp != null) {
                Book book = temp.book;
                System.out.println("Book " + i + " ---> " + book.bName + " (" + book.authorName + ") " + book.ISBN
                        + " - " + book.price + " rs.");
                i++;
                temp = temp.next;
                total_amt = total_amt + book.price;
            }
            if (total_amt <= 0) {
                System.out.println("<<< Oops Your Cart is Empty ...>>>");
                System.out.println("Add Book(s) To Proceed");
                boolean f = true;
                while (f) {
                    if (addToCart(customer)) {
                        System.out.println(green + "Book Has Been Added To Your Cart ....." + reset);
                        System.out.print(
                                "\nDo you want to add more book(s)? if yes then press 1+enter and no then 2+enter --> ");
                        int c = sc.nextInt();
                        System.out.println(yellow
                                + "------------------------------------------------------------------------------------------------\n"
                                + reset);
                        switch (c) {
                            case 1:
                                break;
                            case 2:
                                f = false;
                                Cart.Node temp1 = customer.getCartHeadCust();
                                while (temp1 != null) {
                                    Book book = temp1.book;
                                    System.out.println("Book " + i + " ---> " + book.bName + " (" + book.authorName
                                            + ") " + book.ISBN
                                            + " - " + book.price + " rs.");
                                    i++;
                                    temp1 = temp1.next;
                                    total_amt = total_amt + book.price;
                                }
                                sc.nextLine();
                                break;
                            default:
                                System.out.println(red + "<<< Invalid choice >>>" + reset);
                                break;
                        }
                    }
                }
            }
            System.out.println(yellow
                    + "\n------------------------------------------------------------------------------------------------"
                    + reset);
            System.out.println("Total Payable Amount          : " + total_amt);
            System.out.print("Do You Want To Proceed ...?   : ");
            String ans = sc.nextLine();

            if (ans.equalsIgnoreCase("yes")) {
                System.out.print("How do you want to pay (yes or no): ");
                System.out.println(cyan + "\n1. Cash \n2. Online\n");
                System.out.print("Enter your choice : " + reset);
                int c = sc.nextInt();

                if (c == 1 || c == 2) {

                    System.out.println(cyan + "PLEASE VARIFY YOUR DETAILS BEFORE PROCEED!" + reset);
                    if (verifyDetails(customer)) {
                        System.out.println(green + "***** VERIFIED *****" + reset);
                    } else {
                        System.out.println(red + "PROFILE VERIFICATION FAILED!!!" + reset);
                        System.out.println(red + "Please Try Again !" + reset);
                        // verifyDetails(customer);
                        return;
                    }
                    if (c == 2) {
                        int otp = (int) (Math.random() * 1000000);
                        int enteredOtp = 0;
                        JOptionPane.showMessageDialog(null, "YOUR OTP : " + otp,
                                "Message",
                                JOptionPane.PLAIN_MESSAGE);
                        System.out.print(cyan + "----Enter OTP here : ");
                        try {
                            enteredOtp = sc.nextInt();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Enter Valid Input!",
                                    "Message",
                                    JOptionPane.ERROR_MESSAGE);
                            try {
                                enteredOtp = sc.nextInt();
                            } catch (Exception e1) {
                                System.out.println(red + "Many Unsuccessfull Attemps!!!");
                                System.out.println("TRY AGAIN!!" + reset);
                                placeOrder(cart, customer);
                            }
                        }
                        if (otp == enteredOtp) {
                            System.out.println(green + "TRANSACTION SUCCESSFUL!" + reset);
                        } else {
                            System.out.println(red + "Many Unsuccessfull Attemps!!!");
                            System.out.println("TRY AGAIN!!" + reset);
                            placeOrder(cart, customer);

                        }
                    }
                    System.out.println(cyan + "\nAmount To Pay : " + total_amt + reset + "\n");
                    System.out.println("\n\t\t\tBILL \n");

                    System.out.println(yellow +
                            "__________________________________________________________");

                    System.out.println("==========================================================" + reset);

                    System.out.println("\tName                 : " + customer.getC_name());
                    System.out.println("\tUser Id              : " + customer.getUserID());
                    System.out.println("\tAddress              : " + customer.getC_address());
                    System.out.println("\tContact Number       : " + customer.getPh_no());
                    System.out.println("\tTotal Payable Amount : " + total_amt);
                    System.out.println(yellow + "__________________________________________________________");
                    System.out.println(yellow + "==========================================================" + reset);

                    System.out.println("__________________________________________________________" + reset);
                    System.out.println(green + "\n\t\tOrder Confirmed!\n" + reset);
                    System.out.println(cyan + "\n\t\tTHANK YOU!! " + red + "\3\3\3\n" + reset);
                    System.out.println("\n\t\tVISIT AGAIN " + red + "\3\3\3" + reset);
                    customer.setCartHeadCust(null);
                }
            }
        }
    }

    boolean verifyDetails(Customer customer) {
        System.out.println("\nID        : " + customer.getC_id());
        System.out.println("Name      : " + customer.getC_name());
        System.out.println("Address   : " + customer.getC_address());
        System.out.println("Pincode   : " + customer.getPincode());
        System.out.println("Phone No. : " + customer.getPh_no() + "\n\n");

        sc.nextLine();

        System.out.print(cyan + "\nDo You Want To Change Your Profile ");
        System.out.print("(yes or no) : " + reset);
        String ans1 = sc.nextLine();

        if (ans1.equalsIgnoreCase("yes")) {
            if (updateProfile()) {
                System.out.println(green + "\nProfile Updated Successfully" + reset);
                return true;
            } else {
                System.out.println(red + "\nSomething went wrong...!" + reset);
                return false;
            }
        } else if (ans1.equalsIgnoreCase("no")) {
            return true;
        } else {
            System.out.println(red + "Something went wrong...!" + reset);
            System.out.println(red + "Please Enter Valid Inputs!!" + reset);
            return false;
        }
    }
}