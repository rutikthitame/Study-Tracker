package MarvellousStudyTracker;

import java.time.LocalDate;

/////////////////////////////////////////////////////////////////////////
//
//  Class :         StudyLog
//  Description:    Represents a single study record with Date, Subject, 
//                  Duration, and Description fields. 
//  Author :        Rutik Shivaji Thitame
//
/////////////////////////////////////////////////////////////////////////

public class StudyLog
{
    // Attributes of StudyLog
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : StudyLog (Constructor)
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : LocalDate A, String B, double C, String D
    //  Description     : Initializes a study log entry with given parameters
    //  Returns         : NONE
    //
    /////////////////////////////////////////////////////////////////////////////////
    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : toString
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Returns string representation of the StudyLog object
    //  Returns         : String
    //
    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+Duration+" | "+Description;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : getDate
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Getter for Date field
    //  Returns         : LocalDate
    //
    /////////////////////////////////////////////////////////////////////////////////
    public LocalDate getDate()
    {
        return Date;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : getSubject
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Getter for Subject field
    //  Returns         : String
    //
    /////////////////////////////////////////////////////////////////////////////////
    public String getSubject()
    {
        return Subject;
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : getDuration
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Getter for Duration field
    //  Returns         : double
    //
    /////////////////////////////////////////////////////////////////////////////////
    public double getDuration()
    {
        return Duration;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : getDescription
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : NONE
    //  Description     : Getter for Description field
    //  Returns         : String
    //
    /////////////////////////////////////////////////////////////////////////////////
    public String getDescription()
    {
        return Description;
    }

}
