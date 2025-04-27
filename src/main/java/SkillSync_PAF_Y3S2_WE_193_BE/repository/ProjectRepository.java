package SkillSync_PAF_Y3S2_WE_193_BE.repository;

import SkillSync_PAF_Y3S2_WE_193_BE.model.Project;
import SkillSync_PAF_Y3S2_WE_193_BE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCollaboratorsContaining(User user);
    List<Project> findByOwner(User owner);
    List<Project> findByRequiredSkillsId(Long skillId);
}