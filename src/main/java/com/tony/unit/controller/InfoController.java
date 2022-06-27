package com.tony.unit.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping
@Slf4j
public class InfoController {

	@Value("${spring.application.name}")
	private String application;

	private AtomicInteger count = new AtomicInteger(0);

	@GetMapping("/name")
	public ResponseEntity<String> id() {
		Map<String, Object> result = new HashMap<>();
		result.put("name","wengweng");


		return ResponseEntity.ok(JSON.toJSONString(result));
	}

	@GetMapping("/error/500")
	public ResponseEntity<String> error500() throws Exception {
		throw new Exception();
	}

	@GetMapping("/error/{status}")
	public ResponseEntity<String> error503(@PathVariable("status")Integer status, HttpServletResponse response) throws Exception {
		response.setStatus(status);
		response.getWriter().write("abcd");
		return null;
	}

	@GetMapping("/request/info")
	public ResponseEntity<String> info(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			log.info("{} = {}",name,request.getHeader(name));
			result.put(name, request.getHeader(name));
		}

		return ResponseEntity.ok(JSON.toJSONString(result));
	}

	@GetMapping("/request/times")
	public ResponseEntity<String> times() {
		int i = count.incrementAndGet();
		String info = String.format("I'm a shadow service, receive %s times request", i);
		log.info(info);
		return ResponseEntity.ok(info);
	}

	@GetMapping("/request/times/reset")
	public ResponseEntity<String> reset() {
		count.set(0);
		log.info("reset 0");
		return ResponseEntity.ok("reset 0");
	}


	@GetMapping("/request/sleep/{time}")
	public ResponseEntity<String> sleep(@PathVariable("time") Long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("success");
	}

	@GetMapping("/request/running/{time}")
	public ResponseEntity<String> running(@PathVariable("time") Long time) {
		Long start = System.currentTimeMillis();
		while (true) {
			Long end = System.currentTimeMillis();
			if (end - start >= time * 1000) {
				break;
			}
		}
		return ResponseEntity.ok("success");
	}

	@GetMapping("/request/headers")
	public ResponseEntity<Object> headers(HttpServletRequest request) {

		Enumeration<String> headerNames = request.getHeaderNames();
		System.out.println("--------- headers ------------");
		Map<String, Object> result = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			System.out.println("name : " + name + ", value: " + value);
			result.put(name, value);
		}
		result.put("remote addr : ", request.getRemoteAddr());

		return ResponseEntity.ok(result);
	}
}
