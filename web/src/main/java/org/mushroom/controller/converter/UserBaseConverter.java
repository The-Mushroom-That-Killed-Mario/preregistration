//package org.mushroom.controller.converter;
//
//;
//import org.mushroom.controller.requests.create.UserCreateRequest;
//import org.mushroom.model.User;
//import org.springframework.core.convert.converter.Converter;
//
//import java.sql.Timestamp;
//
//public abstract class UserBaseConverter<S, T> implements Converter<S, T> {
//
//    public User doConvert(User user, UserCreateRequest request) {
//
//        user.setName(request.getName());
//        user.setSurname(request.getSurname());
//        user.setLogin(request.getLogin());
//        user.setPhoneNumber(request.getPhoneNumber());
//
//        return user;
//    }
//
//}
