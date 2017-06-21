package se.nackademin.librarytest.librarytestsystem.model;

import se.nackademin.librarytest.librarytestsystem.model.Author;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Container for a list of authors, to facilitate XML serialization.
 *
 * @author Lennart Moraeus
 */
public class Authors extends ArrayList<Author> {

    private static final long serialVersionUID = -6552522298451729237L;

    public Authors() {
        super();
    }

    public Authors(Collection<? extends Author> c) {
        super(c);
    }

    public List<Author> getAuthors() {
        return this;
    }

    public void setAuthors(List<Author> authors) {
        this.addAll(authors);
    }
}
