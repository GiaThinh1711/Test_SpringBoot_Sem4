package aptech.t2008m.testspringboot.repository;

import aptech.t2008m.testspringboot.entity.Street;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
    Page<Street> findAll(Pageable pageable);
    @Query(value = "select * from streets s where s.name like %:name% and s.district_id=:distinctId",nativeQuery = true)
    List<Street> findByInfo(@Param("name") String name,@Param("distinctId") int distinctId);

    @Query(value = "select * from streets s where s.name like %:name%",nativeQuery = true)
    List<Street> findByInfo(@Param("name") String name);
}
