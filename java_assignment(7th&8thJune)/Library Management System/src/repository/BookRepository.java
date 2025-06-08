package repository;

import model.Book;
import model.BookStatus;

import java.util.List;
import java.util.stream.Collectors;

public class BookRepository extends Repository<Book>{
    public List<Book> findByAuthor(String author){
        return getAll().stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }
    public List<Book> findByTitle(String title){
        return getAll().stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }
    public List<Book> findAvailableBooks(){
        return getAll().stream().filter(book -> book.getStatus() == BookStatus.AVAILABLE).collect(Collectors.toList());
    }
    public List<Book> findIssuedBooks(){
        return getAll().stream().filter(book -> book.getStatus() == BookStatus.ISSUED).collect(Collectors.toList());
    }

}
