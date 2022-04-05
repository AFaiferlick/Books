//Good as of 4/2
package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.DiscriminatorValue;

@Entity
@NamedNativeQuery(
        name="ReturnWritingGroup",
        query = "SELECT * " +
                "FROM   authoringEntities " +
                "WHERE  email = ? AND authoringEntityType = ?",
        resultClass = WritingGroups.class
)

@DiscriminatorValue("Writing Groups")
public class WritingGroups extends AuthoringEntities {
    @Column(length = 20)
    private String headWriter;

    @Column(length = 4)
    private int yearFormed;

    /** Returns this specific authoring entity type as "Writing Group"
     * @return "Writing Group"  The authoring entity type of this authoring entity in String form
     */
    @Override
    public String getType() { return "Writing Group"; }

    /**
     * Calls default constructor of parent class for this child class's default constructor
     */
    public WritingGroups(){ super(); }

    /** Creates a WritingGroups object with the email, name, headWriter, and yearFormed specified.
     * @param email             The email for the writing group.
     * @param name              The name of the writing group.
     * @param headWriter        The head writer for the writing group.
     * @param yearFormed        The year the writing group was formed.
     */
    public WritingGroups(String name, String email, String headWriter, int yearFormed)
    {
        super(name, email);  //calls parent's constructor for email and name, assigns email and name
        this.headWriter = headWriter;
        this.yearFormed = yearFormed;
    }

    /** Retrieves the head writer of the writing group.
     * @return headWriter       The head writer of the writing group.
     */
    public String getHeadWriter() {return headWriter;}

    /** Sets the head writer of the writing group to the head writer specified.
     * @param HeadWriter       The head writer to set for the writing group.
     */
    public void setHeadWriter(String HeadWriter) {this.headWriter = headWriter;}

    /** Retrieves the year formed of the writing group.
     * @return yearFormed       The year formed of the writing group.
     */
    public int getYearFormed() {return yearFormed;}

    /** Sets the year formed of the writing group to the year formed specified.
     * @param yearFormed       The year formed to set for the writing group.
     */
    public void setYearFormed(int yearFormed) {this.yearFormed = yearFormed;}

    /** Outputs this class in a string representation.
     * @return the class in a string representation
     */
    public String toString()
    {
        return "Writing Group\nName: " + super.getName() + ", eMail: " + super.getEmail() + ", Head writer: " +
                this.headWriter + "Year formed: " + this.yearFormed;
    }
}
