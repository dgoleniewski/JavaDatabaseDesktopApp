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
    
    public int login(String username, String pass){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
            "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=Note",
            "noteLogin","qwerty"
            );
            int userId = 0;
            PreparedStatement ps=
                connection.prepareCall("{call dbo.findUser(?,?)}");
            ps.setString(1,username);
            ps.setString(2,pass);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                userId = result.getInt("id");
            }
            connection.close();
            return userId;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Błąd "+e.getMessage(),
                "Błąd",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    public void addNote(String title, String note, int importance, int userId){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
            "jdbc:sqlserver://localhost\\MSSQLServer;databaseName=Note",
            "noteLogin","qwerty"
            );
            CallableStatement cs=
                connection.prepareCall("{call dbo.addNote(?,?,?,?)}");
            cs.setString(1, title);
            cs.setString(2, note);
            cs.setInt(3, importance);
            cs.setInt(4, userId);
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
    public ArrayList getNotes(int userId){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection=DriverManager.getConnection(
                "jdbc:sqlserver://localhost\\mssqlserver;databaseName=Note",
                "noteLogin","qwerty"
            );
            ArrayList<Note> notes=new ArrayList();
            PreparedStatement ps=
                connection.prepareStatement("{call dbo.getNotes(?)}");
            ps.setInt(1, userId);
            ResultSet result=ps.executeQuery();
            while(result.next()){
                notes.add(new Note(result.getInt("id"),
                    result.getString("title"),
                    result.getString("note"),
                    result.getObject("creationdate"),
                    result.getInt("importance"),
                    userId));
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
