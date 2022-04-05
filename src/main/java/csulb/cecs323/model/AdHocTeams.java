//Good as of 4/2
package csulb.cecs323.model;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Ad Hoc Teams")
public class AdHocTeams extends AuthoringEntities {

    @ManyToMany//(mappedBy = "adHocTeamsList")
    List<IndividualAuthors> individualAuthorsList;

    /**
     * Calls default constructor of parent class for this child class's default constructor
     */
    public AdHocTeams(){ super(); }

    /**
     *
     * @param email     The Email of the ad hoc team.
     * @param name      The Name of the ad hoc team.
     */
    public AdHocTeams(String name, String email){
        super(name, email);
        individualAuthorsList = new ArrayList<>();
    }

    /** Outputs this class in a string representation.
     * @return the class in a string representation
     */
    public String toString() { return "Ad Hoc Team\nName: " + super.getName() + " Email: " + super.getEmail(); }

    /** Retrieves the list of individual authors this ad hoc team contains.
     * @return individualAuthorsList   The list of individual authors this ad hoc team contains.
     */
    public List<IndividualAuthors> getIndividualAuthors() { return this.individualAuthorsList; }

    /** Adds an individual author to this ad hoc team's list of individual authors.
     * @param individualAuthor     The individual author to add to the list of individual authors.
     */
    public void addIndividualAuthor(IndividualAuthors individualAuthor) { this.individualAuthorsList.add(individualAuthor); }

    /** Returns this specific authoring entity type as "Ad Hoc Team"
     * @return "Ad Hoc Team"  The authoring entity type of this authoring entity in String form
     */
    @Override
    public String getType() { return "Ad Hoc Team"; }
}
