//Good as of 4/2
package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="ReturnPublisher",
        query = "SELECT * " +
                "FROM   PUBLISHERS " +
                "WHERE  name = ? ",
        resultClass = Publishers.class
)

@NamedNativeQuery(
        name="ReturnAllPublishers",
        query = "SELECT * " +
                "FROM   PUBLISHERS ",
        resultClass = Publishers.class
)

@NamedNativeQuery(
        name="ReturnPublisherPKs",
        query = "SELECT name " +
                "FROM   PUBLISHERS " +
                "ORDER BY name asc ",
        resultClass = Publishers.class
)

/**
 * A person or company that prepares and issues books, journals, music, or other works for sale..
 */
public class Publishers {
    @Id
    @Column(nullable = false, length = 20)
    /** The name of the publisher. Limited to 20 characters. */
    private String name;

    @Column(nullable = false, length = 20)
    /** The phone number of the publisher.  Limited to 20 characters. */
    private String phone;

    @Column(nullable = false, length = 50)
    /** The email of the publisher.  Limited to 50 characters. */
    private String email;

    /** Default constructor that creates a Publisher. */
    public Publishers() {}

    /** Overloaded constructor that creates a Publisher object.
     *  @param name              The name of the publisher.
     *  @param phone             The phone number of the publisher.
     *  @param email             The email of the publisher.
     */
    public Publishers (String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /** Retrieves the name of the publisher.
     * @return name  The name of the publisher.
     */
    public String getName() {
        return name;
    }

    /** Sets the name for a publisher.
     * @param name  The name of the publisher.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Retrieves the phone number of the publisher.
     * @return phone  The phone number of the publisher.
     */
    public String getPhone() {
        return phone;
    }

    /** Sets the phone number for a publisher.
     * @param phone  The phone number of the publisher.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Retrieves the email of the publisher.
     * @return email  The email of the publisher.
     */
    public String getEmail() {
        return email;
    }

    /** Sets the email for a publisher.
     * @param email The email of the publisher.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** The string representation of Publishers
     * @return string representation of Publishers
     */
    @Override
    public String toString () {
        return "Publisher\nName: " + this.name + " Phone: " + this.phone +
                " Email: " + this.email;
    }
}