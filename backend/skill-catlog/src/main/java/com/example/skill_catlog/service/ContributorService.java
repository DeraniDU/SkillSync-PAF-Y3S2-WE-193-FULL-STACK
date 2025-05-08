package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.ContributorDTO;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.Contributor;
import com.example.skill_catlog.repository.ContributorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContributorService {

    @Autowired
    private ContributorRepository contributorRepository;

    public ContributorDTO createContributor(ContributorDTO contributorDTO) {
        Contributor contributor = convertToEntity(contributorDTO);
        Contributor savedContributor = contributorRepository.save(contributor);
        return convertToDTO(savedContributor);
    }

    public ContributorDTO getContributorById(String id) {
        Contributor contributor = contributorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contributor not found with id: " + id));
        return convertToDTO(contributor);
    }

    public ContributorDTO getContributorByEmail(String email) {
        Contributor contributor = contributorRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Contributor not found with email: " + email));
        return convertToDTO(contributor);
    }

    public List<ContributorDTO> getAllContributors() {
        List<Contributor> contributors = contributorRepository.findAll();
        return contributors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContributorDTO updateContributor(String id, ContributorDTO contributorDTO) {
        Contributor existingContributor = contributorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contributor not found with id: " + id));

        if (contributorDTO.getFullName() != null) {
            existingContributor.setFullName(contributorDTO.getFullName());
        }
        if (contributorDTO.getEmail() != null) {
            existingContributor.setEmail(contributorDTO.getEmail());
        }
        if (contributorDTO.getGithubProfile() != null) {
            existingContributor.setGithubProfile(contributorDTO.getGithubProfile());
        }
        if (contributorDTO.getLinkedinProfile() != null) {
            existingContributor.setLinkedinProfile(contributorDTO.getLinkedinProfile());
        }
        if (contributorDTO.getSkills() != null) {
            existingContributor.setSkills(contributorDTO.getSkills());
        }
        if (contributorDTO.getRole() != null) {
            existingContributor.setRole(contributorDTO.getRole());
        }

        Contributor updatedContributor = contributorRepository.save(existingContributor);
        return convertToDTO(updatedContributor);
    }

    public void deleteContributor(String id) {
        Contributor contributor = contributorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contributor not found with id: " + id));

        contributorRepository.delete(contributor);
    }

    private Contributor convertToEntity(ContributorDTO dto) {
        Contributor contributor = new Contributor();
        BeanUtils.copyProperties(dto, contributor);
        return contributor;
    }

    private ContributorDTO convertToDTO(Contributor contributor) {
        ContributorDTO dto = new ContributorDTO();
        BeanUtils.copyProperties(contributor, dto);
        return dto;
    }
}