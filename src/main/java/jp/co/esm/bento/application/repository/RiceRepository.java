package jp.co.esm.bento.application.repository;

import jp.co.esm.bento.application.entity.Rice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiceRepository extends JpaRepository<Rice, Integer> {

}
