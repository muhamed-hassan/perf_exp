package com.exp.configs;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseUtil<R> {

    @Value("${server.port}")
    protected int serverPort;

    protected abstract R getData(int delay);

}
