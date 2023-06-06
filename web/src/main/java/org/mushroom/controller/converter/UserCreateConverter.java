//package org.mushroom.controller.converter;
//
//import lombok.RequiredArgsConstructor;
//import org.mushroom.controller.requests.create.UserCreateRequest;
//import org.mushroom.model.User;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, User> {
//
////    private final UserFieldsGenerator fieldsGenerator;
//
////    private final JWTConfiguration configuration;
//
////    private final PasswordEncoder encoder;
//
//    @Override
//    public User convert(UserCreateRequest request) {
//
//        User user = new User();
//
////        String generateEmail = fieldsGenerator.generateEmail(user);
////        String generatePassword = fieldsGenerator.generatePassword();
//
////        String resultPassword = generatePassword + configuration.getServerPasswordSalt();
////        String encode = encoder.encode(resultPassword);
//
////        AuthenticationInfo info = new AuthenticationInfo(generateEmail, encode);
//
////        user.setAuthenticationInfo(info);
//
//        return doConvert(user, request);
//    }
//}
