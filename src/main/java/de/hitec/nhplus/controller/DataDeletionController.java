package de.hitec.nhplus.controller;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDeletionController {

    private final PatientDao patientDao;
    //private final StaffDao staffDao;
    private final TreatmentDao treatmentDao;


    public DataDeletionController() {
        this.patientDao = DaoFactory.getDaoFactory().createPatientDAO();
        //this.staffDao = DaoFactory.getDaoFactory().createStaffDAO();
        this.treatmentDao = DaoFactory.getDaoFactory().createTreatmentDao();
    }

    public void deletePatientData(int patientId) {
        try {
            // Löscht Behandlungen des Patienten
            treatmentDao.deleteByPid(patientId);
            // Patient wird gelöscht
            patientDao.deleteById(patientId);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteStaffData(int staffId) {
        try {
            staffDao.deleteById(staffId);
            //
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

}

