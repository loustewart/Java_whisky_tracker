package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;


	@Test
	public void contextLoads() {
	}


	@Test
	public void findWhiskiesByYear() {
		List<Whisky> found = whiskyRepository.findWhiskiesByYear(2018);  // can use this for a derived query
		assertEquals(2, found.size());
	}

	@Test
	public void findDistilleryByRegion() {
		List<Distillery> found = distilleryRepository.findDistilleryByRegion("Speyside");
		assertEquals(2, found.size());
	}

	@Test
	public void findWhiskyByDistilleryAndAge() {
		List<Whisky> found = whiskyRepository.findWhiskyByAgeAndDistilleryId(15, 1L);
		assertEquals(1, found.size());
	}

	@Test
	public void findWhiskyByRegion() {
		List<Whisky> found = whiskyRepository.findWhiskyByRegion("Highland");
		assertEquals(2, found.size());
	}

	@Test
	public void findDistilleryWith12YearOldWhisky() {
		List<Distillery> found = distilleryRepository.findDistilleryByWhiskyAge(12);
		assertEquals(2, found.size());
	}
}