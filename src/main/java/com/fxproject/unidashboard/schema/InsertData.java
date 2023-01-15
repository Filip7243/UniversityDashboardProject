package com.fxproject.unidashboard.schema;

import com.fxproject.unidashboard.email.EmailAddressGenerator;
import com.fxproject.unidashboard.email.PasswordGenerator;
import com.fxproject.unidashboard.model.*;
import com.fxproject.unidashboard.repository.PersonRepository;
import com.fxproject.unidashboard.utils.HibernateConnect;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class InsertData {

    private static final List<String> MALE_FIRST_NAMES = List.of(
            "John", "Steve", "Harry", "Tomas", "Ron", "Walter", "Jeremy", "Paul", "Michael", "Lionel",
            "Wayne", "Peter");
    private static final List<String> FEMALE_FIRST_NAMES = List.of(
            "Hermione", "Gwen", "Mary", "Fiona", "Lily", "Olivia", "Pam", "Emma", "Mia", "Sophia", "Amelia", "Ava"
    );
    private static final List<String> LAST_NAMES = List.of(
            "Potter", "Weasley", "Granger", "Malfoy", "Weasley", "Griffin", "Scott", "Beesly", "Halpert", "Schrute",
            "Smith", "Doe"
    );
    private static final Random RANDOM = new Random();
    private static PersonRepository pr = new PersonRepository();


    public InsertData() {

    }

    static {
        Transaction tx = null;
        try (Session session = HibernateConnect.openSession()) {
            System.out.println(session);
            tx = session.beginTransaction();

            // Departments
            Departments humanities = new Departments(null, "Humanities Department");
            Departments naturalScience = new Departments(null, "Natural Science Department");
            Departments medicalScience = new Departments(null, "Medical Science Department");
            session.persist(humanities);
            session.persist(naturalScience);
            session.persist(medicalScience);

            // Fields of study
            FieldsOfStudy physics = new FieldsOfStudy(null, "Physics", TypesOfStudy.BACHELOR, humanities);
            FieldsOfStudy it = new FieldsOfStudy(null, "IT", TypesOfStudy.ENGINEERING, humanities);
            FieldsOfStudy mechatronics = new FieldsOfStudy(null, "Mechatronics", TypesOfStudy.BACHELOR, humanities);
            session.persist(physics);
            session.persist(it);
            session.persist(mechatronics);

            FieldsOfStudy agriculture = new FieldsOfStudy(null, "Agriculture", TypesOfStudy.DOCTORAL, naturalScience);
            FieldsOfStudy math = new FieldsOfStudy(null, "Math", TypesOfStudy.ENGINEERING, naturalScience);
            FieldsOfStudy culturalStudies = new FieldsOfStudy(null, "Cultural Studies", TypesOfStudy.MASTERS, naturalScience);
            session.persist(agriculture);
            session.persist(math);
            session.persist(culturalStudies);

            FieldsOfStudy medicine = new FieldsOfStudy(null, "Medicine", TypesOfStudy.DOCTORAL, medicalScience);
            FieldsOfStudy obstetrics = new FieldsOfStudy(null, "Obstetrics", TypesOfStudy.BACHELOR, medicalScience);
            FieldsOfStudy nursing = new FieldsOfStudy(null, "Nursing", TypesOfStudy.MASTERS, medicalScience);
            session.persist(medicine);
            session.persist(obstetrics);
            session.persist(nursing);

            // Groups
            Groups lab1Physics = new Groups(null, "Lab1 - Physics", physics);
            Groups lab2Physics = new Groups(null, "Lab2 - Physics", physics);
            Groups lab1IT = new Groups(null, "Lab1 - IT", it);
            Groups lab2IT = new Groups(null, "Lab2 - IT", it);
            Groups lab1Mechatronics = new Groups(null, "Lab1 - Mechatronics", mechatronics);
            Groups lab2Mechatronics = new Groups(null, "Lab2 - Mechatronics", mechatronics);

            Groups ex1Agriculture = new Groups(null, "Exercise1 - Agriculture", agriculture);
            Groups ex2Agriculture = new Groups(null, "Exercise2 - Agriculture", agriculture);
            Groups ex1Math = new Groups(null, "Exercise1 - Math", math);
            Groups ex2Math = new Groups(null, "Exercise2 - Math", math);
            Groups ex1CulturalStudies = new Groups(null, "Exercise1 - Cultural Studies", culturalStudies);
            Groups ex2CulturalStudies = new Groups(null, "Exercise2 - Cultural Studies", culturalStudies);

            Groups ex1Medicine = new Groups(null, "Exercise1 - Medicine", medicine);
            Groups ex2Medicine = new Groups(null, "Exercise2 - Medicine", medicine);
            Groups ex1Obstetrics = new Groups(null, "Exercise1 - Obstetrics", obstetrics);
            Groups ex2Obstetrics = new Groups(null, "Exercise2 - Obstetrics", obstetrics);
            Groups ex1Nursing = new Groups(null, "Exercise1 - Nursing", nursing);
            Groups ex2Nursing = new Groups(null, "Exercise2 - Nursing", nursing);
            List<Groups> groups = List.of(lab1Physics, lab2Physics, lab1IT, lab2IT, lab1Mechatronics, lab2Mechatronics,
                    ex1Agriculture, ex2Agriculture, ex1Medicine, ex2Medicine, ex1Math, ex1Math, ex2Math, ex2Math,
                    ex1CulturalStudies, ex2CulturalStudies, ex1Obstetrics, ex2Obstetrics, ex1Nursing, ex2Nursing);
            for (Groups group : groups) {
                session.persist(group);
            }

            // Addresses
            Addresses a1 = new Addresses(null, "Poland", "Rzeszow", "Hetmanska", 21, 3, "37-100");
            Addresses a2 = new Addresses(null, "Poland", "Rzeszow", "Powstancow Warszawy", 2, 511, "37-100");
            Addresses a3 = new Addresses(null, "Poland", "Rzeszow", "Hetmanska", 48, 52, "37-100");
            List<Addresses> addresses = List.of(a1, a2, a3);
            for (Addresses address : addresses) {
                session.persist(address);
            }

            // Subjects
            Subjects s1 = new Subjects(null, "Math");
            Subjects s2 = new Subjects(null, "English");
            Subjects s3 = new Subjects(null, "Social Sciences");
            Subjects s4 = new Subjects(null, "Arts");
            Subjects s5 = new Subjects(null, "Computer Basics");
            Subjects s6 = new Subjects(null, "Philosophy");
            List<Subjects> subjects = List.of(s1, s2, s3, s4, s5, s6);
            for (Subjects subject : subjects) {
                session.persist(subject);
            }

            // Male Students
            for (int i = 0; i < MALE_FIRST_NAMES.size(); i++) {
                int randomFirstName = RANDOM.nextInt(MALE_FIRST_NAMES.size());
                int randomLastName = RANDOM.nextInt(LAST_NAMES.size());
                int randomAddress = RANDOM.nextInt(addresses.size());
                int randomGroup = RANDOM.nextInt(groups.size());
                Students s = new Students(null, MALE_FIRST_NAMES.get(randomFirstName), LAST_NAMES.get(randomLastName),
                        "example@mail.com", "777666555", LocalDateTime.of(2001, 2, 2, 10, 21),
                        "New York", "00000000000", 'M', 22, addresses.get(randomAddress));
                s.getGroups().add(groups.get(randomGroup));
                session.persist(s);
                UniversityAccounts acc = new UniversityAccounts(EmailAddressGenerator.generateMailForStudent(s), PasswordGenerator.generatePassword(), LocalDateTime.now(), true, s, Roles.ROLE_STUDENT);
                session.persist(acc);
                s.setAcc(acc);
            }

            // Female Students
            for (int i = 0; i < FEMALE_FIRST_NAMES.size(); i++) {
                int randomFirstName = RANDOM.nextInt(FEMALE_FIRST_NAMES.size());
                int randomLastName = RANDOM.nextInt(LAST_NAMES.size());
                int randomAddress = RANDOM.nextInt(addresses.size());
                int randomGroup = RANDOM.nextInt(groups.size());
                Students s = new Students(null, FEMALE_FIRST_NAMES.get(randomFirstName), LAST_NAMES.get(randomLastName),
                        "female@mail.com", "999888777", LocalDateTime.of(2000, 2, 2, 10, 21),
                        "Krakow", "11111111111", 'F', 23, addresses.get(randomAddress));
                s.getGroups().add(groups.get(randomGroup));
                session.persist(s);
                UniversityAccounts acc = new UniversityAccounts(EmailAddressGenerator.generateMailForStudent(s), PasswordGenerator.generatePassword(), LocalDateTime.now(), true, s, Roles.ROLE_STUDENT);
                session.persist(acc);
                s.setAcc(acc);
            }

            // Male Professors
            AcademicTitles[] academicTitles = AcademicTitles.values();
            for (int i = 0; i < MALE_FIRST_NAMES.size(); i++) {
                int randomFirstName = RANDOM.nextInt(FEMALE_FIRST_NAMES.size());
                int randomLastName = RANDOM.nextInt(LAST_NAMES.size());
                int randomAddress = RANDOM.nextInt(addresses.size());
                int randomGroup = RANDOM.nextInt(groups.size());
                int randomSubject = RANDOM.nextInt(subjects.size());
                int randomTitle = RANDOM.nextInt(academicTitles.length);
                Professors p = new Professors(null, MALE_FIRST_NAMES.get(randomFirstName), LAST_NAMES.get(randomLastName),
                        "abc@mail.com", "111222333", LocalDateTime.of(1990, 2, 2, 10, 21),
                        "Wroclaw", "22222222222", 'M', 33, addresses.get(randomAddress), academicTitles[randomTitle]);
                ProfessorsSubjectsInGroups psig =
                        new ProfessorsSubjectsInGroups(p, subjects.get(randomSubject), groups.get(randomGroup));
                session.persist(psig);
                p.getPsig().add(psig);
                session.persist(p);
                UniversityAccounts acc = new UniversityAccounts(EmailAddressGenerator.generateMailForProfessor(p), PasswordGenerator.generatePassword(), LocalDateTime.now(), true, p, Roles.ROLE_PROFESSOR);
                session.persist(acc);
                p.setAcc(acc);
            }

            // Female Professors
            for (int i = 0; i < FEMALE_FIRST_NAMES.size(); i++) {
                int randomFirstName = RANDOM.nextInt(FEMALE_FIRST_NAMES.size());
                int randomLastName = RANDOM.nextInt(LAST_NAMES.size());
                int randomAddress = RANDOM.nextInt(addresses.size());
                int randomGroup = RANDOM.nextInt(groups.size());
                int randomSubject = RANDOM.nextInt(subjects.size());
                int randomTitle = RANDOM.nextInt(academicTitles.length);
                Professors p = new Professors(null, FEMALE_FIRST_NAMES.get(randomFirstName), LAST_NAMES.get(randomLastName),
                        "def@mail.com", "888333777", LocalDateTime.of(1995, 2, 2, 10, 21),
                        "Poznan", "33333333333", 'F', 28, addresses.get(randomAddress), academicTitles[randomTitle]);
                ProfessorsSubjectsInGroups psig =
                        new ProfessorsSubjectsInGroups(p, subjects.get(randomSubject), groups.get(randomGroup));
                session.persist(psig);
                p.getPsig().add(psig);
                session.persist(p);
                UniversityAccounts acc = new UniversityAccounts(EmailAddressGenerator.generateMailForProfessor(p), PasswordGenerator.generatePassword(), LocalDateTime.now(), true, p, Roles.ROLE_PROFESSOR);
                session.persist(acc);
                p.setAcc(acc);
            }

            // !!! ADMIN !!!
            if(pr.findPersonWithFirstName("admin").isEmpty()) {
                Person admin = new Person(null, "admin", "admin", "admin@mail.com", "12345678", LocalDateTime.now(),
                        "Rzeszow", "22222123212", 'M', 22, null);
                UniversityAccounts accAdmin = new UniversityAccounts("admin", "admin", LocalDateTime.now(), true, admin, Roles.ROLE_ADMIN);
                session.persist(admin);
                session.persist(accAdmin);
                admin.setAcc(accAdmin);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                System.out.println(e.getMessage());
                tx.rollback();
            }
        }
    }
}
