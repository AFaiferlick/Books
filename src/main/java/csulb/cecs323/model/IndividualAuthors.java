package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="ReturnIndividualAuthors",
        query = "SELECT * " +
                "FROM   authors " +
                "WHERE  name = ? ",
        resultClass = IndividualAuthors.class
)

public class IndividualAuthors {
    @Id
    @Column(nullable = false, length = 20)
    /** The name of the Author. Limited to 20 characters. */
    private String name;

    @Column(nullable = false, length = 30)
    /** The email of the Author.  Limited to 30 characters. */
    private String email;

    public IndividualAuthors() {}

    /**
     * Overloaded constructor that creates a IndividualAuthors object.
     *
     * @param name  The Name of the Author
     * @param email The Email of the Author
     */
    public IndividualAuthors(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the name of the Author
     *
     * @return name  the name of the Author
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the Author
     *
     * @param name the name of the Author
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the Author
     *
     * @return email the email of the author
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the name for the Author
     *
     * @param email The email of the other
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** The string representation of Individual Authors
     * @return string representation of Individual Authors
     */
    @Override
    public String toString() {return "Publisher\nName: " + this.name + " Email: " + this.email;}
}
