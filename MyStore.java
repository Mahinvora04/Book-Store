import java.io.Console;
import java.util.Map;
import java.util.Scanner;

//Main class
public class MyStore {
    static int c_id = 1;

    static Scanner sc = new Scanner(System.in);
    static String bold = "\u001B[1m";
    static String reset = "\u001B[0m";
    static String green = "\u001B[32m";
    static String yellow = "\u001B[33m";
    static String blue = "\u001B[34m";
    static String cyan = "\u001B[36m";
    static String red = "\u001B[91m";

    public static void main(String[] args) {

        System.out.println("");

        Admin admin = new Admin();
        admin.addedBook("A Brief History of Time", "Stephen Hawking", 1000, "Science");
        admin.addedBook("Cosmos", "Carl Sagan", 500, "Science");
        admin.addedBook("The Selfish Gene", "Richard Dawkins", 350, "Science");
        admin.addedBook("Silent Spring", "Rachel Carson", 560, "Science");
        admin.addedBook("The Martian", "Andy Weir", 630, "Science");
        admin.addedBook("The Guide", "Narayan R K", 370, "Novel");
        admin.addedBook("The Discovery of India", "Jawaharlal Nehru", 900, "History");
        admin.addedBook("The Second World War", "Winston Churchill", 410, "History");
        admin.addedBook("1776", "David McCullough", 700, "History");
        admin.addedBook("Goodnight Moon", "Margaret Wise Brown", 430, "Children");
        admin.addedBook("Harry Potter", "J.K. Rowling", 430, "Children");
        admin.addedBook("The Cat in the Hat", "Dr. Seuss", 430, "Children");

        Customer customer;

        while (true) {

            System.out.println(bold + "\n1. LogIn As Admin");
            System.out.println("2. LogIn As Customer" + reset);

            System.out.print(cyan + "\n----Enter Your Choice[1 or 2] : " + reset);
            int c1 = 0;
            try {
                c1 = sc.nextInt();
            } catch (Exception e) {
                System.out.println(red + "\n-------------*Input must be of integer type" + reset);
            }
            switch (c1) {
                case 1:
                    System.out.println(blue + bold + "\n***** PLEASE ENTER YOUR LOG IN DETAILS *****\n" + reset);
                    admin.logIn();

                    System.out.println();
                    boolean t1 = true;
                    while (t1) {
                        sc.nextLine();
                        System.out.print("\nClick Enter To See Menu:");
                        String enter = sc.nextLine();
                        System.out.println(
                                "\n1. Display all Books\n2. To View All Customers \n3. To add book\n4. To remove book\n5. To search book To \n6. To Update Book Details \n7. To Exit");
                        System.out.print(cyan + "\n----Enter Your Choice[1-7] : " + reset);

                        int c2 = 0;
                        try {
                            c2 = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println(red + "-------------*Input must be of integer type" + reset);
                        }
                        switch (c2) {
                            case 1:
                                System.out.println(yellow + "\n*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
                                System.out.println("*=*=*=*=*" + reset + bold + "  Available Books  " + reset + yellow
                                        + "*=*=*=*=*");
                                System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*   \n" + reset);
                                admin.displayBooks();
                                break;
                            case 2:
                                System.out.println(yellow + "\n*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
                                System.out
                                        .println("*=*=*=*=" + reset + "     ALL CUSTOMERS     " + yellow + "=*=*=*=*");
                                System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*\n" + reset);

                                for (Map.Entry<Integer, Customer> cust : Customer.customers.entrySet()) {
                                    Customer i = cust.getValue();
                                    System.out.println("\nID        : " + cust.getKey());
                                    System.out.println("Name      : " + i.getC_name());
                                    System.out.println("Address   : " + i.getC_address());
                                    System.out.println("Pincode   : " + i.getPincode());
                                    System.out.println("Phone No. : " + i.getPh_no() + "\n\n");
                                }
                                break;
                            case 3:
                                admin.addBook();
                                System.out.println(green + "\n<<< Book Has Been Added Successfully >>>" + reset);
                                break;
                            case 4:
                                admin.removeBook();
                                break;
                            case 5:
                                admin.searchBook();
                                break;
                            case 6:
                                admin.updateBook();
                                break;
                            case 7:
                                System.out.println(yellow + bold + "\t\t\t\t\tExiting...." + reset);
                                t1 = false;
                                break;
                            default:
                                System.out
                                        .println(red + "\n<<< Invalid choice! Please Try Again !! >>>" + reset);
                                sc.nextLine();
                        }
                    }
                    break;
                case 2:
                    System.out.println(yellow
                            + "\n*********************************************************************************************************\n");
                    System.out.println(reset + "                                     WELCOME TO OUR ONLINE BOOK SHOP");
                    System.out.println(yellow
                            + "\n*********************************************************************************************************\n"
                            + reset);
                    boolean t2 = true;
                    while (t2) {
                        System.out.println(
                                "\nPress 1 If you are existing Customer\nPress 2 if you are new Customer\nPress 3 To Exit");
                        System.out.print(cyan + "\n----Enter Your Choice : " + reset);
                        int c3 = 0;
                        try {
                            c3 = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println(red + "-------------*Input must be of integer type" + reset);
                        }
                        switch (c3) {
                            case 1:
                                sc.nextLine();
                                System.out.print("Enter Your UserID   : ");
                                String userId = sc.nextLine();
                                Console console = System.console();
                                char[] c_passwordArray = console.readPassword("Enter Your Password : ");
                                String pass = new String(c_passwordArray);

                                Customer existingCust = Customer.logIn(userId, pass);

                                if (existingCust != null) {
                                    System.out.println(cyan + "Welcome " + existingCust.getC_name() + " !!!" + reset);
                                    Cart ecart = new Cart(existingCust.getC_id());
                                    WishList ewishlist = new WishList(existingCust.getC_id());
                                    choices(existingCust, ecart, ewishlist);
                                } else {
                                    System.out.println(
                                            red + "\n<<< Invalid user id or password... Please try again>>>" + reset);
                                    break;
                                }
                                break;
                            case 2:

                                Cart cart = new Cart(c_id);
                                WishList wishlist = new WishList(c_id);
                                c_id++;
                                customer = Customer.signIn(c_id, cart, wishlist);

                                customer.setC_id(c_id);
                                customer.setCartId(c_id);

                                System.out.println(green + "<<< SUCCESSFULLY REGISTERED >>>\n");
                                System.out.println(cyan + "Welcome " + customer.getC_name() + " !!!" + reset);
                                choices(customer, cart, wishlist);
                                break;
                            case 3:
                                System.out.println(yellow + bold + "\t\t\t\t\tExiting...." + reset);
                                t2 = false;
                                break;
                            default:
                                System.out.println("\n<<< INVALID CHOICE >>>");
                                sc.nextLine();

                                return;
                        }
                    }
                    break;
                default:
                    System.out.println(red + "\n<<< INVALID CHOICE!  PLEASE ENTER VALID CHOICE!! >>>" + reset);
                    sc.nextLine();
                    break;
            }
        }
    }

    static void choices(Customer customer, Cart cart, WishList wishlist) {
        boolean t3 = true;
        while (t3) {
            sc.nextLine();
            System.out.print("\nClick Enter To See Menu:");
            String enter = sc.nextLine();
            System.out.println(
                    "\n1.  View Books\n2.  Search Book \n3.  View My Cart\n4.  Add Book To Cart \n5.  To Update Cart\n6.  View My WishList \n7.  Add Book To WishList\n8.  Update WishList \n9.  Place Order\n10. View Profile \n11. Update My Profile\n12. Exit");
            System.out.print(cyan + "\n----Enter Your Choice :" + reset);
            int c4 = 0;
            try {
                c4 = sc.nextInt();
            } catch (Exception e) {
                System.out.println(red + "-------------*Input must be of integer type" + reset);
            }
            switch (c4) {
                case 1:
                    customer.viewBooks();
                    break;
                case 2:
                    customer.searchBook();
                    break;
                case 3:
                    customer.viewCart(customer);
                    break;
                case 4:
                    Cart.setCart_id(customer.getCartID());
                    if (customer.addToCart(customer)) {
                        System.out.println(green + "Book Has Been Added To Your Cart ....." + reset);
                    }
                    break;
                case 5:
                    if (customer.updateCart(cart, customer)) {
                        System.out.println(green + "Cart Has Been Updated!!" + reset);
                    }
                    break;
                case 6:
                    customer.viewWishList(customer);
                    break;
                case 7:
                    if (customer.addToWishList(customer)) {
                        System.out.println(green + "Book Has Been Added To Your WishList ....." + reset);
                    }
                    break;
                case 8:
                    if (customer.updateWishList(wishlist, customer)) {
                        System.out.println(green + "Cart Has Been Updated!!" + reset);
                    }
                    break;
                case 9:
                    customer.placeOrder(cart, customer);
                    break;
                case 10:
                    customer.viewProfile(customer);
                    break;
                case 11:
                    customer.updateProfile();
                    break;
                case 12:
                    System.out.println(yellow + bold + "\t\t\t\t\tExiting...." + reset);
                    t3 = false;
                    break;
                default:
                    System.out.println(red + "\n<<< INVALID CHOICE!  PLEASE ENTER VALID CHOICE!! >>>" + reset);
                    sc.nextLine();
                    break;
            }
        }
    }
}