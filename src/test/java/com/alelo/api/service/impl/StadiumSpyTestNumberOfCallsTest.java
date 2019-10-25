package com.alelo.api.service.impl;

import com.alelo.api.entity.Stadium;
import com.alelo.api.repository.StadiumRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import static org.hamcrest.CoreMatchers.equalTo;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class StadiumSpyTestNumberOfCallsTest {

@Spy private StadiumServiceImpl stadiumService;

@Mock private StadiumRepository stadiumRepository;

@Mock private Stadium stadium;

    @Test
    public void shouldVerifyThatGetStadiumByIdIsCalled() throws Exception {
        Mockito.doReturn(stadium).when(stadiumService).getStadiumById(10);
        Stadium retrievedStadium = stadiumService.getStadiumById(10);
        Mockito.verify(stadiumService).getStadiumById(10);
    }

    @Test
    public void shouldVerifyThatSaveStadiumIsNotCalled() throws Exception {
        Mockito.doReturn(stadium).when(stadiumService).getStadiumById(10);
        Stadium retrievedStadium = stadiumService.getStadiumById(10);
        Mockito.verify(stadiumService, never()).saveStadium(stadium);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException_whenStadiumIsOutOfContext() throws Exception{
        Stadium stadium = stadiumService.getStadiumById(10);
        assertThat(stadium,is(equalTo(stadium)));
    }

}
