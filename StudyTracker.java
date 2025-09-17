package MarvellousStudyTracker;

import java.util.*;
import java.time.LocalDate;
import java.io.*;
import java.sql.*;

/////////////////////////////////////////////////////////////////////////
//
//  Class :         StudyTracker
//  Description:    Manages study logs using Database + File Export
//  Author :        Rutik Shivaji Thitame
//
/////////////////////////////////////////////////////////////////////////

public class StudyTracker
{
    // Data Structure to hold the data about study
    private ArrayList<StudyLog> Database = new ArrayList<StudyLog>();

    /////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : getDatabase
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Returns the in-memory list of StudyLogs
    //  Returns         : ArrayList<StudyLog>
    //
    /////////////////////////////////////////////////////////////////////////////
    public ArrayList<StudyLog> getDatabase()
    {
        return Database;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : InsertLog
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : StudyLog log
    //  Description     : Inserts a StudyLog into the database and memory
    //  Returns         : void
    //
    /////////////////////////////////////////////////////////////////////////////
    public void InsertLog(StudyLog log)
    {
        DBHelper DBHelper = new DBHelper();

        String sql = "insert into StudyLog (Date,Subject,Duration,Description) values (?,?,?,?)";

        try(Connection cobj = DBHelper.getConnection();
            PreparedStatement pobj = cobj.prepareStatement(sql))
        {
            pobj.setDate(1, java.sql.Date.valueOf(log.getDate()));
            pobj.setString(2, log.getSubject());
            pobj.setDouble(3, log.getDuration());
            pobj.setString(4, log.getDescription());

            pobj.executeUpdate();
            Database.add(log);
            System.out.println("Log inserted Succesfully");
        }
        catch(SQLException eobj)
        {
            eobj.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : DisplayLog
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Fetches all study logs from DB and updates memory list
    //  Returns         : ArrayList<StudyLog>
    //
    /////////////////////////////////////////////////////////////////////////////
    public ArrayList<StudyLog> DisplayLog()
    {
        Database.clear();
        DBHelper dobj = new DBHelper();
        String sql = "select Date,Subject,Duration,Description from StudyLog";

        try(Connection conn = DBHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql))
        {
            while (rs.next()) 
            {
                LocalDate Date = rs.getDate("Date").toLocalDate();
                String Subject = rs.getString("Subject");
                double Duration = rs.getDouble("Duration");
                String Description = rs.getString("Description");
                
                StudyLog log = new StudyLog(Date, Subject, Duration, Description);
                getDatabase().add(log);
            }
        }
        catch(SQLException eobj)
        {
            eobj.printStackTrace();
        }

        return Database;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : ExportCSV
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Exports study log data into CSV file
    //  Returns         : void
    //
    /////////////////////////////////////////////////////////////////////////////
    public void ExportCSV()
    {
        DisplayLog();
        if(Database.isEmpty())
        {
            return;
        }

        String FileName = "MarvellousStudy.csv";

        try(FileWriter fwobj = new FileWriter(FileName))
        {
            // Write header
            fwobj.write("Date,Subject,Duration,Description\n");

            //Travel database
            for(StudyLog sobj : Database)
            {
                // Write each record in CSV
                fwobj.write(sobj.getDate()+","+sobj.getSubject().replace(",", " ")+","+sobj.getDuration()+","+sobj.getDescription().replace(",", " ")+"\n");
            }
            System.out.println("Log created succesfully");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating the csv");
            System.out.println("Report the issue to Marvellous Infosystems");
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : SummuryByDate
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Generates daily summary of total study duration
    //  Returns         : TreeMap<LocalDate, Double>
    //
    /////////////////////////////////////////////////////////////////////////////
    public TreeMap<LocalDate,Double> SummuryByDate() 
    {
        DisplayLog();
        TreeMap<LocalDate,Double> tobj = new TreeMap<LocalDate,Double>();
        LocalDate lobj = null;
        double d,old;
        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();
            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj, d+old);
            }
            else
            {
                tobj.put(lobj, d);
            }
        }
        for(LocalDate lDate : tobj.keySet())
        {
            System.out.println(lDate+"\t"+tobj.get(lobj));
        }
        return tobj;
    }

    /////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : SummuryBySubject
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Generates subject-wise summary of total study duration
    //  Returns         : TreeMap<String, Double>
    //
    /////////////////////////////////////////////////////////////////////////////
    public TreeMap<String,Double> SummuryBySubject()
    {
        DisplayLog();
        TreeMap<String,Double> tobj = new TreeMap<String,Double>();
        String s;
        double d,old;
        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();
            if(tobj.containsKey(s))
            {
                old = tobj.get(s);
                tobj.put(s, d+old);
            }
            else
            {
                tobj.put(s, d);
            }
        }
        return tobj;
    }
}
