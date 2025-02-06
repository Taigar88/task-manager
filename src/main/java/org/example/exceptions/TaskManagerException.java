package org.example.exceptions;

public class TaskManagerException extends RuntimeException {
    public TaskManagerException(String ex) {
        super(ex);
    }
    public TaskManagerException() {
        super("Something Unexpected Happened While trying to create Task");
    }
}
