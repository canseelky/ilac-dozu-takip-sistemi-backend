package com.msku.drugdosemonitoringsystem;


import com.msku.drugdosemonitoringsystem.entities.Formula;
import com.msku.drugdosemonitoringsystem.entities.Role;
import com.msku.drugdosemonitoringsystem.enums.FormulaType;
import com.msku.drugdosemonitoringsystem.repositories.FormulaRepo;
import com.msku.drugdosemonitoringsystem.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;


@SpringBootApplication
@EnableWebMvc
public class DrugdosemonitoringsystemApi  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DrugdosemonitoringsystemApi.class, args);
	}

	@Autowired
	private FormulaRepo formulaRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {
		Formula dubois = new Formula();
		dubois.setId(1L);
		dubois.setName(FormulaType.valueOf("DUBOIS").toString());
		Formula mostelller = new Formula();
		mostelller.setName(FormulaType.valueOf("MOSTELLER").toString());
		mostelller.setId(2L);
		Formula haycock = new Formula();
		haycock.setName(FormulaType.valueOf("HEYCOCK").toString());
		haycock.setId(3L);

		formulaRepo.saveAll(List.of(dubois, mostelller, haycock));

		Role patient = new Role();
		patient.setName("patient");
		patient.setId(1L);
		Role doctor = new Role();
		doctor.setName("doctor");
		doctor.setId(2L);

		roleRepo.saveAll(List.of(patient, doctor));



	}
}
