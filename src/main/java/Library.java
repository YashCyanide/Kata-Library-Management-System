import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public Book findBookByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public void borrowBook(String isbn) throws Exception {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            throw new Exception("Book not found");
        }
        if (!book.isAvailable()) {
            throw new Exception("Book is not available");
        }
        book.setAvailable(false);
    }

    public void returnBook(String isbn) throws Exception {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            throw new Exception("Book not found");
        }
        book.setAvailable(true);
    }
}
