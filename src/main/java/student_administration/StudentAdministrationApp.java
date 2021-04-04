  package student_administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import student_administration.client.MainViewManager;

@SpringBootApplication
@EnableJpaRepositories
public class StudentAdministrationApp extends Application{
	
	protected ConfigurableApplicationContext springContext;
	
	public static void start() {
		launch(StudentAdministrationApp.class);
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(StudentAdministrationApp.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Raf Studentska sluzba");
		MainViewManager mainView = springContext.getBean(MainViewManager.class);
		mainView.setMainStage(primaryStage);
		primaryStage.setScene(mainView.createScene());
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		springContext.close();
		Platform.exit();
	}
}
