package your_package.domain.repository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import your_package.IntegrationTest;
import your_package.domain.model.Thingy;

import static org.junit.Assert.*;
import static util.SessionFactoryExtensions.*;

public class GenericRepositoryTest extends IntegrationTest {

    private Repository<Thingy> repository;

    @Before
    public void setUp() throws Exception {
        repository = new GenericRepository<Thingy>(sessionFactory, Thingy.class);
    }

    @Test
    public void should_save_a_new_object() throws Exception {
        Thingy thingy = new Thingy("Hello World!");
        repository.save(thingy);

        assertNotNull(reload(sessionFactory, thingy));
    }

    @Test
    public void should_retrieve_an_object_by_id() throws Exception {
        Thingy thingy = new Thingy("Fetch Me");

        persist(sessionFactory, thingy);

        assertNotNull(repository.get(thingy.getId()));
    }

    @Test
    public void should_retrieve_all_objects() throws Exception {
        persist(sessionFactory, new Thingy("one"), new Thingy("two"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void should_retrieve_all_objects_matching_a_specified_field_value() throws Exception {
        persist(sessionFactory, new Thingy("Miss Me!"), new Thingy("Find Me!"));

        assertEquals(1, repository.findAll("name", "Find Me!").size());
    }

    @Test
    @Ignore
    public void should_retrieve_a_single_object_matching_a_specified_field_value() throws Exception {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Test
    @Ignore
    public void should_update_an_object() throws Exception {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Test
    @Ignore
    public void should_delete_an_object() throws Exception {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}
