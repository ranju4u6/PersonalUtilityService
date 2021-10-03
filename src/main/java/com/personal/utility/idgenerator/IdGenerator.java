package com.personal.utility.idgenerator;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * Handles the ID generation for Hibernate entities
 * @author renjith
 *
 */

public class IdGenerator implements IdentifierGenerator, Configurable {
	
	private String prefix = "";
	private String queryKey = "";

	@Override
	public void configure(Type arg0, Properties arg1, ServiceRegistry arg2) throws MappingException {
		prefix = arg1.getProperty("PREFIX");
		queryKey = arg1.getProperty("QUERY_KEY");

	}

	@Override
	public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
		long count = (long)arg0.getNamedQuery(queryKey).list().get(0);
		
		long id = count + 1;
		String generatedId = prefix+id;
		return generatedId;
	}

}
