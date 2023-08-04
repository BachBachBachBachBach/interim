import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.event.*;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;


public class BookUI extends Application {

    static BookList currentList =  new BookList();

    

    static Book currentBook =  new Book();

    static int k = 0;

    

    @Override
    public void start(Stage primaryStage) {

        
        //Creating the grid Pane
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(11, 12, 11, 12));
        mainPane.setHgap(5.5);
        mainPane.setVgap(5.5);
        
        //buttons set
        Button addBook = new Button("Add Book");
        Button removeCurrent = new Button("Remove Current Book");
        Button firstBook = new Button("First Book");
        Button lastBook = new Button("Last Book");
        Button nextBook = new Button("Next Book");
        Button previousBook = new Button("Previous Book");
        Button showBookList = new Button("Show Book List");
        Button saveBookList = new Button("Save List");
        Button seachBook = new Button("Search by Title");
        Button openFile = new Button("Open List");
        Button saveToCurrent = new Button("Edit current book");

        seachBook.setMinWidth(240);
        removeCurrent.setMinWidth(200);
        addBook.setMinWidth(100);
        firstBook.setMinWidth(100);
        lastBook.setMinWidth(200);
        nextBook.setMinWidth(200);
        previousBook.setMinWidth(100);
        showBookList.setMinWidth(100);
        saveBookList.setMinWidth(100);
        openFile.setMinWidth(100);
        saveToCurrent.setMinWidth(240);
        

        //label
        Label title = new Label("Book Title: ");
        Label author = new Label("Author: ");
        Label libref = new Label("Library Reference: ");
        Label printYear = new Label("Print Year: ");
        Label genre = new Label("Book Genre: ");
        Label course = new Label("Relevant Course: ");
        Label searchTitle = new Label("Search by Title: ");

        //text boxes
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        TextField librefField = new TextField();
        TextField printYearField = new TextField();
        TextField genreField = new TextField();
        TextField courseField = new TextField();
        TextField searchTitleField = new TextField();

        //display area
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setFont(Font.font("monospace"));
        
        
        //setting the layout
        //label
        mainPane.add(displayArea,0,0);
        mainPane.add(title,0,1);
        mainPane.add(author,0,2);
        mainPane.add(libref,0,3);
        mainPane.add(printYear,2,1);
        mainPane.add(genre,2,2);
        mainPane.add(course,2,3);
        mainPane.add(searchTitle, 5, 1);
        //button
        mainPane.add(addBook, 0, 4);
        mainPane.add(removeCurrent, 1, 4);
        mainPane.add(saveBookList, 2, 4);
        mainPane.add(firstBook, 0, 5);
        mainPane.add(lastBook, 1, 5);
        mainPane.add(previousBook, 0, 6);
        mainPane.add(nextBook, 1, 6);
        mainPane.add(showBookList, 2, 6);
        mainPane.add(openFile, 2, 5);
        mainPane.add(seachBook, 5, 2);
        mainPane.add(saveToCurrent, 5, 3);
        //fields
        mainPane.add(titleField, 1, 1);
        mainPane.add(authorField, 1, 2);
        mainPane.add(librefField, 1, 3);
        mainPane.add(printYearField, 3, 1);
        mainPane.add(genreField, 3, 2);
        mainPane.add(courseField, 3, 3);
        mainPane.add(searchTitleField, 6, 1);
        
        //Set nodes/button to span certain collumn
        mainPane.setColumnSpan(displayArea, 7);
        
        mainPane.setColumnSpan(seachBook, 2);
        mainPane.setColumnSpan(saveToCurrent, 2);

        //FileChooser fileChooser = new FileChooser();
        //fileChooser.setTitle("Open Resource File");
        //fileChooser.showOpenDialog(primaryStage);


        Scene mainScene = new Scene(mainPane);
        primaryStage.setTitle("Book Archive");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        //button event handling

