package Entities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeBeers on 23.07.2016.
 */
public class Message {

    public String reciever;
    public String subject;
    public String message;
    public String pathToFile;

    public Message(String reciever, String subject, String message, String pathToFile) {
        this.reciever = reciever;
        this.subject = subject;
        this.message = message;
        this.pathToFile = pathToFile;
    }


    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
