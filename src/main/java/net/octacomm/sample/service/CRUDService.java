package net.octacomm.sample.service;

import java.util.List;

import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.netty.client.exception.ConnectionFailureException;

public interface CRUDService<T extends Domain> {
	
	T get() throws ConnectionFailureException;
	
	List<T> getList() throws ConnectionFailureException;
	
	boolean add(T t) throws ConnectionFailureException;

	boolean addList(List<T> t) throws ConnectionFailureException;
	
	boolean edit(T t) throws ConnectionFailureException;
	
	boolean delete(T t) throws ConnectionFailureException;
	
	boolean deleteAll() throws ConnectionFailureException;
	
	boolean deleteSelection(List<T> e) throws ConnectionFailureException;
}
