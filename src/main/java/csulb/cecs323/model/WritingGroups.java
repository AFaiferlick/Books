package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="ReturnWritingGroups",
        query = "SELECT * " +
                "FROM   WritingGroups " +
                "WHERE  name = ? ",
        resultClass = WritingGroups.class
)

public class WritingGroups {
}
