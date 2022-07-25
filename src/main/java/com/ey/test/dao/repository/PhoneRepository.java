package com.ey.test.dao.repository;

import com.ey.test.dao.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
}
