package com.encore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Advertising;
import com.encore.domain.Timetable;
import com.encore.persistence.AdvertisingRepository;
import com.encore.persistence.TimetableRepository;

@Service
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	private TimetableRepository rep;
	@Autowired
	private AdvertisingRepository adrep;
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Timetable> findAll() {
		List<Timetable> list = (List<Timetable>) rep.findAll();
		return list;
	}

	@Override
	public void insertTB(Timetable tb) {
		rep.save(tb);
	}

	@Override
	public List<Advertising> findallad() {
		List<Advertising> list = null;
		String qstr = "from Advertising a where a.adseq in (select b.productseq from Timetable b)";
		Query query = entityManager.createQuery(qstr);
		list = query.getResultList();
		return list;
	}

	@Override
	public void deletetb(Long seq) {
		rep.delete(rep.findByProductseq(seq).get(0));
		
	}

}
