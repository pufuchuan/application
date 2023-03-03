package com.ly.application.hander;

public abstract class AbstractHander implements Hander {

    @Override
    public <T> T msgHander(T t) {
        throw new RuntimeException("");
    }


    public abstract boolean isSupport();

}
