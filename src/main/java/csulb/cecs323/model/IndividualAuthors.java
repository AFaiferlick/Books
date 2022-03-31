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
}
