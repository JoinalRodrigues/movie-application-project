package com.niit.project.pushnotification.dto;

import com.niit.project.pushnotification.domain.Movie;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NotificationDTO {
    private String emailId;
    private Movie movie;
}
