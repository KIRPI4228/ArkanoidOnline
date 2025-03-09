package ru.arkanoid.gamebackend.engine;

import lombok.Getter;
import ru.arkanoid.gamebackend.engine.primitives.Collider;
import ru.arkanoid.gamebackend.engine.primitives.GameObject;
import ru.arkanoid.gamebackend.helpers.CollectionsHelper;
import ru.arkanoid.gamebackend.update.Updater;
import ru.arkanoid.gamebackend.update.scheduler.Scheduler;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private final Set<GameObject> gameObjects = CollectionsHelper.createConcurrentSet();

    @Getter
    private final Updater updater;
    @Getter
    private final Scheduler scheduler;

    @Getter
    private final float width = 12;
    @Getter
    private final float height = 10;


    public Room(Updater updater, Scheduler scheduler) {
        insert(new RoomBorder(this, new Vector(0, height / 2, 0), new Scale(width+4, 1)));
        insert(new RoomBorder(this, new Vector(-(width / 2), 0, 0), new Scale(1, height+4)));
        insert(new RoomBorder(this, new Vector(0, -(height / 2), 0), new Scale(width+4, 1)));
        insert(new RoomBorder(this, new Vector(width / 2, 0, 0), new Scale(1, height+4)));

        this.updater = updater;
        this.scheduler = scheduler;

        this.updater.addExecutor(this, this::update);
    }

    public void insert(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObjects.stream().filter(reference -> gameObject.equals(reference)).findFirst().orElse(null));
    }

    public void exit() {
        updater.removeExecutor(this);
    }

    public GameObject findGameObjectFromPoint(Vector point) {
        return gameObjects.stream()
                .filter(gameObject -> gameObject.matchPosition(point))
                .findFirst()
                .orElse(null);
    }

    public Set<CollisionOptions> findCollidedGameObjects(Collider target) {
        return gameObjects.stream()
                .filter(gameObject ->  {
                    var colliding = gameObject;
                    return !target.equals(colliding) && target.filter(colliding);
                })
                .map(gameObject -> (gameObject instanceof Collider colliding) ? target.doesCollide(colliding) : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    protected void update() {
        gameObjects.stream().forEach(GameObject::onUpdate);
    }
}
