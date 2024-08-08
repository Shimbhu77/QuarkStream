package com.quarkstream.api.service;

import com.quarkstream.api.exceptions.ChannelException;
import com.quarkstream.api.model.Channel;

import java.util.List;

public interface ChannelService {

    Channel createChannel(Channel channel) throws ChannelException;
    Channel findChannelByChannelId(Integer channelId) throws ChannelException;
    List<Channel> findChannelByName(String channelName) throws ChannelException;
}
