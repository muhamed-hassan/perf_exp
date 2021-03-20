package com.exp.configs;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseUtil<R> {

    protected static final String URI_TEMPLATE = "http://localhost:%s/data?delay=%s";

    @Value("${server.port}")
    protected int serverPort;

    protected abstract R getData(int delay);

}
