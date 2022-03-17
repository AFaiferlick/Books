package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="ReturnAuthoringEntity",
        query = "SELECT * " +
                "FROM    " +
                "WHERE  name = ? ",
        resultClass = AuthoringEntity.class
)

/**
 * A writer of a book. Could be a single individual, a writing group, or an AD Hoc Team.
 */
public class AuthoringEntity {
    @Id
    @Column(nullable = false, length = 30)
    /** The name of the authoring entity. Limited to 30 characters. */
    private String name;

    @Column(nullable = false, length = 40)
    /** The email of the authoring entity. Limited to 40 characters. */
    private String email;
        
    /** Default constructor that creates an Authoring Entity. */
    public AuthoringEntity () {}
    
    /** Overloaded constructor that creates an Authoring Entity object.
     *  @param name              The name of the authoring entity.
     *  @param email             The email of the authoring entity.
     */
    public AuthoringEntity (String name, String email) {
        this.name = name;
        this.email = email;
    }

     /** Retrieves the name of the authoring entity.
     * @return name  The name of the  authoring entity.
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
     * @param name  The email of the authoring entity.
     */
    public void setEmail(String email) {
        this.emai = email;
    }
        
     /** The string representation of AuthoringEntity
     * @return string representation of AuthoringEntity
     */
    @Override
    public String toString () {
        return "AuthoringEntity \nName: " + this.getName() + " Email: " + this.geEmail();
    }
}
