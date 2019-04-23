package com.ravi.jpa.hibernate.onetoone.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ravi.jpa.hibernate.onetoone.entity.Passport;

@Repository
@Transactional
public class PassportRepository {

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public void findPassportAndStudent() {
		Passport passport=em.find(Passport.class, 40001L);
		logger.info("------> "+passport);
		/*
		 *     select
        passport0_.id as id1_1_0_,
        passport0_.number as number2_1_0_ 
    from
        passport passport0_ 
    where
        passport0_.id=?
		 */
		logger.info("------> "+passport.getStudent());
		/*
		 *     select
        student0_.id as id1_3_0_,
        student0_.name as name2_3_0_,
        student0_.passport_id as passport3_3_0_ 
    from
        student student0_ 
    where
        student0_.passport_id=?
		 */
	}
}
