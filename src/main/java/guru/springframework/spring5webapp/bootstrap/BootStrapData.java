package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher folio = new Publisher("Folio", "rue de l'Eglise 19", "Mons", "Wallonie", "5000");
        publisherRepository.save(folio);

        System.out.println("Number of publisher: " + publisherRepository.count());

        Author cormacMcCarthy = new Author("Cormac", "McCarthy");
        Book theRoad = new Book("the Road", "244484");
        cormacMcCarthy.getBooks().add(theRoad);
        theRoad.getAuthors().add(cormacMcCarthy);

        theRoad.setPublisher(folio);
        folio.getBooks().add(theRoad);

        authorRepository.save(cormacMcCarthy);
        bookRepository.save(theRoad);
        publisherRepository.save(folio);


        Author rogerMartinDuGrand = new Author("Roger Martin", "DuGrand");
        Book jeanBarois = new Book("Jean Barois", "554019");
        rogerMartinDuGrand.getBooks().add(jeanBarois);
        jeanBarois.getAuthors().add(rogerMartinDuGrand);

        folio.getBooks().add(jeanBarois);
        jeanBarois.setPublisher(folio);

        authorRepository.save(rogerMartinDuGrand);
        bookRepository.save(jeanBarois);
        publisherRepository.save(folio);

        System.out.println("Number of publishers: " + bookRepository.count());
        System.out.println("Number of books: " + folio.getBooks().size());
    }
}
