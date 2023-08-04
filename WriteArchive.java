import java.io.*;


// EriteArchive class to wirte the booklist to a file
public class WriteArchive {
    private File archive;

    public File getArchive() {
        return archive;
    }


    public void setArchive(File archive) {
        this.archive = archive;
    }


    public WriteArchive(File file){
        archive = file;
    }
    
    //Write the book list to the specified file path
    public void writeToArchive(String list) throws FileNotFoundException{
        

        PrintWriter writeFile = new PrintWriter(archive);

        writeFile.write(list);

        writeFile.close();

    }


}
