package ru.tusur.udo.sensors;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.tusur.ru.sensors.camel.processors.EmulationProcessor;
import ru.tusur.ru.sensors.camel.processors.JSONProcessor;
import ru.tusur.udo.sensors.core.SensorEmulationRuntime;

import org.apache.activemq.camel.component.ActiveMQComponent;

@Configuration
public class AppContext {

	@Bean
	ConnectionFactory connectionFactory() throws NamingException {
	
		Properties ENV = new Properties() {
	        private static final long serialVersionUID = 1L;
	        {
	          //put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	          put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	          put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
	          put(Context.SECURITY_PRINCIPAL, "jmsUser");
	          put(Context.SECURITY_CREDENTIALS, "jmsUser123!");
	        }
	      };
	      
	      Context ctx = new InitialContext(ENV);
	      return (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
	}
    public ActiveMQComponent activeMQComponent() throws NamingException {
        ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory());
        
        return activeMQComponent;
    }
	@Bean
	public ClassPathXmlApplicationContext classPathApplicationContext() {
		return new ClassPathXmlApplicationContext("applicationConfig.xml");
	}
	@Bean
	public JSONProcessor jProcessor() {
		return new JSONProcessor();
	}
	@Bean
	public EmulationProcessor emulationProcessor() {
		
		SensorEmulationRuntime sensorEmulationRuntime = (SensorEmulationRuntime)classPathApplicationContext().getBean("runtime");
		EmulationProcessor eProc = new EmulationProcessor();
		eProc.setSensorEmulationRuntime(sensorEmulationRuntime);
		return eProc;
	}
	@Bean
	public SensorRoutes sensorRoutes() {
		SensorRoutes routes = new SensorRoutes();
		routes.setEmulationProcessor(emulationProcessor());
		routes.setjProcessor(jProcessor());
		return routes;
	}
	@Bean
	public CamelContext camelContext() throws Exception {
		DefaultCamelContext camelContext = new DefaultCamelContext();
		camelContext.addComponent("activemq", this.activeMQComponent());
		this.activeMQComponent().start();
		camelContext.addRoutes(sensorRoutes());
		return camelContext;
	}
	

}
