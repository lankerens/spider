package com.lankeren.mapper;


import com.lankeren.bean.CartoonDaysBroadCast;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author lankeren
 * @ClassName SpiderMapper
 * @Deacription:
 * @create: 2020-11-13 13:13
 */
public interface SpiderMapper extends CrudRepository<CartoonDaysBroadCast, Integer> {
    List<CartoonDaysBroadCast> findCartoonDaysBroadCastsByAnimeName(String animeName);

    @Override
    <S extends CartoonDaysBroadCast> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<CartoonDaysBroadCast> findById(Integer integer);
}


