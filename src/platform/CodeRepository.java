package platform;


import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {

    List<Code> findTop10ByRestrictedViewsFalseAndRestrictedTimeFalseOrderByDateDesc();
    Optional<Code> findById(String id);
}