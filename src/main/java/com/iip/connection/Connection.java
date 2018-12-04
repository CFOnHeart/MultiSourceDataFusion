package com.iip.connection;

/**
 * @Author Junnor.G
 * @Date 2018/12/4 下午4:06
 */

import java.util.List;
import java.util.Map;

public interface Connection {
    String getNickName();
    String getType();
    Map<String, Table> getTables();
    boolean isTarget();
}
