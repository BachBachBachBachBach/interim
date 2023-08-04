import java.io.*;

public class Book {

    //Attributes of the Book class

    private String name;
    private String author;
    private String libRef;
    private int printYear;
    private String genre;
    private String relevantCourse;

    //constructors with method overload
    //Default Constructors

    public Book() {
        this("N/A","N/A","N/A",0,"N/A","N/A");
    }

    public Book(String name, String author, String libRef, int printYear, String genre) {
        this(name,author,libRef,printYear,genre,"N/A");
    }

    public Book(String name, String author, String libRef, int printYear) {
        this(name,author,libRef,printYear,"N/A","N/A");
    }

    public Book(String name, String author, String libRef) {
        this(name,author,libRef,0,"N/A","N/A");
    }

    public Book(String name, String author) {
        this(name,author,"N/A",0,"N/A","N/A");
    }

    public Book(String name) {
        this(name,"N/A","N/A",0,"N/A","N/A");
    }
    //Constructors to set all the attributes
    public Book(String name, String author, String libRef, int printYear, String genre, String relevantCourse) {
        this.name = name;
        this.author = author;
        this.libRef = libRef;
        this.printYear = printYear;
        this.genre = genre;
        this.relevantCourse = relevantCourse;
    }

    //Get methods to retrieve the attributes
    public String getAuthor() {
        return author;
    }

    public String getLibRef() {
        return libRef;
    }

    public String getName() {
        return name;
    }

    public int getPrintYear() {
        return printYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getRelevantCourse() {
        return relevantCourse;
    }

    //Set methods to update the attributes
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRelevantCourse(String relevantCourse) {
        this.relevantCourse = relevantCourse;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLibRef(String libRef) {
        this.libRef = libRef;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrintYear(int printYear) {
        if (printYear <= 0) {
            throw new IllegalArgumentException("Please enter a valid print year");
        } else {
            this.printYear = printYear;
        }
        
    }

    //set the file attribure according to a line of strings
    public void fileToBook(String fileToBookString) {
        String[] details = fileToBookString.split("\t",0);
        name = details[0];
        author = details[1];
        libRef = details[2];
        printYear = Integer.parseInt(details[3].trim());
        genre = details[4];
        relevantCourse = details[5];
    }

    public String toString() {
        return String.format("%-15s\t%-15s\t%-15s\t%-15d\t%-15s\t%-15s",name,author,libRef,printYear,genre,relevantCourse);
    }

}
