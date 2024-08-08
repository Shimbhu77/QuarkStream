package com.quarkstream.api.exceptions;

public class ChannelException extends RuntimeException {
	public ChannelException() {};
	public ChannelException(String message) {
		super(message);
	}
}
