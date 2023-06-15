package me.project.school.industry.academia.domain.user.repository;

import me.project.school.industry.academia.domain.user.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {
}
