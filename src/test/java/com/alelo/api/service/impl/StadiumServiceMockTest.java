package com.alelo.api.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.alelo.api.entity.Stadium;
import com.alelo.api.repository.StadiumRepository;


public class StadiumServiceMockTest {
	
	@Autowired
	private StadiumServiceImpl stadiumService;
	@Mock
	private StadiumRepository stadiumRepository;
	@Mock
	private Stadium stadium;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		stadiumService = new StadiumServiceImpl();
		stadiumService.setStadiumRepository(stadiumRepository);
	}
	
	@Test
	public void shouldReturnStadiumWhenGettingStadiumById() throws Exception{
		when(stadiumRepository.findOne(3)).thenReturn(stadium);
		Stadium fetchedStadium = stadiumService.getStadiumById(3);
		assertThat(fetchedStadium,is(equalTo(stadium)));
	}
	
	@Test
	public void shouldReturnStadiumWhenSaving() throws Exception{
		when(stadiumRepository.save(stadium)).thenReturn(stadium);
		Stadium savedStadium = stadiumService.saveStadium(stadium);
		assertThat(savedStadium,is(equalTo(stadium)));
	}	
	
	@Test
	public void shouldInvokeTheDeleteFromStadiumRepositoryWhenDeleting() throws Exception{
		doNothing().when(stadiumRepository).delete(3);
		stadiumService.deleteStadium(3);
		verify(stadiumRepository, times(1)).delete(3);
	}
	
	
}
