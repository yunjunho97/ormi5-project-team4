package com.example.ormi5projectteam4.domain.dto;

import com.example.ormi5projectteam4.domain.constant.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStatus {
    private AdoptionStatus adoptionStatus;
}
