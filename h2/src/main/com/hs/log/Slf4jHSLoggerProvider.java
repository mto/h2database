package com.hs.log;

import org.slf4j.LoggerFactory;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 2/2/17
 */
public class Slf4jHSLoggerProvider implements HSLoggerProvider {

    @Override
    public HSLogger getLogger(Class ctxClazz) {
        return new Slf4jHSLogger(LoggerFactory.getLogger(ctxClazz));
    }

    @Override
    public HSLogger getLogger(String logName) {
        return new Slf4jHSLogger(LoggerFactory.getLogger(logName));
    }
}
