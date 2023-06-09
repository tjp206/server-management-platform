package com.tjp206.servermanagement.controller;

import com.tjp206.servermanagement.model.Response;
import com.tjp206.servermanagement.model.Server;
import com.tjp206.servermanagement.service.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.tjp206.servermanagement.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceImpl serverService;

    @GetMapping("/server-list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.serverList(25)))
                        .successMessage("Servers retrieved")
                        .status(HttpStatus.OK)
                        .serverStatusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/ping-server/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {

        Server server = serverService.pingServer(ipAddress);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .successMessage(server.getStatus() == SERVER_UP ? "Ping successful" : "Ping unsuccessful")
                        .status(HttpStatus.OK)
                        .serverStatusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
