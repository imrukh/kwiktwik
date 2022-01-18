package com.kwiktwik.feedbackservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UserSlot")
public class UserSlot implements Serializable {
    @Id
    private String id;
    private String userId;
    private ArrayList<Slot> slots;
    public UserSlot(String userId){
        this.userId = userId;
        this.slots = new ArrayList<>();
    }
}
