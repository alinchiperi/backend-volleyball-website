package ro.usv.ip.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.Tag;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    private Post postOnCsm;
    private Post postOnSuceva;

    @BeforeEach
    public void setUp() {
        Tag csm = entityManager.persist(new Tag(null, "csm"));
        Tag suceava = entityManager.persist(new Tag(null, "suceava"));

        postOnCsm = createPost("Alin", "Csm campioana", csm, suceava);
        entityManager.persist(postOnCsm);

        postOnSuceva = createPost("Stefan","Sucevava", suceava);
        entityManager.persist(postOnSuceva);

    }

    @Test
    public void
    returnPostOnTesting_WhenFindByTags_Name_Csm() {
        List<Post> results = postRepository.findByTags_Name("csm");

        assertThat(results).containsExactly(postOnCsm);
    }
    @Test public void
    returnBothRecords_WhenFindByTags_Name_Suceava() {
        List<Post> results = postRepository.findByTags_Name("suceava");

        assertThat(results).containsExactly(postOnCsm, postOnSuceva);
    }

    private Post createPost(String user, String message, Tag... tags) {
        Post postOnTesting = new Post();
        postOnTesting.setTitle(message);
        postOnTesting.setCreatedBy(user);
        postOnTesting.getTags().addAll(Arrays.asList(tags));
        return postOnTesting;
    }
}
