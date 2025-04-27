package SkillSync_PAF_Y3S2_WE_193_BE.repository;

import SkillSync_PAF_Y3S2_WE_193_BE.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByName(String name);
    List<Skill> findByCategory(Skill.SkillCategory category);
    boolean existsByName(String name);
}