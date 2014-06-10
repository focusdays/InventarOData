package com.focusdays2014.inventory_core.odata_core;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.ODataProducerFactory;
import org.odata4j.producer.jpa.JPAProducer;

public class AddressBookJpaFactory implements ODataProducerFactory {

	@Override
	public ODataProducer create(Properties properties) {
		String persistenceUnitName = "AddressBookService" + JPAProvider.JPA_PROVIDER.caption;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		String namespace = "AddressBook";
		JPAProducer producer = new JPAProducer(entityManagerFactory, namespace, 50);
		return producer;
	}
}