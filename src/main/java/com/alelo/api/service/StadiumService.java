package com.alelo.api.service;

import com.alelo.api.entity.Stadium;

public interface StadiumService {
	
	public Iterable<Stadium> listAllStadiums();
    
	public Stadium getStadiumById(Integer id);
	
	public Stadium getStadiumByName(String Name);
    
	public Stadium saveStadium(Stadium user);
    
    public void deleteStadium(Integer id);
    
}
