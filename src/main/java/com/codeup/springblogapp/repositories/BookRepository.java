package repositories;

import com.codeup.springblogapp.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Ad, Long> {
}
