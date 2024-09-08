import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

        @Test
        public void shouldRegisterPlayer() {
            Game game = new Game();
            Player player = new Player(3, "Ivanov", 50);
            game.register(player);

            boolean expected = true;
            boolean actual = game.isRegistered(player);

            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void shouldRoundWithRegisteredPlayers() throws NotRegistredException {
            Game game = new Game();
            Player player1 = new Player(3, "Ivanov", 80);
            Player player2 = new Player(5, "Petrov", 25);
            game.register(player1);
            game.register(player2);

            game.round(player1.getName(), player2.getName());

            int expected = 1;
            int actual = game.round(player1.getName(), player2.getName());
            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void shouldRoundIfSecondPlayerStronger() throws NotRegistredException {
            Game game = new Game();
            Player player1 = new Player(7, "Ivanov", 25);
            Player player2 = new Player(10, "Petrov", 80);
            game.register(player1);
            game.register(player2);

            game.round(player2.getName(), player2.getName());

            int expected = 2;
            int actual = game.round(player1.getName(), player2.getName());
            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void shouldRoundWithUnRegisteredPlayers() {
            Game game = new Game();
            Player player1 = new Player(7, "Ivanov", 199);
            Player player2 = new Player(10, "Petrov", 150);
            Exception exception = assertThrows(NotRegistredException.class, () -> {
                game.round("Неизвестный игрок", "Неизвестный игрок");
            });

            String expected = "Один из игроков не зарегистрирован";
            String actial = exception.getMessage();
            Assertions.assertEquals(expected, actial);
        }

        @Test
        public void shouldRoundWithUnRegisteredPlayer1() {
            Game game = new Game();
            Player player1 = new Player(3, "Ivanov", 100);
            Player player2 = new Player(5, "Petrov", 99);
            game.register(player2);
            Exception exception = assertThrows(NotRegistredException.class, () -> {
                game.round("Неизвестный игрок", "Ivanov");
            });

            String expected = "Один из игроков не зарегистрирован";
            String actial = exception.getMessage();
            Assertions.assertEquals(expected, actial);
        }

        @Test
        public void shouldRoundWithUnRegisteredPlayer2() {
            Game game = new Game();
            Player player1 = new Player(7, "Sidorov", 100);
            Player player2 = new Player(3, "Ivanov", 99);
            game.register(player1);
            Exception exception = assertThrows(NotRegistredException.class, () -> {
                game.round("Sidorov", "Неизвестный игрок");
            });

            String expected = "Один из игроков не зарегистрирован";
            String actial = exception.getMessage();
            Assertions.assertEquals(expected, actial);
        }

        @Test
        public void shouldRoundWithTheSamePlayersStats() throws NotRegistredException {
            Game game = new Game();
            Player player1 = new Player(3, "Ivanov", 100);
            Player player2 = new Player(7, "Sidorov", 100);

            game.register(player1);
            game.register(player2);

            int excepted = 0;
            int actual = game.round(player1.getName(), player2.getName());

            Assertions.assertEquals(excepted, actual);
        }
    }
