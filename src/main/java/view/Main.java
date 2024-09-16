package view;

import java.io.IOException;
import java.util.*;

import utils.CSVExporter;
import utils.MyFileLogWriter;
import utils.UtilsMethods;
import autopilot.OutputDocument;
import autopilot.Section;
import control.Hospital;
import enums.*;
import model.*;

import java.util.List;

public class Main {


	private static Hospital hospital = Hospital.getInstance();
	private static OutputDocument document = new OutputDocument();
	private static Map<String,Command> commands = new HashMap<>();
	private static Map<String,Section> sections = new HashMap<>();
	private static final String OUTPUT_FILE = "output.txt";
	

	
	private interface Command {  
		void execute(Section section,String... args);
	} 
	static {
		Section space = document.nextSection();
		Section finish = document.nextSection();
		
		//ADD 
		Section addDepartment = document.nextSection();
		Section addDisease = document.nextSection();
		Section addDoctor = document.nextSection();
		Section addFracture = document.nextSection();
		Section addInjury = document.nextSection();
		Section addMedication = document.nextSection();
		Section addNurse = document.nextSection();
		Section addPatient = document.nextSection();
		Section addTreatment = document.nextSection();
		Section addVisit = document.nextSection();
		Section addNurseToDepartment = document.nextSection();
		Section addDoctorToDepartment = document.nextSection();
		Section addIntensiveCareDoctor=document.nextSection();
		Section addIntensiveCareNurse=document.nextSection();
		Section addTreatmentToMedicalProblem=document.nextSection();



		//Remove
		Section removeDepartment = document.nextSection();
		Section removeDisease = document.nextSection();
		Section removeDoctor = document.nextSection();
		Section removeFracture = document.nextSection();
		Section removeInjury = document.nextSection();
		Section removeMedication = document.nextSection();
		Section removeNurse = document.nextSection();
		Section removePatient = document.nextSection();
		Section removeTreatment = document.nextSection();
		Section removeVisit = document.nextSection();

		
		//Query
		Section countMedications=document.nextSection();
		Section differenceBetweenTheLongestAndShortestVisit=document.nextSection();
		Section printHowManyFinishInternship=document.nextSection();
		Section howManyVisitBefore=document.nextSection();
		Section printOldestNurse=document.nextSection();
		Section staffMembersThatWorksInMoreThenOneDepartment=document.nextSection();
		Section getTreatmentsByMedicalProblemsByDepartment=document.nextSection();
		Section getNumberOfDoctorsBySpecialization=document.nextSection();
		Section howManyIntensiveCareStaffMembers=document.nextSection();
		Section avgSalary=document.nextSection();
		Section isCompliesWithTheMinistryOfHealthStandard=document.nextSection();
		Section AppointANewManager=document.nextSection();
		
		
		//Sections's HashMap PUT
		
		sections.put("addNurseToDepartment",addNurseToDepartment);//
		sections.put("addDoctorToDepartment",addDoctorToDepartment);//
		sections.put("addDepartment",addDepartment);
		sections.put("addDisease",addDisease);
		sections.put("addDoctor",addDoctor);
		sections.put("addFracture",addFracture);
		sections.put("addInjury",addInjury); 
		sections.put("addMedication",addMedication);
		sections.put("addNurse",addNurse);
		sections.put("addPatient",addPatient);
		sections.put("addTreatment",addTreatment);
		sections.put("addVisit",addVisit);
		sections.put("addIntensiveCareDoctor",addIntensiveCareDoctor);
		sections.put("addIntensiveCareNurse",addIntensiveCareNurse);
		sections.put("addTreatmentToMedicalProblem",addTreatmentToMedicalProblem);

		
		sections.put("removeDepartment",removeDepartment);
		sections.put("removeDisease",removeDisease);
		sections.put("removeDoctor",removeDoctor);
		sections.put("removeFracture",removeFracture);
		sections.put("removeInjury",removeInjury);
		sections.put("removeMedication",removeMedication);
		sections.put("removeNurse",removeNurse);
		sections.put("removePatient", removePatient);
		sections.put("removeTreatment", removeTreatment);
		sections.put("removeVisit",removeVisit);
		
		sections.put("countMedications",countMedications);
		sections.put("differenceBetweenTheLongestAndShortestVisit",differenceBetweenTheLongestAndShortestVisit);
		sections.put("printHowManyFinishInternship",printHowManyFinishInternship);
		sections.put("howManyVisitBefore",howManyVisitBefore);
		sections.put("printOldestNurse",printOldestNurse);		
		sections.put("staffMembersThatWorksInMoreThenOneDepartment",staffMembersThatWorksInMoreThenOneDepartment);
		sections.put("getTreatmentsByMedicalProblemsByDepartment",getTreatmentsByMedicalProblemsByDepartment);
		sections.put("getNumberOfDoctorsBySpecialization",getNumberOfDoctorsBySpecialization);
		sections.put("howManyIntensiveCareStaffMembers",howManyIntensiveCareStaffMembers);
		sections.put("avgSalary",avgSalary);
		sections.put("isCompliesWithTheMinistryOfHealthStandard",isCompliesWithTheMinistryOfHealthStandard);
		sections.put("AppointANewManager",AppointANewManager);
		
		//PUT Query	
		sections.put("space",space);
		sections.put("finish",finish);

		commands.put("space", (section, args) -> {
			MyFileLogWriter.println(""); 
		});

		commands.put("finish", (section, args) -> {
			MyFileLogWriter.saveLogFile();
		}); 
		
		commands.put("addDepartment", (section, args) -> {
			Department department = new Department(Integer.parseInt(args[0]),args[1],hospital.getRealDoctor(Integer.parseInt( args[2])),args[3],Specialization.valueOf(args[4]));
			if(hospital.addDepartment(department)) 
				MyFileLogWriter.println("successfully added department "+department.getNumber());
			else
				MyFileLogWriter.println("failed to add department "+department.getNumber());
			if(department.getNumber()==1) {
				MyFileLogWriter.println("Department details: "+department.toString());				
			}
		});
		commands.put("addDisease", (sections,args) -> {
			Disease disease = new Disease(args[0],hospital.getRealDepartment(Integer.parseInt(args[1])),args[2]);
			if(hospital.addDisease(disease))
				MyFileLogWriter.println("successfully added disease "+disease.getCode());
			else
				MyFileLogWriter.println("failed to add disease "+disease.getCode());
			if(disease.getCode().equals("d1")) {
				MyFileLogWriter.println("Disease details: "+disease.toString());
			}
			if(disease.getCode().equals("d2")) {
				disease.describeSpecialProperties();
			}
			
		});
		commands.put("addFracture", (sections,args) -> {
			Fracture fracture = new Fracture(args[0],hospital.getRealDepartment(Integer.parseInt(args[1])),args[2],Boolean.parseBoolean(args[3]));
			if(hospital.addFracture(fracture))
				MyFileLogWriter.println("successfully added Fracture "+fracture.getCode());
			else
				MyFileLogWriter.println("failed to add Fracture "+fracture.getCode());
			if(fracture.getCode().equals("f8")) {
				fracture.describeSpecialProperties();
			}
			if(fracture.getCode().equals("f7")||fracture.getCode().equals("f9")) {
				fracture.setIntensiveCare();
				MyFileLogWriter.println("Fracture details: "+fracture.toString());
			}
		});
		commands.put("addInjury", (sections,args) -> {
			Injury injury = new Injury(args[0],hospital.getRealDepartment(Integer.parseInt(args[1])),Double.parseDouble( args[2]),args[3]);
			//String name, String type, Department departmentString description
			if(hospital.addInjury(injury))
				MyFileLogWriter.println("successfully added injury "+injury.getCode());
			else
				MyFileLogWriter.println("failed to add injury "+injury.getCode());
			if(injury.getCode().equals("i5")) {
				injury.describeSpecialProperties();
			}
			if(injury.getCode().equals("i4")||injury.getCode().equals("i6")) {
				injury.setIntensiveCare();
				MyFileLogWriter.println("Injury details: "+injury.toString());
			}
		});
		commands.put("addDoctor", (sections,args) -> {
			Doctor doctor = new Doctor(Integer.parseInt(args[0]),args[1],args[2],UtilsMethods.parseDate(args[3])
					,args[4],args[5],args[6],args[7],UtilsMethods.parseDate(args[8]),
					Double.parseDouble(args[9]),Integer.parseInt(args[10]),Boolean.parseBoolean(args[11]),
					Specialization.valueOf(args[12])
					);
			if(hospital.addDoctor(doctor))
				MyFileLogWriter.println("successfully added doctor "+doctor.getId());
			else
				MyFileLogWriter.println("failed to add doctor "+doctor.getId());
			if(doctor.getId()==1) {
				MyFileLogWriter.println("doctor details: "+doctor.toString());				
			}
		});
		commands.put("addIntensiveCareDoctor", (sections,args) -> {
			IntensiveCareDoctor doctor = new IntensiveCareDoctor(Integer.parseInt(args[0]),args[1],args[2],UtilsMethods.parseDate(args[3])
					,args[4],args[5],args[6],args[7],UtilsMethods.parseDate(args[8]),
					Double.parseDouble(args[9]),Integer.parseInt(args[10]),Boolean.parseBoolean(args[11])
					);
			if(hospital.addIntensiveCareDoctor(doctor))
				MyFileLogWriter.println("successfully added IntensiveCareDoctor "+doctor.getId());
			else
				MyFileLogWriter.println("failed to add IntensiveCareDoctor "+doctor.getId());
			if(doctor.getId()==14) {
				MyFileLogWriter.println("doctor details: "+doctor.toString());				
			}
		});
		
		commands.put("addPatient", (sections,args) -> {
			Patient patient = new Patient(Integer.parseInt(args[0]),args[1],args[2],UtilsMethods.parseDate(args[3])
					,args[4],args[5],args[6],args[7],HealthFund.valueOf(args[8]),
					BiologicalSex.valueOf(args[9])
					);

			if(hospital.addPatient(patient))
				MyFileLogWriter.println("successfully added patient "+patient.getId());
			else
				MyFileLogWriter.println("failed to add patient "+patient.getId());
			if(patient.getId()==34) {
				MyFileLogWriter.println("Patient details: "+patient.toString());
			}
		});
		
		commands.put("addNurse", (sections,args) -> {
			Nurse nurse = new Nurse(Integer.parseInt(args[0]),args[1],args[2],UtilsMethods.parseDate(args[3])
					,args[4],args[5],args[6],args[7],UtilsMethods.parseDate(args[8]),
					Double.parseDouble(args[9]),Integer.parseInt(args[10])
					);
			if(hospital.addNurse(nurse))
				MyFileLogWriter.println("successfully added nurse "+nurse.getId());
			else
				MyFileLogWriter.println("failed to add nurse "+nurse.getId());
			if(nurse.getId()==24) {
				MyFileLogWriter.println("Nurse details: "+nurse.toString());				
			}
		});
		
		commands.put("addIntensiveCareNurse", (sections,args) -> {
			IntensiveCareNurse nurse = new IntensiveCareNurse(Integer.parseInt(args[0]),args[1],args[2],UtilsMethods.parseDate(args[3])
					,args[4],args[5],args[6],args[7],UtilsMethods.parseDate(args[8]),
					Double.parseDouble(args[9]),Integer.parseInt(args[10])
					);
			if(hospital.addNurse(nurse))
				MyFileLogWriter.println("successfully added IntensiveCareNurse "+nurse.getId());
			else
				MyFileLogWriter.println("failed to add IntensiveCareNurse "+nurse.getId());
			if(nurse.getId()==29) {
				MyFileLogWriter.println("Nurse details: "+nurse.toString());				
			}
		});

		
		commands.put("addNurseToDepartment", (sections,args) -> {
			if(hospital.addNurseToDepartment(hospital.getRealDepartment(Integer.parseInt(args[0])),hospital.getRealNurse(Integer.parseInt(args[1]))))
				MyFileLogWriter.println("successfully added Nurse "+args[1]+" to Department "+args[0]);
			else
				MyFileLogWriter.println("failed to add Nurse "+args[1]+" to Department "+args[0]);
		});
		
		commands.put("addDoctorToDepartment", (sections,args) -> {
			if(hospital.addDoctorToDepartment(hospital.getRealDepartment(Integer.parseInt(args[0])),hospital.getRealDoctor(Integer.parseInt(args[1]))))
				MyFileLogWriter.println("successfully added Doctor "+args[1]+" to Department "+args[0]);
			else
				MyFileLogWriter.println("failed to add Doctor "+args[1]+" to Department "+args[0]);
		});

		commands.put("addMedication", (sections,args) -> {
			Medication medication = new Medication(Integer.parseInt(args[0]),args[1],
					Double.parseDouble(args[2]),Integer.parseInt(args[3])
					);
			if(hospital.addMedication(medication))
				MyFileLogWriter.println("successfully added medication "+medication.getCode());
			else
				MyFileLogWriter.println("failed to add medication "+medication.getCode());
			if(medication.getCode()==1) {
				MyFileLogWriter.println("Medication details: "+medication.toString());
			}
		});
		commands.put("addTreatment", (sections,args) -> {
			Treatment treatment = new Treatment(Integer.parseInt( args[0]),args[1]);
			treatment.addMedication(hospital.getRealMedication(Integer.parseInt(args[2])));
			treatment.addMedication(hospital.getRealMedication(Integer.parseInt(args[3])));
			treatment.addMedication(hospital.getRealMedication(Integer.parseInt(args[4])));
			treatment.addMedication(hospital.getRealMedication(Integer.parseInt(args[5])));
			treatment.addDoctor(hospital.getRealDoctor(Integer.parseInt(args[6])));
			treatment.addDoctor(hospital.getRealDoctor(Integer.parseInt(args[7])));
			treatment.addDoctor(hospital.getRealDoctor(Integer.parseInt(args[8])));

			if(hospital.addTreatment(treatment))
				MyFileLogWriter.println("successfully added treatment "+treatment.getSerialNumber());
			else
				MyFileLogWriter.println("failed to add treatment "+treatment.getSerialNumber());
			if(treatment.getSerialNumber()==1) {
				MyFileLogWriter.println("Treatment details: "+treatment.toString());
			}
		});
		
		commands.put("addVisit", (sections,args) -> {
			Visit visit = new Visit(Integer.parseInt(args[0]),hospital.getRealPatient(Integer.parseInt(args[1]))
					,UtilsMethods.parseDate(args[2]),UtilsMethods.parseDate(args[3])
					);
			visit.addDisease(hospital.getRealDisease(args[4]));
			visit.addDisease(hospital.getRealDisease(args[5]));
			visit.addInjury(hospital.getRealInjury(args[6]));
			visit.addInjury(hospital.getRealInjury(args[7]));
			visit.addFracture(hospital.getRealFracture(args[8]));
			visit.addTreatment(hospital.getRealTreatment(Integer.parseInt( args[9])));
			visit.addTreatment(hospital.getRealTreatment(Integer.parseInt( args[10])));
			if(hospital.addVisit(visit))
				MyFileLogWriter.println("successfully added visit "+visit.getNumber());
			else
				MyFileLogWriter.println("failed to add visit "+visit.getNumber());
			if(visit.getNumber()==1) {
				MyFileLogWriter.println("Visit details: "+visit.toString());
				MyFileLogWriter.println(visit.getTreatmentsList().toString());
			}
		});
		
		commands.put("addTreatmentToMedicalProblem", (sections,args) -> {
			Treatment treatment=hospital.getRealTreatment(Integer.parseInt(args[0]));
			MedicalProblem medicalProblem=hospital.getMedicalProblem(args[1]);
			medicalProblem.addTreatment(treatment);
			treatment.addMedicalProblem(medicalProblem);
		});
		
		
		commands.put("removeDepartment", (sections,args) -> {
			if(hospital.removeDepartment(hospital.getRealDepartment(Integer.parseInt(args[0]))))
				MyFileLogWriter.println("successfully removed Department "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Department "+args[0]);
		});
		commands.put("removeDisease", (sections,args) -> {
			if(hospital.removeDisease(hospital.getRealDisease(args[0])))
				MyFileLogWriter.println("successfully removed Disease "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Disease "+args[0]);
		});
		commands.put("removeDoctor", (sections,args) -> {
			
			if(hospital.removeDoctor(hospital.getRealDoctor(Integer.parseInt(args[0]))))
				MyFileLogWriter.println("successfully removed Doctor "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Doctor "+args[0]);
		});
		commands.put("removeFracture", (sections,args) -> {
			
			if(hospital.removeFracture(hospital.getRealFracture(args[0])))
				MyFileLogWriter.println("successfully removed Fracture "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Fracture "+args[0]);
		});
		commands.put("removeInjury", (sections,args) -> {
			
			if(hospital.removeInjury(hospital.getRealInjury(args[0])))
				MyFileLogWriter.println("successfully removed Injury "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Injury "+args[0]);
		});
		commands.put("removeMedication", (sections,args) -> {
			
			if(hospital.removeMedication(hospital.getRealMedication(Integer.parseInt(args[0]))))
				MyFileLogWriter.println("successfully removed Medication "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Medication "+args[0]);
		});
		commands.put("removeNurse", (sections,args) -> {

			if(hospital.removeNurse(hospital.getRealNurse(Integer.parseInt(args[0]))))
				MyFileLogWriter.println("successfully removed Nurse "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Nurse "+args[0]);
		});
		commands.put("removePatient", (sections,args) -> {

			if(hospital.removePatient(hospital.getRealPatient(Integer.parseInt(args[0]))))
				MyFileLogWriter.println("successfully removed Patient "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Patient "+args[0]);
		});
		commands.put("removeTreatment", (sections,args) -> {
			if(hospital.removeTreatment(hospital.getRealTreatment(Integer.parseInt( args[0]))))
				MyFileLogWriter.println("successfully removed Treatment "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Treatment "+args[0]);
		});
		commands.put("removeVisit", (sections,args) -> {

			if(hospital.removeVisit(hospital.getRealVisit(Integer.parseInt(args[0]))))
				MyFileLogWriter.println("successfully removed Visit "+args[0]);
			else
				MyFileLogWriter.println("failed to remove Visit "+args[0]);
		});
		
		commands.put("countMedications", (sections,args) -> {
			MyFileLogWriter.println("the number of medications is: " +hospital.countMedications(Double.parseDouble(args[0]),Double.parseDouble(args[1])));
		});
		
		commands.put("differenceBetweenTheLongestAndShortestVisit", (sections,args) -> {
			try {
				MyFileLogWriter.println("the diffBetween The Longest And Shortest Visit is: " +
				(hospital.differenceBetweenTheLongestAndShortestVisit
						(hospital.getRealPatient(Integer.parseInt(args[0])))));
			}catch (Exception e) {
				MyFileLogWriter.println("An invalid ID number was given or of a patient that does not exist in the system");
			}
		});
		commands.put("printHowManyFinishInternship", (sections,args) -> {
			MyFileLogWriter.println("The number How Finish Internship");
			hospital.printHowManyFinishInternship();
		});
		
		commands.put("howManyVisitBefore", (sections,args) -> {
			MyFileLogWriter.println("The ammount of visits before Date is: " + 
		(hospital.howManyVisitBefore(UtilsMethods.parseDate(args[0]))));
		});
		
		commands.put("printOldestNurse", (sections,args) -> {
			MyFileLogWriter.println("The oldest Nurse details is: ");
			hospital.printOldestNurse();
		});
		
		commands.put("staffMembersThatWorksInMoreThenOneDepartment", (sections,args) -> {
			MyFileLogWriter.println("staffMembersThatWorksInMoreThenOneDepartment");
			HashMap<StaffMember, ArrayList<Department>>map=
					hospital.staffMembersThatWorksInMoreThenOneDepartment();
			for(StaffMember staffMember:map.keySet()) {
				MyFileLogWriter.println("The StaffMember: "+staffMember.getId()+": "+map.get(staffMember).toString());
			}
		});
		
		commands.put("getTreatmentsByMedicalProblemsByDepartment", (sections,args) -> {
			MyFileLogWriter.println("getTreatmentsByMedicalProblemsByDepartment");
			HashMap<Department, HashMap<MedicalProblem, ArrayList<Treatment>>>map=
					hospital.getTreatmentsByMedicalProblemsByDepartment();
			for(Department department:map.keySet()) {
				MyFileLogWriter.println("The Department: "+department.getNumber()+": ");
				for(MedicalProblem medicalProblem:map.get(department).keySet()) {
					MyFileLogWriter.println("The MedicalProblem: "+medicalProblem.getCode()+": "+map.get(department).get(medicalProblem).toString());
				}
				MyFileLogWriter.println("");
			}
		});
		
		commands.put("getNumberOfDoctorsBySpecialization", (sections,args) -> {
			MyFileLogWriter.println("getNumberOfDoctorsBySpecialization");
			HashMap<Specialization, Integer>map=hospital.getNumberOfDoctorsBySpecialization();
			for(Specialization specialization:map.keySet()) {
				MyFileLogWriter.println("The Specialization: "+specialization.toString()+
						", the number of doctors: "+map.get(specialization).toString());
			}
		});
		
		commands.put("howManyIntensiveCareStaffMembers", (sections,args) -> {
			MyFileLogWriter.println("The number of IntensiveCareStaffMembers is: " + 
					hospital.howManyIntensiveCareStaffMembers());
		});
		
		commands.put("avgSalary", (sections,args) -> {
			MyFileLogWriter.println("The average salary is: " + 
					hospital.avgSalary());
		});
		
		commands.put("isCompliesWithTheMinistryOfHealthStandard", (sections,args) -> {
			if(hospital.isCompliesWithTheMinistryOfHealthStandard()) {
				MyFileLogWriter.println("The hospital complies with the ministry of health standard");
			}else {
				MyFileLogWriter.println("The hospital is not complies with the ministry of health standard");
			}
		});
		
		commands.put("AppointANewManager", (sections,args) -> {
			MyFileLogWriter.println("The new Manager of department " +args[0]+" is "+
					hospital.AppointANewManager(hospital.getRealDepartment(Integer.parseInt(args[0]))));
		});
		
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stu
		MyFileLogWriter.initializeMyFileWriter();

		try{
			List<String[]> input = CSVExporter.importCSV("INPUT.csv");

			for (int i = 1; i < input.size(); i++) {

				//get row
				String[] values = input.get(i);

				if(values.length == 0)
					continue;
				//get command
				String command = values[0];

				//get params
				String[] params = Arrays.copyOfRange(values,1,values.length);

				//send to func 
				try {
					func(command, params);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			System.out.println("All commands executed. Please check \"" + OUTPUT_FILE + "\".");
		}catch (Exception e) {
			System.err.println("Failed to executed all commands.");
			e.printStackTrace();
		}

		Hospital.getInstance().serialize();
		Object obj = Hospital.deserialize("hospital.ser");
	}

	private static void func(String command,String[] args){
		//extract command
		Command c = commands.get(command);

		//check that command exists
		if (c != null){
			//get relevant section
			Section section = sections.get(command);

			//execute
			c.execute(section,args);
		}
	}


	//---------------------------------------------------------------------------------------
}
