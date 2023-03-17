package com.lendtech.mselevator.repository;

import com.lendtech.mselevator.entity.TblElevator;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblElevatorRepository extends R2dbcRepository<TblElevator,Long> {
}
