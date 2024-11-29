module LawFirmApp {
    requires spring.context;
    requires spring.beans;
    requires java.sql;

    opens LawFirmApp;
    opens LawFirmApp.entities;
    opens LawFirmApp.repositories;
    opens LawFirmApp.services;
    opens LawFirmApp.views;
    opens LawFirmApp.config;
}