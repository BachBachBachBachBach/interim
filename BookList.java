import java.io.*;
import java.util.*;

//BookList class represents a list of books and provides methods
// to manipulate the list
public class BookList extends ArrayList<Book> {

    //Attribute to store the list of Books
    private ArrayList<Book> list = new ArrayList<Book>();

    //Constructor to initialize the book list
    public BookList() {
        list = new ArrayList<Book>();
    }





/* 
    public void fileToBook(String filePath) throws FileNotFoundException, IOException {
        
        File sourceFile = new File(filePath);
    
        Scanner input = new Scanner(sourceFile);
        
        

        while (input.hasNextLine() == true) {
            String newBook = input.nextLine();
            Book addbook = new Book(newBook);
            
            list.add(addbook);
            
            
        }
        input.close();
    }

    //Create abook list from file
    public BookList(String filePath) throws FileNotFoundException, IOException {
        
        
        
        list = new ArrayList<Book>();

        //Create new book using String on each lines and add it to the book list
        
        fileToBook(filePath);

        


    }
    */
 
    public String toString() {
        String outString;
        StringBuffer buffer1 = new StringBuffer("");
        //buffer1.append("Name\tAuthor\tReference\tPrint-Year\tGenre Course\n");
        int i = 0;
        while (i < list.size()) {
            buffer1.append(list.get(i).toString()+ "\n");
            i++;
        }

        outString = buffer1.toString();
        return outString;

    }

}

