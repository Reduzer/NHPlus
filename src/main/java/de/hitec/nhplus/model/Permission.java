package de.hitec.nhplus.model;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    private List<String> m_UserPermissions = new ArrayList<String>(){};

    public Permission(){

    }

    public List<String> getPermission(){
        return m_UserPermissions;
    }

    public void setPermissions(String PermissionName){
        m_UserPermissions.add(PermissionName);
    }
}
