package org.myorg.basicejb.warejb;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.myorg.basicejb.ejb.ReservationRemote;

public class ReservationIT {
    private static final Logger logger = LoggerFactory.getLogger(ReservationIT.class);
    private InitialContext jndi;
    private static final String reservationJNDI = System.getProperty("jndi.name.reservation");
//    private static final String reservationJNDI = System.getProperty("jndi.name.reservation" , "ejb:basicejb-ear-1.0-SNAPSHOT/myorg.basicejb-basicejb-ejb-1.0-SNAPSHOT/ReservationEJB!org.myorg.basicejb.ejb.ReservationRemote"); 
    private ReservationRemote reservationist; 


    @Before
    public void setUp() throws NamingException {
        assertNotNull("jndi.name.reservation not supplied", reservationJNDI);

        logger.debug("getting jndi initial context");
        jndi=new InitialContext();
        logger.debug("jndi={}", jndi.getEnvironment());
        jndi.lookup("jms");
        
        logger.debug("jndi name:{}", reservationJNDI);
        reservationist = (ReservationRemote) jndi.lookup(reservationJNDI);
        logger.debug("reservationist={}", reservationist);
    }

    @Test
    public void testPing() {
        logger.info("*** testPing ***");
        reservationist.ping();
    }
}