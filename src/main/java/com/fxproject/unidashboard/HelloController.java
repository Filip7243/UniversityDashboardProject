package com.fxproject.unidashboard;

import com.fxproject.unidashboard.dto.StudentDto;
import com.fxproject.unidashboard.model.AcademicTitle;
import com.fxproject.unidashboard.model.Professor;
import com.fxproject.unidashboard.model.Student;
import com.fxproject.unidashboard.model.UniversityMember;
import com.fxproject.unidashboard.repository.UniversityMemberRepository;
import com.fxproject.unidashboard.utils.HibernateUtils;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HelloController {
    @FXML
    private TextField idInput;
    @FXML
    private ListView<StudentDto> studentsDtoList;
    @FXML
    private Button findButton;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField secondNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private Button addButton;

    private ObservableList<StudentDto> students;
    private UniversityMemberRepository uniRepo;
    EntityManager em = HibernateUtils.getEntityManager();


    public void initialize() {
        this.uniRepo = new UniversityMemberRepository(em);

        // input
        findButton.setDisable(true);
        idInput.textProperty()
                .addListener((obs, oldText, newText) -> findButton.setDisable(obs.getValue().trim().isEmpty()));

    }

    @FXML
    protected void onHelloButtonClick() {
//        Student professor = new Student();
//        professor.setFirstName("John");
//        professor.setSecondName("Mark");
//        professor.setLastName("Doe");
//        professor.setEmail("john.mark.doe@mail.com");
//        professor.setPlaceOfBirth("New York City");
//        professor.setPesel("65439201832");
//        professor.setDateOfBirth(LocalDateTime.now());
//        professor.setPhoneNumber("(+48)786-492-771");
//        professor.setAlbumId(UUID.randomUUID().toString());
//        professor.setYears(new ArrayList<>());
//
//        em.getTransaction().begin();
//        uniRepo.save(professor);
//        em.getTransaction().commit();

        em.getTransaction().begin();
        List<UniversityMember> all = uniRepo.findAll();
        em.getTransaction();
        System.out.println(all);
    }

    @FXML
    protected void onFindButtonClick() {

    }

    @FXML
    protected void onAddButtonClick() {

    }

    @FXML
    protected void onRemoveButtonClick() {

    }


}