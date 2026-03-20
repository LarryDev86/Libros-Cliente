module es.larry.libroscliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires jdk.compiler;

    opens es.larry.libroscliente to javafx.fxml;
    opens es.larry.libroscliente.dto to com.fasterxml.jackson.databind;

    exports es.larry.libroscliente;
    exports es.larry.libroscliente.dto;
    exports es.larry.libroscliente.controller;
    exports es.larry.libroscliente.service;
    exports es.larry.libroscliente.cliente;
    exports es.larry.libroscliente.view;
}