        //open a file with filechooser > parse each line to a new Book object using fileToBook method 
        openFile.setOnAction(e ->{
            try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
                    
                    String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
                    fileChooser.setInitialDirectory(new File(currentPath));
                    
                    fileChooser.setTitle("Open Book List");
                    Stage stage = new Stage();
                    //fileChooser.showOpenDialog(stage);
                    File list = fileChooser.showOpenDialog(stage);
                    
                    if (currentList != null){
                        currentList.clear();

                    }


                    currentList = new BookList();
                    
                    File sourceFile = new File(list.getPath());
    
                    Scanner input = new Scanner(sourceFile);
                    
                    int x = 0;
                    input.nextLine();
                    while (input.hasNextLine() == true) {
                        String newBook = input.nextLine();
            
                        
                        
                        currentList.add(x,new Book());
                        currentList.get(x).fileToBook(newBook);;
                        x++;
                    }

                    input.close();
                    

                    //displayArea.setText(currentList.toString() + "\n" + currentList.isEmpty());
                    displayArea.setText(showTheList());
                

                } catch (Exception x) {
                    displayArea.setText(x.getMessage());
                }
        });

        //use fiel chooser to pick directory and fileneame and call WriteArhives method to wrtire file
        saveBookList.setOnAction(e ->{
            try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

                    String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
                    fileChooser.setInitialDirectory(new File(currentPath));

                    fileChooser.setTitle("Save Book List");
                    Stage stage = new Stage();
                    WriteArchive savelist = new WriteArchive(fileChooser.showSaveDialog(stage));
                    savelist.writeToArchive(showTheList());
                } catch (Exception x) {
                    displayArea.setText(x.getMessage());
                }
        });

        //add book to lisk using the fields as inputs parameters, check if book title is blank and year is valid
        addBook.setOnAction(e -> {    
            String bookName = titleField.getText();
            String authorName = authorField.getText();
            String librefstr = librefField.getText();
            
            String genre1 = genreField.getText();
            String course1 = courseField.getText();

            if (bookName == "" || bookName.length() <= 0) {
                displayArea.setText("Please enter a book title");
            } else if (printYearField.getText() == null || printYearField.getText().length() <= 0) {
                displayArea.setText("Please enter a valid print year");
            } else {
                try {
                    int prYr = Integer.parseInt(printYearField.getText());
                    Book newBook = new Book(bookName, authorName, librefstr, prYr, genre1, course1);
                    currentList.add(newBook);
                    displayArea.setText(showTheList());

                    titleField.setText("");
                    authorField.setText("");
                    librefField.setText("");
                    printYearField.setText("");
                    genreField.setText("");
                    courseField.setText("");
                } catch (NumberFormatException error) {
                    displayArea.setText("Please enter a valid print year");
                }
            }
        });

        //display the book list  on the text area
        showBookList.setOnAction(e ->{

            displayArea.setText(showTheList());   
        });

        
        
        //check each book title and display the match
        seachBook.setOnAction(e ->{    
            String criteria = searchTitleField.getText();
            for (Book book : currentList) {
                if (book.getName().contains(criteria)) {
                    currentBook = book;
                    displayArea.setText(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course") + 
                    currentBook.toString());
                }
            }
        });

        //display the 1st book  on the text area
        firstBook.setOnAction(e -> {

            if (currentList.size() == 0) {
                displayArea.setText("The list is empty");
            } else {
                k = 0;
                currentBook = currentList.get(k);
                displayArea.setText(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course") + 
                currentList.get(0).toString());
            }
        });

        //display the last book  on the text area
        lastBook.setOnAction(e -> {
            if (currentList.size() == 0) {
                displayArea.setText("The list is empty");
            } else {
                k = currentList.size() - 1;
                currentBook = currentList.get(k);
                displayArea.setText(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course") + 
                currentList.get(currentList.size() - 1).toString());
            }
        });

        //display the next book in the list  on the text area
        nextBook.setOnAction(e -> {
            
            if (currentList.size() == 0) {
                displayArea.setText("The list is empty");
            } else {
                if (k < currentList.size() - 1) {
                    k += 1;
                }
                
                currentBook = currentList.get(k);
                displayArea.setText(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course") + 
                currentList.get(k).toString());
            }  
        });

        //display the previous book in the list  on the text area
        previousBook.setOnAction(e -> {
            if (currentList.size() == 0) {
                displayArea.setText("The list is empty");
            } else {
                if (k !=0) {
                    k -= 1;
                }               
                currentBook = currentList.get(k);
                displayArea.setText(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course") + 
                currentList.get(k).toString());
            }        
        });

        //remove the current selected book
        removeCurrent.setOnAction(e -> {
            currentList.remove(currentBook);
            displayArea.setText(showTheList());
        });

        //use the filled text field as parameters to edit the currently selected book, blank field does edit the book attributes 
        saveToCurrent.setOnAction(e -> {
            String replaceName = titleField.getText();
            String replaceAuthor = authorField.getText();
            String replaceLib = librefField.getText();
            String replaceYear = printYearField.getText();
            String replaceGenre = genreField.getText();
            String replaceCourse = courseField.getText();

            if (replaceName != "" && replaceName.length() > 0) {
                currentBook.setName(replaceName);
            }
            
            if (replaceAuthor != "" && replaceAuthor.length() > 0) {
                currentBook.setAuthor(replaceAuthor);
            }

            if (replaceLib != "" && replaceLib.length() > 0) {
                currentBook.setLibRef(replaceLib);
            }

            if (replaceYear != "" && replaceYear.length() > 0) {
                currentBook.setPrintYear(Integer.parseInt(replaceYear));
            }

            if (replaceGenre != "" && replaceGenre.length() > 0) {
                currentBook.setGenre(replaceGenre);
            }

            if (replaceCourse != "" && replaceCourse.length() > 0) {
                currentBook.setRelevantCourse(replaceCourse);
            }
            
            titleField.setText("");
            authorField.setText("");
            librefField.setText("");
            printYearField.setText("");
            genreField.setText("");
            courseField.setText("");

            displayArea.setText(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course") + 
            currentBook.toString());
        });
        


        
    }

    //format list strings for display
    public String showTheList(){
       StringBuffer buffer1 = new StringBuffer("");
            buffer1.append(String.format("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","Name","Author","Reference","Print-Year","Genre", "Course"));
            int i = 0;
            while (i < currentList.size()) {
                buffer1.append(currentList.get(i).toString()+ "\n");
                i++;
            }
            String outString = buffer1.toString();
            return outString;
 
    }

    public static void main(String[] args) {
        
        launch(args);
    }

    

}
