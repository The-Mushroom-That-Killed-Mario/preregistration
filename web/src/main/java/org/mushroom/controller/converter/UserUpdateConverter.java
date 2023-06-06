//package org.mushroom.controller.converter;
//
//
//import lombok.RequiredArgsConstructor;
//import org.mushroom.controller.requests.update.UserUpdateRequest;
//import org.mushroom.model.User;
//import org.mushroom.repository.UserRepository;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class UserUpdateConverter extends UserBaseConverter<UserUpdateRequest, User> {
//
//    private final UserRepository repository;
//
//    @Override
//    public User convert(UserUpdateRequest source) {
//
//        Optional<User> user = repository.findById(source.getId());
//        return doConvert(user.orElseThrow(EntityNotFoundException::new), source);
//    }
//}
