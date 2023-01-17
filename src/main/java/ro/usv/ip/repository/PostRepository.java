package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTags_Name(String tagName);

    @Query(value="SELECT * FROM post order by created_on ASC ", nativeQuery = true)
    List<Post>findAllOrderByCreatedOnAsc();

    List<Post>findByMatchOrderByPostedOnAsc(boolean match);


}
