package net.tfedu.zhl.cloud.core.service;

import org.springframework.stereotype.Service;

@Service
public interface BaseService<T> {
	T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

}
