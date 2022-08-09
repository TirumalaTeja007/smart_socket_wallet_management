package com.sana.avocado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sana.avocado.model.UserWallet;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Integer> {

	Optional<UserWallet> findByUserName(String userName);

}
