package com.sczapla.salon.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sczapla.salon.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

	@Query(value = "FROM Visit visit WHERE visit.userFrom.id = :userFromId and visit.userTo.id = :userToId "
			+ "and visit.visitFrom >= :dateFrom and visit.visitTo <= :dateTo "
			+ "and (visit.status = 'ZAREZEROWANE' or visit.status = 'ZAKONCZONE')")
	List<Visit> findAllByUserPersonel(@Param("userFromId") Long userFromId, @Param("userToId") Long userToId,
			@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

	@Query(value = "FROM Visit visit WHERE visit.userFrom.id = :userFromId ORDER BY visit.visitFrom ASC")
	List<Visit> findAllUserVisit(@Param("userFromId") Long userFromId);
}
