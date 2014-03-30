package net.octacomm.sample.service.crud;

import java.util.List;

import net.octacomm.sample.domain.Domain;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface CRUDService<T extends Domain> {

	String CACHE_NAME = "CRUD_CACHE";
	String CACHE_KEY = "#root.targetClass.toString()";

	@Cacheable(value = CACHE_NAME, key = CACHE_KEY)
	List<T> getList();
	
	@CacheEvict(value = CACHE_NAME, key = CACHE_KEY, allEntries = true)
	boolean add(T t);

	@CacheEvict(value = CACHE_NAME, key = CACHE_KEY, allEntries = true)
	boolean edit(T t);
	
	@CacheEvict(value = CACHE_NAME, key = CACHE_KEY, allEntries = true)
	boolean delete(T t);
	
	@CacheEvict(value = CACHE_NAME, key = CACHE_KEY, allEntries = true)
	boolean deleteAll();
	
	List<T> getListByCondition(T t);
	
}
