package com.example.jdbcmysqldemo.dao;

import com.example.jdbcmysqldemo.model.Employee;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.concurrent.Callable;

@Component
public class EmployeeJdbcDao {
    // conn.setAutoCommit(false);       // set auto commit to false, so we can beginning, commit or rollback.
    public Employee getEmployeeById(Connection conn, Long id) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee where ");

        return null;

    }

    public void addEmployee(Connection conn, Employee employee) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "insert into employee"
                + " (last_name, first_name, email_address) "
                + " values ( 'Brown', 'David', 'david.brown@yahoo.com')";

        stmt.executeUpdate(sql);
        System.out.println("Insert complete");
    }

    public void updateEmployee(Connection conn, Employee employee) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "update employee set email = 'demo@yahoo.com' where id = 9";

        stmt.executeUpdate(sql);
        System.out.println("Update complete");
    }

    public void deleteEmployee(Connection conn, Employee employee) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "delete employee where id = 9";

        stmt.executeUpdate(sql);
        System.out.println("Delete complete");
    }

    // Prepared Statements, a precompiled SQL statement,
    // ================================================
    // following is en example for input criteria, ? is placehold, indexed, first ? is 1, second ? index is 2
    public void getEmployeeByLastNameAndEmail(Connection conn, Employee employee) throws SQLException {
        PreparedStatement mystmt = conn.prepareStatement("select * from employee where last_name = ? and email = ?");
        mystmt.setString(1, "new_last_name");
        mystmt.setString(2, "new_email@yahoo.com");

//        mystmt.setDouble(3, 80000);
        ResultSet myRs = mystmt.executeQuery();


        /*
            to update, delete records, we can use the PreparedStatement too.
            delete from table_name where id = ?
            update employee set salary = ? where iD = ?
         */
    }

    public void increaseSalary(Connection conn, String department, int amount) throws SQLException {
    // how to call storedProcedure, which is called ==========  Callable Statements
    // Call Stored Procedures that take parameters.
    //  First IN parameters  (MySQL), folloiwng is sql and execute in workbench.
/*
        PROCEDURE `increase_salaries_for_deparment` (
            IN the_deparment VARCHAR(64), IN increase_amount DECIMAL(10, 2))
        BEGIN
            UPDATE employee SET salary=salar + inccrease_amount WHERE
            where department = the_department;
        END


 */

        CallableStatement stmt = conn.prepareCall("{ call increase_salaries_for_department(?, ?) }");
        stmt.setString(1, department);
        stmt.setDouble(2, amount);

        System.out.println("\n\nCalling stored procedure. ..... ");
        stmt.execute();
        System.out.println("Finished calling stored procedure.");
    }


    public void greetingDepartment(Connection conn, String department) throws SQLException {

        /**
            PROCEDURE `greet_the_department`(INOUT department VARCHAR(64))
            BEGIN
                SET department = concat('Hello to the awesome ', department, ' team!');
            END
         */
        CallableStatement stmt = conn.prepareCall("{ call greet_the_department(?)}");
        stmt.registerOutParameter(1, Types.VARCHAR);
        stmt.setString(1, "Engineering");

        stmt.execute();

        String theResult = stmt.getString(1);  // The result is "Hello to the awesome Engineering team!"

        System.out.println("\nThe result = " + theResult);
    }

    public void getCountForDepartment(Connection conn, String theDepartment) throws SQLException {

        /*
               PROCEDURE `get_count_for_department` (IN the_department VARCHAR(64), OUT the_count INT)
               BEGIN
                    SELECT COUNT(*) INTO the_count FROM employees
                    WHERE department = the_department;
               END

         */

        CallableStatement stmt = conn.prepareCall("{call get_the_count_for_department(?, ?)}");
        stmt.setString(1, theDepartment);
        stmt.registerOutParameter(2, Types.INTEGER);

        stmt.execute();

        int count = stmt.getInt(2);

        System.out.println("\nThe count = " + count);
    }


    public void getEmployeesForDepartment(Connection conn, String deparment) throws SQLException {

        /**
                PROCEDURE `get_employees_for_department`(IN the_department VARCHAR(64))
                BEGIN
                    SELECT * FROM EMPLOYEE WHERE department = the_department;
                END
         */

        CallableStatement stmt= conn.prepareCall("{call get_employee_for_department(?)}");
        stmt.setString(1, "Engineering");
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        // display(rs);
    }
}
