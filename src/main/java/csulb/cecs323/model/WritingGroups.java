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
    @Id
    @Column(nullable = false, length = 20)
    private String head_writer;

    @Column(nullable = false)
    private int year_formed;

    @Column(nullable = true, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String email;

    public WritingGroups(){}

    public String getHead_writer() {return head_writer;}
    public void setHead_writer(String Head_writer) {this.head_writer = head_writer;}

    public int getyear_formed() {return year_formed;}
    public void setyear_formed(int year_formed) {this.year_formed = year_formed;}

    public String getname() {return name;}
    public void setname(String name) {this.name = name;}

    public String getemail() {return email;}
    public void setemail(String email) {this.email = email;}
}
