package com.debs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.debs.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	@Query(value = "CALL AddAccount(:uid, :accType);", nativeQuery = true)
	int addAccount(@Param("uid") int uid, @Param("accType") String accType);

	@Query(value = "CALL FindAllAccountsNames(:uid, :accType);", nativeQuery = true)
	List<String> findAllAccountsNames(@Param("uid") int uid, @Param("accType") String accType);

	@Query(value = "CALL GetAccountId(:acc, :atype, :uid);", nativeQuery = true)
	int getAccountId(@Param("acc") String acc, @Param("atype") String atype, @Param("uid") int uid);
}
