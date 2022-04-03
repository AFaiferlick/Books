//Good as of 4/2
package csulb.cecs323.model;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Individual Authors")
public class IndividualAuthors extends AuthoringEntities {

    @ManyToMany
    @JoinTable(
            name = "MemberAdHocTeams"
    )
    List<AdHocTeams> adHocTeamsList;

    /**
     * Calls default constructor of parent class for this child class's default constructor
     */
    public IndividualAuthors() { super(); }

    /**
     * Overloaded constructor that creates a IndividualAuthors object.
     *
     * @param name  The Name of the Author
     * @param email The Email of the Author
     */
    public IndividualAuthors(String email, String name) {
        super(email, name);
        adHocTeamsList = new ArrayList<>();
    }

    /** Outputs this class in a string representation.
     * @return the class in a string representation
     */
    public String toString() { return "Individual Author:\nName: " + super.getName() + " Email: " + super.getEmail(); }

    /** Retrieves the list of ad hoc teams this individual author belongs to.
     * @return adHocTeamsList   The list of ad hoc teams this individual author belongs to.
     */
    public List<AdHocTeams> getAdHocTeams() { return this.adHocTeamsList; }

    /** Adds an ad hoc team to this individual author's list of ad hoc teams.
     * @param adHocTeam     The ad hoc team to add to the list of ad hoc teams.
     */
    public void addAdHocTeam(AdHocTeams adHocTeam) { this.adHocTeamsList.add(adHocTeam); };

    /** Returns this specific authoring entity type as "Individual Author"
     * @return "Individual Author"  The authoring entity type of this authoring entity in String form
     */
    @Override
    public String getType() { return "Individual Author"; }

}
