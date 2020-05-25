/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Timestamp;

/**
 *
 * @author Goleniewski
 */
public class Note {
    int id;
    String title;
    String note;
    Timestamp creationdate;
    int importance;

    public Note(int id, String title, String note, Object creationdate, int importance) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.creationdate = (Timestamp)creationdate;
        this.importance = importance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
    
}
