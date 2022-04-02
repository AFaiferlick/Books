package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name="ReturnAdHocTeams",
        query = "SELECT * " +
                "FROM   AdHocTeams " +
                "WHERE  name = ? ",
        resultClass = AdHocTeams.class
)

public class AdHocTeams {
    @Id
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 20)
    private String A_name;

    @Column(nullable = false, length = 30)
    private String A_email;

    public AdHocTeams(){}

    public AdHocTeams(String name, String email, String A_name, String A_email){
        this.name = name;
        this.email = email;
        this.A_name = A_name;
        this.A_email = A_email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getA_Name() {
        return name;
    }
    public void setA_Name(String A_name) {this.A_name = A_name;}

    public String getA_Email() {return A_email;}
    public void setA_Email(String A_email) {this.A_email = A_email;}
}
