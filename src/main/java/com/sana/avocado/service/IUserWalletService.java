package com.sana.avocado.service;

import java.util.List;
import java.util.Optional;

import com.sana.avocado.model.UserWallet;

public interface IUserWalletService {

	Optional<UserWallet> updateUserWallet(UserWallet existingUserWallet);

	Optional<UserWallet> getUserByUserName(String userName);

	Optional<List<UserWallet>> getUserWallet();

}
