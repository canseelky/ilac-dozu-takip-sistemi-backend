package com.msku.drugdosemonitoringsystem.entities;

import java.util.UUID;

public interface User {
     UUID getId();
     String getName();
     String getSurname();
     String getEmail();
     String getPassword();
     Role getRole();
}
