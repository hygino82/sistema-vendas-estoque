package br.dev.hygino.erp.repository;

import br.dev.hygino.erp.entities.Client;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("""
       SELECT obj FROM Client obj
       WHERE
           (:name IS NULL OR :name = '' OR UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%')))
       AND
           (:city IS NULL OR :city = '' OR UPPER(obj.city) LIKE UPPER(CONCAT('%', :city, '%')))
       AND
           (:state IS NULL OR :state = '' OR UPPER(obj.state) LIKE UPPER(CONCAT('%', :state, '%')))
       """)
    public Page<Client> getEmployees(Pageable pageable, String name, String state, String city);

    @Query("""
            SELECT obj FROM Client obj WHERE UPPER(obj.email) = UPPER(:email)
            """)
    Optional<Client> findByEmail(@Param("email") String email);
}
