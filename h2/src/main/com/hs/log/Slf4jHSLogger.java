package com.hs.log;


import org.slf4j.Logger;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 2/2/17
 */
public class Slf4jHSLogger implements HSLogger {

    private final Logger slf4j;
    
    private final String marker = "";

    public Slf4jHSLogger(Logger _log4j){
        this.slf4j = _log4j;
    }

    @Override
    public void trace(Object msg) {
        
        slf4j.trace(msg.toString());
    }

    @Override
    public void trace(Object msg, Throwable t) {
        slf4j.trace(msg.toString(), t);
    }

    @Override
    public void debug(Object msg) {
        slf4j.debug(msg.toString());
    }

    @Override
    public void debug(Object msg, Throwable t) {
        slf4j.debug(msg.toString(), t);
    }

    @Override
    public void info(Object msg) {
        slf4j.info(msg.toString());
    }

    @Override
    public void info(Object msg, Throwable t) {
        slf4j.info(msg.toString(), t);
    }

    @Override
    public void warn(Object msg) {
        slf4j.warn(msg.toString());
    }

    @Override
    public void warn(Object msg, Throwable t) {
        slf4j.warn(msg.toString(), t);
    }

    @Override
    public void error(Object msg) {
        slf4j.error(msg.toString());
    }

    @Override
    public void error(Object msg, Throwable t) {
        slf4j.error(msg.toString(), t);
    }
}
