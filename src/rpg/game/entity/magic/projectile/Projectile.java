package rpg.game.entity.magic.projectile;

import rpg.game.Direction;
import rpg.game.entity.Entity;
import rpg.game.entity.magic.Magic;
import rpg.game.main.GamePanel;

public class Projectile extends Magic {
    public int manaCost;
    Entity user;
    public int damage;
    private int shotAvailableCounter = 0;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    public void set(int worldX, int worldY, Direction direction, Entity user) {
        if (user.mana > 0) {
            this.worldX = worldX;
            this.worldY = worldY;
            this.direction = direction;
            this.user = user;
            alive = true;
            hp = maxHp;
            spriteCounter = 0;
            user.mana -= manaCost;
        }
    }

    @Override
    public void update() {
        if (user == gamePanel.player) {
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
            if (monsterIndex != 999) {
                damageMonster(monsterIndex);
                alive = false;
            }
        } else {
            if (gamePanel.collisionChecker.checkPlayer(this)) {
                damagePlayer();
                alive = false;
            }
        }
        gamePanel.collisionChecker.checkTile(this);
        if (collisionOn) {
            alive = false;
        }


        switch (direction) {
            case RIGHT, UP_RIGHT, DOWN_RIGHT -> worldX += speed;
            case LEFT, UP_LEFT, DOWN_LEFT -> worldX -= speed;
            case UP -> worldY -= speed;
            case DOWN -> worldY += speed;
        }
        hp--;
        if (hp <= 0) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter <= 2) {
            spriteNumber = 0;
        }
        else if (spriteCounter <= 4) {
            spriteNumber = 1;
        }
        else if (spriteCounter >= 6){
            spriteNumber = 3;
        }
    }

    public void damageMonster(int index) {
        Entity monster = gamePanel.monsters.get(index);
        if (!monster.invincible) {
            monster.hp -= damage;
            monster.hpBarOn = true;
            monster.invincible = true;
            if (monster.hp <= 0) {
                gamePanel.monsters.remove(monster);
            }
        }
    }

    public void damagePlayer() {
        if (!gamePanel.player.invincible) {
            gamePanel.player.hp -= damage;
            gamePanel.player.invincible = true;
        }
    }

}
