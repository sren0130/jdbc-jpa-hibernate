Spring Data JPA does mapping for java object to database table.

Annotation does help to get ride of mapping files.

FullTimeEmployee inherites Employee

PartTimeEmployee inherites Employee


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)             // there is EMPLOYEE table.
@DiscriminationColumn(name="EMPLOYEE_TYPE)                      // difference value for full and part time
public class Employee {
.....
}

public class FullTimeEmployee extends Employee {
    protected Integer salary;

}

public class PartTimeEmployee extends Employee {
....
}


Many-to-Many
    it uses link table or joint table.