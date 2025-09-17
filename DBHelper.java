package MarvellousStudyTracker;

import java.sql.*;

/////////////////////////////////////////////////////////////////////////
//
//  Class :         DBHelper
//  Description:    Provides database connectivity to MySQL database
//  Author :        Rutik Shivaji Thitame
//
/////////////////////////////////////////////////////////////////////////

public class DBHelper 
{
    // JDBC URL, username, and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/StudyTracker";
    private static final String user = "root";
    private static final String password = "root";

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : getConnection
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Establishes and returns a connection to the MySQL database
    //  Returns         : Connection (java.sql.Connection)
    //
    /////////////////////////////////////////////////////////////////////////////////
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }
}
