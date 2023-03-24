package main.java.game.UI;

import main.java.game.item.Item;
import main.java.game.main.GamePanel;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2D;
    public boolean messageOn = false;
    public String message;
    public String currentDialogue;
    public int commandNum = 0;
    public Font pixelFont;
    public int slotCol;
    public int slotRow;
    public InventorySlotType inventorySlotType;
    private final BasicStroke stroke = new BasicStroke(3);
    public int subState = 0;
    public boolean hasActiveSlot;
    public boolean dragItem;
    public Item draggingItem;
    public int draggingItemInventoryIndex;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            InputStream myStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/Fifaks10Dev1.ttf"));
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;
        g2D.setFont(pixelFont.deriveFont(Font.PLAIN, 28F));
        g2D.setColor(Color.white);
        if (gamePanel.gameState == gamePanel.titleState) {
            drawTitleScreen();
        } else if (gamePanel.gameState == gamePanel.playState) {
            drawHp();
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            drawHp();
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.dialogueState) {
            drawHp();
            drawDialogueScreen();
        } else if (gamePanel.gameState == gamePanel.inventoryState) {
            drawInventory();
        } else if (gamePanel.gameState == gamePanel.characterState) {
            drawCharacterWindow();
        } else if (gamePanel.gameState == gamePanel.optionsState) {
            drawOptionsScreen();
        } else if (gamePanel.gameState == gamePanel.gameOverState) {
            drawGameOverScreen();
        }
    }


    private void drawHp() {
        g2D.setColor(Color.WHITE);
        int x = gamePanel.tileSize;
        int y = gamePanel.tileSize / 4;
        int width = gamePanel.screenWidth / 4;
        int height = gamePanel.tileSize / 2;

        g2D.fillRect(x, y, width, height);
        g2D.setColor(Color.BLACK);
        g2D.fillRect(x + 3, y + 3, width - 6, height - 6);
        g2D.setColor(Color.RED);

        width = (int) Math.ceil(width * ((double) gamePanel.player.hp / gamePanel.player.maxHp));
        g2D.fillRect(x + 3, y + 3, width - 6, height - 6);

        width = gamePanel.screenWidth / 4;
        y += gamePanel.tileSize / 3 * 2;
        g2D.setColor(Color.WHITE);
        g2D.fillRect(x, y, width, height);
        g2D.setColor(Color.BLACK);
        g2D.fillRect(x + 3, y + 3, width - 6, height - 6);
        g2D.setColor(new Color(50, 0, 250));

        width = (int) Math.ceil(width * ((double) gamePanel.player.mana / gamePanel.player.maxMana));
        g2D.fillRect(x + 3, y + 3, width - 6, height - 6);
    }

    private void drawTitleScreen() {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        //TITLE
        g2D.setFont(pixelFont.deriveFont(Font.BOLD, 65F));
        String text = "Wise tree adventures";
        int x = getXForCenteredText(text);
        int y = gamePanel.tileSize * 3;

        g2D.setColor(Color.GRAY);
        g2D.drawString(text, x + 5, y + 5);
        g2D.setColor(Color.WHITE);
        g2D.drawString(text, x, y);

        //IMAGE
        x = gamePanel.screenWidth / 2 - gamePanel.tileSize;
        y = gamePanel.screenHeight / 2 - gamePanel.tileSize * 2;
        g2D.drawImage(gamePanel.player.right0, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);

        //MENU
        g2D.setFont(pixelFont.deriveFont(Font.PLAIN, 48F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize * 4;
        g2D.drawString(text, x, y);
        if (commandNum == 0) {
            g2D.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize;
        g2D.drawString(text, x, y);
        if (commandNum == 1) {
            g2D.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize;
        g2D.drawString(text, x, y);
        if (commandNum == 2) {
            g2D.drawString(">", x - gamePanel.tileSize, y);
        }
    }

    private void drawGameOverScreen() {
        g2D.setColor(new Color(0, 0, 0, 150));
        g2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        int x;
        int y;
        String text = "You died";
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 110f));
        g2D.setColor(Color.WHITE);
        x = getXForCenteredText(text);
        y = gamePanel.tileSize * 4;
        g2D.drawString(text, x, y);

        g2D.setFont(g2D.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize * 4;
        g2D.drawString(text, x, y);
        if (commandNum == 0) {
            g2D.drawString(">", x - 40, y);
        }

        text = "Quit";
        x = getXForCenteredText(text);
        y += gamePanel.tileSize;
        g2D.drawString(text, x, y);
        if (commandNum == 1) {
            g2D.drawString(">", x - 40, y);
        }


    }

    private void drawDialogueScreen() {
        //WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2D.setFont(pixelFont.deriveFont(Font.PLAIN, 28));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawSubWindow(int x, int y, int width, int height) {
        g2D.setColor(new Color(0, 0, 0, 200));
        g2D.fillRoundRect(x, y, width, height, 35, 35);
        g2D.setColor(new Color(255, 255, 255));
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawPauseScreen() {
        g2D.setFont(pixelFont.deriveFont(Font.PLAIN, 40));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight / 2;
        g2D.drawString(text, x, y);
    }

    private void drawCharacterWindow() {
        int frameX = gamePanel.tileSize;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize * 6;
        int frameHeight = gamePanel.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2D.setColor(Color.WHITE);
        g2D.setFont(g2D.getFont().deriveFont(24f));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 38;

        g2D.drawString("Level", textX, textY);
        textY += lineHeight;
        g2D.drawString("HP", textX, textY);
        textY += lineHeight;
        g2D.drawString("Damage", textX, textY);
        textY += lineHeight;
        g2D.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2D.drawString("Exp", textX, textY);

        int taleX = (frameX + frameWidth) - 100;
        textY = frameY + gamePanel.tileSize;

        g2D.drawString(String.valueOf(gamePanel.player.lvl), taleX, textY);
        textY += lineHeight;
        g2D.drawString(gamePanel.player.hp + "/" + gamePanel.player.maxHp, taleX, textY);
        textY += lineHeight;
        g2D.drawString(String.valueOf(gamePanel.player.damage), taleX, textY);
        textY += lineHeight;
        g2D.drawString(String.valueOf(gamePanel.player.defense), taleX, textY);
        textY += lineHeight;
        g2D.drawString(gamePanel.player.exp + "/" + gamePanel.player.nextLvlExp, taleX, textY);


    }

    private void drawOptionsScreen() {
        g2D.setColor(Color.WHITE);
        g2D.setFont(g2D.getFont().deriveFont(24f));

        int frameX = gamePanel.tileSize * 6;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.tileSize * 8;
        int frameHeight = gamePanel.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1:
                optionsFullscreenNotification(frameX, frameY);
                break;
            case 2:
                optionsControls(frameX, frameY);
                break;
            case 3:
                optionsEndGameConfirm(frameX, frameY);
                break;
        }

    }

    private void optionsTop(int frameX, int frameY) {
        int textX;
        int textY;

        textX = getXForCenteredText("Options");
        textY = frameY + gamePanel.tileSize;
        g2D.drawString("Options", textX, textY);

        textX = frameX + gamePanel.tileSize;
        textY += gamePanel.tileSize * 2;
        g2D.drawString("Full screen", textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                if (gamePanel.fullscreenOn) {
                    gamePanel.fullscreenOn = false;
                } else {
                    gamePanel.fullscreenOn = true;
                }
                subState = 1;
            }
        }

        textY += gamePanel.tileSize;
        g2D.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2D.drawString(">", textX - 25, textY);
        }

        textY += gamePanel.tileSize;
        g2D.drawString("Sounds", textX, textY);
        if (commandNum == 2) {
            g2D.drawString(">", textX - 25, textY);
        }

        textY += gamePanel.tileSize;
        g2D.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2D.drawString(">", textX - 25, textY);
        }

        textY += gamePanel.tileSize;
        g2D.drawString("Exit game", textX, textY);
        if (commandNum == 4) {
            g2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subState = 3;
                commandNum = 1;
            }
        }

        textY += gamePanel.tileSize * 2;
        g2D.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.gameState = gamePanel.playState;
                commandNum = 0;
            }
        }

        //FULLSCREEN CHECKBOX
        textX = frameX + gamePanel.tileSize * 5;
        textY = frameY + gamePanel.tileSize * 2 + gamePanel.tileSize / 3;
        g2D.setStroke(stroke);
        g2D.drawRect(textX, textY, gamePanel.tileSize / 2, gamePanel.tileSize / 2);
        if (gamePanel.fullscreenOn) {
            g2D.fillRect(textX, textY, gamePanel.tileSize / 2, gamePanel.tileSize / 2);
        }

        //MUSIC
        textY += gamePanel.tileSize;
        g2D.drawRect(textX, textY, 120, gamePanel.tileSize / 2);
        int volumeWidth = 24 * gamePanel.music.volumeScale;
        g2D.fillRect(textX, textY, volumeWidth, 24);

        //SOUND
        textY += gamePanel.tileSize;
        g2D.drawRect(textX, textY, 120, gamePanel.tileSize / 2);
        volumeWidth = 24 * gamePanel.sound.volumeScale;
        g2D.fillRect(textX, textY, volumeWidth, 24);

        gamePanel.keyHandler.enterPressed = false;

        gamePanel.config.saveConfig();
    }

    private void optionsFullscreenNotification(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";
        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, textX, textY);
            textY += 40;
        }

        textY = frameY + gamePanel.tileSize * 6;
        g2D.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subState = 0;
                gamePanel.keyHandler.enterPressed = false;
            }
        }
    }

    private void optionsControls(int frameX, int frameY) {

    }

    private void optionsEndGameConfirm(int frameX, int frameY) {
        int textX = frameX + gamePanel.tileSize;
        int textY = frameY + gamePanel.tileSize * 3;

        currentDialogue = "Quit the game end \nreturn to the menu?";
        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, textX, textY);
            textY += 40;
        }

        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gamePanel.tileSize * 3;
        g2D.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subState = 0;
                gamePanel.gameState = gamePanel.titleState;
                gamePanel.keyHandler.enterPressed = false;
                gamePanel.music.stopMusic();
            }
        }

        text = "No";
        textX = getXForCenteredText(text);
        textY += gamePanel.tileSize;
        g2D.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2D.drawString(">", textX - 25, textY);
            if (gamePanel.keyHandler.enterPressed) {
                subState = 0;
                commandNum = 4;
                gamePanel.keyHandler.enterPressed = false;
            }
        }
    }

    private void drawInventory() {
        gamePanel.mouseHandler.checkMousePosition();
        hasActiveSlot = false;

        g2D.setColor(new Color(0, 0, 0, 245));
        g2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        int startX = gamePanel.tileSize;
        int startY = gamePanel.tileSize;
        int x = startX;
        int y = startY;
        final int armorSlotSize = (int) (gamePanel.tileSize * 1.5);
        final int slotSize = armorSlotSize;

        //ARMOR SLOTS
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.helmet != null) {
            g2D.drawImage(gamePanel.player.helmet.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.HELMET;
        }

        y += armorSlotSize + 10;
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.breastplate != null) {
            g2D.drawImage(gamePanel.player.breastplate.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.BREASTPLATE;
        }

        y += armorSlotSize + 10;
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.gloves != null) {
            g2D.drawImage(gamePanel.player.gloves.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.GLOVES;
        }

        y += armorSlotSize + 10;
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.boots != null) {
            g2D.drawImage(gamePanel.player.boots.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.BOOTS;
        }


        //DRAW PLAYER
        x += gamePanel.tileSize;
        y = startY + 16;

        drawPlayer(x, y);


        //ARMOR SLOTS

        x += gamePanel.tileSize * 5.5;
        y = startY;


        //WEAPON
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.weapon != null && !gamePanel.player.weapon.name.equals("Hand")) {
            g2D.drawImage(gamePanel.player.weapon.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.WEAPON;
        }

        //NECKLACE
        y += armorSlotSize + 10;
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.necklace != null) {
            g2D.drawImage(gamePanel.player.necklace.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.NECKLACE;
        }


        //RING
        y += armorSlotSize + 10;
        drawSlot(x, y, armorSlotSize);
        if (gamePanel.player.ring != null) {
            g2D.drawImage(gamePanel.player.ring.icon, x, y, armorSlotSize, armorSlotSize, null);
        }
        if (isActiveSlot(x, y, armorSlotSize)) {
            inventorySlotType = InventorySlotType.RING;
        }
        //INVENTORY SLOTS

        x += gamePanel.tileSize * 2.5;
        y = startY;
        int invXStart = x;
        int invYStart = y;
        int col = 0;


        for (int i = 0; i < gamePanel.player.maxInventorySize; i++) {
            drawSlot(x, y, slotSize);
            if (isActiveSlot(x, y, slotSize)) {
                inventorySlotType = InventorySlotType.INVENTORY;
                slotCol = (x - invXStart) / (slotSize + 10);
                slotRow = (y - invYStart) / (slotSize + 10);
            }
                Item item = gamePanel.player.inventory[i];
                if (item != null) {
                    g2D.drawImage(item.icon, x, y, slotSize, slotSize, null);
                }

            x += slotSize + 10;
            if (col == 4) {
                col = 0;
                x = invXStart;
                y += slotSize + 10;
            } else {
                col++;
            }
        }

        //ITEM DESCRIPTION
        x = invXStart;
        y += 16;
        drawInventoryDescription(x, y);


        //DRAGGING ITEM
        if (dragItem) {
            drawDragItem(slotSize);
        }
    }

    private void drawDragItem(int slotSize) {
        gamePanel.mouseHandler.checkMousePosition();
        int x = (int) (gamePanel.mouseHandler.mousePositionX - slotSize / 2);
        int y = (int) (gamePanel.mouseHandler.mousePositionY - slotSize / 2);
        g2D.drawImage(draggingItem.icon, x, y, slotSize, slotSize, null);

    }

    private void drawInventoryDescription(int x, int y) {
        g2D.setColor(new Color(30, 30, 30));
        g2D.fillRoundRect(x, y, gamePanel.tileSize * 8 + 16, gamePanel.tileSize * 3, 10, 10);
        g2D.setStroke(stroke);
        g2D.setColor(Color.GRAY);
        g2D.drawRoundRect(x, y, gamePanel.tileSize * 8 + 16, gamePanel.tileSize * 3, 10, 10);

        String itemName = "";
        String itemDescription = "";

        if (dragItem) {
            itemName = draggingItem.name;
            itemDescription = draggingItem.description;
        }
        else if (hasActiveSlot) {
            if (gamePanel.player.inventory[getItemIndexOfSlot()] != null && inventorySlotType == InventorySlotType.INVENTORY) {
                itemName = gamePanel.player.inventory[getItemIndexOfSlot()].name;
                itemDescription = gamePanel.player.inventory[getItemIndexOfSlot()].description;
            } else if (inventorySlotType == InventorySlotType.HELMET && gamePanel.player.helmet != null) {
                itemName = gamePanel.player.helmet.name;
                itemDescription = gamePanel.player.helmet.description;
            } else if (inventorySlotType == InventorySlotType.BREASTPLATE && gamePanel.player.breastplate != null) {
                itemName = gamePanel.player.breastplate.name;
                itemDescription = gamePanel.player.breastplate.description;
            } else if (inventorySlotType == InventorySlotType.GLOVES && gamePanel.player.gloves != null) {
                itemName = gamePanel.player.gloves.name;
                itemDescription = gamePanel.player.gloves.description;
            } else if (inventorySlotType == InventorySlotType.BOOTS && gamePanel.player.boots != null) {
                itemName = gamePanel.player.boots.name;
                itemDescription = gamePanel.player.boots.description;
            } else if (inventorySlotType == InventorySlotType.WEAPON && gamePanel.player.weapon != null) {
                itemName = gamePanel.player.weapon.name;
                itemDescription = gamePanel.player.weapon.description;
            } else if (inventorySlotType == InventorySlotType.NECKLACE && gamePanel.player.necklace != null) {
                itemName = gamePanel.player.necklace.name;
                itemDescription = gamePanel.player.necklace.description;
            } else if (inventorySlotType == InventorySlotType.RING && gamePanel.player.ring != null) {
                itemName = gamePanel.player.ring.name;
                itemDescription = gamePanel.player.ring.description;
            }
        }

        x += 12;
        y += gamePanel.tileSize;
        g2D.setColor(Color.WHITE);
        g2D.setFont(g2D.getFont().deriveFont(32f));
        g2D.drawString(itemName, x, y);
        g2D.setFont(g2D.getFont().deriveFont(24f));
        y += gamePanel.tileSize / 2;
        g2D.drawString(itemDescription, x, y);
    }

    private boolean isActiveSlot(int x, int y, int slotSize) {
        if (gamePanel.mouseHandler.mousePositionX >= x && gamePanel.mouseHandler.mousePositionX <= x + slotSize
                && gamePanel.mouseHandler.mousePositionY >= y && gamePanel.mouseHandler.mousePositionY <= y + slotSize) {
            hasActiveSlot = true;
            return true;
        } else return false;
    }

    private void drawPlayer(int x, int y) {
        g2D.drawImage(gamePanel.player.down0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        g2D.drawImage(gamePanel.player.eDown0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        g2D.drawImage(gamePanel.player.hDown0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        if (gamePanel.player.helmet != null) {
            g2D.drawImage(gamePanel.player.helmet.down0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        }
        if (gamePanel.player.breastplate != null) {
            g2D.drawImage(gamePanel.player.breastplate.down0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        }
        if (gamePanel.player.gloves != null) {
            g2D.drawImage(gamePanel.player.gloves.down0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        }
        if (gamePanel.player.boots != null) {
            g2D.drawImage(gamePanel.player.boots.down0, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 6, null);
        }
    }

    private void drawSlot(int x, int y, int slotSize) {
        g2D.setColor(Color.GRAY);
        g2D.drawRoundRect(x, y, slotSize, slotSize, 10, 10);
        if (gamePanel.mouseHandler.mousePositionX >= x && gamePanel.mouseHandler.mousePositionX <= x + slotSize
                && gamePanel.mouseHandler.mousePositionY >= y && gamePanel.mouseHandler.mousePositionY <= y + slotSize) {
            drawCursor(x, y, slotSize);
            g2D.setColor(new Color(100, 10, 10));
        } else {
            g2D.setColor(new Color(30, 30, 30));
        }
        g2D.fillRoundRect(x, y, slotSize, slotSize, 10, 10);
        g2D.setStroke(stroke);
    }

    public void drawSystemParameters(long timeLast) {
        g2D.setColor(Color.white);
        g2D.setFont(g2D.getFont().deriveFont(32f));
        int y = 400;
        g2D.drawString("Draw time: " + (double)timeLast / 1_000_000_000.0 + " sec", 10, y);
        y += 30;
        g2D.drawString("x: " + gamePanel.player.worldX / gamePanel.tileSize, 10, y);
        y += 30;
        g2D.drawString("y: " + gamePanel.player.worldY / gamePanel.tileSize, 10, y);
        y += 30;
    }

    private void drawCursor(int x, int y, int slotSize) {
        g2D.setColor(Color.WHITE);
        g2D.setStroke(stroke);
        g2D.drawRoundRect(x, y, slotSize, slotSize, 10, 10);
    }

    public int getItemIndexOfSlot() {
        return slotCol + (slotRow * 5);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        return gamePanel.screenWidth / 2 - length / 2;
    }
}
