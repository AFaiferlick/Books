//Good as of 4/2
package csulb.cecs323.model;

import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name="ReturnBook",
        query = "SELECT * " +
                "FROM   books " +
                "WHERE  ISBN = ? ",
        resultClass = Books.class
)

@NamedNativeQuery(
        name="ReturnBookTitleAuthoringEntityName",
        query = "SELECT * " +
                "FROM books " +
                "WHERE title = ? AND authoring_entity_name = ?",
        resultClass = Books.class
)

@NamedNativeQuery(
        name="ReturnBookList", // This used be "ReturnAllBooks"
        query = "SELECT * " +
                "FROM books " +
                "ORDER BY title", // Added this ORDER BY clause
        resultClass = Books.class
)
/**
 * 4. Update a Book – Change the authoring entity for an existing book.
 */
@NamedNativeQuery(
        name="UpdateBook",
        query = "UPDATE books " +
                "SET authoring_entity_name = ? " +
                "WHERE ISBN = ?",
        resultClass = Books.class
)

@NamedNativeQuery(
        name="ReturnBookPKs",
        query = "SELECT title, ISBN " +
                "FROM   books " +
                "ORDER BY title asc ",
        resultClass = Books.class
)

@NamedNativeQuery(
        name="ReturnBooksWithISBN",
        query = "SELECT ISBN " +
                "FROM   books " +
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

    @ManyToOne
    @JoinColumn(name = "publisher_name",
            referencedColumnName = "name")
    /**  The publishing company of the book. **/
    private Publishers publisher;

    @ManyToOne
    @JoinColumn(name = "authority_entity_name",
            referencedColumnName = "name")
    /**  The authoring entity of the book. **/
    private AuthoringEntities authoringEntity;

    /** Default constructor that creates a Book. */
    public Books() {}

    /** Overloaded constructor that creates a Book object.
     *  @param ISBN              The ISBN of the book.
     *  @param title             The title of the book.
     *  @param yearPublished     The year the book was published.
     */
    public Books (String ISBN, String title, int yearPublished, Publishers publisher, AuthoringEntities authoringEntity) {
        this.ISBN = ISBN;
        this.title = title;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.authoringEntity = authoringEntity;
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

    /** Retrieves the publisher of the book.
     * @return publisher  The publisher of the book.
     */
    public Publishers getPublisher() { return publisher; }

    /** Sets the publisher of the book.
     * @param publisher   The publisher of the book.
     */
    public void setPublisher(Publishers publisher) { this.publisher = publisher; }

    /**
     * Retrieves the authoring entity of the book.
     * @return authoringEntity      The authoring entity of the book.
     */
    public AuthoringEntities getAuthoringEntity() { return authoringEntity; }

    /**
     * Sets the authoring entity of the book.
     * @param authoringEntity      The authoring entity of the book.
     */
    public void setAuthoringEntity(AuthoringEntities authoringEntity) { this.authoringEntity = authoringEntity; }

    /** 5b. List the primary key of all the rows of Books (show the title as well as the ISBN)
     * Lists the Primary Key of the Book, which is the title and the ISBN, as a String
     * @return String of the PK      The title and ISBN of the book as a String.
     */
    public String getBookPK() { return "Book title: " + title + ", ISBN: " + ISBN; }

    /** The string representation of Books
     * @return string representation of Books
     */
    @Override
    public String toString () {
        return "Book\nISBN: " + this.ISBN+ " Title: " + this.title +
                " Year Published: " + this.yearPublished + " Publisher: " + this.publisher +
                " Authoring Entity: " + this.authoringEntity;
    }
}
