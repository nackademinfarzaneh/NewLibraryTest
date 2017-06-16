package se.nackademin.librarytest.librarytestsystem.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * JPA bean for representing a loan in the library.
 *
 * @author Lennart Moraeus
 */
public class Loan {
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    public static final String FIND_ALL = "Loan.findAll";
    public static final String FIND_ALL_WITH_USER = "Loan.findAllWithUser";
    public static final String FIND_ALL_WITH_BOOK = "Loan.findAllWithBook";
    public static final String FIND_ALL_WITH_BOOK_AND_USER = "Loan.findAllWithBookAndUser";

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
//    private Date dateBorrowed;
//    private Date dateDue;
    private String dateBorrowed;
    private String dateDue;
    private Integer id;

    public Loan() {
        this(null, null, null, null, null);
    }
 
    
//    public Loan(Integer id, Book book1, String dateBorrowedStr, String dateDueStr, User user) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    

//    public Loan(Integer id ,Book book, User user, String dateBorrowedStr, String dateDueStr) {
//        this(null , id , book, user, dateBorrowedStr, dateDueStr);
//        
//    }
    public Loan(Integer id, Book book, String dateBorrowedStr, String dateDueStr , User user) {
        this.id = id;
        this.book = book;
        this.user = user;
        setDateBorrowed(dateBorrowedStr);
        setDateDue(dateDueStr);
    }

    public Loan(Book book, String dateBorrowedStr, String dateDueStr, User user) {
        this.book = book;
        setDateBorrowed(dateBorrowedStr);
        setDateDue(dateDueStr);
        this.user = user;

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateBorrowed() {
        return String.valueOf(dateBorrowed);
    }

    public String getDateDue() {
        return String.valueOf(dateDue);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Loan)) {
            return super.equals(other);
        }
        Loan otherLoan = (Loan) other;
        return getId().equals(otherLoan.getId())
                && book.equals(otherLoan.book)
                && user.equals(otherLoan.user)
                && dateBorrowed.equals(otherLoan.dateBorrowed)
                && dateDue.equals(otherLoan.dateDue);
    }

    /**
     * @param dateBorrowed the dateBorrowed to set
     */
    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * @param dateDue the dateDue to set
     */
    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }
}
