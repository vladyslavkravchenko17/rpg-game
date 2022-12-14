package rpg.game.main;

import rpg.game.entity.Entity;

import java.util.ArrayList;

public class CollisionChecker {
    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                entity.collisionOn = checkTileByDirection(entityLeftCol, entityTopRow, entityRightCol, entityTopRow);
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                entity.collisionOn = checkTileByDirection(entityLeftCol, entityBottomRow, entityRightCol, entityBottomRow);
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                entity.collisionOn = checkTileByDirection(entityLeftCol, entityTopRow, entityLeftCol, entityBottomRow);
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                entity.collisionOn = checkTileByDirection(entityRightCol, entityTopRow, entityRightCol, entityBottomRow);
                break;
            case UP_LEFT:
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                if (checkTileByDirection(entityLeftCol, entityTopRow, entityRightCol, entityTopRow) ||
                        checkTileByDirection(entityLeftCol, entityTopRow, entityLeftCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
            case UP_RIGHT:
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                if (checkTileByDirection(entityLeftCol, entityTopRow, entityRightCol, entityTopRow) ||
                        checkTileByDirection(entityRightCol, entityTopRow, entityRightCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN_LEFT:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                if (checkTileByDirection(entityLeftCol, entityBottomRow, entityRightCol, entityBottomRow) ||
                        checkTileByDirection(entityLeftCol, entityTopRow, entityLeftCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN_RIGHT:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                if (checkTileByDirection(entityLeftCol, entityBottomRow, entityRightCol, entityBottomRow) ||
                        checkTileByDirection(entityRightCol, entityTopRow, entityRightCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    private boolean checkTileByDirection(int x, int y, int x1, int y1) {
        boolean collision = false;
        int tileNum1 = gamePanel.tileManager.tilesMap[x][y];
        int tileNum2 = gamePanel.tileManager.tilesMap[x1][y1];
        if (gamePanel.tileManager.tiles[tileNum1].isCollision() ||
                gamePanel.tileManager.tiles[tileNum2].isCollision()) {
            collision = true;
        }

        return collision;
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (Entity object : gamePanel.objects) {
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            object.solidArea.x = object.worldX + object.solidArea.x;
            object.solidArea.y = object.worldY + object.solidArea.y;

            switch (entity.direction) {
                case UP:
                    entity.solidArea.y -= entity.speed;
                    break;
                case DOWN:
                    entity.solidArea.y += entity.speed;
                    break;
                case LEFT:
                    entity.solidArea.x -= entity.speed;
                    break;
                case RIGHT:
                    entity.solidArea.x += entity.speed;
                    break;
                case UP_LEFT:
                    entity.solidArea.y -= entity.speed;
                    entity.solidArea.x -= entity.speed;
                    break;
                case UP_RIGHT:
                    entity.solidArea.y -= entity.speed;
                    entity.solidArea.x += entity.speed;
                    break;
                case DOWN_LEFT:
                    entity.solidArea.y += entity.speed;
                    entity.solidArea.x -= entity.speed;
                    break;
                case DOWN_RIGHT:
                    entity.solidArea.y += entity.speed;
                    entity.solidArea.x += entity.speed;
                    break;
            }
            if (checkObjectByDirection(entity, object, player)) {
                index = gamePanel.objects.indexOf(object);
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            object.solidArea.x = object.solidAreaDefaultX;
            object.solidArea.y = object.solidAreaDefaultY;
        }
        return index;
    }

    private boolean checkObjectByDirection(Entity entity, Entity object, boolean player) {
        if (entity.solidArea.intersects(object.solidArea)) {
            if (object.collision) {
                entity.collisionOn = true;
            }
            if (player) {
                return true;
            }
        }
        return false;
    }

    public int checkEntity(Entity entity, ArrayList<Entity> targets) {
        int index = 999;
        for (Entity target : targets) {
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            target.solidArea.x = target.worldX + target.solidArea.x;
            target.solidArea.y = target.worldY + target.solidArea.y;

                switch (entity.direction) {
                    case UP:
                        entity.solidArea.y -= entity.speed;
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.speed;
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        break;
                    case UP_LEFT:
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                    case UP_RIGHT:
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        break;
                    case DOWN_LEFT:
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        break;
                    case DOWN_RIGHT:
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        break;
                }
            if (entity.solidArea.intersects(target.solidArea) && target != entity) {
                entity.collisionOn = true;
                index = targets.indexOf(target);
            }
                entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            target.solidArea.x = target.solidAreaDefaultX;
            target.solidArea.y = target.solidAreaDefaultY;
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch (entity.direction) {
            case UP:
                entity.solidArea.y -= entity.speed;
                break;
            case DOWN:
                entity.solidArea.y += entity.speed;
                break;
            case LEFT:
                entity.solidArea.x -= entity.speed;
                break;
            case RIGHT:
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
