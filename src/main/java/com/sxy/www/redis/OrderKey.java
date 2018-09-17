package com.sxy.www.redis;

public class OrderKey extends BasePrefix {

	public OrderKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

}
