package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByDetails_CreatedByOrderByDetails_CreatedOnDesc(String name);

    List<Post> findByTags_Name(String tagName);

    @Query(value = "select p from Post p where p.details.createdBy = ?1 order by p.details.createdOn desc")
    List<Post> findAllByUserName(String name);
}
