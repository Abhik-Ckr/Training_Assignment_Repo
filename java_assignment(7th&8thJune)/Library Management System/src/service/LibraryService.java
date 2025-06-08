package service;

import exception.BookNotAvailableException;
import exception.MemberNotFoundException;
import exception.OverdueBookException;
import model.*;
import repository.BookRepository;
import repository.LendingRecordRepository;
import repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LibraryService {
    private final BookRepository bookRepository = new BookRepository();
    private final MemberRepository memberRepository = new MemberRepository();
    private final LendingRecordRepository lendingRecordRepository = new LendingRecordRepository();

    //adding a new book in the library
    public void addBook(Book book){
        bookRepository.add(book.getBookId(), book);
    }

    //new member at a library
    public void registerMember(Member member){
        memberRepository.add(member.getMemberId(), member);
    }

    //issueBook function
    public void issueBook(String bookId, String memberId) throws Exception {
        Book book = bookRepository.get(bookId);
        if(book == null){
            throw new IllegalArgumentException("Book with id : " + bookId + " not found");
        }

        Member member = memberRepository.get(memberId);
        if(member == null){
            throw new MemberNotFoundException("Member with ID : " + memberId + " not found");
        }

        if(book.getStatus() == BookStatus.ISSUED){
            throw new BookNotAvailableException("Book with ID : " + bookId + " is already issued");
        }

        List<LendingRecord> memberRecords = lendingRecordRepository.findByMemberId(memberId);
        boolean hasOverdue = memberRecords.stream().anyMatch(record -> record.getReturnDate() == null && record.getDueDate().isBefore(LocalDate.now()));
        if(hasOverdue){
            throw new OverdueBookException("Member with ID : " + memberId + " has overdue books");
        }

        //updating book status
        book.setStatus(BookStatus.ISSUED); // new lending rec.

        LendingRecord record = new LendingRecord(
                UUID.randomUUID().toString(), // new unique record id.
                book,
                member,
                LocalDate.now(),
                LocalDate.now().plusDays(14), //setting up the due date after issuing.
                null
        );
        lendingRecordRepository.add(record.getRecordId(), record);
    }

    //return a book issed to a memeber by updating return date and issue date
    public void returnBook(String bookId, String memberId){
        List<LendingRecord> records = lendingRecordRepository.findByMemberId(memberId);
        for(LendingRecord record : records){
            if(record.getBook().getBookId().equals(bookId)&& record.getReturnDate() == null){
                record.setReturnDate(LocalDate.now());
                record.getBook().setStatus(BookStatus.AVAILABLE);
                break;
            }
        }
    }

    //list books that are currently available for lending
    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }

    //books issued to members
    public List<Book> getIssuedBooks() {
        return bookRepository.findIssuedBooks();
    }

    //members who currently have one or more book issued
    public List<Member> getMemberWithActiveBorrowings() {
        return lendingRecordRepository.findActiveLendings().stream().map(LendingRecord::getMember).distinct().toList();
    }

    //overdue books
    public List<LendingRecord> getOverdueRecords() {
        return lendingRecordRepository.findOverdueRecords();
    }

    //searching books by title
    public List<Book> searchBooksByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    //searching books with author name
    public List<Book> searchBooksByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    //search members by email
    public List<Member> searchMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    //search members by name
    public List<Member> serachMemberByName(String name){
        return memberRepository.findByName(name);
    }

    public void addLendingRecord(LendingRecord lendingRecord) {
        if (lendingRecord == null) {
            throw new IllegalArgumentException("LendingRecord cannot be null");
        }

        // Ensure the book exists in the repository
        Book book = bookRepository.get(lendingRecord.getBook().getBookId());
        if (book == null) {
            throw new IllegalArgumentException("Book with ID " + lendingRecord.getBook().getBookId() + " does not exist");
        }

        // Ensure the member exists in the repository
        Member member = memberRepository.get(lendingRecord.getMember().getMemberId());
        if (member == null) {
            throw new IllegalArgumentException("Member with ID " + lendingRecord.getMember().getMemberId() + " does not exist");
        }

        // Add the lending record to the repository
        lendingRecordRepository.add(lendingRecord.getRecordId(), lendingRecord);

        // Update the book's status to ISSUED
        book.setStatus(BookStatus.ISSUED);
    }
}
