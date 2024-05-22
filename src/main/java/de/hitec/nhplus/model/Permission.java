package de.hitec.nhplus.model;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    // Liste der Benutzerberechtigungen
    private List<String> m_UserPermissions = new ArrayList<String>(){};

    // Standardkonstruktor
    public Permission(){
    }

    // Gibt die Liste der Berechtigungen zurück
    public List<String> getPermission(){
        return m_UserPermissions;
    }

    // Fügt eine neue Berechtigung zur Liste hinzu
    public void setPermissions(String PermissionName){
        m_UserPermissions.add(PermissionName);
    }
}
