package main.java.game.entity;

import main.java.game.controls.KeyHandler;
import main.java.game.entity.magic.projectile.Fireball;
import main.java.game.item.armor.bandit_set.BanditBoots;
import main.java.game.item.armor.bandit_set.BanditBreastplate;
import main.java.game.item.armor.bandit_set.BanditGloves;
import main.java.game.item.armor.bandit_set.BanditHelmet;
import main.java.game.item.armor.drain_set.DrainBoots;
import main.java.game.item.armor.drain_set.DrainBreastplate;
import main.java.game.item.armor.drain_set.DrainGloves;
import main.java.game.item.armor.drain_set.DrainHelmet;
import main.java.game.item.armor.necklace.HealthNecklace;
import main.java.game.item.armor.necklace.HelloKittyNecklace;
import main.java.game.item.armor.ring.DrainRing;
import main.java.game.item.armor.ring.RingOfStrength;
import main.java.game.item.potion.LowHealthPotion;
import main.java.game.item.potion.UltraMonsterWhite;
import main.java.game.item.weapon.BadSword;
import main.java.game.item.weapon.DrainBat;
import main.java.game.item.weapon.Hand;
import main.java.game.main.GamePanel;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public BufferedImage hUp0, hUp1, hUp2, hDown0, hDown1, hDown2, hLeft0, hLeft1, hLeft2, hRight0, hRight1, hRight2;
    public BufferedImage eUp0, eUp1, eUp2, eDown0, eDown1, eDown2, eLeft0, eLeft1, eLeft2, eRight0, eRight1, eRight2;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        setSolidArea();
        setDefaultValues();
        setDefaultPosition();
        setImages("src/main/resources/player/move/body/");
        setHairImages("src/main/resources/player/move/hair/");
        setEyesImages("src/main/resources/player/move/eyes/");
        setAttackImages("src/main/resources/player/attack/");
    }

    public void setDefaultValues() {
        direction = Direction.DOWN;
        type = EntityType.PLAYER;

        lvl = 1;
        exp = 0;
        nextLvlExp = 5;
        agility = 5;
        body = 1;
        strength = 5;
        vitality = 20;
        maxHp = vitality;
        hp = maxHp;
        intelligence = 20;
        maxMana = intelligence;
        mana = maxMana;
        money = 15;

        weapon = new Hand(gamePanel);
        magicSpell = new Fireball(gamePanel);
        inventory[0] = new BanditBreastplate(gamePanel);
        inventory[1] = new BanditBreastplate(gamePanel);
        inventory[2] = new BanditBoots(gamePanel);
        inventory[3] = new LowHealthPotion(gamePanel);
        inventory[4] = new LowHealthPotion(gamePanel);
        inventory[5] = new RingOfStrength(gamePanel);
        inventory[6] = new HealthNecklace(gamePanel);
        inventory[7] = new BanditGloves(gamePanel);
        inventory[8] = new BanditHelmet(gamePanel);
        inventory[9] = new BadSword(gamePanel);
        inventory[10] = new DrainBoots(gamePanel);
        inventory[11] = new DrainGloves(gamePanel);
        inventory[12] = new DrainBreastplate(gamePanel);
        inventory[13] = new DrainRing(gamePanel);
        inventory[14] = new HelloKittyNecklace(gamePanel);
        inventory[15] = new DrainHelmet(gamePanel);
        inventory[16] = new DrainBat(gamePanel);
        inventory[17] = new UltraMonsterWhite(gamePanel);
        inventory[18] = new UltraMonsterWhite(gamePanel);


        calculateStats();
    }

    public void setDefaultPosition() {
        worldX = gamePanel.tileSize * 25;
        worldY = gamePanel.tileSize * 40;
    }

    public void restoreHP() {
        hp = maxHp;
        invincible = false;
    }


    public void update() {
        if (attacking) {
            weapon.setDirectionByMouse();
            weapon.attackDirection = direction;
            attack();
        }
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed ||
                keyHandler.rightPressed || keyHandler.fPressed) {
            boolean goUp = false, goDown = false, goLeft = false, goRight = false;
            if (keyHandler.upPressed && keyHandler.leftPressed) {
                direction = Direction.UP_LEFT;
                goUp = true;
                goLeft = true;
            } else if (keyHandler.upPressed && keyHandler.rightPressed) {
                direction = Direction.UP_RIGHT;
                goUp = true;
                goRight = true;
            } else if (keyHandler.downPressed && keyHandler.leftPressed) {
                direction = Direction.DOWN_LEFT;
                goDown = true;
                goLeft = true;
            } else if (keyHandler.downPressed && keyHandler.rightPressed) {
                direction = Direction.DOWN_RIGHT;
                goDown = true;
                goRight = true;
            } else if (keyHandler.upPressed) {
                direction = Direction.UP;
                goUp = true;
            } else if (keyHandler.downPressed) {
                direction = Direction.DOWN;
                goDown = true;
            } else if (keyHandler.rightPressed) {
                direction = Direction.RIGHT;
                goRight = true;
            } else if (keyHandler.leftPressed) {
                direction = Direction.LEFT;
                goLeft = true;
            }
            checkCollision();
            if (weapon.attackDirection != null) {
                direction = weapon.attackDirection;
            }

            if (!collisionOn && !keyHandler.fPressed) {
                if (goUp) worldY -= speed;
                if (goDown) worldY += speed;
                if (goLeft) worldX -= speed;
                if (goRight) worldX += speed;
            }
            gamePanel.keyHandler.fPressed = false;

            if (!attacking) {
                updateSpriteNumber();
            }
        } else if (!attacking) {
            spriteNumber = 0;
        }

        if (casting && !magicSpell.alive) {
            casting = false;
            magicSpell.set(worldX, worldY, direction, this);

            gamePanel.projectiles.add(magicSpell);
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (hp <= 0) {
            gamePanel.gameState = gamePanel.gameOverState;
        }
        checkLevel();
    }

    public void checkLevel() {
        if (exp >= nextLvlExp) {
            lvl++;
            int i = 5;
            if (lvl <= 10) {
                i += 5;
                nextLvlExp += i * lvl;
            }
            else if (lvl <= 20) {
                i += 4;
                nextLvlExp += i * lvl;
            }
            else if (lvl <= 40) {
                i += 3;
                nextLvlExp += i * lvl;
            }
            else if (lvl <= 60) {
                i += 2;
                nextLvlExp += i * lvl;
            }
            else {
                i += 1;
                nextLvlExp += i * lvl;
            }

        }
    }

    private void updateSpriteNumber() {
        spriteCounter++;
        if (spriteCounter > animationSpeed) {
            switch (spriteNumber) {
                case 0 -> spriteNumber = 1;
                case 1 -> spriteNumber = 2;
                case 2 -> spriteNumber = 3;
                case 3 -> spriteNumber = 0;
            }
            spriteCounter = 0;
        } else {
//            spriteNumber = 0;
        }
    }

    private void checkCollision() {
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
        pickUpObject(objectIndex);

        int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        interactNPC(npcIndex);

        int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
        contactMonster(monsterIndex);

        gamePanel.eventHandler.checkEvent();
    }

    private void attack() {
        spriteCounter++;
        if (spriteCounter <= 3) {
            spriteNumber = 0;
        } else if (spriteCounter <= 6) {
            spriteNumber = 1;
        } else if (spriteCounter == 16) {
            gamePanel.sound.playSound(4);
        }
        else if (spriteCounter <= 16) {
            spriteNumber = 2;

            switch (direction) {
                case UP -> {
                    weapon.worldY = worldY - weapon.solidArea.height;
                    weapon.worldX = worldX + (gamePanel.tileSize - weapon.solidArea.width) / 2;
                }
                case DOWN -> {
                    weapon.worldY = worldY + solidArea.y + solidArea.height;
                    weapon.worldX = worldX + (gamePanel.tileSize - weapon.solidArea.width) / 2;
                }
                case LEFT, UP_LEFT, DOWN_LEFT -> {
                    weapon.worldX = worldX - weapon.solidArea.width + (gamePanel.tileSize - solidArea.width) / 2;
                    weapon.worldY = worldY + (gamePanel.tileSize - weapon.solidArea.height) / 2;
                }
                case RIGHT, UP_RIGHT, DOWN_RIGHT -> {
                    weapon.worldX = worldX + solidArea.x + solidArea.width;
                    weapon.worldY = worldY + (gamePanel.tileSize - weapon.solidArea.height) / 2;
                }
            }

            int monsterIndex = gamePanel.collisionChecker.checkEntity(weapon, gamePanel.monsters);
            damageMonster(monsterIndex);



        } else if (spriteCounter <= 19) {
            spriteNumber = 1;
        } else if (spriteCounter <= 22) {
            spriteNumber = 0;
            spriteCounter = 0;
            weapon.attackDirection = null;
            attacking = false;
        }
    }

    public void drawHitBox(Graphics2D g2D) {
        g2D.setColor(new Color(100, 0, 0));
        g2D.fillRect(worldX, worldY, solidArea.width, solidArea.height);
    }

    private void damageMonster(int i) {
        if (i != 999) {
            if (!gamePanel.monsters.get(i).invincible) {
                gamePanel.monsters.get(i).invincible = true;
                gamePanel.monsters.get(i).hp -= weapon.damage + damage;

                if (gamePanel.monsters.get(i).hp <= 0) {
                    gamePanel.player.exp += gamePanel.monsters.get(i).exp;
                    gamePanel.monsters.remove(i);
                }
            }
        }
    }

    private void contactMonster(int i) {
        if (i != 999) {
            if (!invincible && !gamePanel.keyHandler.godMode) {
                hp -= 15 - defense;
                invincible = true;
            }
        }
    }

    private void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    private void interactNPC(int i) {
        if (i != 999) {
            if (gamePanel.keyHandler.fPressed) {
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc.get(i).speak();
            }
        }
        gamePanel.keyHandler.fPressed = false;
    }

    public void draw(Graphics2D g2D) {
        if (attacking) {
            setAttackImageByDirection();
            setWeaponAttackImage();
            g2D.drawImage(weaponImage, screenX, screenY, null);
        } else {
            setImageByDirection();
        }
        if (invincible) {
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
        }
        g2D.drawImage(image, screenX, screenY, null);
        g2D.drawImage(eyesImage, screenX, screenY, null);
        g2D.drawImage(hairImage, screenX, screenY, null);
        if (breastplate != null) {
            g2D.drawImage(breastplateImage, screenX, screenY, null);
        }
        if (boots != null) {
            g2D.drawImage(bootsImage, screenX, screenY, null);
        }
//        if (gloves != null) {
//            g2D.drawImage(glovesImage, screenX, screenY, null);
//        }
//        if (helmet != null) {
//            g2D.drawImage(helmetImage, screenX, screenY, null);
//        }

        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
        int screenX = weapon.worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = weapon.worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        g2D.setColor(Color.RED);
        g2D.fillRect(screenX, screenY, weapon.solidArea.width, weapon.solidArea.height);

        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
    }

    private void setWeaponAttackImage() {
        switch (direction) {
//            case UP:
//                switch (spriteNumber) {
//                    case 0 -> weaponImage = up0;
//                    case 1 -> weaponImage = up1;
//                    case 2 -> weaponImage = up2;
//                }
//                break;
//            case DOWN:
//                switch (spriteNumber) {
//                    case 0 -> weaponImage = down0;
//                    case 1 -> weaponImage = down1;
//                    case 2 -> weaponImage = down2;
//                }
//                break;
//            case LEFT, UP_LEFT, DOWN_LEFT:
//                switch (spriteNumber) {
//                    case 0 -> weaponImage = left0;
//                    case 1 -> weaponImage = left1;
//                    case 2 -> weaponImage = left2;
//                }
//                break;
            case RIGHT, UP_RIGHT, DOWN_RIGHT:
                switch (spriteNumber) {
                    case 0 -> weaponImage = weapon.aRight0;
                    case 1 -> weaponImage = weapon.aRight1;
                    case 2 -> weaponImage = weapon.aRight2;
                }
        }
    }

    private void setImageByDirection() {
        switch (direction) {
            case UP:
                switch (spriteNumber) {
                    case 0, 2 -> {
                        image = up0;
                        hairImage = hUp0;
                        if (breastplate != null) {
                            breastplateImage = breastplate.up0;
                        }
                        if (boots != null) {
                            bootsImage = boots.up0;
                        }
                    }
                    case 1 -> {
                        image = up1;
                        hairImage = hUp1;
                        if (breastplate != null) {
                            breastplateImage = breastplate.up1;
                        }
                        if (boots != null) {
                            bootsImage = boots.up1;
                        }
                    }
                    case 3 -> {
                        image = up2;
                        hairImage = hUp2;
                        if (breastplate != null) {
                            breastplateImage = breastplate.up2;
                        }
                        if (boots != null) {
                            bootsImage = boots.up2;
                        }
                    }
                }
                break;
            case DOWN:
                switch (spriteNumber) {
                    case 0, 2 -> {
                        image = down0;
                        hairImage = hDown0;
                        eyesImage = eDown0;
                        if (breastplate != null) {
                            breastplateImage = breastplate.down0;
                        }
                        if (boots != null) {
                            bootsImage = boots.down0;
                        }
                    }
                    case 1 -> {
                        image = down1;
                        hairImage = hDown1;
                        eyesImage = eDown1;
                        if (breastplate != null) {
                            breastplateImage = breastplate.down1;
                        }
                        if (boots != null) {
                            bootsImage = boots.down1;
                        }
                    }
                    case 3 -> {
                        image = down2;
                        hairImage = hDown2;
                        eyesImage = eDown2;
                        if (breastplate != null) {
                            breastplateImage = breastplate.down2;
                        }
                        if (boots != null) {
                            bootsImage = boots.down2;
                        }
                    }
                }
                break;
            case LEFT, UP_LEFT, DOWN_LEFT:
                switch (spriteNumber) {
                    case 0, 2 -> {
                        image = left0;
                        hairImage = hLeft0;
                        eyesImage = eLeft0;
                        if (breastplate != null) {
                            breastplateImage = breastplate.left0;
                        }
                        if (boots != null) {
                            bootsImage = boots.left0;
                        }
                    }
                    case 1 -> {
                        image = left1;
                        hairImage = hLeft1;
                        eyesImage = eLeft1;
                        if (breastplate != null) {
                            breastplateImage = breastplate.left1;
                        }
                        if (boots != null) {
                            bootsImage = boots.left1;
                        }
                    }
                    case 3 -> {
                        image = left2;
                        hairImage = hLeft2;
                        eyesImage = eLeft2;
                        if (breastplate != null) {
                            breastplateImage = breastplate.left2;
                        }
                        if (boots != null) {
                            bootsImage = boots.left2;
                        }
                    }
                }
                break;
            case RIGHT, UP_RIGHT, DOWN_RIGHT:
                switch (spriteNumber) {
                    case 0, 2 -> {
                        image = right0;
                        hairImage = hRight0;
                        eyesImage = eRight0;
                        if (breastplate != null) {
                            breastplateImage = breastplate.right0;
                        }
                        if (boots != null) {
                            bootsImage = boots.right0;
                        }
                    }
                    case 1 -> {
                        image = right1;
                        hairImage = hRight1;
                        eyesImage = eRight1;
                        if (breastplate != null) {
                            breastplateImage = breastplate.right1;
                        }
                        if (boots != null) {
                            bootsImage = boots.right1;
                        }
                    }
                    case 3 -> {
                        image = right2;
                        hairImage = hRight2;
                        eyesImage = eRight2;
                        if (breastplate != null) {
                            breastplateImage = breastplate.right2;
                        }
                        if (boots != null) {
                            bootsImage = boots.right2;
                        }
                    }
                }
        }
    }

    private void setAttackImageByDirection() {
        switch (direction) {
            case UP:
                switch (spriteNumber) {
                    case 0 -> image = aUp0;
                    case 1 -> image = aUp1;
                    case 2 -> image = aUp2;
                }
                break;
            case DOWN:
                switch (spriteNumber) {
                    case 0 -> image = aDown0;
                    case 1 -> image = aDown1;
                    case 2 -> image = aDown2;
                }
                break;
            case LEFT, UP_LEFT, DOWN_LEFT:
                switch (spriteNumber) {
                    case 0 -> image = aLeft0;
                    case 1 -> image = aLeft1;
                    case 2 -> image = aLeft2;
                }
                break;
            case RIGHT, UP_RIGHT, DOWN_RIGHT:
                switch (spriteNumber) {
                    case 0 -> image = aRight0;
                    case 1 -> image = aRight1;
                    case 2 -> image = aRight2;
                }
        }
    }

    private void setHairImages(String dir) {
        hUp0 = setImage("u0", dir);
        hUp1 = setImage("u1", dir);
        hUp2 = setImage("u2", dir);
        hDown0 = setImage("d0", dir);
        hDown1 = setImage("d1", dir);
        hDown2 = setImage("d2", dir);
        hLeft0 = setImage("l0", dir);
        hLeft1 = setImage("l1", dir);
        hLeft2 = setImage("l2", dir);
        hRight0 = setImage("r0", dir);
        hRight1 = setImage("r1", dir);
        hRight2 = setImage("r2", dir);
    }

    private void setEyesImages(String dir) {
        eUp0 = setImage("u0", dir);
        eUp1 = setImage("u1", dir);
        eUp2 = setImage("u2", dir);
        eDown0 = setImage("d0", dir);
        eDown1 = setImage("d1", dir);
        eDown2 = setImage("d2", dir);
        eLeft0 = setImage("l0", dir);
        eLeft1 = setImage("l1", dir);
        eLeft2 = setImage("l2", dir);
        eRight0 = setImage("r0", dir);
        eRight1 = setImage("r1", dir);
        eRight2 = setImage("r2", dir);
    }
}
