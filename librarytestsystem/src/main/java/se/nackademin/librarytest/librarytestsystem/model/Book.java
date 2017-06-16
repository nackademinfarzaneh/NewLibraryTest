package se.nackademin.librarytest.librarytestsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author testautom-nack
 */
public class Book {

    private Integer id;
    private String description;
    private String isbn;
    private Integer nbrPages;
    private String publicationDate;
    private String title;
    private Integer totalNbrCopies;

    private Author author;

    private List<Author> authors;  // = new ArrayList<>();

    public Book() {

    }

    public Book(Integer id, String title, String description, String isbn,
            Integer nbrPages, String publicationDate) {

        this.author = null;
        this.id = id;
        this.title = title;
        this.description = null;
        this.isbn = null;
        this.nbrPages = null;
        this.publicationDate = null;
    }

    public Book(Integer id, Author author, String description, String isbn, Integer nberPage, String publicationDate, String title, Integer totalNbrCopies) {

        this.id = id;

        this.author = author;
     //   this.authors.add(author);
        this.description = description;
        this.isbn = isbn;
        this.nbrPages = nberPage;
        this.publicationDate = publicationDate;
        this.title = title;
        this.totalNbrCopies = totalNbrCopies;

       // this.authors = new ArrayList<>();
    }

    public Book(Integer id, String description, String bn, Integer nberPage, String publicationDate, String title, Integer totalNbrCopies) {

        this.id = id;
        this.description = description;
        this.isbn = isbn;
        this.nbrPages = nberPage;
        this.publicationDate = publicationDate;
        this.title = title;
        this.totalNbrCopies = totalNbrCopies;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the nbrPages
     */
    public Integer getNbrPages() {
        return nbrPages;
    }

    /**
     * @param nbrPages the nbrPages to set
     */
    public void setNbrPages(Integer nbrPages) {
        this.nbrPages = nbrPages;
    }

    /**
     * @return the publicationDate
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate the publicationDate to set
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the totalNbrCopies
     */
    public Integer getTotalNbrCopies() {
        return totalNbrCopies;
    }

    /**
     * @param totalNbrCopies the totalNbrCopies to set
     */
    public void setTotalNbrCopies(Integer totalNbrCopies) {
        this.totalNbrCopies = totalNbrCopies;
    }

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

    /**
     * @return the authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }


}
