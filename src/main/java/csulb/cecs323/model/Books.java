package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="ReturnBook",
        query = "SELECT * " +
                "FROM   BOOKS " +
                "WHERE  ISBN = ? ",
        resultClass = Books.class
)

/**
 * A long written or printed literary composition.
 */
public class Books {
    @Id
    @Column(nullable = false, length = 13)
    /** The ISBN of the book. Limited to 13 characters. */
    private String ISBN;

    @Column(nullable = false, length = 40)
    /** The title of the book.  Limited to 40 characters. */
    private String title;

    @Column(nullable = false)
    /** The year the book was published. */
    private int yearPublished;

    /** Default constructor that creates a Book. */
    public Books() {}

    /** Overloaded constructor that creates a Book object.
     *  @param ISBN              The ISBN of the book.
     *  @param title             The title of the book.
     *  @param yearPublished     The year the book was published.
     */
    public Books (String ISBN, String title, int yearPublished) {
        this.ISBN = ISBN;
        this.title = title;
        this.yearPublished = yearPublished;
    }

    /** Retrieves the ISBN of the book.
     * @return ISBN  The ISBN of the book.
     */
    public String getISBN() {
        return ISBN;
    }

    /** Sets the ISBN of the book.
     * @param ISBN  The ISBN of the book.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /** Retrieves the title of the book.
     * @return name  The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /** Sets the title for the book.
     * @param title  The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Retrieves the year the book was published.
     * @return yearPublished  The year the book was published.
     */
    public int getYear_published() {
        return yearPublished;
    }

    /** Sets the year the book was published.
     * @param yearPublished   The year the book was published.
     */
    public void setYear_published(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    /** The string representation of Books
     * @return string representation of Books
     */
    @Override
    public String toString () {
        return "Book\nISBN: " + this.ISBN+ " Title: " + this.title +
                " Year Published: " + this.yearPublished;
    }
}
