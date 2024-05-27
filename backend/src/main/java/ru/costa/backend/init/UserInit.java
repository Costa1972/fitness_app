package ru.costa.backend.init;

import org.springframework.stereotype.Component;
import ru.costa.backend.entities.Address;
import ru.costa.backend.entities.Passport;
import ru.costa.backend.entities.Role;
import ru.costa.backend.entities.User;
import ru.costa.backend.repositories.AddressRepository;
import ru.costa.backend.repositories.PassportRepository;
import ru.costa.backend.repositories.RoleRepository;
import ru.costa.backend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Constantin Vashchuk
 */

@Component
public class UserInit {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final PassportRepository passportRepository;

    public UserInit(UserRepository userRepository,
                    RoleRepository roleRepository,
                    AddressRepository addressRepository,
                    PassportRepository passportRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.passportRepository = passportRepository;

        Role admin = roleChecker("ROLE_ADMIN", roleRepository);
        Role user = roleChecker("ROLE_USER", roleRepository);
        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(user);

        Address address1 = new Address(1L, "Moscow", 111234, "Moscow", "Okhotnyi Ryad", 23, 1, 234 );
        Address address2 = new Address(2L, "Pskov", 443897, "Pskov", "Parkovaya", 2, 1, 23 );

        Passport passport1 = new Passport(1L, "4567", "324567", "23.09.1978" );
        Passport passport2 = new Passport(2L, "4523", "543834", "12.05.1980" );

        passportRepository.save(passport1);
        passportRepository.save(passport2);

        addressRepository.save(address1);
        addressRepository.save(address2);

        User user1 = new User(0L, "admin@mail.ru", "$2y$16$zpOl9KZq08ikB2PpuJfKWOFNsiSdCI554Qg21AdPiv/GxBtXlaWcS", "Anton", "Antonov", "anton@mail.ru","+74565436783", 18, User.Sex.MALE, Set.of(admin), passport1, address1);
        User user2 = new User(0L, "user@mail.ru", "$2y$16$mBLH4/eskk4Js31xTZuU4e7Dl4n/efCneN/rcCNTluVaauAUykg/2", "Oleg", "Smirnov", "oleg@mail.ru", "+74533245643", 54, User.Sex.MALE, Set.of(user), passport2, address2);

        userRepository.save(user1);
        userRepository.save(user2);
    }

    private Role roleChecker(String roleName, RoleRepository roleRepository) {
        return roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(Role
                .builder().name(roleName).build()));
    }
}
