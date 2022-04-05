//Good as of 4/2
package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Inheritance;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authoringEntityType", discriminatorType = DiscriminatorType.STRING)
@NamedNativeQuery(
        name="ReturnAuthoringEntity",
        query = "SELECT * " +
                "FROM authoringEntities " +
                "WHERE  email = ? ",
        resultClass = AuthoringEntities.class
)

@NamedNativeQuery(
        name="ReturnAllAuthoringEntities",
        query = "SELECT * " +
                "FROM authoringEntities ",
        resultClass = AuthoringEntities.class
)

/**
 * A writer of a book. Could be a single individual, a writing group, or an AD Hoc Team.
 */
public abstract class AuthoringEntities {
    @Id
    @Column(nullable = false, length = 30)
    /** The name of the authoring entity. Limited to 30 characters. */
    private String name;

    @Column(nullable = false, length = 50)
    /** The email of the authoring entity. Limited to 50 characters. */
    private String email;

    /** Default constructor that creates an Authoring Entity. */
    public AuthoringEntities () {}

    /** Overloaded constructor that creates an Authoring Entity object.
     *  @param name              The name of the authoring entity.
     *  @param email             The email of the authoring entity.
     */
    public AuthoringEntities (String name, String email) {
        this.name = name;
        this.email = email;
    }

    /** Retrieves the name of the authoring entity.
     * @return name  The name of the authoring entity.
     */
    public String getName() {
        return name;
    }

    /** Sets the name of the authoring entity.
     * @param name  The name of the authoring entity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Retrieves the email of the authoring entity.
     * @return name  The email of the  authoring entity.
     */
    public String getEmail() {
        return email;
    }

    /** Sets the email of the authoring entity.
     * @param email  The email of the authoring entity.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Returns the type of the authoring entity.
     * @return Will return the type of the authoring entity as a String
     */
    public abstract String getType();

    /** The string representation of AuthoringEntity
     * @return string representation of AuthoringEntity
     */
    @Override
    public String toString () {
        return "AuthoringEntity \nName: " + this.getName() + " Email: " + this.getEmail();
    }
}