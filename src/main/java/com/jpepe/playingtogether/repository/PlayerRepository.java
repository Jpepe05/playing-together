package com.jpepe.playingtogether.repository;

import com.jpepe.playingtogether.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

  List<Player> findAllByIdIn(Collection<Long> ids);
}
