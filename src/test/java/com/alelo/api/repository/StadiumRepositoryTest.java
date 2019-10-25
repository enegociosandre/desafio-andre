package com.alelo.api.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alelo.api.config.RepositoryConfiguration;
import com.alelo.api.entity.Stadium;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RepositoryConfiguration.class })
public class StadiumRepositoryTest {

	@Autowired
	private StadiumRepository stadiumRepository;

	Stadium morumbi;
	Stadium maracana;
	Stadium mineirao;
	Stadium stadium;
	Stadium fetchedStadium;

	@Before
	public void setup() {
		morumbi = new Stadium("Morumbi",70000,10);
		maracana = new Stadium("Maracanã",90000,15);
		mineirao = new Stadium("Mineirão",50000,7);
		stadium = new Stadium();
		fetchedStadium = new Stadium();
	}

	@After
	public void TearDown() {
		stadiumRepository.deleteAll();
		assertThat(stadiumRepository.count(), is(0L));
		stadium = null;
		fetchedStadium = null;
		assertThat(stadium, is(nullValue()));
		assertThat(fetchedStadium, is(nullValue()));
	}

	@Test
	public void theAttributeIdShouldNotBeNullAfterPersists() {

		stadiumRepository.save(mineirao);

		assertThat(mineirao.getId(), is(notNullValue()));
	}

	@Test
	public void shouldSaveThreeStadiumAndValidateTheCountOfThemInTheEnd() {

		stadiumRepository.save(morumbi);
		stadiumRepository.save(maracana);
		stadiumRepository.save(mineirao);

		long stadiumRepositoryCounter = stadiumRepository.count();
		assertThat(true, is(stadiumRepositoryCounter == 3L ? true : false));
		List<Stadium> stadiums = (List<Stadium>) stadiumRepository.findAll();
		long stadiumEntityCounter = stadiums.stream().count();
		assertThat(true, is(stadiumEntityCounter == 3L ? true : false));

	}

	@Test
	public void theEntityShouldNotBeNullAfterPersists() {

		assertThat(fetchedStadium.getId(), is(nullValue()));
		assertThat(fetchedStadium.getName(), is(nullValue()));

		stadiumRepository.save(maracana);
		Stadium fetchedStadium = stadiumRepository.findOne(maracana.getId());

		assertThat(fetchedStadium, is(notNullValue()));
	}

	@Test
	public void theFetchedIdAndTheFetchedNameShouldBeTheSameOfTheInstantiatedEntity() {

		assertThat(fetchedStadium.getId(), is(stadium.getName()));
		stadiumRepository.save(stadium);
		Stadium fetchedStadium = stadiumRepository.findOne(stadium.getId());
		stadium.setName("Mineirinho");

		assertThat(fetchedStadium.getName(), is(not(stadium.getName())));

	}
}