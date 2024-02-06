package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private final RealConnection realConnection;
    private final ConnectionPool connectionPool;

    public ProxyConnection(
            RealConnection realConnection,
            ConnectionPool connectionPool
    ) {
        this.realConnection = realConnection;
        this.connectionPool = connectionPool;
    }

    public void reallyClose() {
        this.realConnection.close();
    }

    @Override
    public void close() {
        this.connectionPool.releaseConnection(this);
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
