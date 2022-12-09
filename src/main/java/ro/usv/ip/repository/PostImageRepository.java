package ro.usv.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.usv.ip.model.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

}
