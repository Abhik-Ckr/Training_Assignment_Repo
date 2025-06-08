package model;

public class Book implements Comparable<Book>{
    public String bookId;
    public String title;
    public String author;
    public BookStatus isIssued;
    public BookStatus status;



//    public Book(String bookId, String title, String author, BookStatus isIssued, BookStatus status){
//        this.bookId = bookId;
//        this.title = title;
//        this.author = author;
//        this.isIssued = isIssued;
//        this.status = status;
//    }

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public BookStatus isIssued() {
        return isIssued;
    }

    public void setIssued(BookStatus issued) {
        isIssued = issued;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
    @Override
    public int compareTo(Book other){
        return this.title.compareToIgnoreCase(other.title);
    }





}
