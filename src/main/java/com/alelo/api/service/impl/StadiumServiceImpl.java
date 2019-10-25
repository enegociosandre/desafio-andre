package com.alelo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alelo.api.entity.Stadium;
import com.alelo.api.repository.StadiumRepository;
import com.alelo.api.service.StadiumService;

@Service
public class StadiumServiceImpl implements StadiumService{

	@Autowired 
    private StadiumRepository stadiumRepository;

	@Override
	public Iterable<Stadium> listAllStadiums() {
		return stadiumRepository.findAll();
	}

	@Override
	public Stadium getStadiumById(Integer id) {
		return stadiumRepository.findOne(id);
	}

	@Override
	public Stadium saveStadium(Stadium stadium) {
		return stadiumRepository.save(stadium);
	}

	@Override
	public void deleteStadium(Integer id) {
		stadiumRepository.delete(id);
		
	}

	@Override
	public Stadium getStadiumByName(String name) {
		return stadiumRepository.findByName(name);
	}

	public void setStadiumRepository(StadiumRepository stadiumRepository) {
		this.stadiumRepository = stadiumRepository;
	}

}
