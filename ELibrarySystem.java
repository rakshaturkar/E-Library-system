import java.util.*;

// Book class to represent a book
class Book {
    String title;
    String author;
    String category;
    boolean isBorrowed;
    
    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = false;
    }
}

// User class to represent a library user
class User {
    String username;
    String password;
    List<Book> borrowedBooks;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.borrowedBooks = new ArrayList<>();
    }
}

// Library class to handle books and users
class Library {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();
    User currentUser = null;
    
    public void registerUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println("User registered successfully!");
    }
    
    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid credentials.");
        return false;
    }
    
    public void addBook(String title, String author, String category) {
        books.add(new Book(title, author, category));
        System.out.println("Book added successfully!");
    }
    
    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isBorrowed) {
                System.out.println("Title: " + book.title + ", Author: " + book.author + ", Category: " + book.category);
            }
        }
    }
    
    public void borrowBook(String title) {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }
        
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && !book.isBorrowed) {
                book.isBorrowed = true;
                currentUser.borrowedBooks.add(book);
                System.out.println("Book borrowed successfully!");
                return;
            }
        }
        System.out.println("Book not available.");
    }
    
    public void returnBook(String title) {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }
        
        for (Book book : currentUser.borrowedBooks) {
            if (book.title.equalsIgnoreCase(title)) {
                book.isBorrowed = false;
                currentUser.borrowedBooks.remove(book);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("You don't have this book.");
    }
}

// Main class to run the library system
public class ELibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        // Adding some books
        library.addBook("Java Programming", "James Gosling", "Programming");
        library.addBook("The Alchemist", "Paulo Coelho", "Fiction");
        
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Display Books\n4. Borrow Book\n5. Return Book\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    library.registerUser(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    library.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    System.out.print("Enter book title: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 5:
                    System.out.print("Enter book title: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
