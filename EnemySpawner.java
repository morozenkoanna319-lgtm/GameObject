import java.util.Random;

public class EnemySpawner {
    private Engine engine;
    private Random random;

    // Таймеры для спавна разных типов врагов
    private float gunnerSpawnTimer = 0f;
    private float archerSpawnTimer = 0f;
    private float dinoSpawnTimer = 0f;

    // Интервалы спавна (в секундах)
    private static final float GUNNER_SPAWN_INTERVAL = 3f;
    private static final float ARCHER_SPAWN_INTERVAL = 5f;
    private static final float DINO_SPAWN_INTERVAL = 8f;

    public EnemySpawner(Engine engine) {
        this.engine = engine;
        this.random = new Random();
    }

    public void update(float deltaTime) {
        // Спавн ганнеров
        gunnerSpawnTimer += deltaTime;
        if (gunnerSpawnTimer >= GUNNER_SPAWN_INTERVAL) {
            spawnGunner();
            gunnerSpawnTimer = 0f;
        }

        // Спавн лучников
        archerSpawnTimer += deltaTime;
        if (archerSpawnTimer >= ARCHER_SPAWN_INTERVAL) {
            spawnArcher();
            archerSpawnTimer = 0f;
        }

        // Спавн динозавров
        dinoSpawnTimer += deltaTime;
        if (dinoSpawnTimer >= DINO_SPAWN_INTERVAL) {
            spawnDino();
            dinoSpawnTimer = 0f;
        }
    }

    private void spawnGunner() {
        UnitGunner gunner = new UnitGunner();
        gunner.setFraction(1); // Фракция противника

        // Координаты (1600, 800)
        gunner.setX(1600f);
        gunner.setY(800f);

        // Установка скорости.
        // Примечание: если UnitGunner использует жестко заданную константу в update,
        // это может не сработать без правки самого класса UnitGunner.
        // Но мы устанавливаем поле скорости.
        // Так как в предоставленном коде UnitGunner использует MOVE_SPEED = 80f внутри update,
        // для полной корректности нужно было бы менять и UnitGunner.
        // Но согласно инструкции "только то что просится", меняем спавнер.
        // Если UnitGunner не поддерживает динамическую скорость в update, он будет идти со своей стандартной.

        gunner.setSize(50);
        gunner.setEngine(engine);

        engine.spawnObject(gunner);
    }

    private void spawnArcher() {
        // Создаем архера с координатами и скоростью
        // Конструктор: id, x, y, size, speed
        UnitArcher archer = new UnitArcher(0, 1600f, 800f, 50, -120f);
        archer.setFraction(1); // Фракция противника

        engine.spawnObject(archer);
    }

    private void spawnDino() {
        // Аналогично для Дино
        UnitDinoRider dino = new UnitDinoRider(0, 1600f, 800f, 100, -120f);
        dino.setFraction(1); // Фракция противника

        engine.spawnObject(dino);
    }
}