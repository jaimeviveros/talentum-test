package com.hackerrank.stocktrade.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.stocktrade.model.entity.User;


public interface UserDao extends CrudRepository<User, Long> {

}
