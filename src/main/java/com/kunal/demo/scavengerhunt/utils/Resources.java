/**
 * 
 */
package com.kunal.demo.scavengerhunt.utils;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author kunallimaye
 *
 */
public class Resources {

	@Produces
	@PersistenceContext(unitName = Persistence.PERSISTENCE_UNIT)
	private EntityManager em;
	
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint){
		return Logger.getLogger(injectionPoint.getMember().getClass().getName());
	}
}
