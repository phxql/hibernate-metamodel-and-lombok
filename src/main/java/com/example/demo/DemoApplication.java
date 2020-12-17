package com.example.demo;

import com.example.demo.storage.Task;
import com.example.demo.storage.TaskRepository;
import com.example.demo.storage.TaskRepository2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		var application = SpringApplication.run(DemoApplication.class, args);

		var repository = application.getBean(TaskRepository.class);
		// or:
		// var repository = application.getBean(TaskRepository2.class);

		repository.save(new Task("Clean up living room"));
		repository.save(new Task("Hover kitchen"));

		var tasks = repository.findAll();
		LOGGER.info("Tasks:");
		for (Task task : tasks) {
			LOGGER.info("  {} {}", task.getId(), task.getTitle());
		}

		List<Task> hoverKitchenTasks = repository.findAllByTitle("Hover kitchen");
		LOGGER.info("Tasks with title 'Hover kitchen':");
		for (Task task : hoverKitchenTasks) {
			LOGGER.info("  {} {}", task.getId(), task.getTitle());
		}
	}
}
