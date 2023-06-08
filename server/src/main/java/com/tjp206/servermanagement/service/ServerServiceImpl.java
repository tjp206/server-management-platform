package com.tjp206.servermanagement.service;

import com.tjp206.servermanagement.model.Server;
import com.tjp206.servermanagement.repository.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor // Instead of creating constructor Lombok will create it instead with this annotation
@Service
@Transactional
@Slf4j // Bring in log to allow us to see exactly what is happening in console
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        return null;
    }

    @Override
    public Server pingServer(String ipAddress) {
        return null;
    }

    @Override
    public Collection<Server> serverList(int limit) {
        return null;
    }

    @Override
    public Server getServer(Long id) {
        return null;
    }

    @Override
    public Server updateServer(Server server) {
        return null;
    }

    @Override
    public Boolean deleteServer(Long id) {
        return null;
    }
}
