package com.tjp206.servermanagement.service;

import com.tjp206.servermanagement.model.Server;
import com.tjp206.servermanagement.repository.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.tjp206.servermanagement.enumeration.Status.SERVER_DOWN;
import static com.tjp206.servermanagement.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor // Instead of creating constructor Lombok will create it instead with this annotation
@Service
@Transactional
@Slf4j // Bring in log to allow us to see exactly what is happening in console
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server pingServer(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress ina = InetAddress.getByName(ipAddress);
        // if server response is <= 10secs then responds server_up else server_down
        server.setStatus(ina.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> serverList(int limit) {
        log.info("Fetching list of servers");
        // return first page with set limit of servers and transform requested page to list
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server getServer(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server updateServer(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteServer(Long id) {
        log.info("Deleting server with id: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    /*
    Set random images for given server(s),
    Image indexes are 0-5, 'bound' ensures we never look past the last number in index
     */
    private String setServerImageUrl() {
        String[] imageNames =
                {
                        "server1.png",
                        "server2.png",
                        "server3.png",
                        "server4.png",
                        "server5.png",
                        "server6.png"
                };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imageNames[new Random().nextInt(6)]).toUriString();
    }
}
