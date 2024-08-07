package com.quarkstream.api.repository;

import com.quarkstream.api.model.Channel;
import com.quarkstream.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Integer> {
}
