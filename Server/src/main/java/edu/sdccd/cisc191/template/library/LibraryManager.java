package edu.sdccd.cisc191.template.library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager implements Serializable
{
    private Library library = new Library();

    public static LibraryManager getInstance()
    {
        return LibraryManagerHolder.INSTANCE;
    }

    public Library getLibrary()
    {
        return library;
    }

    // Save the library to a file using serialization.
    //module 4 I/O streams
    public void saveLibraryToFile(String filename)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(library);
            System.out.println("Library saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving library to file: " + filename);
            e.printStackTrace();
        }
    }

    // Load the library from a file using deserialization.

    public void loadLibraryFromFile(String filename)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            library = (Library) ois.readObject();
            System.out.println("Library loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error loading library from file: " + filename);
            e.printStackTrace();
        }
    }

    private static class LibraryManagerHolder
    {
        private static final LibraryManager INSTANCE = new LibraryManager();
    }
}

class Library implements Serializable
{
    private List<Book> library = new ArrayList<>();
     //generic collections

     // Add a book to the library.


    public void addBook(Book book)
    {
        library.add(book);
        System.out.println("Book added successfully.");
    }
    public int getLibrarySize()
    {
        return library.size();
    }
    /**
     * Delete a book from the library by index.
     * @return true if the book is deleted successfully, false otherwise
     */

    public boolean deleteBookByIndex(int index)
        {
        if (index >= 0 && index < library.size())
        {
        library.remove(index);
        System.out.println("Book deleted successfully.");
        return true;
        } else {
        System.out.println("Invalid index.");
        return false;
        }
        }

/**
 * Get a book from the library by index.
 * @return the book at the specified index, or null if the index is invalid
 */
public Book getBookByIndex(int index)
        {
        if (index >= 0 && index < library.size())
        {
        return library.get(index);
        } else {
        return null;
        }
        }

/**
 * Get information about all books in the library.
 *
 * return a string containing information about all books in the library
 */
public String getAllBooksInfo()
        {
        StringBuilder builder = new StringBuilder();
        if (library.isEmpty())
        {
        builder.append("No books in the library.");
        } else {
        for (int i = 0; i < library.size(); i++)
        {
        builder.append(i).append(". ").append(library.get(i)).append("\n");
        }
        }
        return builder.toString();
        }

/**
 * Expand the library.
 */
public void expandLibrary()
        {
        System.out.println("Library expanded.");
        }

/**
 * Shrink the library.
 */
public void shrinkLibrary()
        {
        System.out.println("Library shrunk.");
        }
        }

class Book implements Serializable
{
    private String title;
    private String author;

    public Book(String title, String author)
    {
        this.title = title;
        this.author = author;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }
}
