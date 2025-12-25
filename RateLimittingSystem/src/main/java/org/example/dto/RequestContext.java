package org.example.dto;

import lombok.Data;
import org.example.enums.UserType;
import java.sql.Timestamp;

@Data
public class RequestContext {
    String user;
    String endpoint;
    Timestamp timeStamp;
    UserType userType;
}
