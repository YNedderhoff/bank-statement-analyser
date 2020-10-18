package xyz.nedderhoff.bankstatementanalyser.exceptions;

public class FileAlreadyParsedException extends Exception {
    public FileAlreadyParsedException(String message) {
        super(message);
    }
}
