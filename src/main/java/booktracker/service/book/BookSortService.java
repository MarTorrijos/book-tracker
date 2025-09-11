package booktracker.service.book;

import booktracker.dao.book.BookDao;
import booktracker.entities.Book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BookSortService {

    private final BookDao bookDao;

    public BookSortService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getSortedBooks(Boolean isRead, BookSortCriteria criteria) {
        Stream<Book> selection = bookDao.findAllBooks().stream();

        if (isRead != null) {
            selection = selection.filter(book -> book.isRead() == isRead);
        }

        selection = switch (criteria) {
            case AUTHOR_ASC -> selection.sorted(Comparator.comparing(
                    this::getAuthorName,
                    Comparator.nullsLast(String::compareToIgnoreCase)));

            case AUTHOR_DESC -> selection.sorted(Comparator.comparing(
                    this::getAuthorName,
                    Comparator.nullsLast(String::compareToIgnoreCase)).reversed());

            case TITLE_ASC -> selection.sorted(Comparator.comparing(
                    Book::getTitle, Comparator.nullsLast(String::compareToIgnoreCase)));

            case TITLE_DESC -> selection.sorted(Comparator.comparing(
                    Book::getTitle, Comparator.nullsLast(String::compareToIgnoreCase)).reversed());

            case READ_YEAR_ASC -> selection.sorted(Comparator.comparing(
                    this::getReadYear,
                    Comparator.nullsLast(Integer::compareTo)));

            case READ_YEAR_DESC -> selection.sorted(Comparator.comparing(
                    this::getReadYear,
                    Comparator.nullsLast(Integer::compareTo)).reversed());
        };

        return selection.toList();
    }

    private String getAuthorName(Book book) {
        return book.getAuthor() != null ? book.getAuthor().getName() : null;
    }

    private Integer getReadYear(Book book) {
        return book.getReview() != null ? book.getReview().getReadIn() : null;
    }

}