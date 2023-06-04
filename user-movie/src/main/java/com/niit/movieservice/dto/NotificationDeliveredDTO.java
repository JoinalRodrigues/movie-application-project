package com.niit.movieservice.dto;

import com.niit.movieservice.model.Movie;
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
