package com.project.shopapp.Services;

import com.project.shopapp.Repositories.RoleRepository;
import com.project.shopapp.dto.response.RoleResponse;
import com.project.shopapp.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public List<RoleResponse> getAll(){
        return roleRepository.findAll().stream().map(role ->
            RoleResponse.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .build()).toList();
    }
}
