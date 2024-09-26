

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
    }

    @Test
    void testAddBook() {
        Book book = new Book("1234567890", "Test Book", "Test Author", 2023);
        library.addBook(book);
        assertEquals(1, library.getAvailableBooks().size());
        assertEquals(book, library.findBookByIsbn("1234567890"));
    }

    @Test
    void testBorrowBook() throws Exception {
        Book book = new Book("1234567890", "Test Book", "Test Author", 2023);
        library.addBook(book);
        library.borrowBook("1234567890");
        assertFalse(book.isAvailable());
        assertEquals(0, library.getAvailableBooks().size());
    }

    @Test
    void testBorrowNonExistentBook() {
        assertThrows(Exception.class, () -> library.borrowBook("9999999999"));
    }

    @Test
    void testBorrowUnavailableBook() throws Exception {
        Book book = new Book("1234567890", "Test Book", "Test Author", 2023);
        library.addBook(book);
        library.borrowBook("1234567890");
        assertThrows(Exception.class, () -> library.borrowBook("1234567890"));
    }

    @Test
    void testReturnBook() throws Exception {
        Book book = new Book("1234567890", "Test Book", "Test Author", 2023);
        library.addBook(book);
        library.borrowBook("1234567890");
        library.returnBook("1234567890");
        assertTrue(book.isAvailable());
        assertEquals(1, library.getAvailableBooks().size());
    }

    @Test
    void testReturnNonExistentBook() {
        assertThrows(Exception.class, () -> library.returnBook("9999999999"));
    }

    @Test
    void testGetAvailableBooks() {
        Book book1 = new Book("1111111111", "Book 1", "Author 1", 2021);
        Book book2 = new Book("2222222222", "Book 2", "Author 2", 2022);
        Book book3 = new Book("3333333333", "Book 3", "Author 3", 2023);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        assertEquals(3, library.getAvailableBooks().size());
        try {
            library.borrowBook("2222222222");
        } catch (Exception e) {
            fail("Borrowing book should not throw an exception");
        }
        assertEquals(2, library.getAvailableBooks().size());
    }
}
