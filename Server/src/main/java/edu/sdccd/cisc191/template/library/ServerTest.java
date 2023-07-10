package edu.sdccd.cisc191.template.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ServerTest
{


        @Test
        public void testModule1() {
            LibraryManager libraryManager = LibraryManager.getInstance();

            // Test expandLibrary()
            libraryManager.getLibrary().expandLibrary();
            // Add assertions to validate the library expansion

            // Test shrinkLibrary()
            libraryManager.getLibrary().shrinkLibrary();
            // Add assertions to validate the library shrinking
        }

        @Test
        public void testModule3()
        {
            Book book = new Book("Title", "Author");

            // Test getter and setter methods
            assertEquals("Title", book.getTitle());
            assertEquals("Author", book.getAuthor());

            // Test setting new values
            book.setTitle("New Title");
            book.setAuthor("New Author");

            // Verify the updated values
            assertEquals("New Title", book.getTitle());
            assertEquals("New Author", book.getAuthor());
        }

        @Test
        public void testModule4() {
            LibraryManager libraryManager = LibraryManager.getInstance();

            // Test saveLibraryToFile() and loadLibraryFromFile()
            libraryManager.getLibrary().addBook(new Book("Title", "Author"));
            libraryManager.saveLibraryToFile("library.ser");

            // Clear library
            libraryManager.getLibrary().deleteBookByIndex(0);

            libraryManager.loadLibraryFromFile("library.ser");
            assertEquals(1, libraryManager.getLibrary().getLibrarySize());
            assertNotNull(libraryManager.getLibrary().getBookByIndex(0));
        }



}