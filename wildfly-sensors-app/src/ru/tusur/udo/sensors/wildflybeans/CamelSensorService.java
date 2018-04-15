package ru.tusur.udo.sensors.wildflybeans;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import messagebeans.SensorReceiver;

/**
 * Session Bean implementation class CamelSensorService
 */
@Singleton
@Startup
public class CamelSensorService {
	 private static final Logger LOGGER = Logger.getLogger(CamelSensorService.class.toString());
    /**
     * Default constructor. 
     */
    public CamelSensorService() {
    	 LOGGER.info("CamelSensorService started");
    }
}
