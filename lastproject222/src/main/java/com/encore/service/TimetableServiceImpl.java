package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Timetable;
import com.encore.persistence.TimetableRepository;

@Service
public class TimetableServiceImpl implements TimetableService {
	
	@Autowired
	private TimetableRepository rep;

	@Override
	public List<Timetable> findAll() {
		List<Timetable> list = (List<Timetable>)rep.findAll();
		return list;
	}

	@Override
	public void insertTB(Timetable tb) {
		rep.save(tb);
	}
	
	
}
