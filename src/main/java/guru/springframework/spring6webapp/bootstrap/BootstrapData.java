package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {

        Publisher publisher = new Publisher();
        publisher.setName("O'Reilly");
        publisher.setAddress("123 Main St.");
        publisher.setCity("San Francisco");
        publisher.setState("CA");
        publisher.setZip("94107");

        publisherRepository.save(publisher);

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain-Driven Design");
        ddd.setIsbn("978-0321125217");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        dddSaved.getAuthors().add(ericSaved);
        dddSaved.setPublisher(publisher);
        bookRepository.save(dddSaved);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("978-0321125217");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        noEJBSaved.getAuthors().add(rodSaved);
        noEJBSaved.setPublisher(publisher);
        bookRepository.save(noEJBSaved);

        log.info("Bootstrap data loaded successfully");

        log.info("Authors count: " + authorRepository.count());
        log.info("Books count: " + bookRepository.count());
        log.info("Publisher count: " + publisherRepository.count());
    }

}
