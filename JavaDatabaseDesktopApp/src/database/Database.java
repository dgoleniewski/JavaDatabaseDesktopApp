/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Goleniewski
 */
public class Database {
    Connection connection;
    public Database(){
            
    }
    public void addNote(String title, String note, int importance){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
            "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=Note",
            "noteLogin","qwerty"
            );
            CallableStatement cs=
                connection.prepareCall("{call dbo.addNote(?,?,?)}");
            cs.setString(1, title);
            cs.setString(2, note);
            cs.setInt(3, importance);
            cs.execute();
            connection.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Błąd "+e.getMessage(),
                "Błąd",JOptionPane.ERROR_MESSAGE);
            }
    }
    public void updateNote(int id, String title, String note, int importance){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
            "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=Note",
            "noteLogin","qwerty"
            );
            CallableStatement cs=
                connection.prepareCall("{call dbo.updateNote(?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, title);
            cs.setString(3, note);
            cs.setInt(4, importance);
            cs.execute();
            connection.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Błąd "+e.getMessage(),
                "Błąd",JOptionPane.ERROR_MESSAGE);
            }
    }
    public void removeNote(int id){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
            "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=Note",
            "noteLogin","qwerty"
            );
            CallableStatement cs=
                connection.prepareCall("{call dbo.removeNote(?)}");
            cs.setInt(1, id);
            cs.execute();
            connection.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Błąd "+e.getMessage(),
                "Błąd",JOptionPane.ERROR_MESSAGE);
            }
    }
    public ArrayList getNotes(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
                "jdbc:sqlserver://localhost\\mssqlserver;databaseName=Note",
                "noteLogin","qwerty"
            );
            ArrayList<Note> notes=new ArrayList();
            PreparedStatement ps=
                connection.prepareStatement("{call dbo.getNotes}");
            ResultSet result=ps.executeQuery();
            while(result.next()){
                notes.add(new Note(result.getInt("id"),
                    result.getString("title"),
                    result.getString("note"),
                    result.getObject("creationdate"),
                    result.getInt("importance")));
            }
            connection.close();
            return notes;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Błąd  "+e.getMessage(),
                "Błąd",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
