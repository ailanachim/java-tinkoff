package edu.hw8;

import edu.hw8.task3.ParallelPasswordHacker;
import edu.hw8.task3.PasswordHacker;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    void test() throws NoSuchAlgorithmException {
        String[] data = new String[] {
            "a.v.petrov  cd0acfe085eeb0f874391fb9b8009bed",
            "v.v.belov  81dc9bdb52d04dc20036dbd8313ed055",
            "a.s.ivanov  367f7790e1588cc53aed634c1e9df2d7",
            "k.p.maslov  1a1dc91c907325c69271ddf0c944bc72",
        };

        var hacker = new PasswordHacker(data);
        var passwords = hacker.getUserPasswords();

        assertThat(passwords.get("a.v.petrov")).isEqualTo("pas");
        assertThat(passwords.get("v.v.belov")).isEqualTo("1234");
        assertThat(passwords.get("a.s.ivanov")).isEqualTo("asi");
        assertThat(passwords.get("k.p.maslov")).isEqualTo("pass");
    }

    @Test
    void multiThreadTest() throws NoSuchAlgorithmException {
        String[] data = new String[] {
            "a.v.petrov  cd0acfe085eeb0f874391fb9b8009bed",
            "v.v.belov  81dc9bdb52d04dc20036dbd8313ed055",
            "a.s.ivanov  367f7790e1588cc53aed634c1e9df2d7",
            "k.p.maslov  1a1dc91c907325c69271ddf0c944bc72",
        };

        var hacker = new ParallelPasswordHacker(data);
        var passwords = hacker.getUserPasswords();

        assertThat(passwords.get("a.v.petrov")).isEqualTo("pas");
        assertThat(passwords.get("v.v.belov")).isEqualTo("1234");
        assertThat(passwords.get("a.s.ivanov")).isEqualTo("asi");
        assertThat(passwords.get("k.p.maslov")).isEqualTo("pass");
    }
}
