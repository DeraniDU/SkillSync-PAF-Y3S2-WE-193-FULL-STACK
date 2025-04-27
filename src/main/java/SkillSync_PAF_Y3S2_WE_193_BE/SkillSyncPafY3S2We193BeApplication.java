package SkillSync_PAF_Y3S2_WE_193_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = "SkillSync_PAF_Y3S2_WE_193_BE")
@EnableJpaAuditing
@EnableTransactionManagement
public class SkillSyncPafY3S2We193BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillSyncPafY3S2We193BeApplication.class, args);
	}
}

@RestController
class TestController {
	@GetMapping("/")
	public String welcome() {

		return "SkillSync PAF Y3S2 WE_193_BE is working!";
	}
}