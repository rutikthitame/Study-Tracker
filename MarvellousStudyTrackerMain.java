import MarvellousStudyTracker.StudyLog;
import MarvellousStudyTracker.StudyTracker;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

/////////////////////////////////////////////////////////////////////////
//
//  Class :         MarvellousStudyTrackerMain
//  Description:    Entry point for the Study Tracker application. 
//                  Provides GUI for inserting, displaying, summarizing,
//                  and exporting study logs.
//  Author :        Rutik Shivaji Thitame
//
/////////////////////////////////////////////////////////////////////////

public class MarvellousStudyTrackerMain 
{
    /////////////////////////////////////////////////////////////////////////////////
    //
    //  Function Name   : main
    //  Function Date   : 17/09/2025
    //  Function Author : Rutik Shivaji Thitame
    //  Parameters      : String A[]  → Command-line arguments
    //  Description     : Launches GUI window for Study Tracker application.
    //                    Allows user to Insert log, Display log, Show Summary,
    //                    and Export data to CSV.
    //  Returns         : void
    //
    /////////////////////////////////////////////////////////////////////////////////
    public static void main(String A[]) 
    {
        StudyTracker stobj = new StudyTracker(); // ← Single shared instance
        ArrayList<StudyLog> database = stobj.getDatabase();

        JFrame frame = new JFrame("Marvellous Study Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);

        // Components
        JLabel jlobj1 = new JLabel("Enter Subject : ");
        JLabel jlobj2 = new JLabel("Enter Duration : ");
        JLabel jlobj3 = new JLabel("Enter Description : ");
        jlobj1.setBounds(120, 100, 200, 30);
        jlobj2.setBounds(120, 150, 200, 30);
        jlobj3.setBounds(120, 200, 200, 30);

        JTextField jtobj1 = new JTextField(30);
        JTextField jtobj2 = new JTextField(30);
        JTextField jtobj3 = new JTextField(30);
        jtobj1.setBounds(250, 100, 200, 30);
        jtobj2.setBounds(250, 150, 200, 30);
        jtobj3.setBounds(250, 200, 200, 30);

        JTextArea jtaobj = new JTextArea(20, 20);
        jtaobj.setBounds(100, 100, 400, 200);

        JButton jbSubmit = new JButton("Submit");
        jbSubmit.setBounds(250, 250, 100, 40);

        JButton jbExport = new JButton("Export");
        jbExport.setBounds(250, 250, 100, 40);

        // Submit action
        jbSubmit.addActionListener(e -> 
        {
            try {
                String subject = jtobj1.getText();
                double duration = Double.parseDouble(jtobj2.getText());
                String description = jtobj3.getText();

                StudyLog log = new StudyLog(LocalDate.now(), subject, duration, description);
                stobj.InsertLog(log);

                JOptionPane.showMessageDialog(frame, "Log added successfully!");
                jtobj1.setText("");
                jtobj2.setText("");
                jtobj3.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // Export action
        jbExport.addActionListener(e -> stobj.ExportCSV());

        // ComboBox
        String[] options = {"Insert Log", "Display Log", "Summary By Date", "Summary By Subject", "Export to csv"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(200, 50, 200, 30);

        comboBox.addActionListener(e -> 
        {
            frame.getContentPane().removeAll(); // clear previous UI
            frame.add(comboBox);

            String selected = (String) comboBox.getSelectedItem();

            if (selected.equals("Insert Log")) 
            {
                frame.add(jlobj1); frame.add(jlobj2); frame.add(jlobj3);
                frame.add(jtobj1); frame.add(jtobj2); frame.add(jtobj3);
                frame.add(jbSubmit);
            }
            else if (selected.equals("Display Log")) 
            {
                StringBuilder sb = new StringBuilder();
                for (StudyLog log : stobj.DisplayLog()) 
                {
                    sb.append(log.toString()).append("\n");
                }
                jtaobj.setText(sb.toString());
                frame.add(jtaobj);
            }
            else if (selected.equals("Summary By Date")) 
            {
                TreeMap<LocalDate, Double> map = new TreeMap<>(stobj.SummuryByDate());
                StringBuilder sb = new StringBuilder();
                for (LocalDate date : map.keySet()) 
                {
                    sb.append("Date: ").append(date).append(" | Total: ").append(map.get(date)).append("\n");
                }
                jtaobj.setText(sb.toString());
                frame.add(jtaobj);
            }
            else if (selected.equals("Summary By Subject")) 
            {
                TreeMap<String, Double> map = new TreeMap<>(stobj.SummuryBySubject());
                StringBuilder sb = new StringBuilder();
                for (String subject : map.keySet()) 
                {
                    sb.append("Subject: ").append(subject).append(" | Total: ").append(map.get(subject)).append("\n");
                }
                jtaobj.setText(sb.toString());
                frame.add(jtaobj);
            }
            else if (selected.equals("Export to csv")) 
            {
                stobj.ExportCSV();
                JOptionPane.showMessageDialog(frame, "Exported to MarvellousStudy.csv");
            }

            frame.repaint();
            frame.revalidate();
        });

        frame.add(comboBox);
        frame.setVisible(true);
    }
}
