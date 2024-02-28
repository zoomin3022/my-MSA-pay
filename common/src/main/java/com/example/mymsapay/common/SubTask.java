package com.example.mymsapay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private Long membershipID;
    private String subTaskName;
    private TaskType taskType; // "banking", "membership"
    private Status status; // "success", "fail", "ready"

    public static enum TaskType {
        BANKING, MEMBERSHIP
    }

    public static enum Status {
        SUCCESS, FAIL, READY
    }
}
