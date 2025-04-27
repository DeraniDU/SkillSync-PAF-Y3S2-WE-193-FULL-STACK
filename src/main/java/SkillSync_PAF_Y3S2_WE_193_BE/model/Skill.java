package SkillSync_PAF_Y3S2_WE_193_BE.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"users", "projects"})
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    @Size(max = 50, message = "Skill name cannot exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    @Column(length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private SkillCategory category = SkillCategory.TECHNICAL;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "requiredSkills", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Project> projects = new HashSet<>();

    public enum SkillCategory {
        TECHNICAL, DESIGN, BUSINESS, LANGUAGE, OTHER
    }

    public int getPopularityScore() {
        return users.size() + projects.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        return id != null && id.equals(((Skill) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}