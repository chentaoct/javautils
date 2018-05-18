package com.ju.model.base;

/**
 * Created by tao on 2018/5/16.
 */
public class BaseOneRespDTO<E> extends BaseRespDTO {
    private E data;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
