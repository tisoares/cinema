package br.com.as.cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ControllerTest {

	@GetMapping("/")
	public String getWorks() {
		logger.info("Tetse de log {}", 5);
		return "Works!!!!";
	}
}
