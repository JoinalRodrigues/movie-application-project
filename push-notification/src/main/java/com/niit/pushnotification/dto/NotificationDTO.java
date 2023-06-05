package com.niit.pushnotification.dto;

import com.niit.pushnotification.domain.Movie;
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
