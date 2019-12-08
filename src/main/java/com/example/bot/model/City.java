package com.example.bot.model;

import com.example.bot.model.base.BaseId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class City extends BaseId {

    @Column
    @NotNull
    private String title;

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY)
    private Set<InformationCity> informationCities;
}
