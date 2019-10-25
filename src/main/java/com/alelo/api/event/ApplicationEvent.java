package com.alelo.api.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alelo.api.entity.Stadium;
import com.alelo.api.repository.StadiumRepository;
import com.alelo.api.sample.StadiumSample;

@Component
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent>{
	
	   @Autowired 
	   private StadiumRepository stadiumRepository;

	    @Override 
	    public void onApplicationEvent(ContextRefreshedEvent event) {
	        List<Stadium> stadiums = StadiumSample.getStadiums();
	        for (Stadium stadium : stadiums) {
	        	stadiumRepository.save(stadium);
	        }
	    }
}
