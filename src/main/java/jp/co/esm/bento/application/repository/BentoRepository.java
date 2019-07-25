package jp.co.esm.bento.application.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface BentoRepository extends JpaRepository<Bento, Integer> {

}
