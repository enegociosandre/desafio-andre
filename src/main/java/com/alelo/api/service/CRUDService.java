package com.alelo.api.service;

import java.util.List;

public interface CRUDService<T> {
	
    public List<?> listAll();
    
    public T getById(Integer id);
    
    public T saveOrUpdate(T domainObject);
    
    public void delete(Integer id);
}
