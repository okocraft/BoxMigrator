package net.okocraft.boxmigrator.table;

import net.okocraft.box.api.BoxProvider;
import net.okocraft.box.api.model.user.BoxUser;
import net.okocraft.boxmigrator.database.Database;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/*
 * source:
 * https://github.com/okocraft/Box/blob/v3/master/src/main/java/net/okocraft/box/database/PlayerTable.java
 */
public class PlayerTable {

    private final Database database;

    public PlayerTable(@NotNull Database database) {
        this.database = database;
    }

    public @NotNull Map<BoxUser, Integer> load() {
        var userIdMap = new HashMap<BoxUser, Integer>();

        database.execute("SELECT id, uuid, name FROM box_players", rs -> {
            while (rs.next()) {
                var id = rs.getInt("id");

                var strUuid = rs.getString("uuid");
                UUID uuid;

                try {
                    uuid = UUID.fromString(strUuid);
                } catch (IllegalArgumentException e) {
                    BoxProvider.get().getLogger().warning("Could not parse a string to uuid: " + strUuid);
                    continue;
                }

                var name = rs.getString("name");

                var user = new MigratedBoxUser(uuid, name);

                if (Bukkit.getPlayer(uuid) == null) {
                    try {
                        BoxProvider.get().getUserManager().saveUserIfNotExists(user).join();
                    } catch (Exception ignored) {
                    }
                }

                userIdMap.put(user, id);
            }
        });

        return userIdMap;
    }

    private record MigratedBoxUser(@NotNull UUID uuid, @NotNull String name) implements BoxUser {

        @Override
        public @NotNull UUID getUUID() {
            return uuid;
        }

        @Override
        public @NotNull Optional<String> getName() {
            return Optional.of(name);
        }
    }
}
