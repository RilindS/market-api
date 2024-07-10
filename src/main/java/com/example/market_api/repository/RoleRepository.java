package com.example.market_api.repository;

import com.example.market_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role,Long>{
    Role findByName(String name);


}
