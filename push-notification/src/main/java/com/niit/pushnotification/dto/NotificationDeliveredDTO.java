package com.niit.pushnotification.dto;

import com.niit.pushnotification.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class NotificationDeliveredDTO {
    private String emailId;

    private Movie movie;
}
