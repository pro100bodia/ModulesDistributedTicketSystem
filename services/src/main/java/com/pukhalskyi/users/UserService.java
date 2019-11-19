package com.pukhalskyi.users;

public class UserService<T extends Iterable> {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    T findAll() {
        return (T) userRepository.findAll();
    }
}
