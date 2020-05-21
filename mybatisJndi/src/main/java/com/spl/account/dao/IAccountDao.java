package com.spl.account.dao;

import com.spl.account.domain.Account;
import com.spl.account.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户信息及用户信息
     * @return
     */
    List<AccountUser> findAccountUser();
}
