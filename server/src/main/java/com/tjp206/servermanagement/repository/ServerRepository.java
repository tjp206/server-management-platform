package main.java.com.tjp206.servermanagement.repository;

import main.java.com.tjp206.servermanagement.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
