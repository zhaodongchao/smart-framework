package org.smart4j.framework.proxy;

/**
 * Created by DongChao on 2016/7/13.
 */
public interface Proxy {
    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain)throws Throwable;
}
