package com.tjp206.servermanagement.service;

import com.tjp206.servermanagement.model.Server;

import java.util.Collection;

public interface ServerService {
    Server createServer(Server server);

    /*
    Find server by IP Address and return that server
     */
    Server pingServer(String ipAddress);

    /*
    * Limit searches to a specific number:
    * e.g., if you return a list of 100 servers
    * we can limit the search to top 10 servers
    */
    Collection<Server> serverList(int limit);

    Server getServer(Long id);

    Server updateServer(Server server);

    Boolean deleteServer(Long id);
}
