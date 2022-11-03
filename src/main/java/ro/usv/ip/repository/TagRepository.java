package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.usv.ip.model.Tag;

import java.util.Optional;

@Repository
public interface TagRepository  extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

}
