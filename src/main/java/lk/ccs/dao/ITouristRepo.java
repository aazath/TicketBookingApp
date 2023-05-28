package lk.ccs.dao;

import lk.ccs.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> {
}
