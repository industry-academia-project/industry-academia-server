package me.project.school.industry.academia.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.domain.user.dto.request.UserUpdateRequest;
import me.project.school.industry.academia.domain.user.entity.Author;
import me.project.school.industry.academia.domain.user.entity.User;
import me.project.school.industry.academia.domain.user.repository.AuthorRepository;
import me.project.school.industry.academia.domain.user.repository.UserRepository;
import me.project.school.industry.academia.global.jwt.TokenProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    private final TokenProvider tokenProvider;


    public User userInfo(String token) {
        return tokenProvider.validateToken(token);
    }

    public void userUpdate(String token, UserUpdateRequest request) {
        User user = tokenProvider.validateToken(token);
        Author author = user.getAuthor();

        user.setName(request.getName());
        author.setAuthor_name(request.getAuthor_name());
        author.setCareer(request.getCareer());
        author.setIntroduction(request.getIntroduction());
        author.setSpecialized(request.getSpecialized());

        authorRepository.save(author);
        userRepository.save(user);

    }

}
