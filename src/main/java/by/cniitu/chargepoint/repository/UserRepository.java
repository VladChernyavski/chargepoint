package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findOneById(int id);

}
