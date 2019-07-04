package jp.co.esm.bento.application.repository;

import jp.co.esm.bento.application.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BentoRepository extends JpaRepository<Test, Integer> {

}
