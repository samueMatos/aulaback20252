module com.senac.senacadminconfig {
    requires javafx.controls;
    requires javafx.fxml;


    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.senac.senacadminconfig to javafx.fxml;
    opens com.senac.senacadminconfig.model to org.hibernate.orm.core;

    exports com.senac.senacadminconfig;
    exports com.senac.senacadminconfig.controller;
    opens com.senac.senacadminconfig.controller to javafx.fxml;
}