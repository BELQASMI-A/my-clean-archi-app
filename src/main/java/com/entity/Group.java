package com.entity;

import java.util.Date;
import java.util.List;

public interface Group extends Identified, Labeled, Owned, Described {
    // Identity & Hierarchy
    int getDbId();

    void setDbId(int dbId);

    String getPrefix();

    void setPrefix(String prefix);

    int getGroupIdNumeric();

    void setGroupIdNumeric(int groupIdNumeric);

    String getParentId();

    void setParentId(String parentId);

    Integer getParentIdNumeric();

    void setParentIdNumeric(Integer parentIdNumeric);

    // Security & Privileges
    long getPrivileges();

    void setPrivileges(long privileges);

    long getFlags();

    void setFlags(long flags);

    // Dates & State
    Date getDateC();

    void setDateC(Date dateC);

    Date getDateM();

    void setDateM(Date dateM);

    Boolean isExpired();

    void setExpired(Boolean expired);

    Date getDateofExpiration();

    void setDateofExpiration(Date dateofExpiration);

    // External Directory (LDAP)
    String getExternalDirectoryId();

    void setExternalDirectoryId(String externalDirectoryId);

    // Users
    List<User> getUsers();

    void addUser(User user);
}
