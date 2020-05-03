package com.intaehwang.springtanz.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Exam {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Score score;

    private LocalDateTime firstReg;
    private LocalDateTime LastModify;
}
