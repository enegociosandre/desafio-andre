package com.alelo.api.sample;

import java.util.ArrayList;
import java.util.List;

import com.alelo.api.entity.Stadium;

public class StadiumSample {

	public static List<Stadium> getStadiums() {
		
		List<Stadium> stadiums = new ArrayList<Stadium>();

        Stadium morumbi = new Stadium();
        morumbi.setName("Morumbi");
        morumbi.setSeatingCapacity(70000);
        morumbi.setAssociatedRestaurants(10);
        morumbi.setName("Morumbi");
        stadiums.add(morumbi);
        
        Stadium maracana = new Stadium();
        maracana.setName("Maracanã");
        maracana.setSeatingCapacity(90000);
        maracana.setAssociatedRestaurants(15);
        stadiums.add(maracana);
        
        Stadium pacaembu = new Stadium();
        pacaembu.setName("Pacaembu");
        pacaembu.setSeatingCapacity(50000);
        pacaembu.setAssociatedRestaurants(7);
        stadiums.add(pacaembu);
		
		return stadiums;
	}

}
