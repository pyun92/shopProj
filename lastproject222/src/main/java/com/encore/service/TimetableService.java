package com.encore.service;

import java.util.List;

import com.encore.domain.Advertising;
import com.encore.domain.Timetable;

public interface TimetableService {
	List<Timetable> findAll();
	void insertTB(Timetable tb); 
	List<Advertising> findallad();
	void deletetb(Long seq);
}
