package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component

public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com",

                "password" , "jim",
                "Jimmerson", true,
                "jim");
        user.setRoles(Arrays.asList(userRole));
        userService.saveUser(user);

        User admin = new User("admin@admin.com",
                "password", "Admin",
                "User", true,
                "admin");
        user.setRoles(Arrays.asList(adminRole));
        user.setUserimg("https://res.cloudinary.com/duqszimh4/image/upload/w_1000,c_fill,ar_1:1,g_auto,r_max,bo_5px_solid_red,b_rgb:262c35/v1564365436/_1__ek5gni.jpg");
        userService.saveAdmin(admin);


      Message message= new Message("Hello",
                                   "Welcome Message",
                                    LocalDateTime.of(2019,07,20,05,11),
                                    "https://res.cloudinary.com/duqszimh4/image/upload/v1564410258/av16p3brupjmcofhud1h.jpg",
                                    admin);
       messageRepository.save(message);
}
}